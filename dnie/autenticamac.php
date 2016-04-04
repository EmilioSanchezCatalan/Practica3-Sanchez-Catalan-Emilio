<html>
    <head>
        <title>Autenticaci&oacute;n con DNIe</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>


    <body>
        <div id='banner'>
            
              <h2>Pr&aacute;ctica 3. Implementación de un servicio b&aacute;sico de autenticaci&oacute;n con DNIe</h2>
            <h3>Autenticación con datos públicos del DNIe</h3>

        </div>
        <div id='main'>
            <?php
            $datos = NULL;
            
            $datos = filter_input(INPUT_GET, 'datos', FILTER_SANITIZE_STRING);


            if ($datos != NULL){
                ?>
                <h3>Autenticando mediante POST</h3>
                <?php
                
            }
            else{
                die('No se ha enviado el usuario');       
            }
            $datos = imap_base64($datos);
            list($mensaje, $hashr)= explode(".", $datos, 2) or die("Error");
            $nombre = NULL;
            $pass = NULL;
            $dni = NULL;
            $link = mysqli_connect('localhost:3306', 'root') or die('No se puede conectar con el servidor');
                if (!$link) {
                    die('Could not connect to MySQL: ' . mysql_error());
                }

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
                if($hashg == $hashr)
                {
                     ?>
                    <h4>Bienvenido</h4>
                    <?php
                }else
                    {
                     ?>
                    <h4>Error de autenticaci&oacute;n</h4>
                    <?php echo "<p>No se encuentra a <strong>".$user . "</strong> o los datos de DNI y clave son incorrectos</p>";?>
                    <?php
                    }
            ?>
                   
                       
        </div>
        <div id="foot">
             <a href="index.php">Volver</a>
               <h2>Aplicaciones Telemáticas para la Administración</h2>
            <p>Grado en Ingenier&iacute;a Telem&aacute;tica y Grado en Ingenier&iacute;a de Tecnolog&iacute;as de Telecomunicaci&oacute;n</p>
            <p>DEPARTAMENTO DE INGENIERÍA DE TELECOMUNICACIÓN</p>

        </div>
    </body>
</html>