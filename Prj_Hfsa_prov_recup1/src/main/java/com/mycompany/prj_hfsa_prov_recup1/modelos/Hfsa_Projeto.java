/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prj_hfsa_prov_recup1.modelos;

/**
 *
 * @author felip
 */
// ENTIDADE B (Base/Superclasse):
public class Hfsa_Projeto {
    
    
    private String nome;
    private double valor;
    private int prazoMeses;
    private Hfsa_Construtora contru;

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(int prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public Hfsa_Construtora getContru() {
        return contru;
    }

    public void setContru(Hfsa_Construtora contru) {
        this.contru = contru;
    }

    
}
