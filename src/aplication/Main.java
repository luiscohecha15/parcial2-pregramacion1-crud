package aplication;


import aplication.infraestructure.repository.TelefonoRepositoryImpl;
import aplication.service.TelefonoServiceImpl;
import domain.Telefono;
import interfaces.TelefonoRepository;
import interfaces.TelefonoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TelefonoService telefonoService;


    static {
        TelefonoRepository telefonoRepository = new TelefonoRepositoryImpl();
        telefonoService= new TelefonoServiceImpl(telefonoRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Registrar Telefono");
            System.out.println("2. Borrar tel");
            System.out.println("3. Actualizar tel");
            System.out.println("4. Consultar tel");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1://registre
                    registrarTelefono();
                    break;
                case 2:
                    //Borrar
                    eliminarTelefono();
                    break;
                case 3:
                    //actualice
                    actualizarTelefono();
                    break;
                case 4:

                    listarTelefono();
                    //Consulte
                    //consultaTelefono();
                    break;
                case 5:
                    salir = true;

                    break;
                case 6:
                    listarTodosTelefonos();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    private static void registrarTelefono() {

        System.out.print("Ingrese el imei del telefono: ");
        int imei = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el modelo del telefono: ");
        String modelo = scanner.nextLine();

        System.out.print("Ingrese la marca del telefono: ");
        String marca = scanner.nextLine();

        telefonoService.save(new Telefono(imei,modelo,marca));
        System.out.println("Registrado con exito");

    }

    private static void eliminarTelefono() {
        System.out.print("Ingrese el imei del telefono a eliminar: ");
        int imei = scanner.nextInt();

        Telefono telefonos = telefonoService.findByid(imei);
        if (telefonos == null) {
            System.out.println("No se encontró el imei " + imei);
            return;
        }

        telefonoService.delete(imei);
        System.out.println("Telefono eliminado exitosamente.");
    }

    private static void actualizarTelefono () {
        System.out.print("Ingrese el imei del telefono a actualizar: ");
        int imei = scanner.nextInt();
        scanner.nextLine();

        Telefono telefonos = telefonoService.findByid(imei);
        if (telefonos == null) {
            System.out.println("No se encontró el imei: " + imei);
            return;
        }

        System.out.print("Ingrese el cambio de modelo (dejar en blanco para no cambiar): ");
        String modelo = scanner.nextLine();
        if (!modelo.isEmpty()) {
            telefonos.setModelo(modelo);
        }

        telefonoService.update(telefonos);

        try {
            System.out.println("Telefono actualizada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarTelefono() {

        List<Telefono> telefonos = telefonoService.findAll();
        System.out.println("Buscar telefono, digite el imei: ");
        int imei = scanner.nextInt();

        for (Telefono telefono : telefonos) {
            if (telefono.getImei()== imei ) {
                System.out.println(telefono);
            }
        }
    }

    private static void listarTodosTelefonos() {

        List<Telefono> telefonos = telefonoService.findAll();
        if (telefonos.isEmpty()) {
            System.out.println("No hay telefonos registrados.");
        } else {
            System.out.println("Listado de telefonos:");
            for (Telefono telefono : telefonos) {
                System.out.println(telefono);
            }
        }
    }

}
