from datetime import date
from modelo.persona import Persona
class Paciente(Persona):
    def __init__(self, dni: str, nombres: str, apellidos: str, fecha_nacimiento: date, sexo: str,
                 numero_historia_clinica: str, caserio_residencia: str):
        super().__init__(dni, nombres, apellidos, fecha_nacimiento, sexo)
        self.__numero_historia_clinica = numero_historia_clinica
        self.__caserio_residencia = caserio_residencia
    @property
    def historia_clinica(self) -> str:
        return self.__numero_historia_clinica
    @property
    def caserio(self) -> str:
        return self.__caserio_residencia
    def obtener_rol(self) -> str:
        return "PACIENTE"