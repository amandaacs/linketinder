package com.project.dao

import com.project.config.DBConn
import com.project.model.Empresa

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class EmpresaDAO {

    private DBConn dbConn = new DBConn()

    List<Empresa> listarTodos(){
        List<Empresa> empresas = new ArrayList<>()
        Connection conn = dbConn.connect()

        if (conn != null){
            try {
                String sqlEmpresas = "SELECT * FROM empresa"
                PreparedStatement statement = conn.prepareStatement(sqlEmpresas)
                ResultSet resultSet = statement.executeQuery()

                while (resultSet.next()){
                    Empresa empresa = new Empresa()

                    empresa.setId(resultSet.getInt("id"))
                    empresa.setNome(resultSet.getString("nome"))
                    empresa.setCnpj(resultSet.getString("cnpj"))
                    empresa.setEmail(resultSet.getString("email"))
                    empresa.setSenha(resultSet.getString("senha"))
                    empresa.setDescricao(resultSet.getString("descricao"))
                    empresa.setPais(resultSet.getString("pais"))
                    empresa.setCep(resultSet.getString("cep"))

                    empresas.add(empresa)

                }
                statement.close()
            } catch (SQLException e) {
                println("Erro ao listar empresas")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
        return empresas
    }

    void salvar(Empresa empresa){
        Connection conn = dbConn.connect()

        if(conn != null){

            try {
                String sqlEmpresa = "INSERT INTO empresa (nome, cnpj, email, senha, descricao, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id"

                PreparedStatement statement = conn.prepareStatement(sqlEmpresa)
                statement.setString(1, empresa.getNome())
                statement.setString(2, empresa.getCnpj())
                statement.setString(3, empresa.getEmail())
                statement.setString(4, empresa.getSenha())
                statement.setString(5, empresa.getDescricao())
                statement.setString(6, empresa.getPais())
                statement.setString(7, empresa.getCep())

                ResultSet resultSet = statement.executeQuery()
                int idEmpresa = 0
                if(resultSet.next()){
                    idEmpresa = resultSet.getInt("id")
                }
                statement.close()
                println("Empresa cadastrada com sucesso!")
            } catch (SQLException e){
                println("Erro ao salvar empresa")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }

        }
    }

    void atualizar(Empresa empresa){
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlAtualizar = "UPDATE empresa set  nome = ?, cnpj = ?, email = ?, senha = ?, descricao = ?, pais = ?, cep = ? WHERE id = ?"
                PreparedStatement statementAtualizar = conn.prepareStatement(sqlAtualizar)

                statementAtualizar.setString(1, empresa.getNome())
                statementAtualizar.setString(2, empresa.getCnpj())
                statementAtualizar.setString(3, empresa.getEmail())
                statementAtualizar.setString(4, empresa.getSenha())
                statementAtualizar.setString(5, empresa.getDescricao())
                statementAtualizar.setString(6, empresa.getPais())
                statementAtualizar.setString(7, empresa.getCep())
                statementAtualizar.setInt(8, empresa.getId())

                int linhasAlteradas = statementAtualizar.executeUpdate()

                if(linhasAlteradas > 0){
                    println("Dados alterados com sucesso!")
                } else {
                    println("Empresa não encontrada")
                }

                statementAtualizar.close()
            } catch (SQLException e){
                println("Erro ao atualizar a informação da empresa")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

    void deletar(int idEmpresa){
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlDeletar = "DELETE FROM empresa WHERE id = ?"
                PreparedStatement statementDeletar = conn.prepareStatement(sqlDeletar)

                statementDeletar.setInt(1, idEmpresa)

                int linhasAlteradas = statementDeletar.executeUpdate()

                if(linhasAlteradas > 0){
                    println("Empresa deletada com sucesso!")
                } else {
                    println("Empresa não encontrada")
                }

                statementDeletar.close()
            } catch (SQLException e){
                println("Erro ao deletar empresa")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }

    }

}
