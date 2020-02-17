Detalles de la tarea de esta unidad. Enunciado.

Debes realizar una aplicación en Java que acceda gestione los datos referentes a productos de un almacén. 
En concreto las lista de requisitos mínimos de la aplicación es:

*** La aplicación debe poder guardar a disco y recuperar desde disco los datos que se almacenarán sobre cada artículo:

	* Cod_articulo.

	* Descripción.

	* Precio.

	* Descuento.

	* En el caso del IVA, se escogerá entre en 4%, 10% y 21%.

	* Familia a la que pertenece el artículo .


*** Mediante el menú swing de la aplicación se podrán realizar determinas operaciones:

	* Archivo. Donde estarán las opciones de abrir y guardar los artículos.

	* Alta. Dispondrá de las opciones de menú para dar de alta productos y familias de productos,
		que se guardarán en memoria, hasta que se seleccione la opción en el menú de archivo para guardarlos a disco.

	* Datos. Dispondrá de las opciones para mostrar los datos de los artículos y de la familia asociada, 
		cargados en memoria, permitiendo su edición, a modo de ficha, o su eliminación. Si se elimina, 
		será por tanto de los datos cargados en memoria, y no sería en el disco hasta guardar los datos 
		con la opción del menú Archivo.

	* Listados. Mostrará los datos cargados en memoria respecto a los artículos o a las familias, 
		según la opción que se elija. Una buena opción es utilizar controles Jtable.


Además del menú de la aplicación, sería conveniente accesos a las opciones de datos, alta y de salir desde la pantalla principal, 
usando por ejemplo un panel de opciones. Según la opción marcada y un botón de "Datos" o de "Alta" que se pulse, 
el programa se dirigirá a la opción adecuada.
