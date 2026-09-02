from abc import abstractmethod
from datetime import date
from modelo.persona import Persona
class PersonalSalud(Persona):
    def __init__(self, dni: str, nombres: str, apellidos: str, fecha_nac: date, sexo: str, 
                 colegiatura: str, especialidad: str, turno: str):
        super().__init__(dni, nombres, apellidos, fecha_nac, sexo)
        self._codigo_colegiatura = colegiatura
        self._especialidad = especialidad
        self._turno_asignado = turno
        self._activo = True
    @abstractmethod
    def firmar_atencion(self, id_atencion: str) -> str:
        pass
    def validar_disponibilidad(self, fecha: date, turno: str) -> bool:
        return self._activo and (self._turno_asignado.upper() == turno.upper())
    def obtener_rol(self) -> str:
        return "PERSONAL_SALUD"