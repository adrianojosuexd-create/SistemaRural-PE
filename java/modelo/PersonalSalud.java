package modelo;
import java.time.LocalDate;
public abstract class PersonalSalud extends Persona {
    protected String codigoColegiatura;
    protected String especialidad;
    protected String turnoAsignado;
    protected boolean activo;
    public PersonalSalud(String dni, String nombres, String apellidos, LocalDate fechaNacimiento,
                         String sexo, String codigoColegiatura, String especialidad, String turnoAsignado) {
        super(dni, nombres, apellidos, fechaNacimiento, sexo);
        this.codigoColegiatura = codigoColegiatura;
        this.especialidad = especialidad;
        this.turnoAsignado = turnoAsignado;
        this.activo = true;
    }
    public abstract String firmarAtencion(String idAtencion);
    public boolean validarDisponibilidad(LocalDate fecha, String turno) {
        return this.activo && this.turnoAsignado.equalsIgnoreCase(turno);
    }
    @Override
    public String obtenerRol() {
        return "PERSONAL_SALUD";
    }
    public String getCodigoColegiatura() {
        return codigoColegiatura;
    }
}