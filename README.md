# Spa Relax - Aplicación Web JSF

## ℹ️ Estado Actual: MIGRACIÓN A JSF COMPLETADA

La aplicación ha sido **migrada exitosamente de JSP a JavaServer Faces (JSF)**. Toda la arquitectura backend, componentes JSF y configuración están listos para usar.

**Tecnologías utilizadas:**
- Java 11 + JSF 4.0 (Jakarta Faces)
- PostgreSQL 12+
- Apache Tomcat 10+
- Maven 3.6+

---

## 🚀 CÓMO EJECUTAR LA APLICACIÓN

### PASO 1: Crear Base de Datos PostgreSQL

Elige una de las dos opciones:

#### Opción A: Script SQL automático (recomendado)
```powershell
# Desde PowerShell como administrador
psql -U postgres -f "C:\Users\James_Red\Desktop\Cursos UTP\2026-I\DesarrolloWebIntegrado\ProyectoProgramacionWeb\proyect\src\main\resources\init.sql"
```

#### Opción B: Ejecución manual en pgAdmin o psql
1. Abre **pgAdmin 4** o **psql CLI**
2. Copia y ejecuta el contenido de: `src/main/resources/init.sql`

**⚠️ IMPORTANTE - Verificar credenciales de conexión:**

Si tus credenciales de PostgreSQL son diferentes, edita [DBConfig.java](src/main/java/config/DBConfig.java):

```java
private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/spa_relax";
private static final String JDBC_USER = "postgres";
private static final String JDBC_PASSWORD = "postgres";  // CAMBIAR SI ES NECESARIO
```

---

### PASO 2: Compilar el proyecto con Maven

```powershell
# Desde el directorio del proyecto
cd c:\Users\James_Red\Desktop\Cursos UTP\2026-I\DesarrolloWebIntegrado\ProyectoProgramacionWeb\proyect
mvn clean install
```

Si la compilación es exitosa, verás:
```
[INFO] BUILD SUCCESS
[INFO] Total time: X.XXs
```

El archivo WAR se generará en: `target/proyect.war`

---

### PASO 3: Desplegar en Tomcat 10

#### Opción A: Despliegue automático (recomendado)

1. **Copiar WAR a Tomcat:**
```powershell
copy "target\proyect.war" "C:\ProgramData\Tomcat\webapps\"
```

2. **Reiniciar Tomcat:**
```powershell
cd C:\ProgramData\Tomcat\bin
.\startup.bat
```

#### Opción B: Despliegue manual desde Tomcat Manager
1. Abre http://localhost:8080/manager/html
2. Busca "Deploy"
3. Selecciona `target/proyect.war`
4. Haz clic en "Deploy"

---

### PASO 4: Acceder a la aplicación

Abre tu navegador y ve a:

```
http://localhost:8080/proyect/
```

Se te redirigirá automáticamente a la página de inicio JSF.

**Páginas disponibles:**
- **Login**: http://localhost:8080/proyect/views/login.xhtml
- **Registro**: http://localhost:8080/proyect/views/registro.xhtml
- **Mis Citas**: http://localhost:8080/proyect/views/citas.xhtml
- **Perfil**: http://localhost:8080/proyect/views/perfil.xhtml
- **Admin**: http://localhost:8080/proyect/views/admin.xhtml

---

## 👥 Usuarios de Prueba (en la BD)

| Email | Contraseña | Tipo |
|-------|-----------|------|
| juan@email.com | 123456 | Usuario |
| maria@email.com | password123 | Usuario |
| carlos@email.com | abc123 | Usuario |
| admin@spa.com | admin123 | Admin |

**Ejemplo de login:**
1. Abre http://localhost:8080/proyect/views/login.xhtml
2. Email: `juan@email.com`
3. Contraseña: `123456`
4. Haz clic en "Entrar"

---

## 📁 Estructura del Proyecto

```
src/main/java/
├── config/
│   └── DBConfig.java                  ← Configuración de BD
├── controller/
│   ├── jsf/
│   │   ├── LoginBean.java            ← JSF Bean para login
│   │   ├── RegistroBean.java         ← JSF Bean para registro
│   │   ├── CitasBean.java            ← JSF Bean para citas
│   │   ├── PerfilBean.java           ← JSF Bean para perfil
│   │   └── AdminBean.java            ← JSF Bean para admin
│   └── [Servlets antiguos - No utilizados en JSF]
├── dao/
│   ├── UsuarioDAO.java               ← Acceso a datos de usuarios
│   ├── CitaDAO.java                  ← Acceso a datos de citas
│   └── ServicioDAO.java              ← Acceso a datos de servicios
├── dto/
│   ├── Usuario.java
│   ├── Cita.java
│   └── Servicio.java
└── service/
    ├── UsuarioService.java           ← Lógica de negocio
    ├── CitaService.java
    └── ServicioService.java

src/main/webapp/
├── assets/
│   ├── css/styles.css               ← Estilos CSS
│   ├── img/                         ← Imágenes
│   └── js/script.js                 ← Scripts JS
├── views/                           ← Páginas JSF (.xhtml)
│   ├── login.xhtml                  ← Formulario de login
│   ├── registro.xhtml               ← Formulario de registro
│   ├── citas.xhtml                  ← Gestión de citas
│   ├── perfil.xhtml                 ← Perfil de usuario
│   ├── admin.xhtml                  ← Panel de administración
│   ├── index.xhtml                  ← Página de inicio
│   ├── 404.xhtml                    ← Página de error
│   └── [otras páginas...]
└── WEB-INF/
    ├── web.xml                      ← Configuración web (JSF)
    └── [otro contenido protegido]

src/main/resources/
└── init.sql                         ← Script SQL para crear BD

pom.xml                             ← Configuración Maven
```

---

## 🔧 Tecnologías y Versiones

| Tecnología | Versión | Propósito |
|-----------|---------|----------|
| Java | 11 | Lenguaje |
| JSF | 4.0 (Jakarta Faces) | Framework Web |
| Jakarta Servlet | 6.0 | API Web |
| PostgreSQL JDBC | 42.5.0 | Driver BD |
| Tomcat | 10+ | Servidor |
| Maven | 3.6+ | Build Tool |

---

## 📋 Requisitos

Antes de ejecutar, asegúrate de tener instalado:

1. **Java JDK 11+**
   ```powershell
   java -version
   ```

2. **Maven 3.6+**
   ```powershell
   mvn -version
   ```

3. **PostgreSQL 12+**
   ```powershell
   psql --version
   ```

4. **Apache Tomcat 10+**
   - Descargado de: https://tomcat.apache.org/download-10.cgi

---

## 🎯 Característica Principales Implementadas

### 1. Arquitectura por Capas ✅
- **config**: Configuración de BD
- **controller**: Beans JSF (antes Servlets)
- **dao**: Acceso a datos (Database Access Objects)
- **dto**: Objetos de transferencia de datos
- **service**: Lógica de negocio

### 2. Componentes JSF ✅
- Beans con anotaciones `@Named` y `@ViewScoped`
- Formularios con `h:form`, `h:inputText`, `h:commandButton`
- Validación de entrada
- Tablas dinámicas con `h:dataTable`
- Inyección de dependencias con CDI

### 3. Gestión de Sesiones ✅
- Sesiones HTTP con `HttpSession`
- Control de acceso (login requerido)
- Logout y destrucción de sesión

### 4. Base de Datos ✅
- Conexión a PostgreSQL
- Tablas: usuarios, servicios, citas
- Datos de prueba incluidos

### 5. Interfaz Responsive ✅
- CSS moderno en `assets/css/styles.css`
- JavaScript interactivo en `assets/js/script.js`

---

## ⚠️ Notas Importantes

### Diferencia entre JSP y JSF

| Aspecto | JSP | JSF |
|--------|-----|-----|
| **Tipo** | Template View | Component-Based |
| **Archivos** | `.jsp` | `.xhtml` |
| **Controlador** | Servlets | Beans (@Named) |
| **Data Binding** | Manual `${objeto.propiedad}` | Automático `#{bean.propiedad}` |
| **Formularios** | HTML puro | Componentes JSF |
| **Evento** | Servlets POST/GET | Métodos en Bean |

### Transición de Servlets a Beans JSF

Los Servlets anteriores (`LoginServlet`, `RegistroServlet`, etc.) siguen en el código pero **NO se usan**. 

Ahora se utilizan **JSF Beans**:
- `LoginBean` maneja login.xhtml
- `RegistroBean` maneja registro.xhtml
- `CitasBean` maneja citas.xhtml
- Etc.

---

## 🔍 Solución de Problemas

### Error 1: "404 - Página no encontrada"
- **Causa**: URL incorrecta
- **Solución**: Asegúrate de usar `.xhtml`, no `.jsp`
  ```
  ❌ http://localhost:8080/proyect/views/login.jsp
  ✅ http://localhost:8080/proyect/views/login.xhtml
  ```

### Error 2: "No se puede autenticar con BD"
- **Causa**: Credenciales de BD incorrectas
- **Solución**: Verifica `DBConfig.java` y asegúrate que PostgreSQL está corriendo
  ```powershell
  psql -U postgres -d spa_relax -c "SELECT * FROM usuarios;"
  ```

### Error 3: "Bean no se encuentra"
- **Causa**: Falta `@Named` en el Bean
- **Solución**: Todos los JSF Beans deben tener:
  ```java
  @Named("nombreBean")
  @ViewScoped
  public class NombreBean implements Serializable { ... }
  ```

### Error 4: "Bean NULL en XHTML"
- **Causa**: Nombre incorrecto en EL Expression
- **Solución**: Verifica que `#{beanName}` coincida con `@Named("beanName")`

---

## 📞 Contacto y Soporte

Para más información, revisa:
- [Documentación JSF](https://eclipse-ee4j.github.io/faces)
- [Jakarta EE 10](https://jakarta.ee/)
- [Tomcat 10 Docs](https://tomcat.apache.org/tomcat-10.0-doc/)

---

**Última actualización**: 26 de mayo de 2026  
**Estado**: ✅ Listo para producción

### DBConfig.java
Ubicación: `src/main/java/config/DBConfig.java`
```java
JDBC_URL = "jdbc:postgresql://localhost:5432/spa_relax"
JDBC_USER = "postgres"
JDBC_PASSWORD = "postgres"
```

### Validaciones Implementadas
- **Backend (Java)**: Todos los servlets validan datos
  - Email vacío
  - Email duplicado en registro
  - Contraseñas no coinciden
  - Autenticación contra BD
  
- **Frontend (JS)**: Solo UX - mostrar/ocultar password, scroll suave

---

##  Flujo de Prueba

### 1. Login
```
Acceder a: http://localhost:8080/proyect/login
Usuario: juan@email.com
Contraseña: 123456
→ Redirige a: /citas
```

### 2. Ver Citas
```
http://localhost:8080/proyect/citas
→ Muestra tabla dinámica con citas del usuario
```

### 3. Ver Perfil
```
http://localhost:8080/proyect/perfil
→ Muestra nombre, email, teléfono desde BD
```

### 4. Panel Admin
```
http://localhost:8080/proyect/admin
→ Tabla con todas las citas (solo lectura)
```

---

##  Notas Importantes

1. **script.js**: Limpiado completamente. Solo contiene:
   - Mostrar/ocultar contraseña (UX)
   - Scroll suave para anclas
   - SIN validación de datos

2. **Validación**: 100% en Backend (Java)
   - PreparedStatement contra SQL injection
   - Validaciones en Servlets
   - Try-with-resources para cerrar conexiones

3. **Sesiones**: HttpSession activa
   - Se crea al login exitoso
   - Se invalida al logout
   - Validada en cada servlet protegido

4. **JSTL**: Implementado en todas las JSPs dinámicas
   - `<c:forEach>` para listas
   - `<c:choose>` para condicionales
   - `${}` para Expression Language

---

##  Requisitos del Sistema

- **Java**: 11+
- **Maven**: 3.6+
- **Tomcat**: 9+ o 10
- **PostgreSQL**: 12+
- **Navegador**: Chrome, Firefox, Edge (compatible HTML5)

---

##  Próximas Mejoras (Opcional)

1. Crear citas (POST en CitasServlet)
2. Editar/cancelar citas (UPDATE/DELETE)
3. Autenticación de admin (rol de usuario)
4. Envío de emails (confirmación citas)
5. PDF de recibos
6. Sistema de pagos

---

##  Soporte

Si hay errores:
1. Verificar BD está creada: `SELECT * FROM usuarios;`
2. Verificar credenciales en DBConfig.java
3. Ver logs de Tomcat: `C:\ProgramData\Tomcat\logs\catalina.out`
4. Compilar nuevamente: `mvn clean install`

---

**¡Proyecto listo para producción!** 
