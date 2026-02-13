import type { Empresa } from "../models/empresa";

export class EmpresaService{
    private empresas: Empresa[] = [];

    constructor(){
        const dadosSalvos = localStorage.getItem('empresas');
        if(dadosSalvos){
            this.empresas = JSON.parse(dadosSalvos);
        }
    }

    public listAll(): Empresa[]{
        return this.empresas;
    }

    public getByCnpj(cnpjToFind: string): Empresa | undefined{

        return this.empresas.find(e => e.cnpj === cnpjToFind);

    }

    public add(newEmpresa: Empresa): string{
        const exists = this.empresas.find(c => c.cnpj === newEmpresa.cnpj);
        if(exists) throw new Error("Cnpj já cadastrado.");

        this.empresas.push(newEmpresa);

        localStorage.setItem('empresas', JSON.stringify(this.empresas));

        return "Cadastro realizado";        

    }

    public delete(cnpjEmpresa: string): string{
        
        const empresaDeletar = this.getByCnpj(cnpjEmpresa);
        if(!empresaDeletar) throw new Error("Empresa não existe.");

        this.empresas =  this.empresas.filter(d => d.cnpj !== cnpjEmpresa);

        localStorage.setItem('empresas', JSON.stringify(this.empresas));

        return "Empresa " + empresaDeletar.nome + " deletada.";

    }



}