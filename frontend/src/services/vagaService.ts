import type { Vaga } from "../models/vaga";

export class VagaService{
    private vagas: Vaga[] = [];

    public listAll(): Vaga[]{
        return this.vagas;
    }

    public getById(idToFind: number): Vaga | undefined{

        return this.vagas.find(v => v.id === idToFind);;

    }

    public add(newVaga: Vaga): string{
        const exists = this.vagas.find(v => v.id === newVaga.id);
        if(exists) throw new Error("Vaga já cadastrada.");

        this.vagas.push(newVaga);

        return "Cadastro realizado";

    }

    public delete(idVaga: number): string{
        
        const vagaDeletar = this.getById(idVaga);
        if(!vagaDeletar) throw new Error("Vaga não existe.");

        this.vagas =  this.vagas.filter(d => d.id !== idVaga);

        return "Vaga " + vagaDeletar.titulo + " deletada.";

    }



}