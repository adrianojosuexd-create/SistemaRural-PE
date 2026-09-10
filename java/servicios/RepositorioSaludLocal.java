package servicios;
import modelo.Paciente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class RepositorioSaludLocal {
    private static RepositorioSaludLocal instancia;
    private final List<Paciente> bancoPacientes;
    private RepositorioSaludLocal() {
        this.bancoPacientes = new ArrayList<>();
    }
    public static synchronized RepositorioSaludLocal getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioSaludLocal();
        }
        return instancia;
    }
    public void registrarPaciente(Paciente p) {
        this.bancoPacientes.add(p);
    }
    public List<Paciente> getPacientesInmutables() {
        return Collections.unmodifiableList(this.bancoPacientes);
    }
}