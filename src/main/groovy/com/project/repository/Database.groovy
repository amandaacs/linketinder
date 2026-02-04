package com.project.repository

import com.project.model.Candidato
import com.project.model.Empresa

class Database {

    static List<Candidato> candidatos = [
            new Candidato(
                    nome: "Ana Silva",
                    email: "ana.silva@email.com",
                    cpf: "11111111111",
                    idade: 25,
                    estado: "SP",
                    cep: "01000-000",
                    descricao: "Desenvolvedora backend com foco em Java",
                    competencias: ["Java", "Spring", "SQL"]
            ),

            new Candidato(
                    nome: "Bruno Costa",
                    email: "bruno.costa@email.com",
                    cpf: "22222222222",
                    idade: 28,
                    estado: "RJ",
                    cep: "20000-000",
                    descricao: "Desenvolvedor fullstack",
                    competencias: ["JavaScript", "Angular", "Node.js"]
            ),

            new Candidato(
                    nome: "Carla Mendes",
                    email: "carla.mendes@email.com",
                    cpf: "33333333333",
                    idade: 32,
                    estado: "MG",
                    cep: "30000-000",
                    descricao: "Engenheira de software",
                    competencias: ["Python", "Django", "PostgreSQL"]
            ),

            new Candidato(
                    nome: "Diego Rocha",
                    email: "diego.rocha@email.com",
                    cpf: "44444444444",
                    idade: 24,
                    estado: "RS",
                    cep: "90000-000",
                    descricao: "Desenvolvedor frontend",
                    competencias: ["HTML", "CSS", "React"]
            ),

            new Candidato(
                    nome: "Eduarda Lima",
                    email: "eduarda.lima@email.com",
                    cpf: "55555555555",
                    idade: 29,
                    estado: "BA",
                    cep: "40000-000",
                    descricao: "Analista de sistemas",
                    competencias: ["Java", "Spring", "Docker"]
            )

    ]
    static List<Empresa> empresas = [
            new Empresa(
                    nome: "Arroz-Gostoso",
                    email: "rh@arrozgostoso.com",
                    cnpj: "00000000000100",
                    pais: "Brasil",
                    estado: "SP",
                    cep: "01010-000",
                    descricao: "Indústria alimentícia especializada em arroz",
                    competencias: ["Java", "Spring"]
            ),

            new Empresa(
                    nome: "Império do Boliche",
                    email: "contato@imperiodoboliche.com",
                    cnpj: "00000000000200",
                    pais: "Brasil",
                    estado: "RJ",
                    cep: "22000-000",
                    descricao: "Rede de entretenimento e lazer",
                    competencias: ["JavaScript", "React"]
            ),

            new Empresa(
                    nome: "TechNova",
                    email: "talentos@technova.com",
                    cnpj: "00000000000300",
                    pais: "Brasil",
                    estado: "MG",
                    cep: "30100-000",
                    descricao: "Startup focada em soluções digitais",
                    competencias: ["Python", "Django"]
            ),

            new Empresa(
                    nome: "DataPlus",
                    email: "rh@dataplus.com",
                    cnpj: "00000000000400",
                    pais: "Brasil",
                    estado: "RS",
                    cep: "91000-000",
                    descricao: "Empresa especializada em dados e analytics",
                    competencias: ["SQL", "Python"]
            ),

            new Empresa(
                    nome: "CloudWay",
                    email: "jobs@cloudway.com",
                    cnpj: "00000000000500",
                    pais: "Brasil",
                    estado: "SP",
                    cep: "04500-000",
                    descricao: "Soluções em cloud e infraestrutura",
                    competencias: ["Docker", "Java", "Spring"]
            )
    ]

    static List<Candidato> getCandidatos() {
        candidatos
    }

    static List<Empresa> getEmpresas() {
        empresas
    }

    static void addCandidato(Candidato candidato) {
        candidatos.add(candidato)
    }

    static void addEmpresa(Empresa empresa) {
        empresas.add(empresa)
    }

}
