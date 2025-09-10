
import java.util.Scanner;

public class InventarioSupermercado {
	
// Constante para definir el numero fijo de productos
	
	static final int MAX_PRODUCTOS = 5;
		
		static String[] nombres = new String[MAX_PRODUCTOS];
	    static int[] cantidades = new int[MAX_PRODUCTOS];
	    

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
// Registro inicial de productos
			
	System.out.println (" Registro de productos:");
	for (int i = 0; i < MAX_PRODUCTOS; i++) {	
		System.out.print("Ingrese nombre del producto #" + (i + 1) + ": ");
		nombres[i] = sc.nextLine();
	                
		System.out.println(" Ingrese cantidad para '"+ nombres[i] + "':");
		cantidades[i] = sc.nextInt();
		sc.nextLine();
		
	 		
	 	while (cantidades[i] < 0) {
	 		
      System.out.println("Cantidad no puede ser negativa.");
      System.out.print("Ingrese cantidad válida para '" + nombres[i] + "': ");
              cantidades[i] = sc.nextInt();
               sc.nextLine();
            }
		}
	
	
// Menu 
		
	int opcion;
	do {
				
	System.out.println("...Menú de opciones:");
	System.out.println("1. Mostrar todos los productos");
	System.out.println("2. Buscar un producto");
	System.out.println("3. Modificar cantidad de un producto");
	System.out.println("4. Mostrar productos con existencia menor a 3");
	System.out.println("5. Mostrar total de productos en inventario");
	System.out.println("0. Salir");
	System.out.print("Seleccione una opción: ");
	opcion = sc.nextInt();
	sc.nextLine();
	
	switch (opcion) {
	case 1:
        mostrarInventario();
        break;
    case 2:
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombreBuscar = sc.nextLine();
        int pos = buscarProducto(nombreBuscar);
        if (pos != -1) {
            System.out.println("'" + nombreBuscar + "' tiene " + cantidades[pos] + " unidades.");
        } else {
            System.out.println("Producto no encontrado.");
        }
        break;
    case 3:
        System.out.print("Ingrese el nombre del producto a modificar: ");
        String nombreModificar = sc.nextLine();
        int posicion = buscarProducto(nombreModificar);
        if (posicion != -1) {
            System.out.print("Ingrese la nueva cantidad: ");
            int nuevaCantidad = sc.nextInt();
            sc.nextLine();
            if (nuevaCantidad >= 0) {
                cantidades[posicion] = nuevaCantidad;
                System.out.println("Cantidad actualizada.");
            } else {
                System.out.println("No se permiten cantidades negativas.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
        break;
    case 4:
        mostrarBajaExistencia();
        break;
    case 5:
        mostrarTotalInventario();
        break;
    case 0:
        System.out.println("Saliendo del sistema...");
        break;
    default:
        System.out.println("Opción inválida.");
}

} while (opcion != 0);

sc.close();
}

// Buscar producto
		public static int buscarProducto(String nombre) {
	        for (int i = 0; i < MAX_PRODUCTOS; i++) {
	            if (nombres[i].equalsIgnoreCase(nombre)) {
	                return i;
	            }
	        }
	        return -1;
	    }

// Inventario completo
	    public static void mostrarInventario() {
	        System.out.println(" Inventario completo:");
	        for (int i = 0; i < MAX_PRODUCTOS; i++) {
	            System.out.println("- " + nombres[i] + ": " + cantidades[i] + " unidades");
	        }
	    }

// Productos con existencia menor a 3
	    public static void mostrarBajaExistencia() {
	        System.out.println("Nro Productos con existencia menor a 3:");
	        for (int i = 0; i < MAX_PRODUCTOS; i++) {
	            if (cantidades[i] < 3) {
	                System.out.println("- " + nombres[i] + ": " + cantidades[i] + " unidades");
	            }
	        }
	    }

// Total acumulado
	    public static void mostrarTotalInventario() {
	        int total = 0;
	        for (int i = 0; i < MAX_PRODUCTOS; i++) {
	            total += cantidades[i];
	        }
	        System.out.println(" Nro Total de productos en inventario: " + total);
	    }
	}

