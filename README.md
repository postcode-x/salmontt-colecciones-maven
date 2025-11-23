![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)
# Actividad Sumativa 2: Organización modular y creación de una librería personalizada


## Autor del proyecto
- **Nombre completo:** Ignacio Araya
- **Sección:** PRY2202-003A
- **Carrera:** Analista Programador Computacional
- **Sede:** Online

---

## Descripción general del sistema
El proyecto consiste en una aplicacion Java orientada a la gestion de informacion de centros de cultivo. Su objetivo es cargar datos externos (TXT o Excel), almacenarlos en estructuras de colecciones y permitir su consulta mediante distintos filtros. La aplicación esta organizada en paquetes separados segun su responsabilidad, lo que facilita la lectura y el mantenimiento del código. EL proyecto utiliza Maven.

---

## Estructura general del proyecto

```plaintext
src/
├── ui/          # Clase principal con el método main para búsquedas
├── data/        # Clase de servicio para manejo de cargas de archivos y consultas dentro de listas
├── model/       # Clases de dominio (CentroCultivo, Region)
├── utils/       # Utilidades y validaciones
````

---

## Instrucciones para clonar y ejecutar el proyecto

1. Clona el repositorio desde GitHub:

```bash
git clone https://github.com/postcode-x/salmontt-colecciones-maven.git
```

2. Abre el proyecto en IntelliJ IDEA.

3. Sincronizar Maven

4. Ejecuta el archivo `Main.java` desde el paquete `ui`.

5. Para compilar a un archivo .jar, desde Maven ejecutar `clean` y luego `package`.


---

**Repositorio GitHub:** https://github.com/postcode-x/salmontt-colecciones-maven

**Fecha de entrega:** 24/11/2025

---

© Duoc UC | Escuela de Informática y Telecomunicaciones 



