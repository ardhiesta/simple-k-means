/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kme.ans.object;

/**
 *
 * @author linuxluv
 */
public class Negara {
    private String nama;
    private int cbr;
    private int cdr;
    int cluster;
    
    //constructor
    public Negara(){}
    public Negara(String nama, int cbr, int cdr){
        this.nama = nama;
        this.cbr = cbr;
        this.cdr = cdr;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the cbr
     */
    public int getCbr() {
        return cbr;
    }

    /**
     * @param cbr the cbr to set
     */
    public void setCbr(int cbr) {
        this.cbr = cbr;
    }

    /**
     * @return the cdr
     */
    public int getCdr() {
        return cdr;
    }

    /**
     * @param cdr the cdr to set
     */
    public void setCdr(int cdr) {
        this.cdr = cdr;
    }
    
}
