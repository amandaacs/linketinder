import type { Empresa } from "./empresa";



export interface Vaga {
    id: string
    titulo: string
    descricao: string
    pais: string
    estado: string
    competencias: string[]
    salario: number
    empresa: Empresa
}