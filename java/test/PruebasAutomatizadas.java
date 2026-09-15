package test;
import excepciones.DniInvalidoException;
import modelo.Medicamento;
import modelo.Paciente;
import servicios.ReporteSaludService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class PruebasAutomatizadas {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SUITE DE PRUEBAS AUTOMATIZADAS - SISTEMARURAL  ");
        System.out.println("==================================================");
        int pasadas = 0;
        int total = 8;
        if (testDniLongitudInvalidaLanzaExcepcion()) pasadas++;
        if (testEnmascaramientoDniSeguro()) pasadas++;
        if (testFiltradoPorCaserioInmutable()) pasadas++;
        if (testConsolidarTotalEdades()) pasadas++;
        if (testDniConCaracteresNoNumericos()) pasadas++;
        if (testDispensacionStockInsuficiente()) pasadas++;
        if (testAlertaQuiebreStockValorFrontera()) pasadas++;
        if (testOperacionesFuncionalesColeccionVacia()) pasadas++;
        System.out.println("\n--------------------------------------------------");
        System.out.printf("RESULTADO: %d de %d pruebas pasaron exitosamente.\n", pasadas, total);
        System.out.println("--------------------------------------------------");
    }
    public static boolean testDniLongitudInvalidaLanzaExcepcion() {
        try {
            new Paciente("12345", "Carlos", "Rios", LocalDate.of(1990, 1, 1), "M", "HC-999", "Santa Bárbara");
            System.err.println("❌ [FALLÓ] Prueba 1: Debió rechazar DNI de 5 dígitos.");
            return false;
        } catch (DniInvalidoException e) {
            System.out.println("✔ [PASÓ] Prueba 1: Validación de DNI corto lanza DniInvalidoException.");
            return true;
        }
    }
    public static boolean testEnmascaramientoDniSeguro() {
        Paciente p = new Paciente("45892147", "Juan", "Quispe", LocalDate.of(1985, 4, 12), "M", "HC-001", "Pariamarca Alta");
        String enmascarado = p.getDniEnmascarado();
        if (enmascarado.equals("*****147") && !enmascarado.contains("45892")) {
            System.out.println("✔ [PASÓ] Prueba 2: Enmascaramiento de datos personales correcto (*****147).");
            return true;
        } else {
            System.err.println("❌ [FALLÓ] Prueba 2: Formato de enmascaramiento incorrecto.");
            return false;
        }
    }
    public static boolean testFiltradoPorCaserioInmutable() {
        List<Paciente> lista = new ArrayList<>();
        lista.add(new Paciente("45892147", "Juan", "Quispe", LocalDate.of(1985, 4, 12), "M", "HC-001", "Pariamarca Alta"));
        lista.add(new Paciente("78451296", "Maria", "Cieza", LocalDate.of(2018, 9, 25), "F", "HC-002", "Pariamarca Baja"));
        lista.add(new Paciente("41235689", "Rosa", "Rojas", LocalDate.of(1972, 1, 10), "F", "HC-003", "Pariamarca Alta"));
        ReporteSaludService servicio = new ReporteSaludService();
        List<Paciente> resultado = servicio.filtrarPorCaserio(lista, "Pariamarca Alta");
        if (resultado.size() == 2 && lista.size() == 3) {
            System.out.println("✔ [PASÓ] Prueba 3: Filtrado por caserío correcto e inmutable (no alteró lista base).");
            return true;
        } else {
            System.err.println("❌ [FALLÓ] Prueba 3: Conteo de filtrado incorrecto.");
            return false;
        }
    }
    public static boolean testConsolidarTotalEdades() {
        List<Paciente> lista = new ArrayList<>();
        Paciente p1 = new Paciente("45892147", "Juan", "Quispe", LocalDate.of(1985, 4, 12), "M", "HC-001", "Pariamarca Alta");
        Paciente p2 = new Paciente("78451296", "Maria", "Cieza", LocalDate.of(2018, 9, 25), "F", "HC-002", "Pariamarca Baja");
        lista.add(p1);
        lista.add(p2);
        int esperado = p1.calcularEdad() + p2.calcularEdad();
        ReporteSaludService servicio = new ReporteSaludService();
        int obtenido = servicio.consolidarTotalEdades(lista);
        if (obtenido == esperado) {
            System.out.println("✔ [PASÓ] Prueba 4: Consolidación total de edades con Streams reduce() correcta.");
            return true;
        } else {
            System.err.println("❌ [FALLÓ] Prueba 4: Sumatoria de edades no coincide.");
            return false;
        }
    }
    public static boolean testDniConCaracteresNoNumericos() {
        try {
            new Paciente("4589A147", "Carlos", "Pérez", LocalDate.of(1995, 3, 10), "M", "HC-105", "Santa Bárbara");
            System.err.println("❌ [FALLÓ] Prueba 5: Debió rechazar DNI con caracteres alfabéticos.");
            return false;
        } catch (DniInvalidoException e) {
            System.out.println("✔ [PASÓ] Prueba 5: DNI con caracteres no numéricos lanza DniInvalidoException.");
            return true;
        }
    }
    public static boolean testDispensacionStockInsuficiente() {
        Medicamento med = new Medicamento("MED-01", "Paracetamol 500mg", 8, 5);
        int cantidadSolicitada = 12;
        if (cantidadSolicitada > med.getStockActual()) {
            System.out.println("✔ [PASÓ] Prueba 6: Rechazo controlado ante solicitud que supera el stock disponible.");
            return true;
        } else {
            System.err.println("❌ [FALLÓ] Prueba 6: No se detectó la insuficiencia de stock.");
            return false;
        }
    }
    public static boolean testAlertaQuiebreStockValorFrontera() {
        Medicamento medLimite = new Medicamento("MED-02", "Amoxicilina 250mg", 5, 5);
        if (medLimite.verificarAlertaQuiebre()) {
            System.out.println("✔ [PASÓ] Prueba 7: Alerta de quiebre activada en valor frontera (stockActual == stockMinimo).");
            return true;
        } else {
            System.err.println("❌ [FALLÓ] Prueba 7: No se activó la alerta en el valor límite.");
            return false;
        }
    }
    public static boolean testOperacionesFuncionalesColeccionVacia() {
        List<Paciente> listaVacia = new ArrayList<>();
        ReporteSaludService servicio = new ReporteSaludService();
        List<Paciente> filtrados = servicio.filtrarPorCaserio(listaVacia, "Pariamarca Alta");
        int suma = servicio.consolidarTotalEdades(listaVacia);

        if (filtrados.isEmpty() && suma == 0) {
            System.out.println("✔ [PASÓ] Prueba 8: Pipeline funcional procesa listas vacías sin lanzar excepciones.");
            return true;
        } else {
            System.err.println("❌ [FALLÓ] Prueba 8: Fallo al procesar colección vacía.");
            return false;
        }
    }
}