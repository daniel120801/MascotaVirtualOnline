-SINGLETON: guardar datos de configuración para su uso posterior

-ADAPTER: la información de las mascotas se almacena en formato json, al usar este formato se 
        adaptara al formato de una clase que sea tratable por el codigo 
-FACTORY METHOD: para anidar el comportamiento de cada mascota.
-crear distintos tipos de comportamiento dependiendo del tipo de mascota("ave,terrestre")

-----MASCOTA-----
    --Estadisticas de vida--
    -hambre
    -sed
    -diversión
    --Estadisticas de comportamiento--
    -tipo de mascota:ave,terreste.otros?
    -velocidad de movimiento
    -velocidad de cambio de acto
    -actos("lista de acciones que efectua la mascota:volar,caminar,comer,dormir,etc.")


-----Configuración-----
    -al tener distintos actos la mascota, aqui se seleccionara cuales se desea que sean visibles y cuales no
    guardando una clase especifica de Mascota que se encarga solo de mostrar estas preferencias
    -se contara con un valor numerico que determinara la escala por la que e multiplicara el tamano 
    de la mascota para personalización
    