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

    private final Connection connection = ConnectionManager.getConnection();
    private final BoardDao boardDao = new BoardDao();

    public void save(final ChessGame chessGame) {
        delete(chessGame.getName());
        saveChessGame(chessGame);
        boardDao.save(chessGame);
    }

    private void saveChessGame(final ChessGame chessGame) {
        String insertSql = "insert into chess_game (name, is_on, team_value_of_turn) values (?, ?, ?)";
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, chessGame.getName());
            insertStatement.setBoolean(2, chessGame.isOn());
            insertStatement.setString(3, chessGame.getTurn().getNowValue());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void validateChessGameExist(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 ChessGame 입니다.");
        }
    }

    public void delete(final String name) {
        deleteChessGame(name);
        boardDao.delete(name);
    }

    private void deleteChessGame(final String name) {
        String deleteSql = "delete from chess_game where name=?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setString(1, name);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGame load(final String name) {
        String selectSql = "select name, is_on, team_value_of_turn from chess_game where name=?";
        ChessGame chessGame = null;
        try {
            PreparedStatement loadStatement = connection.prepareStatement(selectSql);
            loadStatement.setString(1, name);
            ResultSet resultSet = loadStatement.executeQuery();
            validateChessGameExist(resultSet);
            chessGame = generateChessGame(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chessGame;
    }

    private ChessGame generateChessGame(final ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        boolean isOn = resultSet.getBoolean("is_on");
        Team now = Team.valueOf(resultSet.getString("team_value_of_turn"));
        return new ChessGame(name, boardDao.load(name), new GameSwitch(isOn), new Turn(now));
    }

    public List<ChessGame> loadAll() {
        String loadAllSql = "select * from chess_game";
        List<ChessGame> chessGames = new ArrayList<>();
        try {
            PreparedStatement loadNamesStatement = connection.prepareStatement(loadAllSql);
            ResultSet resultSet = loadNamesStatement.executeQuery();
            while (resultSet.next()) {
                chessGames.add(generateChessGame(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chessGames;
    }
}
