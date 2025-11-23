package cl.salmontt.colecciones.data;

import cl.salmontt.colecciones.model.CentroCultivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.salmontt.colecciones.model.Region;
import org.apache.poi.xssf.usermodel.*;
import java.io.InputStreamReader;

/**
 * Clase encargada de gestionar la carga y administracion de datos externos.
 *
 * Lee un archivo de texto o Excel con informacion de centros de cultivo,
 * crea objetos CentroCultivo y los almacena en una lista para su uso
 * en la aplicacion.
 */
public class GestorDatos {

    private List<CentroCultivo> listaCentros = new ArrayList<>();

    /**
     * Agrega un centro de cultivo a la lista interna.
     */
    public void agregar(CentroCultivo cc) {
        listaCentros.add(cc);
    }

    /**
     * Muestra en consola todos los centros cargados.
     */
    public void listarTodos() {
        int contador = 0;
        for (CentroCultivo cc : listaCentros) {
            contador++;
            System.out.println(contador + ") " + cc);
        }
    }

    /**
     * Busca centros cuyo nombre, comuna o región coincidan el texto de búsqueda.
     *
     * @param texto Texto a buscar dentro del nombre, comuna y región del centro.
     * @return Lista de coincidencias.
     */
    public List<CentroCultivo> buscarPorTexto(String texto) {
        List<CentroCultivo> resultado = new ArrayList<>();
        for (CentroCultivo cc : listaCentros) {
            if (cc.getNombre().toLowerCase().contains(texto.toLowerCase()) ||
                    cc.getComuna().toLowerCase().contains(texto.toLowerCase()) ||
                    cc.getRegion().getNombreRegion().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(cc);
            }
        }
        return resultado;
    }

    /**
     * Busca centros cuyo nombre contenga el texto indicado.
     *
     * @param nombre Texto a buscar dentro del nombre del centro.
     * @return Lista de coincidencias.
     */
    public List<CentroCultivo> buscarPorNombre(String nombre) {
        List<CentroCultivo> resultado = new ArrayList<>();
        for (CentroCultivo cc : listaCentros) {
            if (cc.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(cc);
            }
        }
        return resultado;
    }

    /**
     * Busca centros cuya comuna contenga el texto indicado.
     *
     * @param comuna Texto a buscar en la comuna.
     * @return Lista de coincidencias.
     */
    public List<CentroCultivo> buscarPorComuna(String comuna) {
        List<CentroCultivo> resultado = new ArrayList<>();
        for (CentroCultivo cc : listaCentros) {
            if (cc.getComuna().toLowerCase().contains(comuna.toLowerCase())) {
                resultado.add(cc);
            }
        }
        return resultado;
    }

    /**
     * Busca centros que produzcan igual o mas toneladas que el minimo indicado.
     *
     * @param minimo Toneladas minimas requeridas.
     * @return Lista de centros que cumplen la condicion.
     */
    public List<CentroCultivo> buscarPorToneladasMinimas(int minimo) {
        List<CentroCultivo> resultado = new ArrayList<>();
        for (CentroCultivo cc : listaCentros) {
            if (cc.getToneladasProducidas() >= minimo) {
                resultado.add(cc);
            }
        }
        return resultado;
    }

    /**
     * Carga centros de cultivo desde un archivo .txt.
     * Formato esperado: nombre;comuna;toneladas
     */
    public void cargarCentrosDesdeTxt(String nombreArchivo) {

        try (var is = getClass().getClassLoader().getResourceAsStream(nombreArchivo)) {

            if (is == null) {
                System.out.println("No se encontró el archivo en resources: " + nombreArchivo);
                return;
            }

            try (var br = new BufferedReader(new InputStreamReader(is))) {

                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";");

                    if (partes.length == 4) {
                        String nombre = partes[0].trim();
                        String comuna = partes[1].trim();
                        int toneladas = Integer.parseInt(partes[2].trim());
                        String region = partes[3].trim();

                        agregar(new CentroCultivo(nombre, comuna, toneladas, new Region(region)));
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer archivo TXT: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir número: " + e.getMessage());
        }
    }

    /**
     * Carga centros de cultivo desde un archivo Excel (.xlsx).
     * Se espera que los datos esten en la primera hoja:
     * Columna 0: nombre
     * Columna 1: comuna
     * Columna 2: toneladas producidas
     */
    public void cargarDesdeExcel(String nombreArchivo) {

        try (var is = getClass().getClassLoader().getResourceAsStream(nombreArchivo)) {

            if (is == null) {
                System.out.println("No se encontró el archivo en resources: " + nombreArchivo);
                return;
            }

            try (var libro = new XSSFWorkbook(is)) {

                XSSFSheet hoja = libro.getSheetAt(0);

                for (int i = 1; i <= hoja.getLastRowNum(); i++) {
                    var fila = hoja.getRow(i);

                    if (fila == null) continue;

                    String nombre = fila.getCell(0).getStringCellValue();
                    String comuna = fila.getCell(1).getStringCellValue();
                    int toneladas = (int) fila.getCell(2).getNumericCellValue();
                    String region = fila.getCell(3).getStringCellValue();

                    agregar(new CentroCultivo(nombre, comuna, toneladas, new Region(region)));
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer Excel: " + e.getMessage());
        }
    }

}
