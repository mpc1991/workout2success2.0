# workout2success v2.0
#### Macià Porcel Cifre -- DI04
###
> [!NOTE]
> Interaccion
#####  * De por si la aplicación actualmente no presenta lentitud

> [!NOTE]
> Metaforas
#####  * jPanelHome --> Los botones ahora usan iconos además del texto.

> [!NOTE]
> Affordance (un objeto debe verse clickable, un campo de texto debe verse editable)
#####  * jPanelHome --> Los botones ahora cambian de color al pulsarse
#####  * jPanelHomeUsers --> Ahora la lista de usuarios cambia de color al realizar mouseEntered & mouseExited y al pulsarla.
#####  * jPanelHomeUsers --> Ahora la lista de workouts cambia de color al realizar mouseEntered & mouseExited y al pulsarla.
#####  * jPanelHomeUsers --> Ahora la lista de ejercicios cambia de color al realizar mouseEntered & mouseExited y al pulsarla.
#####  * jPanelHomeUsers --> Ahora los botones de "add", "change" y "create" cambian de color al realizar mouseEntered & mouseExited y al pulsarlos.

> [!NOTE]
> Visibility
#####  * jPanelHome --> Ahora usa botones grandes en vez de los pequeños y rectangulares.
#####  * jPanelHome --> jPanelOptions ahora muestra iconos grandes con metáforas para facilitar la interacción visual
#####  * jPanelHomeUsers --> Se han quitado tablas dejando solo las importantes, haciendolo más ligero visualmente.
#####  * jPanelHomeUsers --> Ahora la lista de texto plano se ha modificado por botones más grandes y aplicado Affordance

> [!NOTE]
> Feedback
#####  * Se ha añadido feedback visual al pasar por encima o clickar en los elementos destinados a ellos

> [!NOTE]
> Natural Mapping
#####  * Se han quitado elementos y separado funcionalidades.
#####  * Se ha implantado un calendario en una página completamente diferente y facilmente accesible
#####  * Los botones son facilmente identificables en la interfaz

> [!NOTE]
> Otros cambios adicionales:
#####  * jPanelHome --> Ahora contiene un splitPane y dentro de él están los paneles jPanelOptions y jPanelShowInfo
#####  * jPanelHome --> jLabelOptions ahora usa Miglayout para representar los botones
#####  * jPanelHome --> Eliminados todos los setBounds
#####  * jPanelHome --> Cambiado look & feel
#####  * jPanelHome --> Ahora al entrar, por defecto nos muestra la pantalla de usuarios.
#####  * jPanelHome --> Cuando se hace pequeño "jLabelOptions" a través del SplitPane, los iconos se colocan en una única fila y viceversa
#####
#####  * jPanelHomeUsers --> ahora usa Miglayout para representar todos sus elementos
#####  * jPanelHomeUsers --> Cambiado look & feel
#####  * jPanelHomeUsers --> Ahora usa MigLyout para mostrar la lista de usuarios usando botones en vez de una lista plana
#####  * jPanelHomeUsers --> Ahora los botones cambian de color al realizar mouseEntered & mouseExited
#####  * jPanelCalendar --> Se ha traspasado el calendario de la UD03, accesible desde jPanelHome --> jPanelOptions --> jButtonCalendar

> [!NOTE]
> Arreglos referentes a la v1

> [!NOTE]
> Páginas consultadas
##### http://www.miglayout.com/QuickStart.pdf
##### https://doc.formdev.com/miglayout-swing/net/miginfocom/swing/MigLayout.html
##### https://icon-icons.com/es/pack/BigMug-Line-icons/935

> [!WARNING]
> Pendiente implementar/modificar que pueden dar problemas todavía
#####  * Los botones Add, change, delete deben cambiarse a iconos. (Metaforas)
#####  * Los botones "Create" para confirmar la creación debe cambiarse a icono de V y X y de color. (Metafora)
#####  * Los botones "Create" para confirmar la creación debe cambiarse de color. (affordance)
#####  * El icono de refresh debe cambiarse por una flecha giratoria (Metafora)
#####  * El jDialog redundante en el loggin debe quitarse
#####  * El logout debería estar más en medio
#####  * Revisar los refresh de las listas y tablas.
#####  * Cuando se crea un usuario parece que no se asigna correctamente al instructor que toca y se asigna todo a Otero
#####  * DAO va en data, Exercici.java va en dto
#####  * Commits por cada funcionalidad
#####  * Readme h1,h2
#####  * Poder cambiar los colores desde settings
#####  * Poder buscar un usuario por nombre --> busqueda, filtros, paginacion
#####  * Tecla enter debe enviar el form
#####  * Tabulacion debe pasar a la siguiente casilla
#####  * Dark Pattern --> botón cancelar menos visible que el de aceptar.
#####  * Regular expressions --> casilla correo
