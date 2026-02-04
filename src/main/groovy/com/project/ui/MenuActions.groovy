package com.project.ui

import com.project.model.Candidato
import com.project.model.Empresa
import com.project.repository.Database
import com.project.service.CadstroService

class MenuActions {

    static Scanner scanner = new Scanner(System.in)

    static void handle(int opt){
        switch (opt){
            case 1 -> listCandidatos()
            case 2 -> listEmpresas()
            case 3 -> addCandidato()
            case 4 -> addEmpresa()
            case 0 -> sair()
            default -> println "Opção inválida"
        }
    }


    static List<Candidato> listCandidatos() {
        def candidatos = Database.getCandidatos();

        if(candidatos.isEmpty()){
            println "Nenhum candidato cadastrado"
            return
        }

        candidatos.eachWithIndex{ c, i ->
            println "---- Candidato ${i + 1} ----"
            println c
        }
    }

    static List<Empresa> listEmpresas() {
        def empresas = Database.getEmpresas();

        if(empresas.isEmpty()){
            println "Nenhuma empresa cadastrada"
            return
        }

        empresas.eachWithIndex{ e, i ->
            println "---- Empresa ${i + 1} ----"
            println e
        }

    }

    static void addCandidato() {

        println "\nCadastro de Candidato"

        print "Nome: "
        String nome = scanner.nextLine()

        print "Email: "
        String email = scanner.nextLine()

        print "CPF: "
        String cpf = scanner.nextLine()

        print "Idade: "
        int idade = scanner.nextInt()
        scanner.nextLine()

        print "Estado: "
        String estado = scanner.nextLine()

        print "CEP: "
        String cep = scanner.nextLine()

        print "Descrição pessoal: "
        String descricao = scanner.nextLine()

        print "Competências (separadas por virgula): "
        List<String> competencias = scanner.nextLine()
            .split(",")
            .collect{ it.trim() }


        def candidato = new Candidato(
                nome: nome,
                email: email,
                cpf: cpf,
                idade: idade,
                estado: estado,
                cep: cep,
                descricao: descricao,
                competencias: competencias
        )

        CadstroService.cadastrarCandidato(candidato)

        println "Candidato cadastrado com sucesso!"

    }

    static void addEmpresa() {

        println "\nCadastro de Empresa"

        print "Nome da empresa: "
        String nome = scanner.nextLine()

        print "Email corporativo: "
        String email = scanner.nextLine()

        print "CNPJ: "
        String cnpj = scanner.nextLine()

        print "País: "
        String pais = scanner.nextLine()

        print "Estado: "
        String estado = scanner.nextLine()

        print "CEP: "
        String cep = scanner.nextLine()

        print "Descrição da empresa: "
        String descricao = scanner.nextLine()

        print "Competências desejadas(separadas por virgula): "
        List<String> competencias = scanner.nextLine()
                .split(",")
                .collect{ it.trim() }


        def empresa = new Empresa(
                nome: nome,
                email: email,
                cnpj: cnpj,
                pais: pais,
                estado: estado,
                cep: cep,
                descricao: descricao,
                competencias: competencias
        )

        CadstroService.cadastrarEmpresa(empresa)

        println "Empresa cadastrada com sucesso!"

    }

    static void sair() {

        println "Encerrando aplicação"
        System.exit(0)
    }
}
