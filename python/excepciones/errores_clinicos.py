class ErrorClinicoException(Exception):
    """Excepción base para violaciones de reglas de negocio clínico."""
    pass
class DniInvalidoException(ErrorClinicoException):
    """Lanzada cuando el DNI no cumple con los 8 dígitos numéricos peruanos."""
    pass
class StockInsuficienteException(ErrorClinicoException):
    """Lanzada cuando se intenta dispensar más unidades de las disponibles en botiquín."""
    pass