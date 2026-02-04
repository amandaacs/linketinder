package com.project.service

import com.project.model.Candidato
import com.project.model.Empresa
import com.project.repository.Database

class CadstroService {

    static void cadastrarCandidato(Candidato candidato){
        Database.addCandidato(candidato)
    }

    static void  cadastrarEmpresa(Empresa empresa){
        Database.addEmpresa(empresa)
    }


}
