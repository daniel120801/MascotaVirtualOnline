
#### Guardado de imagenes en internet pospuesto por costos extra ####
# SINGLETON:
* Guardar datos de configuración para su uso posterior

# ADAPTER:
* La información de las mascotas se almacena en formato JSON, al usar este formato se adaptará al formato de una clase que sea tratable por el código

# FACADE 
* se usara para anidar las funciones de firebase necesarias en la aplicacion, como alimentar la mascota o iniciar sesión.

# FACTORY METHOD:
* Para anidar el comportamiento de cada mascota. Crear distintos tipos de comportamiento dependiendo del tipo de mascota ("ave", "terrestre")

# MASCOTA

## Estadísticas de vida
* **Hambre**
* **Sed**
* **Diversión**

## Estadísticas de comportamiento
* **Tipo de mascota**: ave, terrestre, otros?
* **Velocidad de movimiento**
* **Tiempo para cambio de acto**
* **Actos** (lista de acciones que efectúa la mascota: volar, caminar, comer, dormir, etc.)

### Los actos contarán con la siguiente configuración:
* **Área de acción**: se consideran 3 valores:
    1. Toda la pantalla
    2. De parte baja a media pantalla
    3. Parte baja con un poco menos escondiendo parte del diseño
* **Imágenes que conforman la acción** (animación)

# CONFIGURACIÓN

* Al tener distintos actos la mascota, aquí se guardará cuáles se desea que sean visibles y cuáles no,
* guardando una clase específica de Mascota que se encarga solo de mostrar estas preferencias
* Se contará con un valor numérico que determinará la escala por la que se multiplicará el tamaño de la
* mascota para personalización

# Datos a almacenar en la base de datos

## Usuarios
* email de usuario(id)
* lista de mascotas de las que es dueño(id)
* lista de mascotas de las que tiene acceso(id)

## Mascotas
* id 
* nombre
* usuario dueño(email)
* usuarios con acceso(lista de emails)
* estadisticas(hambre,sed,diversion)
* 
### animaciones(lista de animaciones)
* por cada animación se guarda:
  * Frames(imagenes que conforman el flujo marcadas con un numero en su nombre para orden)
  * Area de acción(explicada en Mascota)

## Mascotas por defecto
* esta sera una lista de mascotas con los mismos parametros de "Mascotas" pero seran de acceso 
  publico(se planea implementar modo de creación de mascotas personalizadas)
### Comportamiento 
* una vez descargada la información se guarda en el telefono del usuario para acceso rapido.
* si el usuario desea compartir su mascota con algun otro usuario, esta mascota(aunque sea igual a 
  las de defecto) se copiara y guardara como una mascota mas co el identificador de su usuario dueño

# implementaciones a FUTURO
## árbol de animaciones

* Por cada estadística de vida existirá una animación específica que se mostrará cada cierto tiempo 
* aleatorio cuando la estadística esté por debajo del 50%

