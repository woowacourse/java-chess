package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.dto.SquareDto;
import chess.exception.DataAccessException;

public class BoardDao {

    private final DBConnection dbConnection = new DBConnection();

    public void updateBoardSquare(final String position, final String piece, final String color) {
        final String query = "UPDATE board SET piece = ? , color = ? WHERE position=?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, piece);
            statement.setString(2, color);
            statement.setString(3, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("[DATABASE_ERROR] 체스 말 업데이트 실패");
        }
    }

    public List<SquareDto> getBoardSquares() {
        final String query = "SELECT * FROM board";
        final List<SquareDto> squares = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                addSquare(squares, resultSet);
            }
            return squares;
        } catch (SQLException e) {
            throw new DataAccessException("[DATABASE_ERROR] 보드 정보 가져오기 실패");
        }
    }

    private void addSquare(final List<SquareDto> squares, final ResultSet resultSet) throws SQLException {
        if (resultSet.getString("piece") == null) {
            squares.add(new SquareDto(resultSet.getString("position")));
            return;
        }
        squares.add(
            new SquareDto(
                resultSet.getString("position"),
                resultSet.getString("piece"),
                resultSet.getString("color")
            )
        );
    }
}
