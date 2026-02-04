package com.project.service

import com.project.model.Candidato
import com.project.model.Empresa
import com.project.repository.Database
import spock.lang.Specification

class CadstroServiceTest extends Specification {

    def "deve cadastrar um novo candidato"(){
        given: def candidato = new Candidato(
                nome: "Amanda",
                email: "amanda@email.com",
                cpf: "12345678900",
                idade: 29,
                estado: "CE",
                cep: "60000-000",
                descricao: "Backend developer",
                competencias: ["Java", "Groovy"]
        )

        when: "o cadastro é feito"
        CadstroService.cadastrarCandidato(candidato)

        then: "candidato deve aparecer na lista"
        Database.getCandidatos().last().nome === "Amanda"


    }

    def "deve cadastrar uma nova empresa"(){
        given: def empresa = new Empresa(
                nome: "Empresa Teste",
                email: "contato@empresa.com",
                cnpj: "65477686765654",
                pais: "Brasil",
                estado: "CE",
                cep: "60000-000",
                descricao: "Indústria alimentícia",
                competencias: ["Java", "Spring"]
        )

        when: "o cadastro é feito"
        CadstroService.cadastrarEmpresa(empresa)

        then: "empresa deve aparecer na lista"
        Database.getEmpresas().last().nome === "Empresa Teste"


    }

}
