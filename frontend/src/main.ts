import { CandidatoController } from "./controllers/candidato.controller";
import { EmpresaController } from "./controllers/empresa.controller";
import { VagaController } from "./controllers/vaga.controller";

const path = window.location.pathname;

if (path.includes("cadastro-empresa.html")){
    new EmpresaController();
} else if (path.includes("cadastro-candidato.html")){
    new CandidatoController();
} else if (path.includes("empresa/vagas.html")){
    new VagaController();
} else if (path.includes("empresa/perfil.html")){
    const empresaCtrl = new EmpresaController();
    if(typeof empresaCtrl.initDashboard === 'function'){
        empresaCtrl.initDashboard();
    } else {
        console.warn("Erro ao iniciar dashboard");
    }    
} else if (path.includes("candidato/perfil.html")){
    const candidatoCtrl = new CandidatoController();
    candidatoCtrl.renderPerfil();
} else if (path.includes("candidato/vagas.html")){
    const vagaCtrl = new VagaController();
    if(typeof vagaCtrl.listarVagasDisponiveis === 'function'){
        vagaCtrl.listarVagasDisponiveis();
    }
} else {
    console.warn("Nenhum controller configurado para essa página");
}