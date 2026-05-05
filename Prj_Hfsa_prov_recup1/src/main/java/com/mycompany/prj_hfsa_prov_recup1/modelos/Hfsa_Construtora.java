/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prj_hfsa_prov_recup1.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */
// ENTIDADE A (Principal):
public class Hfsa_Construtora {
    
    private String cnpj; // prova pode ser CPF, ID, Código
    private String nome;
    
    // Relação 1:N - Uma construtora tem vários projetos adpt
    private List<Hfsa_Projeto> projetos = new ArrayList<>();


//    Getters e Setters

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Hfsa_Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Hfsa_Projeto> projetos) {
        this.projetos = projetos;
    }
    
    
}

