/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author erikssonherlo
 */
public class LimiteReportes {
    private double limiteReporte2;
    private double limiteReporte3;
    private int codigoGerente;

    public LimiteReportes(double limiteReporte2, double limiteReporte3, int codigoGerente) {
        this.limiteReporte2 = limiteReporte2;
        this.limiteReporte3 = limiteReporte3;
        this.codigoGerente = codigoGerente;
    }

    public void setLimiteReporte2(double limiteReporte2) {
        this.limiteReporte2 = limiteReporte2;
    }

    public void setLimiteReporte3(double limiteReporte3) {
        this.limiteReporte3 = limiteReporte3;
    }

    public void setCodigoGerente(int codigoGerente) {
        this.codigoGerente = codigoGerente;
    }

    public double getLimiteReporte2() {
        return limiteReporte2;
    }

    public double getLimiteReporte3() {
        return limiteReporte3;
    }

    public int getCodigoGerente() {
        return codigoGerente;
    }
    
    
}
