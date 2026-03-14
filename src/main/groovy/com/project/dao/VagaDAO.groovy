package com.project.dao

import com.project.config.DBConn
import com.project.model.Vaga

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagaDAO {
    private DBConn dbConn = new DBConn()

    List<Vaga> listarTodas(){
        List<Vaga> vagas = new ArrayList<>()
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlVagas = "SELECT * FROM vaga"
                PreparedStatement statement = conn.prepareStatement(sqlVagas)
                ResultSet resultSet = statement.executeQuery()

                while (resultSet.next()){
                    Vaga vaga = new Vaga()

                    vaga.setId(resultSet.getInt("id"))
                    vaga.setNome(resultSet.getString("nome"))
                    vaga.setDescricao(resultSet.getString("descricao"))
                    vaga.setLocal(resultSet.getString("local"))
                    vaga.setEmpresaId(resultSet.getInt("empresa_id"))

                    vagas.add(vaga)

                }
                statement.close()
            } catch (SQLException e){
                println("Erro ao listar vagas")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
        return vagas
    }

    void salvar(Vaga vaga){
        Connection conn = dbConn.connect()

        if (conn != null){
            try {
                String sqlVaga = "INSERT INTO vaga (nome, descricao, local, empresa_id) VALUES (?, ?, ?, ?) RETURNING id"
                PreparedStatement statement = conn.prepareStatement(sqlVaga)

                statement.setString(1, vaga.getNome())
                statement.setString(2, vaga.getDescricao())
                statement.setString(3, vaga.getLocal())
                statement.setInt(4, vaga.getEmpresaId())

                ResultSet resultSet = statement.executeQuery()

                int idVaga= 0

                if(resultSet.next()){
                    idVaga = resultSet.getInt("id")
                    vaga.setId(idVaga)
                }

                statement.close()

                if(idVaga > 0 && vaga.getCompetencias() != null){
                    for (String nomeCompetencia : vaga.getCompetencias()){
                        int idCompetencia = salvarCompetencia(nomeCompetencia, conn)

                        String sqlComp = "INSERT INTO vaga_competencia (vaga_id, competencia_id) VALUES (?,?)"
                        PreparedStatement statementComp = conn.prepareStatement(sqlComp)

                        statementComp.setInt(1, idVaga)
                        statementComp.setInt(2, idCompetencia)
                        statementComp.executeUpdate()
                        statementComp.close()
                    }

                }
                println("Vaga criada com sucesso!")
            } catch (SQLException e){
                println("Erro ao criar vaga")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

    void atualizar(Vaga vaga){
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlAtualizar = "UPDATE vaga set nome = ?, descricao = ?, local = ?, empresa_id = ? WHERE id = ?"
                PreparedStatement statementAtualizar = conn.prepareStatement(sqlAtualizar)

                statementAtualizar.setString(1, vaga.getNome())
                statementAtualizar.setString(2, vaga.getDescricao())
                statementAtualizar.setString(3, vaga.getLocal())
                statementAtualizar.setInt(4, vaga.getEmpresaId())
                statementAtualizar.setInt(5, vaga.getId())

                int linhasAlteradas = statementAtualizar.executeUpdate()

                if(linhasAlteradas > 0){
                    println("Dados alterados com sucesso")
                } else {
                    println("Vaga não encontrada")
                }

                statementAtualizar.close()

            } catch (SQLException e){
                println("Erro ao atualizar informação da vaga")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

    void deletar(int idVaga){
        Connection conn = dbConn.connect()

        if(conn != null){
            try {
                String sqlDeletar = "DELETE FROM vaga WHERE id = ?"
                PreparedStatement statementDeletar = conn.prepareStatement(sqlDeletar)
                statementDeletar.setInt(1, idVaga)

                int linhasAlteradas = statementDeletar.executeUpdate()

                if(linhasAlteradas > 0){
                    println("Vaga deletada com sucesso")
                } else {
                    println("Vaga não encontrada")
                }

                statementDeletar.close()
            } catch (SQLException e){
                println("Erro ao deletar vaga")
                e.printStackTrace()
            } finally {
                dbConn.closeConn(conn)
            }
        }
    }

    int salvarCompetencia (String nomeCompetencia, Connection connection){
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
