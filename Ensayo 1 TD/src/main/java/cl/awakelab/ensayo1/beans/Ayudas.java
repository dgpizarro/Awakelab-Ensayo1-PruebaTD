package cl.awakelab.ensayo1.beans;

public class Ayudas {
    
    private int ayudaid;
    private Beneficiarios beneficiario;
    private int monto;
    private String motivo;
    
    
    public Ayudas() {
    }


    public int getAyudaid() {
        return ayudaid;
    }


    public void setAyudaid(int ayudaid) {
        this.ayudaid = ayudaid;
    }


    public Beneficiarios getBeneficiario() {
        return beneficiario;
    }


    public void setBeneficiario(Beneficiarios beneficiario) {
        this.beneficiario = beneficiario;
    }


    public int getMonto() {
        return monto;
    }


    public void setMonto(int monto) {
        this.monto = monto;
    }


    public String getMotivo() {
        return motivo;
    }


    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    
    
    

}
