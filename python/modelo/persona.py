from abc import ABC, abstractmethod
from datetime import date
import re
from excepciones.errores_clinicos import DniInvalidoException
class Persona(ABC):
    def __init__(self, dni: str, nombres: str, apellidos: str, fecha_nacimiento: date, sexo: str):
        self.__validar_dni(dni)
        self.__dni = dni
        self.__nombres = nombres
        self.__apellidos = apellidos
        self.__fecha_nacimiento = fecha_nacimiento
        self.__sexo = sexo
    def __validar_dni(self, dni: str) -> None:
        if not re.match(r"^\d{8}$", str(dni)):
            raise DniInvalidoException(f"El DNI '{dni}' debe contener exactamente 8 dígitos numéricos.")
    @property
    def dni(self) -> str:
        return self.__dni
    @property
    def nombre_completo(self) -> str:
        return f"{self.__apellidos}, {self.__nombres}"
    def get_dni_enmascarado(self) -> str:
        return f"*****{self.__dni[-3:]}"
    def calcular_edad(self) -> int:
        hoy = date.today()
        edad = hoy.year - self.__fecha_nacimiento.year
        if (hoy.month, hoy.day) < (self.__fecha_nacimiento.month, self.__fecha_nacimiento.day):
            edad -= 1
        return edad
    @abstractmethod
    def obtener_rol(self) -> str:
        pass