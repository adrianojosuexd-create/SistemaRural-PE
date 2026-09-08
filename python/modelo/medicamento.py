from excepciones.errores_clinicos import StockInsuficienteException
class Medicamento:
    def __init__(self, codigo: str, nombre_generico: str, presentacion: str, stock_actual: int, stock_minimo: int = 10):
        self.__codigo = codigo
        self.__nombre_generico = nombre_generico
        self.__presentacion = presentacion
        self.__stock_actual = stock_actual
        self.__stock_minimo = stock_minimo
    @property
    def stock(self) -> int:
        return self.__stock_actual
    def descontar_stock(self, cantidad: int) -> bool:
        if cantidad > self.__stock_actual:
            raise StockInsuficienteException(f"Stock insuficiente para {self.__nombre_generico}.")
        self.__stock_actual -= cantidad
        return True
    def verificar_alerta_quiebre(self) -> bool:
        return self.__stock_actual <= self.__stock_minimo