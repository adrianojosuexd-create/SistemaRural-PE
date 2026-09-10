from typing import List
from modelo.paciente import Paciente
class RepositorioSaludLocal:
    _instancia = None
    def __new__(cls):
        if cls._instancia is None:
            cls._instancia = super(RepositorioSaludLocal, cls).__new__(cls)
            cls._instancia.__pacientes = []
        return cls._instancia
    def registrar_paciente(self, paciente: Paciente) -> None:
        self.__pacientes.append(paciente)
    def obtener_todos(self) -> List[Paciente]:
        return list(self.__pacientes)