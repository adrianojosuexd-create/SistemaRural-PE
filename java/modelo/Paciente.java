package modelo;
import java.time.LocalDate;
public class Paciente extends Persona {
    private final String numeroHistoriaClinica;
    private String caserioResidencia;
    public Paciente(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                    String sexo, String numeroHistoriaClinica, String caserioResidencia) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo);
        this.numeroHistoriaClinica = numeroHistoriaClinica;
        this.caserioResidencia = caserioResidencia;
    }
    public String getNumeroHistoriaClinica() { return numeroHistoriaClinica; }
    public String getCaserio() { return caserioResidencia; }
    @Override
    public String obtenerRol() {
        return "PACIENTE";
    }
}