package com.project.model

class Vaga {

    int id
    String nome
    String descricao
    String local
    int empresaId
    List<String> competencias

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    String getLocal() {
        return local
    }

    void setLocal(String local) {
        this.local = local
    }

    int getEmpresaId() {
        return empresaId
    }

    void setEmpresaId(int empresaId) {
        this.empresaId = empresaId
    }

    List<String> getCompetencias() {
        return competencias
    }

    void setCompetencias(List<String> competencias) {
        this.competencias = competencias
    }
}
