/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prj_hfsa_prov_recup1.modelos;

/**
 *
 * @author felip
 */
// SUBENTIDADE B1:
public class Hfsa_ProjResidencial extends Hfsa_Projeto{
    
    private int quantidadeCasas; // Atributo exclusivo deste subtipo

    public int getQuantidadeCasas() {
        return quantidadeCasas;
    }

    public void setQuantidadeCasas(int quantidadeCasas) {
        this.quantidadeCasas = quantidadeCasas;
    }
    

    @Override
    public String toString() {
        return String.format("RESIDENCIAL | Nome: %s | Valor: R$ %.2f | Praso em Meses: %d| Quantidade de casas: %d",
               getNome(), getValor(), getPrazoMeses(), quantidadeCasas);
    }
    
}

