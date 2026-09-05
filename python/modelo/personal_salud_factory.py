from datetime import date
from modelo.medico import Medico
from modelo.enfermero import Enfermero
from modelo.personal_salud import PersonalSalud

class PersonalSaludFactory:
    @staticmethod
    def crear_profesional(rol: str, dni: str, nombres: str, apellidos: str, 
                          fecha_nac: date, sexo: str, colegiatura: str, parametro_extra: str = "") -> PersonalSalud:
        rol_limpio = rol.strip().upper() if rol else ""
        if rol_limpio == "MEDICO":
            return Medico(dni, nombres, apellidos, fecha_nac, sexo, colegiatura, rne=parametro_extra)
        elif rol_limpio == "ENFERMERO":
            prog = parametro_extra if parametro_extra else "CRED"
            return Enfermero(dni, nombres, apellidos, fecha_nac, sexo, colegiatura, programa=prog)
        else:
            raise ValueError(f"Rol profesional no reconocido: {rol}")