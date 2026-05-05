/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prj_hfsa_prov_recup1.menus;

import com.mycompany.prj_hfsa_prov_recup1.gerenciar_dados.Hfsa_ConstrutoraDAO;
import com.mycompany.prj_hfsa_prov_recup1.gerenciar_dados.Hfsa_ProjetoSalvaBO;
import com.mycompany.prj_hfsa_prov_recup1.modelos.Hfsa_Construtora;
import com.mycompany.prj_hfsa_prov_recup1.modelos.Hfsa_ProjComercial;
import com.mycompany.prj_hfsa_prov_recup1.modelos.Hfsa_ProjResidencial;
import com.mycompany.prj_hfsa_prov_recup1.modelos.Hfsa_Projeto;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author felip
 */
public class Hfsa_MenuPrincipal {
    
    private Hfsa_ConstrutoraDAO gerenciarDAO = new Hfsa_ConstrutoraDAO();
    private Hfsa_ProjetoSalvaBO gerenciarBO = new Hfsa_ProjetoSalvaBO();
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        int opcao = 0;
        do {
            System.out.println("\n=========================");
            System.out.println("1. Cadastrar construtora e Projetos");
            System.out.println("2. Listar todos projetos");
            System.out.println("3. Consultar projetos por CNPJ");
            System.out.println("4. Apagar todos os dados (Limpar TXT)"); // NOVA OPÇÃO
            System.out.println("5. Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um numero valido.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarEntidadeA(); break;
                case 2: listarTodasB(); break;
                case 3: consultarPorId(); break;
                case 4: apagarDados(); break; // CHAMA A FUNÇÃO DE APAGAR
                case 5: System.out.println("Encerrando o sistema..."); break;
                default: System.out.println("Opcao invalida!");
            }
        } while (opcao != 5);
        scanner.close();
    }

    private void cadastrarEntidadeA() {
        System.out.println("\n--- Cadastro Construtora ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cnpj: ");
        String cnpj = scanner.nextLine();

        Hfsa_Construtora con = new Hfsa_Construtora();
        con.setNome(nome);
        con.setCnpj(cnpj);
        gerenciarDAO.escreverArquivo(con);
        
        System.out.println("Construtora cadastrada!");
        cadastrarSubEntidades(cnpj);
    }

    private void cadastrarSubEntidades(String idEntidadeA) {
        String resposta;
        do {
            System.out.print("\nDeseja adicionar um projeto a esta construtora? (S/N): ");
            resposta = scanner.nextLine().toUpperCase();
            
            if (resposta.equals("S")) {
                System.out.print("Qual o tipo? (1 - Residencial | 2 - Comercial): ");
                String tipo = scanner.nextLine();
                
                System.out.print("Nome do projeto: ");
                String descricao = scanner.nextLine();
                
                System.out.print("Valor do projeto: ");
                double valor = Double.parseDouble(scanner.nextLine());
                
                System.out.print("Prazo em meses: ");
                int prazo = Integer.parseInt(scanner.nextLine());

                if (tipo.equals("1")) {
                    System.out.print("Quantidade de casas: ");
                    int quantidadeCasas = Integer.parseInt(scanner.nextLine());
                    
                    Hfsa_ProjResidencial sub1 = new Hfsa_ProjResidencial();
                    sub1.setNome(descricao);
                    sub1.setValor(valor);
                    sub1.setPrazoMeses(prazo);
                    sub1.setQuantidadeCasas(quantidadeCasas);
                    
                    gerenciarBO.escreverArquivo(sub1, idEntidadeA);
                    System.out.println("Projeto Residencial salvo!");

                } else if (tipo.equals("2")) {
                    System.out.print("Quantidade de salas: ");
                    int quantidadeSalas = Integer.parseInt(scanner.nextLine());
                    
                    Hfsa_ProjComercial sub2 = new Hfsa_ProjComercial();
                    sub2.setNome(descricao);
                    sub2.setValor(valor);
                    sub2.setPrazoMeses(prazo);
                    sub2.setQuantidadeSalas(quantidadeSalas);
                    
                    gerenciarBO.escreverArquivo(sub2, idEntidadeA);
                    System.out.println("Projeto Comercial salvo!");
                } else {
                    System.out.println("Tipo invalido. Tente novamente.");
                }
            }
        } while (resposta.equals("S"));
    }

    private void listarTodasB() {
        System.out.println("\n--- Listar TODOS os projetos ---");
        List<Hfsa_Projeto> todos = gerenciarBO.lerArquivo();
        
        if (todos.isEmpty()) {
            System.out.println("Nenhum projeto encontrado.");
            return;
        }
        for (Hfsa_Projeto item : todos) {
            System.out.println(item.toString());
        }
    }

    private void consultarPorId() {
        System.out.println("\n--- Consultar Projetos ---");
        System.out.print("Digite o CNPJ da Construtora: ");
        String id = scanner.nextLine();
        
        List<Hfsa_Projeto> itens = gerenciarBO.buscarPorIdEntidadeA(id);
        if (itens.isEmpty()) {
            System.out.println("Nenhum projeto encontrado para o CNPJ: " + id);
        } else {
            System.out.println("\n--- Projetos Encontrados (" + itens.size() + ") ---");
            for (Hfsa_Projeto item : itens) {
                System.out.println(item.toString());
            }
        }
    }

    //Limpa os arquivos de texto
    private void apagarDados() {
        System.out.println("\n--- Apagando base de dados ---");
        gerenciarDAO.apagarArquivo();
        gerenciarBO.apagarArquivo();
        System.out.println("Todos os dados foram resetados.");
    }
}
