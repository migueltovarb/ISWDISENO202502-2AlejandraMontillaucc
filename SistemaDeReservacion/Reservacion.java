import java.util.ArrayList;
import java.util.Scanner;

// Clase Sala
class Sala {
    int numeroSala;
    int capacidadMaxima;
    boolean disponible;

    public Sala(int numeroSala, int capacidadMaxima) {
        this.numeroSala = numeroSala;
        this.capacidadMaxima = capacidadMaxima;
        this.disponible = true;
    }
}

// Clase Estudiante
class Estudiante {
    String nombre;
    String codigo;
    String programaAcademico;
    ArrayList<Reserva> reservas;

    public Estudiante(String nombre, String codigo, String programaAcademico) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.programaAcademico = programaAcademico;
        this.reservas = new ArrayList<>();
    }
}

// Clase Reserva
class Reserva {
    Estudiante estudiante;
    Sala sala;
    String fecha;
    String hora;

    public Reserva(Estudiante estudiante, Sala sala, String fecha, String hora) {
        this.estudiante = estudiante;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
    }
}

// Sistema de Reservación
public class Reservacion {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Sala> salas = new ArrayList<>();
    static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println();
            System.out.println("Sistema de Reservación de Salas de Estudio");
            System.out.println();
            System.out.println("1. Registrar Sala");
            System.out.println("2. Registrar Estudiante");
            System.out.println("3. Hacer Reserva");
            System.out.println("4. Consultar Historial de Estudiante");
            System.out.println("5. Mostrar Salas Disponibles");
            System.out.println("6. Salir");
            System.out.println();

            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Ingrese un número de opción válido: ");
                scanner.nextLine();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarSala();
                    break;
                case 2:
                    registrarEstudiante();
                    break;
                case 3:
                    hacerReserva();
                    break;
                case 4:
                    consultarHistorialEstudiante();
                    break;
                case 5:
                    mostrarSalasDisponibles();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    static void registrarSala() {
        System.out.println();
        System.out.println("Registrar Sala");
        System.out.print("Ingrese el número de la sala: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Número inválido. Ingrese el número de la sala: ");
            scanner.nextLine();
        }
        int numeroSala = scanner.nextInt();
        scanner.nextLine();

        for (Sala sala : salas) {
            if (sala.numeroSala == numeroSala) {
                System.out.println("La sala con este número ya existe.");
                return;
            }
        }

        System.out.print("Capacidad máxima: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Valor inválido. Ingrese la capacidad máxima: ");
            scanner.nextLine();
        }
        int capacidad = scanner.nextInt();
        scanner.nextLine();

        Sala sala = new Sala(numeroSala, capacidad);
        salas.add(sala);
        System.out.println("Sala registrada exitosamente.");
    }

    static void registrarEstudiante() {
        System.out.println();
        System.out.println("Registrar Estudiante");
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Ingrese el código del estudiante: ");
        String codigo = scanner.nextLine().trim();

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.codigo.equals(codigo)) {
                System.out.println("El estudiante con este código ya está registrado.");
                return;
            }
        }

        System.out.print("Ingrese el programa académico: ");
        String programaAcademico = scanner.nextLine().trim();
        if (nombre.isEmpty() || codigo.isEmpty() || programaAcademico.isEmpty()) {
            System.out.println("Todos los campos son obligatorios. Registro fallido.");
            return;
        }
        Estudiante estudiante = new Estudiante(nombre, codigo, programaAcademico);
        estudiantes.add(estudiante);
        System.out.println("Estudiante registrado exitosamente.");
    }

    static void hacerReserva() {
        System.out.println();
        System.out.println("Hacer Reserva");

        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados. Por favor, registre un estudiante primero.");
            return;
        }

        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas. Por favor, registre una sala primero.");
            return;
        }

        System.out.print("Ingrese el código del estudiante: ");
        String codigo = scanner.nextLine().trim();
        Estudiante estudiante = null;
        for (Estudiante e : estudiantes) {
            if (e.codigo.equals(codigo)) {
                estudiante = e;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado. Por favor, registre el estudiante primero.");
            return;
        }

        System.out.print("Salas disponibles: ");
        boolean disponibles = false;
        for (Sala sala : salas) {
            if (sala.disponible) {
                System.out.print(sala.numeroSala + " ");
                disponibles = true;
            }
        }
        System.out.println();

        if (!disponibles) {
            System.out.println("No hay salas disponibles.");
            return;
        }

        System.out.print("Ingrese el número de la sala a reservar: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Número inválido. Ingrese el número de la sala a reservar: ");
            scanner.nextLine();
        }
        int numeroSala = scanner.nextInt();
        scanner.nextLine();

        Sala salaSeleccionada = null;
        for (Sala sala : salas) {
            if (sala.numeroSala == numeroSala && sala.disponible) {
                salaSeleccionada = sala;
                break;
            }
        }

        if (salaSeleccionada == null) {
            System.out.println("Sala no encontrada o no disponible.");
            return;
        }

        System.out.print("Ingrese la fecha de la reserva (DD/MM/AAAA): ");
        String fecha = scanner.nextLine().trim();
        System.out.print("Ingrese la hora (HH:MM): ");
        String hora = scanner.nextLine().trim();

        if (fecha.isEmpty() || hora.isEmpty()) {
            System.out.println("Todos los campos son obligatorios. Reserva fallida.");
            return;
        }

        for (Reserva r : reservas) {
            if (r.sala.numeroSala == numeroSala && r.fecha.equals(fecha) && r.hora.equals(hora)) {
                System.out.println("La sala ya está reservada para esta fecha y hora.");
                return;
            }
        }

        for (Reserva r : estudiante.reservas) {
            if (r.fecha.equals(fecha) && r.hora.equals(hora)) {
                System.out.println("El estudiante ya tiene una reserva para esta fecha y hora.");
                return;
            }
        }

        Reserva reserva = new Reserva(estudiante, salaSeleccionada, fecha, hora);
        reservas.add(reserva);
        estudiante.reservas.add(reserva);
        salaSeleccionada.disponible = false;
        System.out.println("Reserva realizada exitosamente.");
        System.out.println("Estudiante: " + estudiante.nombre + ", Sala: " + salaSeleccionada.numeroSala + ", Fecha: " + fecha + ", Hora: " + hora);
    }

    static void consultarHistorialEstudiante() {
        System.out.println();
        System.out.println("Consultar Historial de Estudiante");
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.print("Ingrese el código del estudiante: ");
        String codigo = scanner.nextLine().trim();
        Estudiante estudiante = null;
        for (Estudiante e : estudiantes) {
            if (e.codigo.equals(codigo)) {
                estudiante = e;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        if (estudiante.reservas.isEmpty()) {
            System.out.println("El estudiante no tiene reservas.");
            return;
        }

        System.out.println("Historial de Reservas para " + estudiante.nombre + ":");
        for (Reserva reserva : estudiante.reservas) {
            System.out.println("Sala: " + reserva.sala.numeroSala + ", Fecha: " + reserva.fecha + ", Hora: " + reserva.hora + ", Capacidad: " + reserva.sala.capacidadMaxima + " personas");
        }
    }

    static void mostrarSalasDisponibles() {
        System.out.println();
        System.out.println("SALAS DISPONIBLES");

        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas");
            return;
        }

        boolean hayDisponibles = false;
        for (Sala s : salas) {
            if (s.disponible) {
                System.out.println();
                System.out.println("• Sala: " + s.numeroSala);
                System.out.println("  Capacidad: " + s.capacidadMaxima + " personas");
                System.out.println("  Estado: Disponible");
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay salas disponibles en este momento");
        }

        // Mostrar también las ocupadas
        System.out.println();
        System.out.println("--- SALAS OCUPADAS ---");
        boolean hayOcupadas = false;
        for (Sala s : salas) {
            if (!s.disponible) {
                System.out.println();
                System.out.println("• Sala: " + s.numeroSala);
                System.out.println("  Capacidad: " + s.capacidadMaxima + " personas");
                System.out.println("  Estado: Ocupada");
                hayOcupadas = true;
            }
        }

        if (!hayOcupadas) {
            System.out.println("Todas las salas están disponibles");
        }
    }
}

