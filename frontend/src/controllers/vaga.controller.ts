import { Empresa } from "../models/empresa";
import { Vaga } from "../models/vaga";
import { VagaService } from "../services/vagaService";

export class VagaController{

    private service: VagaService;

    constructor(){
        this.service = new VagaService();

        const path = window.location.pathname;

        
        if(path.includes("empresa/")){
            this.verificarLogin();
            this.configurarForm();
        }
    }

    public configurarForm(){
        const form = document.getElementById("form-vaga") as HTMLFormElement;

        if(form){
            form.addEventListener("submit", (e) => this.salvarVaga(e));
        }
    }

    public verificarLogin(){
        const empresaLogada = localStorage.getItem("empresaLogada");

        if(!empresaLogada){
            alert("Você precisa estar logado como empresa!");
            window.location.href = "../cadastro-empresa.html";
        }
    }

    public salvarVaga(e: Event){
            e.preventDefault();
    
            const empresaStorage = localStorage.getItem("empresaLogada");

            if(!empresaStorage){
                alert("Sessão inválida.");
                return;
            }

            const empresaSessao: Empresa = JSON.parse(empresaStorage);

            const tituloInput = document.getElementById("titulo") as HTMLInputElement;
            const descricaoInput = document.getElementById("descricao") as HTMLInputElement;
            const paisInput = document.getElementById("pais") as HTMLInputElement;
            const estadoInput = document.getElementById("estado") as HTMLInputElement;
            const competenciasInput = document.getElementById("competencias") as HTMLInputElement;
            const salarioInput = document.getElementById("salario") as HTMLInputElement;


            const novaVaga: Vaga = {
                id: crypto.randomUUID(),
                titulo: tituloInput.value,
                descricao: descricaoInput.value,
                pais: paisInput.value,
                estado: estadoInput.value,
                competencias: (competenciasInput.value).split(",").map(c => c.trim()),
                salario: parseInt(salarioInput.value) || 0,
                empresa: empresaSessao
            }

            try {
            this.service.add(novaVaga);

            alert("Vaga Criada!")

            window.location.href = "empresa/perfil.html";
            } catch (error) {
                alert(error);
            }


        }
        

    public listarVagasDisponiveis(){
        const listaElement = document.getElementById("lista-vagas");
        if(!listaElement) return;

        const vagas = this.service.listAll();

        if(vagas.length === 0){
            listaElement.innerHTML = "<p>Nenhuma vaga disponível no momento</p>"
            return
        }

        vagas.forEach(vaga => {
            const div = document.createElement("div");

            div.className = "vaga-card";

            const tagsHtml = vaga.competencias .map(skill => `<span class="tag">${skill}</span>`) .join("");

            div.innerHTML = `
                <div class="vaga-titulo">${vaga.titulo}</div>
                <p>${vaga.descricao}</p>
                <div class="tags"><strong>Requisitos:</strong> ${tagsHtml}</div>
                <p><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
                <button onclick="alert('Inscrição realizada!')">Aplicar</button>
            `;

            listaElement.appendChild(div);

        })

    }
    

}