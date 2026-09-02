from datetime import date
from modelo.personal_salud import PersonalSalud
class Medico(PersonalSalud):
    def __init__(self, dni: str, nombres: str, apellidos: str, fecha_nac: date, sexo: str,
                 colegiatura: str, rne: str = ""):
        super().__init__(dni, nombres, apellidos, fecha_nac, sexo, colegiatura, "MEDICINA_GENERAL", "MAÑANA")
        self.__rne = rne
    def firmar_atencion(self, id_atencion: str) -> str:
        return f"FIRMA_CMP_{self._codigo_colegiatura}_ATENCION_{id_atencion}"
    def obtener_rol(self) -> str:
        return "MEDICO"