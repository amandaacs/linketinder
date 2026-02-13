import { Empresa } from "../models/empresa";
import { CandidatoService } from "../services/candidatoService";
import { EmpresaService } from "../services/empresaService";


export class EmpresaController{

    private service: EmpresaService;

    constructor(){
        this.service = new EmpresaService();
        this.configurarForm();
    }

    public initDashboard(){
        const candidatoService = new CandidatoService();
        const candidatos = candidatoService.listAll();

        const tbody = document.querySelector("#tabela-candidatos tbody");
        if(tbody){
            tbody.innerHTML = "";

            candidatos.forEach((candidato, index) =>{
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>Candidato #${index + 1}</td>
                    <td>${candidato.competencias.join(", ")}</td>
                `;
                tbody.appendChild(tr);
            });
        }

        const canvas = document.getElementById("grafico-competencias") as HTMLCanvasElement;
        if(canvas && candidatos.length > 0){
            const count: Record<string, number> = {};
            candidatos.forEach(c => {
                c.competencias.forEach(skill => {
                    const s = skill.toUpperCase().trim();
                    count[s] = (count[s] || 0) + 1;
                });
            });

            // @ts-ignore
            new Chart(canvas, {
                type: 'bar',
                data: {
                    labels: Object.keys(count),
                    datasets: [{
                        label: '# de candidatos',
                        data: Object.values(count),
                        backgroundColor: 'rgba(54, 162, 235, 0.6)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: { beginAtZero: true, ticks: { stepSize: 1 } }
                    }
                }
            });

        }

    }

    public configurarForm(){
        const form = document.getElementById("form-empresa") as HTMLFormElement;

        if(form){
            form.addEventListener("submit", (e) => this.salvarEmpresa(e));
        }
    }

    public salvarEmpresa(e: Event){
        e.preventDefault();

        const nomeInput = document.getElementById("nome") as HTMLInputElement;
        const cnpjInput = document.getElementById("cnpj") as HTMLInputElement;
        const emailInput = document.getElementById("email") as HTMLInputElement;
        const descricaoInput = document.getElementById("descricao") as HTMLInputElement;
        const paisInput = document.getElementById("pais") as HTMLInputElement;
        const estadoInput = document.getElementById("estado") as HTMLInputElement;
        const cepInput = document.getElementById("cep") as HTMLInputElement;

        const novaEmpresa: Empresa = {
            nome: nomeInput.value,
            cnpj: cnpjInput.value,
            email: emailInput.value,
            descricao: descricaoInput.value,
            pais: paisInput.value,
            estado: estadoInput.value,
            cep: cepInput.value
        } 

        try {
            this.service.add(novaEmpresa);

            localStorage.setItem("empresaLogada", JSON.stringify(novaEmpresa));

            alert("Cadastro Realizado!")

            window.location.href = "./empresa/perfil.html";
        } catch (error) {
            alert(error);
        }

    }

}