package com.project.dao

import com.project.config.DBConn
import com.project.model.Candidato


import java.sql.Connection
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CandidatoDAO {


    private DBConn dbConn = new DBConn()

    List<Candidato> listarTodos(){
        List<Candidato> candidatos = new ArrayList<>()
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlCandidatos = "SELECT * FROM candidato"
                PreparedStatement statement = conn.prepareStatement(sqlCandidatos)
                ResultSet resultSet = statement.executeQuery()

                while (resultSet.next()){
                    Candidato candidato = new Candidato()

                    candidato.setId(resultSet.getInt("id"))
                    candidato.setNome(resultSet.getString("nome"))
                    candidato.setSobrenome(resultSet.getString("sobrenome"))
                    candidato.setDob(resultSet.getDate("dob"))
                    candidato.setEmail(resultSet.getString("email"))
                    candidato.setSenha(resultSet.getString("senha"))
                    candidato.setCpf(resultSet.getString("cpf"))
                    candidato.setPais(resultSet.getString("pais"))
                    candidato.setCep(resultSet.getString("cep"))
                    candidato.setDescricao(resultSet.getString("descricao"))

                    candidatos.add(candidato)
                }
                statement.close()
            } catch (SQLException e){
                println("Erro ao listar candidatos")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }

        }
        return candidatos
    }

    void salvar(Candidato candidato){
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlCandidato = "INSERT INTO candidato (nome, sobrenome, dob, email, senha, cpf, pais, cep, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id"

                PreparedStatement statement = conn.prepareStatement(sqlCandidato)
                statement.setString(1, candidato.getNome())
                statement.setString(2, candidato.getSobrenome())
                statement.setDate(3, new Date(candidato.getDob().getTime()))
                statement.setString(4, candidato.getEmail())
                statement.setString(5, candidato.getSenha())
                statement.setString(6, candidato.getCpf())
                statement.setString(7, candidato.getPais())
                statement.setString(8, candidato.getCep())
                statement.setString(9, candidato.getDescricao())

                ResultSet resultSet = statement.executeQuery()
                int idCandidato = 0
                if (resultSet.next()){
                    idCandidato = resultSet.getInt("id")
                }
                statement.close()

                if (idCandidato > 0 && candidato.getCompetencias() != null){
                    for (String nomeCompetencia : candidato.getCompetencias()){
                        int idCompetencia = salvarCompetencia(nomeCompetencia, conn)

                        String sqlComp = "INSERT INTO candidato_competencia (candidato_id,competencia_id) VALUES (?,?)"
                        PreparedStatement statementComp = conn.prepareStatement(sqlComp)
                        statementComp.setInt(1, idCandidato)
                        statementComp.setInt(2, idCompetencia)
                        statementComp.executeUpdate()
                        statementComp.close()
                    }
                }

                println("Candidato cadastrado com sucesso!")
            }catch (SQLException e){
                println("Erro ao salvar candidato")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }


        }
    }

    void atualizar(Candidato candidato){
        Connection conn = dbConn.connect()

        if(conn != null){
            try{
                String sqlAtualizar = "UPDATE candidato set nome = ?, sobrenome = ?, dob = ?, email = ?, senha = ?, cpf = ?, pais = ?, cep = ?, descricao = ? WHERE id = ?"
                PreparedStatement statementAtualizar = conn.prepareStatement(sqlAtualizar)

                statementAtualizar.setString(1, candidato.getNome())
                statementAtualizar.setString(2, candidato.getSobrenome())
                statementAtualizar.setDate(3, new Date(candidato.getDob().getTime()))
                statementAtualizar.setString(4, candidato.getEmail())
                statementAtualizar.setString(5, candidato.getSenha())
                statementAtualizar.setString(6, candidato.getCpf())
                statementAtualizar.setString(7, candidato.getPais())
                statementAtualizar.setString(8, candidato.getCep())
                statementAtualizar.setString(9, candidato.getDescricao())
                statementAtualizar.setInt(10, candidato.getId())

                int linhasAlteradas = statementAtualizar.executeUpdate()

                if (linhasAlteradas > 0){
                    println("Dados alterados com sucesso!")
                } else {
                    println("Candidato não encontrado")
                }

                statementAtualizar.close()
            } catch (SQLException e){
                println("Erro ao atualizar informação do candidato")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

    void deletar(int idCandidato){
        Connection conn = dbConn.connect()

        if(conn != null){
            try{
                String sqlDeletar = "DELETE FROM candidato WHERE id = ?"
                PreparedStatement statementDeletar = conn.prepareStatement(sqlDeletar)
                statementDeletar.setInt(1, idCandidato)

                int linhasAlteradas = statementDeletar.executeUpdate()

                if(linhasAlteradas > 0){
                    println("Candidato deletado com sucesso")
                } else {
                    println("Candidato não encontrado")
                }

                statementDeletar.close()
            } catch (SQLException e){
                println("Erro ao deletar candidato")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

    int salvarCompetencia(String nomeCompetencia, Connection connection) {

        String sqlBusca = "SELECT id FROM competencia WHERE LOWER(nome) = LOWER(?)"
        PreparedStatement statementBusca = connection.prepareStatement(sqlBusca)
        statementBusca.setString(1, nomeCompetencia)
        ResultSet resultSetBusca = statementBusca.executeQuery()

        if(resultSetBusca.next()){
            int idExistente = resultSetBusca.getInt("id")
            statementBusca.close()
            return idExistente
        }
        statementBusca.close()

        String sqlInsert = "INSERT INTO competencia (nome) VALUES (?) RETURNING id"
        PreparedStatement statementInsert = connection.prepareStatement(sqlInsert)
        statementInsert.setString(1, nomeCompetencia)
        ResultSet resultSetInsert = statementInsert.executeQuery()

        int novoId = 0
        if(resultSetInsert.next()){
            novoId = resultSetInsert.getInt("id")
        }
        resultSetInsert.close()
        return novoId
    }
}
