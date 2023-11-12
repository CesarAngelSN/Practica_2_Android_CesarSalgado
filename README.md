# Practica_2_Android_CesarSalgado

Esta aplicación pone en práctica el uso del ORM optimizado para Android, Room, con el que se realizará una persistencia en unua base de datos. 
Esta base de datos guardará datos de preguntas y de usuarios, siendo posible darse de alta como jugador de las preguntas o como administrador
creador de preguntas.

En e árbol de directorios del proyecto se visualiza un paquete "entity", con las entidades de usuario y pregunta y con sus anotaciones KSP que
serán interpretadas por Room. También hay un paquete "dao", que contiene las interfaces que implementarán las entidades para realizr la persistencia.
Estas interfaces "UserDao y QuestionDao" contienen las consultas de SQLite que proporcionarán los métodos desarrollados en los ficheros autogenerados 
"UserDao_Impl y QuestionDao_Impl", que son los que realizarán la persistencia en la base de datos. Hay un paquete "db" que contiene la clase "AppDatabase"
que tiene los métodos abstractos para dar acceso a las entidades mapeadas en la base de datos.

Respecto al desarrollo de los puntos que se pedían en la práctica, hay algunos que no se han realizado o no están realizados correctamente.
