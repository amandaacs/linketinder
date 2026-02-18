export class RegexValidator {

    static nome(nome: string): boolean{
        return /^[a-zA-ZÀ-ÿ\s]{3,}$/.test(nome);
    }

    static email(email: string): boolean{
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }

    
    static cpf(cpf: string): boolean{
        return /^\d{11}$/.test(cpf);
    }

    static cnpj(cnpj: string): boolean{
        return /^\d{14}$/.test(cnpj);
    }

    static telefone(telefone: string): boolean{
        return /^\d{10,11}$/.test(telefone);
    }

    static linkedin(linkedin: string): boolean{
        return /^https:\/\/(www\.)?linkedin\.com\/in\/[a-zA-Z0-9-]+\/?$/.test(linkedin);
    }

    static cep(cep: string): boolean{
        return /^\d{5}-?\d{3}$/.test(cep);
    }




}