import type { Vaga } from "../models/vaga";

export class VagaService{
    private vagas: Vaga[] = [];

    constructor(){
        const dadosSalvos = localStorage.getItem('vagas');
        if(dadosSalvos){
            this.vagas = JSON.parse(dadosSalvos);
        }
    }

    public listAll(): Vaga[]{
        return this.vagas;
    }

    public getById(idToFind: string): Vaga | undefined{

        return this.vagas.find(v => v.id === idToFind);;

    }

    public add(newVaga: Vaga): string{
        const exists = this.vagas.find(v => v.id === newVaga.id);
        if(exists) throw new Error("Vaga já cadastrada.");

        this.vagas.push(newVaga);

        localStorage.setItem('vagas', JSON.stringify(this.vagas));

        return "Cadastro realizado";

    }

    public delete(idVaga: string): string{
        
        const vagaDeletar = this.getById(idVaga);
        if(!vagaDeletar) throw new Error("Vaga não existe.");

        this.vagas =  this.vagas.filter(d => d.id !== idVaga);

        localStorage.setItem('vagas', JSON.stringify(this.vagas));

        return "Vaga " + vagaDeletar.titulo + " deletada.";

    }



}