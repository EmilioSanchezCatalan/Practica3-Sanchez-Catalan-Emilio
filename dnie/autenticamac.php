<?php
//recepcion de datos
$datos = filter_input(INPUT_GET, 'datos', FILTER_SANITIZE_STRING);
if ($datos != NULL){
    
}else{
    die('No se ha enviado el usuario');
}
$datos = imap_base64($datos);
list($mensaje, $hashr)= explode(".", $datos, 2) or die("Error");


$link = mysqli_connect('localhost:3306', 'root') or die('No se puede conectar con el servidor');
if (!$link) {
    die('Could not connect to MySQL: ' . mysql_error());
}

//conexion de la base de datos
mysqli_select_db($link, 'dniauth') or die('No se puede conectar con la base de datos');
$sql = "SELECT * FROM users";
$resultado = mysqli_query($link, $sql);
while ($row = mysqli_fetch_assoc($resultado)) {
    $nombre = $row["user"];
    $dni = $row["dni"];
    $pass = $row["password"];
}
list($apellido1, $apellido2, $nombre) = explode(" ", $nombre, 3) or die("Error");
$apellido2 = substr($apellido2, 0, 1);
$nombre = substr($nombre, 0, 1);
$dni = substr($dni, strlen($dni)-1, strlen($dni));
$hashg = sha1(strtolower($nombre.$apellido1.$apellido2.$dni).$pass);

//comprobación del hash
if($hashg == $hashr){
    echo "Bienvenido " . $mensaje;
}
else{
    echo "Error de autenticacion No se encuentra al usuario o los datos de DNI y clave son incorrectos";  
}
?>