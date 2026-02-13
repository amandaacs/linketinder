import { Candidato } from "../models/candidato";
import { CandidatoService } from "../services/candidatoService";


export class CandidatoController{

    private service: CandidatoService;

    constructor(){
        this.service = new CandidatoService();
        this.configurarForm();
    }

    private configurarForm(){
        const form = document.getElementById("form-candidato") as HTMLFormElement;

        if(form){
            form.addEventListener("submit", (e) => this.salvarCandidato(e));
        }
    }

    public renderPerfil(){
        const storage = localStorage.getItem("candidatoLogado");
        if(!storage) return;

        const candidato = JSON.parse(storage);
        const div = document.getElementById("dados-perfil");

        if(div){
            div.innerHTML = `
                <p><strong>Nome:</strong> ${candidato.nome}</p>
                <p><strong>Email:</strong> ${candidato.email}</p>
                <p><strong>Competências:</strong> ${candidato.competencias.join(", ")}</p>
            `;
        }
    }

    private salvarCandidato(e: Event){
        e.preventDefault();

        const nomeInput = document.getElementById("nome") as HTMLInputElement;
        const idadeInput = document.getElementById("idade") as HTMLInputElement;
        const cpfInput = document.getElementById("cpf") as HTMLInputElement;
        const emailInput = document.getElementById("email") as HTMLInputElement;
        const descricaoInput = document.getElementById("descricao") as HTMLInputElement;
        const estadoInput = document.getElementById("estado") as HTMLInputElement;
        const cepInput = document.getElementById("cep") as HTMLInputElement;
        const competenciasInput = document.getElementById("competencias") as HTMLInputElement;

        const novoCandidato: Candidato = {
            nome: nomeInput.value,
            idade: parseInt(idadeInput.value),
            cpf: cpfInput.value,
            email: emailInput.value,
            descricao: descricaoInput.value,
            estado: estadoInput.value,
            cep: cepInput.value,
            competencias: (competenciasInput.value).split(",").map(c => c.trim())
        } 

        try {
            this.service.add(novoCandidato);

            localStorage.setItem("candidatoLogado", JSON.stringify(novoCandidato));

            alert("Cadastro Realizado!")

            window.location.href = "./candidato/perfil.html";
        } catch (error) {
            alert(error);
        }

    }

}