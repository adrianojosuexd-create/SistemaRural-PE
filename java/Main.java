import modelo.PersonalSalud;
import modelo.PersonalSaludFactory;
import modelo.Paciente;
import servicios.ReporteSaludService;
import servicios.RepositorioSaludLocal;
import java.time.LocalDate;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  Iniciando SistemaRural-PE - P.S. Pariamarca    ");
        System.out.println("==================================================");
        System.out.println("\n--- Asignación con Factory Method ---");
        PersonalSalud doc = PersonalSaludFactory.crearProfesional(
            "MEDICO", "10203040", "Carlos", "Mendoza", 
            LocalDate.of(1980, 5, 10), "M", "CMP-54321", "RNE-9876"
        );
        PersonalSalud enf = PersonalSaludFactory.crearProfesional(
            "ENFERMERO", "20304050", "Ana", "Torres", 
            LocalDate.of(1988, 8, 22), "F", "CEP-12345", "CRED"
        );
        System.out.println("Profesional: " + doc.getNombreCompleto() + " | Rol: " + doc.obtenerRol());
        System.out.println("Firma: " + doc.firmarAtencion("AT-001"));
        System.out.println("Profesional: " + enf.getNombreCompleto() + " | Rol: " + enf.obtenerRol());
        System.out.println("Firma: " + enf.firmarAtencion("AT-002"));
        RepositorioSaludLocal repo = RepositorioSaludLocal.getInstancia();
        Paciente p1 = new Paciente("45892147", "Juan", "Quispe", LocalDate.of(1985, 4, 12), "M", "HC-001", "Pariamarca Alta");
        Paciente p2 = new Paciente("78451296", "Maria", "Cieza", LocalDate.of(2018, 9, 25), "F", "HC-002", "Pariamarca Baja");
        Paciente p3 = new Paciente("41235689", "Carlos", "Rojas", LocalDate.of(1972, 1, 10), "M", "HC-003", "Pariamarca Alta");
        repo.registrarPaciente(p1);
        repo.registrarPaciente(p2);
        repo.registrarPaciente(p3);
        ReporteSaludService servicio = new ReporteSaludService();
        List<Paciente> listaPacientes = repo.getPacientesInmutables();
        System.out.println("\n--- Reporte Sanitizado (Ley N.° 29733) ---");
        List<String> reporte = servicio.exportarReporteProtegido(listaPacientes);
        reporte.forEach(System.out::println);
        System.out.println("\n--- Pacientes en Pariamarca Alta ---");
        List<Paciente> alta = servicio.filtrarPorCaserio(listaPacientes, "Pariamarca Alta");
        alta.forEach(p -> System.out.println(p.getNombreCompleto() + " (" + p.getDniEnmascarado() + ")"));
        int sumaEdades = servicio.consolidarTotalEdades(listaPacientes);
        System.out.println("\nTotal acumulado de edades: " + sumaEdades + " años");
    }
}