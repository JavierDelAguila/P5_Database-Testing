# P5_Database-Testing


## Estructura HTML -> fetch -> Controller -> Service -> Repository
Se ha implantado una estructura de control en el flujo de datos, la cual está compuesta por HTML para la presentación de datos en la vista, fetch para la comunicación con el backend, controlador para la gestión de peticiones, servicio para la lógica de negocio y repositorio para la gestión de datos de la aplicación.
Pagina web disponible desde:  http://localhost:8080

## Base de datos
Se han creado dos bases de datos: una de usuarios y otra de localidades españolas. La base de datos de usuarios tiene las siguientes columnas: id_usuario, username (único), contraseña (encriptada por el programa) y localidad (valor que puede cambiar). La base de datos de localidades españolas tiene las siguientes columnas: comunidad, provincia, localidad (unique, primary key) , latitud, longitud, nº habitantes. 

## Operaciones CRUD
Se ha implementado la metodología CRUD para la base de datos de usuarios, instanciando Queries personalizadas para cada método requerido por la aplicación. Todas las operaciones de la base de datos de usuario son realizables a través de la página web, incluyendo la creación, borrado, actualización y búsqueda de usuarios.

## Autenticación
Todas las operaciones de la base de datos de usuario son realizables a través de la página web. Se ha diseñado un menú deslizante donde, al clicar en "usuarios", se despliega un menú que permite crear, borrar, actualizar y buscar usuarios. Las opciones relacionadas con la modificación y destrucción de usuarios requieren autenticación previa del usuario. Se pide que introduzca su contraseña, siendo verificada por el backend, para posteriormente ejecutar la operativa deseada.
Estas operaciones utilizan @Transactional, ya que en cuanto una de ellas falle se desea abortar y no realizar la operación.

## Seguridad
Todos los inputs de los html están protegidos para no permitir caracteres que no sean alfanuméricos, además de su correspondiente seguridad en el backend, la cual ha sido comprobada exhaustivamente con el testing. Los formularios que requieren introducir la contraseña 2 veces cuentan con un factor extra de seguridad que es la deshabilitación del botón de submit.

##UserDTO
Para la operación de obtener los usuarios se ha creado un objeto UserDTO para asegurarnos de enviar al usuario solo la información que queremos que conozca, es decir, el nombre del usuario y su localidad.

#Pruebas
Se ha realizado un análisis exhaustivo de pruebas al Controlador de usuarios, confirmando todos los casos correctos (200, 201) y todos los posibles casos erróneos: IM_USED, para cuando se desea crear un usuario y este ya existe; BAD_REQUEST para cuando se ha incluido información errónea en los inputs.

#Usuarios
Nueva operativa relacionada con CRUD en la base de datos Usuarios efectuada en las páginas:
-	http://localhost:8080/FormularioSignUp.html (Método POST)
-	http://localhost:8080/UserSearcher.html (Método GET) 
-	http://localhost:8080/Update_user.html (Método PUT) 
-	http://localhost:8080/Delete_user.html (Método DELETE)



#Localidades Españolas
También se ha creado la estructura de controlador-service-repository y modelo para la base de datos de localidades, aunque el acceso a esta información no ha sido hecho público a los usuarios, por lo que no se puede acceder navegando por la página web. Si se desea, se puede navegar a http://localhost:8080/api/v1/locations, donde se ejecutará la carga de todas las localidades españolas (alrededor de 8500 datos que se guardarán en ese momento en la base de datos) y se mostrarán en pantalla.

## H2
H2 esta activado, siendo posible su acceso siguiendo desde http://localhost:8080/h2 y utilizando driver Class: jdbc:h2:mem:testdb ;  jdbcURL= jdbc:h2:mem:testdb ; username= sa ; password="" (ninguna contraseña).

A continuación, se incluyen las propiedades relevantes de application.properties:

- server.port: 8080
- management.endpoints.web.exposure.include=*
- management.endpoint.health.show-components=always
- management.endpoint.health.show-details=always
- management.endpoint.health.probes.enabled=true
- spring.thymeleaf.prefix=classpath:/static/

//Logging
- spring.output.ansi.enabled=always
- logging.Level.web=INFO
-logging.Level.org.springframework.jdbc.core=DEBUG

//Database configuration
- spring.datasource.url=jdbc:h2:mem:testdb
- spring.datasource.driverClassName=org.h2.Driver
- spring.datasource.username=sa
- spring.datasource.password=

- spring.h2.console.enabled=true
- spring.jpa.generate-ddl=true
- spring.sql.init.mode=always
 spring.sql.init.continue-on-error=true
