/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prj_hfsa_prov_recup1.modelos;

/**
 *
 * @author felip
 */
// SUBENTIDADE B2
public class Hfsa_ProjComercial extends Hfsa_Projeto{
    
    private int quantidadeSalas; // Atributo exclusivo deste subtipo

    public int getQuantidadeSalas() {
        return quantidadeSalas;
    }

    public void setQuantidadeSalas(int quantidadeSalas) {
        this.quantidadeSalas = quantidadeSalas;
    }

    @Override
    public String toString() {
        return String.format("COMERCIAL | Nome: %s | Valor: R$ %.2f | Prazo (Meses): %d "
                + "| Qtrd Salas: %d", getNome(), getValor(), getPrazoMeses(), quantidadeSalas);
    }
       
}
