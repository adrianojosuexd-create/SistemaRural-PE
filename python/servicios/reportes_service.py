from functools import reduce
from typing import List, Dict, Any
from modelo.paciente import Paciente
class ReportesService:
    @staticmethod
    def filtrar_por_caserio(pacientes: List[Paciente], caserio_target: str) -> List[Paciente]:
        return list(filter(lambda p: p.caserio.strip().lower() == caserio_target.strip().lower(), pacientes))
    @staticmethod
    def generar_reporte_anonimizado(pacientes: List[Paciente]) -> List[Dict[str, Any]]:
        return list(map(lambda p: {
            "historia_clinica": p.historia_clinica,
            "identificacion_protegida": p.get_dni_enmascarado(),
            "edad": p.calcular_edad(),
            "caserio": p.caserio
        }, pacientes))
    @staticmethod
    def consolidar_total_edades(pacientes: List[Paciente]) -> int:
        if not pacientes:
            return 0
        return reduce(lambda acumulador, p: acumulador + p.calcular_edad(), pacientes, 0)