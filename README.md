# 💇‍♀️ Sistema de Gestión para Salón de Belleza - Kamerinos

Este es un sistema monolítico desarrollado en **Java 21** con **Spring Boot**, diseñado para la gestión integral de un salón de belleza. Ofrece una interfaz moderna con **Thymeleaf**, **Tailwind CSS**, **Alpine.js** y un sistema de calendario con **FullCalendar**, enfocado en la eficiencia administrativa y la experiencia del usuario.

## 🚀 Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.4.4**
  - Spring Security
  - Spring Data JPA
  - Spring Web
- **Thymeleaf** (plantillas del lado del servidor)
- **Tailwind CSS** (estilos)
- **Alpine.js** (interactividad ligera en el frontend)
- **FullCalendar** (gestión de citas y calendario visual)
- **MySQL** (base de datos relacional)
- **PDF Generation** (reportes y documentación)
- **JWT** y sesiones con roles diferenciados (seguridad)
- **Notificaciones en tiempo real** para el administrador

## 🧠 Funcionalidades Clave

- Autenticación con **Spring Security**
  - Soporte para dos roles: `ADMIN` y `EMPLEADO`
- Panel de administración completo:
  - Gestión de **servicios**, **categorías**, **empleados**, **clientes**
  - Registro de **ventas** y generación de **reportes PDF**
  - Visualización de **notificaciones** en tiempo real
- Panel para empleados:
  - Gestión de citas personales
  - Consulta de clientes asignados
- Gestión de **documentación por cliente**
- Visualización y edición de citas mediante **FullCalendar**
- Estética moderna y responsive gracias a Tailwind

## 📦 Estructura del Proyecto

```plaintext
kamerinos/
├── src/
│   ├── main/
│   │   ├── java/          # Código fuente backend
│   │   └── resources/
│   │       ├── static/    # Archivos estáticos (CSS, JS, imágenes)
│   │       └── templates/ # Vistas Thymeleaf
├── .mvn/
├── target/
├── node_modules/
├── package.json
├── tailwind.config.js
├── pom.xml
└── README.md
```

## ⚙️ Instalación y Uso

### 1. Clona el repositorio

```bash
git clone https://github.com/tuusuario/kamerinos.git
cd kamerinos
```

### 2. Instala las dependencias de Tailwind/Alpine

```bash
npm install
```

### 3. Compila y levanta el servidor de desarrollo

```bash
.\mvnw spring-boot:run
```

> ⚠️ Es necesario haber corrido `npm install` antes de iniciar el backend para asegurar que los estilos estén disponibles correctamente.

### 4. Para regenerar el build completo

```bash
.\mvnw clean install
```

Este comando elimina archivos de compilación previos y reinstala todas las dependencias necesarias del proyecto.

## 📄 Notas

- Asegúrate de tener **MySQL** corriendo y una base de datos creada con el nombre `kamerinos1`.
- Puedes modificar los datos de conexión en `src/main/resources/application.properties`.
- La generación de PDF y documentación está integrada y organizada por cliente.
- Las notificaciones al administrador se muestran dentro del panel de administración.

## ✍️ Autor

Desarrollado por **Juan Esteban Peraza**  
Sistema integral para la gestión de salones de belleza con enfoque profesional y moderno.
