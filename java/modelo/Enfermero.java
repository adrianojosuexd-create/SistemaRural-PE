package modelo;
import java.time.LocalDate;
public class Enfermero extends PersonalSalud {
    private String programaAsignado;
    public Enfermero(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                     String sexo, String codigoColegiatura, String programaAsignado) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, codigoColegiatura, "ENFERMERIA", "TARDE");
        this.programaAsignado = programaAsignado;
    }
    @Override
    public String firmarAtencion(String idAtencion) {
        return "FIRMA_CEP_" + this.codigoColegiatura + "_TRIAGE_" + idAtencion;
    }
    @Override
    public String obtenerRol() {
        return "ENFERMERO";
    }
    public String getProgramaAsignado() {
        return programaAsignado;
    }
}