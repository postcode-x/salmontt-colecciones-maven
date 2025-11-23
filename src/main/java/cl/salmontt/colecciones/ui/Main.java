package cl.salmontt.colecciones.ui;

import cl.salmontt.colecciones.data.GestorDatos;
import cl.salmontt.colecciones.model.CentroCultivo;
import cl.salmontt.colecciones.util.InputUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GestorDatos gestor = new GestorDatos();

        // Cargar datos iniciales desde Excel y TXT
        gestor.cargarDesdeExcel("centros.xlsx");
        gestor.cargarCentrosDesdeTxt("centros.txt");

        Scanner sc = new Scanner(System.in);
        int opcion;

        // Bucle principal del menu
        do {
            System.out.println("\n--- Menu ---\n");
            System.out.println("1. Busqueda por texto");
            System.out.println("2. Busqueda por nombre");
            System.out.println("3. Busqueda por comuna");
            System.out.println("4. Busqueda por toneladas producidas");
            System.out.println("5. Mostrar todos los centros");
            System.out.println("6. Salir");

            opcion = InputUtils.leerEntero(sc, "\nSeleccione una opcion: ");

            // Manejo de opciones del menu
            switch (opcion) {
                case 1 -> buscarUI(sc, gestor, "texto");   // UI busqueda por texto
                case 2 -> buscarUI(sc, gestor, "nombre");   // UI busqueda por nombre
                case 3 -> buscarUI(sc, gestor, "comuna");   // UI busqueda por comuna
                case 4 -> buscarPorToneladasUI(sc, gestor); // UI busqueda por toneladas
                case 5 -> mostrarTodosUI(gestor);           // UI listar todos
                case 6 -> System.out.println("\nHasta luego!");
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 6);

        // Cerrar Scanner
        sc.close();
    }

    // UI para buscar por nombre o comuna
    private static void buscarUI(Scanner sc, GestorDatos gestor, String tipo) {
        System.out.println("\nBuscar centros por " + tipo + "\n");

        boolean seguir = true;

        while (seguir) {

            // Solicitar texto de busqueda
            String textoBusqueda = preguntarBuscarTexto(sc, tipo);

            // Buscar segun el tipo solicitado
            var resultados = tipo.equals("texto")
                    ?   gestor.buscarPorTexto(textoBusqueda)
                    :   tipo.equals("nombre")
                        ?   gestor.buscarPorNombre(textoBusqueda)
                        :   gestor.buscarPorComuna(textoBusqueda);

            // Mostrar resultados
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron centros.");
            } else {
                for (CentroCultivo cc : resultados) {
                    System.out.println(cc);
                }
            }

            // Preguntar si desea continuar buscando
            String resp;
            do {
                System.out.print("Desea realizar otra busqueda? (s/n): ");
                resp = sc.nextLine().trim().toLowerCase();
            } while (!resp.equals("s") && !resp.equals("n"));

            if (resp.equals("n")) {
                seguir = false;
            }
        }

        System.out.println("Saliendo del buscador.");
    }

    // UI para solicitar texto de busqueda (nombre o comuna)
    private static String preguntarBuscarTexto(Scanner sc, String tipo){
        String texto;
        do {
            System.out.print("Ingrese " + tipo + " a buscar: ");
            texto = sc.nextLine().trim();

            // Validacion de entrada vacia
            if (texto.isEmpty()) {
                System.out.println("El texto de busqueda no puede estar vacio.");
            }
        } while (texto.isEmpty());

        return texto;
    }

    // UI para busqueda por toneladas minimas
    private static void buscarPorToneladasUI(Scanner sc, GestorDatos gestor) {
        System.out.println("\nBuscar centros por toneladas producidas\n");

        boolean seguir = true;

        while (seguir) {

            // Solicitar minimo de toneladas
            int minimo = InputUtils.leerEntero(sc, "Ingrese toneladas minimas: ");

            if(minimo < 0) {
                System.out.println("El minimo ingresado debe ser mayor o igual a cero");
            }else{
                // Ejecutar la busqueda
                var resultados = gestor.buscarPorToneladasMinimas(minimo);

                // Mostrar resultados
                if (resultados.isEmpty()) {
                    System.out.println("No se encontraron centros con toneladas >= " + minimo);
                } else {
                    for (CentroCultivo cc : resultados) {
                        System.out.println(cc);
                    }
                }
            }
            // Pregunta para continuar
            String resp;
            do {
                System.out.print("Desea realizar otra busqueda? (s/n): ");
                resp = sc.nextLine().trim().toLowerCase();
            } while (!resp.equals("s") && !resp.equals("n"));

            if (resp.equals("n")) seguir = false;
        }

        System.out.println("Saliendo del buscador de toneladas.");
    }

    // UI para mostrar todos los centros cargados
    private static void mostrarTodosUI(GestorDatos gestor) {
        gestor.listarTodos();
    }
}
