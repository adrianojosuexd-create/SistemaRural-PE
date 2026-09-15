import pytest
import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).resolve().parent.parent))
from datetime import date
from modelo.paciente import Paciente
from modelo.medicamento import Medicamento
from servicios.reportes_service import ReportesService
from excepciones.errores_clinicos import DniInvalidoException, StockInsuficienteException
def test_1_dni_longitud_invalida_lanza_excepcion():
    """Prueba 1: Valida rechazo de DNI con longitud menor a 8 dígitos."""
    with pytest.raises(DniInvalidoException):
        Paciente("12345", "Carlos", "Rios", date(1990, 1, 1), "M", "HC-999", "Santa Bárbara")
def test_2_enmascaramiento_dni_seguro():
    """Prueba 2: Cumplimiento de la Ley N.º 29733 (enmascaramiento)."""
    p = Paciente("45892147", "Juan", "Quispe", date(1985, 4, 12), "M", "HC-001", "Pariamarca Alta")
    assert p.get_dni_enmascarado() == "*****147"
    assert "45892147" not in p.get_dni_enmascarado()
def test_3_filtrado_por_caserio_inmutable():
    """Prueba 3: Filtrado funcional inmutable con filter()."""
    p1 = Paciente("45892147", "Juan", "Quispe", date(1985, 4, 12), "M", "HC-001", "Pariamarca Alta")
    p2 = Paciente("78451296", "Maria", "Cieza", date(2018, 9, 25), "F", "HC-002", "Pariamarca Baja")
    p3 = Paciente("12457896", "Rosa", "Alcantara", date(1960, 2, 10), "F", "HC-003", "Pariamarca Alta")
    lista = [p1, p2, p3]
    resultado = ReportesService.filtrar_por_caserio(lista, "Pariamarca Alta")
    assert len(resultado) == 2
    assert len(lista) == 3 
def test_4_consolidar_total_edades_reduce():
    """Prueba 4: Sumatoria acumulativa funcional con reduce()."""
    p1 = Paciente("45892147", "Juan", "Quispe", date(1985, 4, 12), "M", "HC-001", "Pariamarca Alta")
    p2 = Paciente("78451296", "Maria", "Cieza", date(2018, 9, 25), "F", "HC-002", "Pariamarca Baja")
    total = ReportesService.consolidar_total_edades([p1, p2])
    assert total == (p1.calcular_edad() + p2.calcular_edad())
def test_5_dni_con_caracteres_no_numericos():
    """Prueba 5: Rechazo obligatorio de DNI con letras o caracteres especiales."""
    with pytest.raises(DniInvalidoException):
        Paciente("4589A147", "Carlos", "Pérez", date(1995, 3, 10), "M", "HC-105", "Santa Bárbara")
def test_6_dispensacion_stock_insuficiente():
    """Prueba 6: Manejo de error cuando el despacho supera el stock de botiquín."""
    med = Medicamento("MED-01", "Paracetamol", "500mg", 8, stock_minimo=5)
    with pytest.raises(StockInsuficienteException):
        med.descontar_stock(12)
def test_7_alerta_quiebre_stock_valor_frontera():
    """Prueba 7: Alerta en valor límite exacto (stock_actual == stock_minimo)."""
    med = Medicamento("MED-02", "Amoxicilina", "250mg", 5, stock_minimo=5)
    assert med.verificar_alerta_quiebre() is True
def test_8_operaciones_funcionales_coleccion_vacia():
    """Prueba 8: Resiliencia ante listas vacías sin lanzar excepciones no controladas."""
    lista_vacia = []
    filtrados = ReportesService.filtrar_por_caserio(lista_vacia, "Pariamarca Alta")
    suma = ReportesService.consolidar_total_edades(lista_vacia)
    assert filtrados == []
    assert suma == 0