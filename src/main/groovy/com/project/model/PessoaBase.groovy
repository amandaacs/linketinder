package com.project.model

class PessoaBase implements Pessoa{

    String nome
    String email
    List<String> competencias = []

    String getNome() { nome }
    String getEmail() { email }
    List<String> getCompetencias() { competencias }

}
