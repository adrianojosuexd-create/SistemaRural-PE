package servicios;
import modelo.Paciente;
import java.util.List;
import java.util.stream.Collectors;
public class ReporteSaludService {
    public List<Paciente> filtrarPorCaserio(List<Paciente> pacientes, String caserio) {
        return pacientes.stream()
                .filter(p -> p.getCaserio().equalsIgnoreCase(caserio))
                .collect(Collectors.toList());
    }
    public List<String> exportarReporteProtegido(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(p -> String.format("HC: %s | ID: %s | Edad: %d años | Sector: %s",
                        p.getNumeroHistoriaClinica(),
                        p.getDniEnmascarado(),
                        p.calcularEdad(),
                        p.getCaserio()))
                .collect(Collectors.toList());
    }
    public int consolidarTotalEdades(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(Paciente::calcularEdad)
                .reduce(0, Integer::sum);
    }
}