package chess.dao;

import chess.entity.ChessGameEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {

    private final Connection connection = ConnectionManager.getConnection();

    public void save(final ChessGameEntity chessGameEntity) {
        String insertSql = "insert into chess_game (name, is_on, team_value_of_turn) values (?, ?, ?)";
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, chessGameEntity.getName());
            insertStatement.setBoolean(2, chessGameEntity.isOn());
            insertStatement.setString(3, chessGameEntity.getTeamValueOfTurn());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final String name) {
        String deleteSql = "delete from chess_game where name=?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setString(1, name);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGameEntity load(final String name) {
        String selectSql = "select name, is_on, team_value_of_turn from chess_game where name=?";
        ChessGameEntity chessGameEntity = null;
        try {
            PreparedStatement loadStatement = connection.prepareStatement(selectSql);
            loadStatement.setString(1, name);
            ResultSet resultSet = loadStatement.executeQuery();
            validateChessGameExist(resultSet);
            chessGameEntity = generateChessGameDto(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chessGameEntity;
    }

    private void validateChessGameExist(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 ChessGame 입니다.");
        }
    }

    private ChessGameEntity generateChessGameDto(final ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        boolean isOn = resultSet.getBoolean("is_on");
        String teamValueOfTurn = resultSet.getString("team_value_of_turn");
        return new ChessGameEntity(name, isOn, teamValueOfTurn);
    }

    public List<ChessGameEntity> loadAll() {
        String loadAllSql = "select * from chess_game";
        List<ChessGameEntity> chessGameEntities = new ArrayList<>();
        try {
            PreparedStatement loadNamesStatement = connection.prepareStatement(loadAllSql);
            ResultSet resultSet = loadNamesStatement.executeQuery();
            while (resultSet.next()) {
                chessGameEntities.add(generateChessGameDto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chessGameEntities;
    }
}
