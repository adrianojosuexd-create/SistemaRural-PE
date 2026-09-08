package modelo;
public class Medicamento {
    private String codigo;
    private String nombreGenerico;
    private int stockActual;
    private int stockMinimo;
    public Medicamento(String codigo, String nombreGenerico, int stockActual, int stockMinimo) {
        this.codigo = codigo;
        this.nombreGenerico = nombreGenerico;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }
    public String getCodigo() { return codigo; }
    public String getNombreGenerico() { return nombreGenerico; }
    public int getStockActual() { return stockActual; }
    public int getStockMinimo() { return stockMinimo; }

    public boolean verificarAlertaQuiebre() {
        return this.stockActual <= this.stockMinimo;
    }
}