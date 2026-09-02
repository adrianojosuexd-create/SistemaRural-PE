from datetime import date
from modelo.personal_salud import PersonalSalud
class Enfermero(PersonalSalud):
    def __init__(self, dni: str, nombres: str, apellidos: str, fecha_nac: date, sexo: str,
                 colegiatura: str, programa: str = "CRED"):
        super().__init__(dni, nombres, apellidos, fecha_nac, sexo, colegiatura, "ENFERMERIA", "TARDE")
        self.__programa = programa
    def firmar_atencion(self, id_atencion: str) -> str:
        return f"FIRMA_CEP_{self._codigo_colegiatura}_TRIAGE_{id_atencion}"
    def obtener_rol(self) -> str:
        return "ENFERMERO"