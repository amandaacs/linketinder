import type { Candidato } from "../models/candidato";

export class CandidatoService{
    private candidatos: Candidato[] = [];

    public listAll(): Candidato[]{
        return this.candidatos;
    }

    public getByCpf(cpfToFind: string): Candidato | undefined{

        return this.candidatos.find(c => c.cpf === cpfToFind);;

    }

    public add(newCandidato: Candidato): string{
        const exists = this.candidatos.find(c => c.cpf === newCandidato.cpf);
        if(exists) throw new Error("CPF já cadastrado.");

        this.candidatos.push(newCandidato);

        return "Cadastro realizado";

    }

    public delete(cpfCandidato: string): string{
        
        const candidatoDeletar = this.getByCpf(cpfCandidato);
        if(!candidatoDeletar) throw new Error("Usuário não existe.");

        this.candidatos =  this.candidatos.filter(d => d.cpf !== cpfCandidato);

        return "Usuário " + candidatoDeletar.nome + " deletado.";

    }



}