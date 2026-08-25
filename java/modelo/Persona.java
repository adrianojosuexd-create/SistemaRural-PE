package modelo;
import excepciones.DniInvalidoException;
import java.time.LocalDate;
import java.time.Period;
public abstract class Persona {
    private final String dni;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String sexo;
    public Persona(String dni, String nombres, String apellidos, LocalDate fechaNacimiento, String sexo) {
        validarDni(dni);
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }
    private void validarDni(String dni) {
        if (dni == null || !dni.matches("^\\d{8}$")) {
            throw new DniInvalidoException("DNI inválido: debe contener exactamente 8 dígitos numéricos.");
        }
    }
    public String getDni() { 
        return this.dni; 
    }
    public String getNombres() { 
        return this.nombres; 
    }
    public String getApellidos() { 
        return this.apellidos; 
    }
    public LocalDate getFechaNacimiento() { 
        return this.fechaNacimiento; 
    }
    public String getSexo() { 
        return this.sexo; 
    }
    public String getNombreCompleto() { 
        return this.apellidos + ", " + this.nombres; 
    }
    public String getDniEnmascarado() {
        return "*****" + this.dni.substring(5);
    }
    public int calcularEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
    public void setNombres(String nombres) { 
        this.nombres = nombres; 
    }
    public void setApellidos(String apellidos) { 
        this.apellidos = apellidos; 
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { 
        this.fechaNacimiento = fechaNacimiento; 
    }
    public void setSexo(String sexo) { 
        this.sexo = sexo; 
    }
    public abstract String obtenerRol();
}