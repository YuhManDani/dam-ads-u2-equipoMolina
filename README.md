
# Club DAMA – Gestor deportivo


Aplicación de escritorio para la gestión integral de un club deportivo, desarrollada en JavaFX con base de datos MySQL. Permite administrar socios, pistas y reservas a través de una interfaz gráfica intuitiva y moderna. Proyecto creado como parte del módulo de acceso a datos y persistencia.


## Autores

- [@Daniel Illán](https://github.com/YuhManDani)
- [@David Fernández](https://github.com/DavidFM2004)
- [@Jose David Navarro](https://github.com/Joseda005)
- [@Jorge Corbalán](https://github.com/Jorgecs96)




## Características del proyecto


📊 **Dashboard**

- Vista principal con tablas ordenables que muestran de forma resumida:

- Socios registrados

- Pistas disponibles

- Reservas activas

👤 **Gestión de Socios**

- Registrar nuevos socios con datos como DNI, nombre, teléfono y correo.

- Eliminar socios existentes.

📅 **Sistema de Reservas**

- Crear reservas seleccionando socio, pista disponible, fecha, hora y duración.

- Cancelar reservas existentes.

🛠️ **Tecnologías utilizadas**

- Lenguaje: Java

- Framework UI: JavaFX

- Base de datos: MySQL

- Conexión: JDBC




## Estructura del proyecto

```
src/
├── 📂 data/          → Conexión a la BD mediante Singleton (dbutils.java)
├── 📂 modelo/        → POJOs: Socio, Pista, Reserva
├── 📂 servicio/      → Lógica de negocio (ClubDeportivo.java)
├── 📂 vista/         → Capa de presentación JavaFX
├── 📂 views/         → Formularios y paneles gráficos (dashboard, socios, pistas…)
└── 📄 MainApp.java   → Punto de entrada de la aplicación
```
## Conexión a la base de datos

1️⃣ **Requisitos**

**MySQL** en ejecución

Base de datos con nombre: **club_dama**

2️⃣ **Crear tablas necesarias**

**socios** → id_socio, dni, nombre, apellidos, telefono, email

**pistas** → id_pista, deporte, descripcion, disponible

**reservas** → id_reserva, id_socio, id_pista, fecha, hora_inicio, duracion_min, precio

**Además**, la aplicación utiliza el procedimiento almacenado:
sp_crear_reserva

3️⃣ **Configuración de la conexión**

En **src/data/dbutils.java**, ajusta tus credenciales MySQL:

private final String url = "jdbc:mysql://localhost:3306/club_dama";

private final String user = "root";

private final String password = "tucontraseña";
## Ejecución de la aplicación

**Clona el repositorio** 

git clone https://github.com/yuhmandani/dam-ads-u2-equipomolina.git


Abre el proyecto en tu IDE favorito (IntelliJ, Eclipse…).

**Asegúrate de tener configurados:** 

**JDBC MySQL (mysql-connector)**

**JavaFX SDK**

Ejecuta MainApp.java ubicado en src/vista/ para iniciar la aplicación.

## Recursos y agradecimientos

Durante el desarrollo del sistema de conexión a la base de datos y la implementación del patrón Singleton, consulté varios recursos muy útiles de la comunidad. ¡Gracias a todos ellos por aportar soluciones, ideas y ejemplos!

**🔗 Recursos Consultados**

**Reddit – r/learnjava:**
“Cómo implementar correctamente un Singleton en Java”

https://www.reddit.com/r/learnjava/

**StackOverflow – JDBC & conexiones:**
“Best practices para manejar conexiones JDBC y evitar fugas”

https://stackoverflow.com/questions/6631739/proper-way-of-handling-database-connections-in-a-medium-sized-web-application


