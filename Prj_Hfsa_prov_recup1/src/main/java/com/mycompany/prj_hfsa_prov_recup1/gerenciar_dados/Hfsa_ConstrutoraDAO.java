/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prj_hfsa_prov_recup1.gerenciar_dados;

import com.mycompany.prj_hfsa_prov_recup1.modelos.Hfsa_Construtora;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */
public class Hfsa_ConstrutoraDAO {
    
    private final String NOME_ARQUIVO = "C:\\Users\\felip\\OneDrive\\Área de Trabalho\\construtoraProvR1.txt";

    public void escreverArquivo(Hfsa_Construtora obj) {
        String texto = "Cnpj:" + obj.getCnpj() + ";Nome:" + obj.getNome();
        try (FileWriter fw = new FileWriter(NOME_ARQUIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(texto);
        } catch (IOException ex) {
            System.err.println("Erro ao escrever arquivo: " + ex.getMessage());
        }
    }

    public List<Hfsa_Construtora> lerArquivo() {
        List<Hfsa_Construtora> lista = new ArrayList<>();
        File f = new File(NOME_ARQUIVO);
        if (!f.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains("Cnpj:")) {
                    String[] partes = linha.split(";");
                    String cnpj = partes[0].split(":")[1].trim();
                    String nome = partes[1].split(":")[1].trim();
                    
                    Hfsa_Construtora obj = new Hfsa_Construtora();
                    obj.setCnpj(cnpj);
                    obj.setNome(nome);
                    lista.add(obj);
                }
            }
        } catch (IOException ex) {
            System.err.println("Erro ao ler arquivo: " + ex.getMessage());
        }
        return lista;
    }

    //Apagar o arquivo TXT
    public void apagarArquivo() {
        File f = new File(NOME_ARQUIVO);
        if (f.exists()) {
            f.delete();
            System.out.println("Arquivo de construtoras apagado com sucesso.");
        }
    }
}
