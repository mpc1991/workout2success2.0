# workout2success v2.0
#### Macià Porcel Cifre -- DI04
###
> [!NOTE]
> Lista de cambios realizados:
#####  * jPanelHome --> Ahora contiene un splitPane y dentro de él están los paneles jPanelOptions y jPanelShowInfo
#####  * jPanelHome --> jLabelOptions ahora usa Miglayout para representar los botones
#####  * jPanelHome --> Eliminados todos los setBounds
#####  * jPanelHome --> Cambiado look & feel
#####  * jPanelHome --> Los botones ahora cambian de color al pulsarse
#####  * jPanelHome --> Cuando se hace pequeño "jLabelOptions" a través del SplitPane, los iconos se colocan en una única fila y viceversa
#####  * jPanelHome --> Ahora al entrar, por defecto nos muestra la pantalla de usuarios.
#####
#####  * jPanelHomeUsers --> ahora usa Miglayout para representar todos sus elementos
#####  * jPanelHomeUsers --> Cambiado look & feel
#####  * jPanelHomeUsers --> Ahora usa MigLyout para mostrar la lista de usuarios usando botones en vez de una lista plana
#####  * jPanelHomeUsers --> Ahora la lista de usuarios cambia de color al realizar mouseEntered & mouseExited y al pulsarla
#####  * jPanelHomeUsers --> Ahora los botones cambian de color al realizar mouseEntered & mouseExited
#####  
#####  * jPanelCalendar --> Se ha traspasado el calendario de la UD03, accesible desde jPanelHome --> jPanelOptions --> jButtonCalendar

> [!NOTE]
> Arreglos referentes a la v1

> [!NOTE]
> Páginas consultadas
##### http://www.miglayout.com/QuickStart.pdf
##### https://doc.formdev.com/miglayout-swing/net/miginfocom/swing/MigLayout.html
##### https://icon-icons.com/es/pack/BigMug-Line-icons/935

> [!ALERT]
> Pendiente implementar/modificar
#####  * Los botones Add, change, delete deben cambiarse a iconos.
#####  * Los botones "Create" para confirmar la creación debe cambiarse a icono de V.
#####  * El icono de refresh debe cambiarse por una flecha giratoria
#####  * El jDialog redundante
#####  * El logout debería estar más en medio
#####  * Revisar los refresh de las listas y tablas.
#####  * Cuando se crea un usuario parece que no se asigna correctamente al instructor que toca y se asigna todo a Otero
#####  * DAO va en data, Exercici.java va en dto
#####  * Commits por cada funcionalidad
#####  * Readme h1,h2
