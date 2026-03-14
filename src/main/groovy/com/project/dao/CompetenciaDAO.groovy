package com.project.dao

import com.project.config.DBConn
import com.project.model.Competencia

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CompetenciaDAO {
    private DBConn dbConn= new DBConn()

    List<Competencia> listarTodas(){
        List<Competencia> competencias = new ArrayList<>()
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlCompetencias = "SELECT * FROM competencia"
                PreparedStatement statement = conn.prepareStatement(sqlCompetencias)
                ResultSet resultSet = statement.executeQuery()

                while (resultSet.next()){
                    Competencia competencia = new Competencia()

                    competencia.setId(resultSet.getInt("id"))
                    competencia.setNome(resultSet.getString("nome"))

                    competencias.add(competencia)
                }
                statement.close()
            } catch (SQLException e){
                println("Erro ao listar competências")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
        return competencias
    }

    void salvar(Competencia competencia){
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlCompetencia = "INSERT INTO competencia (nomes) VALUES (?) RETURNING id"
                PreparedStatement statement = conn.prepareStatement(sqlCompetencia)
                statement.setString(1, competencia.getNome())

                ResultSet resultSet = statement.executeQuery()
                int idComp = 0
                if(resultSet.next()){
                    idComp = resultSet.getInt("id")
                    competencia.setId(idComp)
                }
                statement.close()
                println("Competência salva com sucesso!")
            } catch (SQLException e){
                println("Erro ao salvar competência")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

    void atualizar(Competencia competencia){
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlAtualizar = "UPDATE competencia set nome = ? WHERE id = ?"
                PreparedStatement statementAtualizar = conn.prepareStatement(sqlAtualizar)

                statementAtualizar.setString(1, competencia.getNome())
                statementAtualizar.setInt(2, competencia.getId())

                int linhasAlteradas = statementAtualizar.executeUpdate()

                if(linhasAlteradas > 0){
                    println("Dados alterados com sucesso")
                } else {
                    println("Competência não encontrada")
                }

                statementAtualizar.close()
            } catch (SQLException e){
                println("Erro ao atualizar competência")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

    void deletar(int idCompetencia){
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlDeletar = "DELETE FROM candidato WHERE id = ?"
                PreparedStatement statementDeletar = conn.prepareStatement(sqlDeletar)
                statementDeletar.setInt(1, idCompetencia)

                int linhasAlteradas = statementDeletar.executeUpdate()

                if(linhasAlteradas > 0){
                    println("Competência deletada com sucesso")
                } else {
                    println("Competência não encontrada")
                }
            } catch (SQLException e){
                println("Erro ao deletar competência")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

}
