package cl.salmontt.colecciones.model;

/**
 * Representa un centro de cultivo de la empresa Salmontt.
 * <p>
 * Cada centro contiene su nombre, la comuna donde se ubica y las toneladas
 * de producción anual. Esta clase se utiliza para almacenar los datos
 * cargados desde un archivo externo y manipularlos dentro de la aplicación.
 * </p>
 */
public class CentroCultivo {
    private String nombre;
    private String comuna;
    private int toneladasProducidas;
    private Region region;
    /**
     * Crea un nuevo centro de cultivo con todos sus datos.
     *
     * @param nombre              Nombre del centro de cultivo.
     * @param comuna              Comuna donde se ubica.
     * @param toneladasProducidas Toneladas producidas por el centro.
     * @param region              La región del centro, por relación de composición
     */
    public CentroCultivo(String nombre, String comuna, int toneladasProducidas, Region region) {
        this.nombre = nombre;
        this.comuna = comuna;
        this.toneladasProducidas = toneladasProducidas;
        this.region = region;
    }

    /* Getters y Setters */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getToneladasProducidas() {
        return toneladasProducidas;
    }

    public void setToneladasProducidas(int toneladasProducidas) {
        this.toneladasProducidas = toneladasProducidas;
    }

    public Region getRegion() { return region; }

    public void setRegion(Region region) { this.region = region; }
    /**
     * Retorna una representación en texto del centro de cultivo,
     * mostrando sus atributos principales.
     *
     * @return Cadena con el nombre, comuna y toneladas producidas.
     */
    @Override
    public String toString() {
        return "Centro Cultivo: " +
                "Nombre : '" + nombre + '\'' +
                ", Comuna : '" + comuna + '\'' +
                ", Región : '" + region + '\'' +
                ", Toneladas producidas : " + toneladasProducidas;
    }
}
