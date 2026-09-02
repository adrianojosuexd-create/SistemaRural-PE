package modelo;
import java.time.LocalDate;
public class Medico extends PersonalSalud {
    private String rne;
    public Medico(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                  String sexo, String codigoColegiatura, String rne) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo, codigoColegiatura, "MEDICINA_GENERAL", "MAÑANA");
        this.rne = rne;
    }
    @Override
    public String firmarAtencion(String idAtencion) {
        return "FIRMA_CMP_" + this.codigoColegiatura + "_ATENCION_" + idAtencion;
    }
    @Override
    public String obtenerRol() {
        return "MEDICO";
    }
    public String getRne() {
        return rne;
    }
}