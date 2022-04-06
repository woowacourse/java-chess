package chess.dao;

import chess.domain.game.ChessGame;
import chess.domain.game.GameSwitch;
import chess.domain.game.Turn;
import chess.domain.piece.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDao {

    private static final Connection connection = ConnectionManager.getConnection();

    public static void save(final ChessGame chessGame) {
        delete(chessGame.getName());
        String insertSql = "insert into chess_game (name, is_on, team_value_of_turn) values (?, ?, ?)";
        String name = chessGame.getName();
        boolean isOn = chessGame.isOn();
        String teamValueOfTurn = chessGame.getTurn().getNow().getValue();
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, name);
            insertStatement.setBoolean(2, isOn);
            insertStatement.setString(3, teamValueOfTurn);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BoardDao.save(chessGame);
    }

    public static void delete(final String name) {
        String deleteSql = "delete from chess_game where name=?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setString(1, name);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ChessGame load(final String name) {
        String loadSql = "select name, is_on, team_value_of_turn from chess_game where name=?";
        try {
            PreparedStatement loadStatement = connection.prepareStatement(loadSql);
            loadStatement.setString(1, name);
            ResultSet resultSet = loadStatement.executeQuery();
            validateChessGameExist(resultSet);
            return generateChessGame(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void validateChessGameExist(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 ChessGame 입니다.");
        }
    }

    private static ChessGame generateChessGame(final ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        boolean isOn = resultSet.getBoolean("is_on");
        Team now = Team.valueOf(resultSet.getString("team_value_of_turn"));
        return new ChessGame(name, BoardDao.load(name), new GameSwitch(isOn), new Turn(now));
    }

    public static List<ChessGame> loadAll() {
        String loadNamesSql = "select * from chess_game";
        List<ChessGame> chessGames = new ArrayList<>();
        try {
            PreparedStatement loadNamesStatement = connection.prepareStatement(loadNamesSql);
            ResultSet resultSet = loadNamesStatement.executeQuery();
            while (resultSet.next()) {
                chessGames.add(generateChessGame(resultSet));
            }
            return chessGames;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
