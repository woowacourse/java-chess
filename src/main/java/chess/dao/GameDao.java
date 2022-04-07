package chess.dao;

import chess.domain.piece.Color;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class GameDao {

    static final int GAME_ID = 0;

    static {
        try {
            if (!hasAlreadyGame()) {
                loadGame();
            }
        } catch (SQLException e) {
            System.out.println("이미 존재하는 게임 아이디입니다.");
        }
    }

    private static boolean hasAlreadyGame() throws SQLException {
        final var sql = "SELECT COUNT(*) from game WHERE game_id = ?";
        final var prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
        prepareStatement.setInt(1, GAME_ID);
        final ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet.next()) {
            final var count = Integer.parseInt(resultSet.getString(1));
            return count != 0;
        }
        throw new SQLException("game_id: " + GAME_ID + "를 조회할수 없습니다.");
    }

    private static void loadGame() throws SQLException {
        final var sql = "INSERT INTO game (game_id) VALUES(?)";
        final var prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
        prepareStatement.setInt(1, GAME_ID);
        prepareStatement.execute();
    }

    public void updateTurn(final Color nextColor) {
        final var sql = "UPDATE game SET current_turn=? WHERE game_id=?";
        final PreparedStatement prepareStatement;
        try {
            prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
            prepareStatement.setString(1, nextColor.name());
            prepareStatement.setInt(2, GAME_ID);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findCurrentColor() {
        final var sql = "SELECT current_turn FROM game WHERE game_id=?";
        final PreparedStatement prepareStatement;
        try {
            prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
            prepareStatement.setInt(1, GAME_ID);
            final ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            throw new SQLException("현재 순서의 색상을 조회할 수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void initTurn() {
        updateTurn(Color.WHITE);
    }
}
