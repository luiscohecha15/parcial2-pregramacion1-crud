package aplication;

import aplication.infraestructure.repository.CitaRepositoryImpl;
import aplication.service.CitaServiceImpl;
import domain.Cita;
import domain.Paciente;
import interfaces.CitaRepository;
import interfaces.CitaService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CitaService citaService;


    static {
        CitaRepository citaRepository = new CitaRepositoryImpl();
        citaService = new CitaServiceImpl(citaRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Registrar Cita");
            System.out.println("2. Borre cita");
            System.out.println("3. Actualizar cita");
            System.out.println("4. Consultar cita");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1://registre
                    registrarCita();
                    break;
                case 2:
                    //Borrar
                    eliminarCita();
                    break;
                case 3:
                    //actualice
                    actualizarCita();
                    break;
                case 4:
                    //Consulte
                    listarCitas();
                    break;
                case 5:
                    salir = true;

                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    private static void registrarCita() {

        List<Cita> citas = citaService.findAll();
        int counter = citas.size() + 1;

        System.out.print("Ingrese la identificacion del paciente: ");
        String cc = scanner.nextLine();

        System.out.print("Ingrese el nombre del paciente: ");
        String nombrePaciente = scanner.nextLine();

        System.out.print("Ingrese el apellido del paciente: ");
        String apellidoPaciente = scanner.nextLine();

        System.out.print("Ingrese el telefono del paciente: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese la hora de la cita: ");
        String hora = scanner.nextLine();

        System.out.print("Ingrese la fecha del paciente: ");
        String fecha = scanner.nextLine();

        Paciente paciente = new Paciente(cc, nombrePaciente, apellidoPaciente, telefono);

        citaService.save(new Cita(counter, paciente, hora, fecha));

        System.out.println("Registrado con exito");

    }

    private static void eliminarCita() {
        System.out.print("Ingrese el ID de la cita a eliminar: ");
        int id = scanner.nextInt();

        Cita citas = citaService.findByid(id);
        if (citas == null) {
            System.out.println("No se encontró la cita con ID " + id);
            return;
        }

        citaService.delete(id);
        System.out.println("Producto eliminado exitosamente.");
    }

    private static void listarCitas() {

        //List<Cita> citas = citaService.findAll();
        System.out.println("Identificacion del paciente: ");
        String cc = scanner.nextLine();
        citaService.filterCitas(cc);

        //for (Cita cita : citas) {
        //  if (cita.getPaciente().getCc().equals(cc)) {
        //    System.out.println(cita);
        // }
        //}
    }
    private static void actualizarCita () {
        System.out.print("Ingrese el cc del paciente a actualizar: ");
        String id = scanner.nextLine();

        Cita citas = citaService.findByid(Integer.parseInt(id));
        if (citas == null) {
            System.out.println("No se encontró el paciente con el id " + id);
            return;
        }

        System.out.print("Ingrese la nueva hora (dejar en blanco para no cambiar): ");
        String hora = scanner.nextLine();
        if (!hora.isEmpty()) {
            citas.setHora(hora);
        }
        System.out.print("Ingrese el nuevo apellido del paciente (dejar en blanco para no cambiar): ");
        String fecha = scanner.nextLine();
        if (!fecha.isEmpty()) {
            citas.setFecha(fecha);
        }

        try {

            System.out.println("Cita actualizada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
