/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prj_hfsa_prov_recup1.gerenciar_dados;

import com.mycompany.prj_hfsa_prov_recup1.modelos.Hfsa_ProjComercial;
import com.mycompany.prj_hfsa_prov_recup1.modelos.Hfsa_ProjResidencial;
import com.mycompany.prj_hfsa_prov_recup1.modelos.Hfsa_Projeto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author felip
 */
public class Hfsa_ProjetoSalvaBO {
    
    private final String NOME_ARQUIVO = "C:\\Users\\felip\\OneDrive\\Área de Trabalho\\projetoprovProvR1.txt";

    public void escreverArquivo(Hfsa_Projeto objB, String cnpjConstrutora) {
        String texto = obj2Str(objB, cnpjConstrutora);
        try (FileWriter fw = new FileWriter(NOME_ARQUIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(texto);
        } catch (IOException ex) {
            System.err.println("Erro ao escrever no arquivo: " + ex.getMessage());
        }
    }

    public List<Hfsa_Projeto> lerArquivo() {
        List<Hfsa_Projeto> lista = new ArrayList<>();
        File f = new File(NOME_ARQUIVO);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    lista.add(str2Obj(linha));
                }
            }
        } catch (IOException ex) {
            System.err.println("Erro ao ler arquivo: " + ex.getMessage());
        }
        return lista;
    }

    public List<Hfsa_Projeto> buscarPorIdEntidadeA(String idEntidadeA) {
        List<Hfsa_Projeto> encontrados = new ArrayList<>();
        File f = new File(NOME_ARQUIVO);
        if (!f.exists()) return encontrados;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Filtra apenas as linhas que contém o CNPJ vinculado
                if (linha.contains("CnpjVinculado:" + idEntidadeA + ";")) {
                    encontrados.add(str2Obj(linha));
                }
            }
        } catch (IOException ex) {
            System.err.println("Erro ao buscar: " + ex.getMessage());
        }
        return encontrados;
    }

    //transforma o Objeto em texto para salvar no TXT
    private String obj2Str(Hfsa_Projeto objB, String cnpjConstrutora) {
        StringBuilder sb = new StringBuilder();
        sb.append("CnpjVinculado:").append(cnpjConstrutora).append(";");
        sb.append("Nome:").append(objB.getNome()).append(";");
        sb.append("Valor:").append(objB.getValor()).append(";");
        sb.append("PrazoMeses:").append(objB.getPrazoMeses()).append(";"); 

        if (objB instanceof Hfsa_ProjResidencial) {
            Hfsa_ProjResidencial sub1 = (Hfsa_ProjResidencial) objB;
            sb.append("Tipo:Residencial;");
            sb.append("QuantidadeCasas:").append(sub1.getQuantidadeCasas());
        } else if (objB instanceof Hfsa_ProjComercial) {
            Hfsa_ProjComercial sub2 = (Hfsa_ProjComercial) objB;
            sb.append("Tipo:Comercial;");
            sb.append("QuantidadeSalas:").append(sub2.getQuantidadeSalas());
        }
        return sb.toString();
    }

    //transforma o Texto lido do TXT de volta em Objeto
    private Hfsa_Projeto str2Obj(String texto) {
        Map<String, String> atributos = extraiAtributos(texto);
        
        String nome = atributos.get("Nome");
        double valor = Double.parseDouble(atributos.get("Valor"));
        int prazoMeses = Integer.parseInt(atributos.get("PrazoMeses"));
        String tipo = atributos.get("Tipo");

        if ("Residencial".equalsIgnoreCase(tipo)) {
            Hfsa_ProjResidencial sub1 = new Hfsa_ProjResidencial();
            sub1.setNome(nome);
            sub1.setValor(valor);
            sub1.setPrazoMeses(prazoMeses);
            sub1.setQuantidadeCasas(Integer.parseInt(atributos.get("QuantidadeCasas")));
            return sub1;
        } else if ("Comercial".equalsIgnoreCase(tipo)) {
            Hfsa_ProjComercial sub2 = new Hfsa_ProjComercial();
            sub2.setNome(nome);
            sub2.setValor(valor);
            sub2.setPrazoMeses(prazoMeses);
            sub2.setQuantidadeSalas(Integer.parseInt(atributos.get("QuantidadeSalas")));
            return sub2;
        }
        return null;
    }

    private Map<String, String> extraiAtributos(String texto) {
        Map<String, String> mapa = new HashMap<>();
        String[] partes = texto.split(";");
        for (String parte : partes) {
            if (parte.contains(":")) {
                String[] chaveValor = parte.split(":", 2);
                mapa.put(chaveValor[0].trim(), chaveValor[1].trim());
            }
        }
        return mapa;
    }

    // apagar o arquivo TXT
    public void apagarArquivo() {
        File f = new File(NOME_ARQUIVO);
        if (f.exists()) {
            f.delete();
            System.out.println("Arquivo de projetos apagado com sucesso.");
        }
    }
}
