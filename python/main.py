from datetime import date
from modelo.paciente import Paciente
from modelo.personal_salud_factory import PersonalSaludFactory
from servicios.repositorio_singleton import RepositorioSaludLocal
from servicios.reportes_service import ReportesService
def main():
    print("==================================================")
    print("  Iniciando SistemaRural-PE (Python) - Pariamarca ")
    print("==================================================")
    repo1 = RepositorioSaludLocal()
    repo2 = RepositorioSaludLocal()
    print(f"Patrón Singleton verificado: ¿repo1 es repo2? -> {repo1 is repo2} (Misma instancia en memoria)")
    print("\n--- Asignación con Factory Method ---")
    doc = PersonalSaludFactory.crear_profesional(
        "MEDICO", "10203040", "Carlos", "Mendoza", 
        date(1980, 5, 10), "M", "CMP-54321", parametro_extra="RNE-9876"
    )
    enf = PersonalSaludFactory.crear_profesional(
        "ENFERMERO", "20304050", "Ana", "Torres", 
        date(1988, 8, 22), "F", "CEP-12345", parametro_extra="CRED"
    )
    print(f"Profesional: {doc.nombre_completo} | Rol: {doc.obtener_rol()}")
    print(f"Firma: {doc.firmar_atencion('AT-001')}")
    print(f"Profesional: {enf.nombre_completo} | Rol: {enf.obtener_rol()}")
    print(f"Firma: {enf.firmar_atencion('AT-002')}")
    p1 = Paciente("45892147", "Juan", "Quispe", date(1985, 4, 12), "M", "HC-001", "Pariamarca Alta")
    p2 = Paciente("78451296", "Maria", "Cieza", date(2018, 9, 25), "F", "HC-002", "Pariamarca Baja")
    p3 = Paciente("41235689", "Carlos", "Rojas", date(1972, 1, 10), "M", "HC-003", "Pariamarca Alta")
    repo1.registrar_paciente(p1)
    repo1.registrar_paciente(p2)
    repo1.registrar_paciente(p3)
    print("\n--- Reporte Sanitizado (Ley N.º 29733) ---")
    reporte = ReportesService.generar_reporte_anonimizado(repo1.obtener_todos())
    for r in reporte:
        print(f"Historia: {r['historia_clinica']} | DNI: {r['identificacion_protegida']} | Edad: {r['edad']} años | Sector: {r['caserio']}")
    print("\n--- Pacientes en Pariamarca Alta ---")
    alta = ReportesService.filtrar_por_caserio(repo1.obtener_todos(), "Pariamarca Alta")
    for p in alta:
        print(f"{p.nombre_completo} ({p.get_dni_enmascarado()})")
    total_edades = ReportesService.consolidar_total_edades(repo1.obtener_todos())
    print(f"\nTotal acumulado de edades: {total_edades} años")
if __name__ == "__main__":
    main()