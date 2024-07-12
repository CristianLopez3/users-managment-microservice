# Prueba Técnica Backend Java - Microservicios

Implementar una API Rest en Java con persistencia de datos, la cual debe proveer las
siguientes funcionalidades.

### Introducción:

Un sistema de administración de usuarios de la compañía CONTOSO está conformado de la
siguiente manera:

**Usuarios**: Tienen las siguientes propiedades:

* Id
* Nombre
* Apellido
* Cargo
* Direccion
* Telefono
* Ciudad Residencia
* Estado Usuario


**Compañia**: Tienen las siguientes propiedades:

* Id
* Nombre
* Direccion
* Ciudad Operacion

**Departamento**: Tiene las siguientes propiedades
* Id
* descripción


El sistema debe ofrecer los siguientes servicios (CRUD):

1. Listar todos los usuarios pertenecientes a una compañía en específico.
2. Agregar y modificar departamentos pertenecientes a una compañía (Legal, Financiero,
   TI, Compras, etc)
3. Enrolar nuevos usuarios a una compañía, relacionándolos con un departamento en especifico.
4. Eliminar un usuario por id
   De acuerdo con lo anterior, se requiere realizar una aplicación basada en microservicios
   el cual exponga los endpoints para los servicios descritos anteriormente.
   Requisitos del sistema:
   Debe estar desarrollado con Spring Boot
   Utilizar como BD MySQL o Postgres, la que se desee. (Se puede utilizar una H2
   embebida para facilidad)
   Debe tener sus respectivas pruebas unitarias y su respectiva cobertura de código, el
   cual debe tener un porcentaje mínimo del 80%


### Entregables:

1. Archivo ZIP con el código fuente desarrollado.
2. README con la explicación de los artefactos y componentes utilizados en el
   desarrollo.
3. Modelo E/R de la BD y scripts de creación de tablas.