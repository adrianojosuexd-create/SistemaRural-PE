package modelo;
import java.time.LocalDate;
public class PersonalSaludFactory {
    public static PersonalSalud crearProfesional(String rol, String dni, String nombres, 
                                                 String apellidos, LocalDate fechaNac, 
                                                 String sexo, String colegiatura, String parametroExtra) {
        if (rol == null) {
            throw new IllegalArgumentException("El rol profesional no puede ser nulo.");
        }
        switch (rol.toUpperCase().trim()) {
            case "MEDICO":
                return new Medico(dni, nombres, apellidos, fechaNac, sexo, colegiatura, parametroExtra);
            case "ENFERMERO":
                String programa = (parametroExtra == null || parametroExtra.trim().isEmpty()) ? "CRED" : parametroExtra;
                return new Enfermero(dni, nombres, apellidos, fechaNac, sexo, colegiatura, programa);
            default:
                throw new IllegalArgumentException("Rol profesional no soportado: " + rol);
        }
    }
}