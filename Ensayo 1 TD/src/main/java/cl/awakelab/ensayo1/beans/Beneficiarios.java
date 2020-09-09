package cl.awakelab.ensayo1.beans;

public class Beneficiarios {

    private int beneficiarioid;
    private String nombre;
    private int edad;
    private Ciudades ciudad;

    public Beneficiarios() {

    }

    public int getBeneficiarioid() {
        return beneficiarioid;
    }

    public void setBeneficiarioid(int beneficiarioid) {
        this.beneficiarioid = beneficiarioid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

}
