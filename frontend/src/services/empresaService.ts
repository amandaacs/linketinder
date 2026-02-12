import type { Empresa } from "../models/empresa";

export class EmpresaService{
    private empresas: Empresa[] = [];

    public listAll(): Empresa[]{
        return this.empresas;
    }

    public getByCnpj(cnpjToFind: string): Empresa | undefined{

        return this.empresas.find(e => e.cnpj === cnpjToFind);;

    }

    public add(newEmpresa: Empresa): string{
        const exists = this.empresas.find(c => c.cnpj === newEmpresa.cnpj);
        if(exists) throw new Error("Cnpj já cadastrado.");

        this.empresas.push(newEmpresa);

        return "Cadastro realizado";

    }

    public delete(cnpjEmpresa: string): string{
        
        const empresaDeletar = this.getByCnpj(cnpjEmpresa);
        if(!empresaDeletar) throw new Error("Empresa não existe.");

        this.empresas =  this.empresas.filter(d => d.cnpj !== cnpjEmpresa);

        return "Empresa " + empresaDeletar.nome + " deletada.";

    }



}