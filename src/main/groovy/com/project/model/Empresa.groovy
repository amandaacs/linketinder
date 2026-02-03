package com.project.model

class Empresa extends PessoaBase{

    String cnpj
    String pais
    String estado
    String cep
    String descricao

    @Override
    String toString(){
        """\
            Empresa: $nome
            Email: $email
            CNPJ: $cnpj
            País: $pais
            Estado: $estado
            CEP: $cep
            Descrição: $descricao
            Competências desejadas: ${competencias.join(', ')}
        """
    }

}
