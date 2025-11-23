package cl.salmontt.colecciones.model;

/**
 * Representa una region a la que pertenece un centro de cultivo.
 * Establece una relación de composición con la clase {@link CentroCultivo}.
 */
public class Region {
    private String nombreRegion;

    public Region(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public String getNombreRegion() { return this.nombreRegion; }
    public void setNombreRegion(String nombreRegion) { this.nombreRegion = nombreRegion; }

    @Override
    public String toString() {
        return nombreRegion;
    }
}
