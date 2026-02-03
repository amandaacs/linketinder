package com.project.model

class Candidato extends PessoaBase{

    String cpf
    int idade
    String estado
    String cep
    String descricao

    @Override
    String toString(){
        """\
            Nome: $nome
            Email: $email
            CPF: $cpf
            Idade: $idade
            Estado: $estado
            CEP: $cep
            Descrição: $descricao
            Competências: ${competencias.join(', ')}
        """
    }

}
