package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.GameSwitch;
import chess.domain.game.Turn;
import chess.domain.piece.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {

    public void save(final ChessGame chessGame) {
        String deleteSql = "delete from chess_game where id=1";
        String insertSql = "insert into chess_game (id, is_on, team_value_of_turn) values (?, ?, ?)";

        boolean isOn = chessGame.isOn();
        String teamValueOfTurn = chessGame.getTurn().getNow().getValue();

        try {
            Connection connection = ConnectionManager.getConnection();

            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.execute();

            PreparedStatement insertStatement = ConnectionManager.getConnection().prepareStatement(insertSql);
            insertStatement.setInt(1, 1);
            insertStatement.setBoolean(2, isOn);
            insertStatement.setString(3, teamValueOfTurn);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChessGame load(final Board board) {
        String sql = "select is_on, team_value_of_turn from chess_game where id = 1";
        try {
            PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return ChessGame.createInit();
            }
            boolean isOn = resultSet.getBoolean("is_on");
            Team now = Team.valueOf(resultSet.getString("team_value_of_turn"));
            return new ChessGame(board, new GameSwitch(isOn), new Turn(now));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
