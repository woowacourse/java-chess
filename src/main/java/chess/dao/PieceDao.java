package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PieceDao extends Dao {

    private static final String BOARD_ID = "b2c2256e-c30c-4f9d-8603-f439e47c4f03";
    // TODO: 방 만들기 기능을 추가하면 제거해야함

    // TODO: 리팩토링
    public Board findAll() throws SQLException {
        String query = "SELECT x_axis, y_axis, piece_type, piece_color FROM piece";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<Position, Piece> board = new HashMap<>();
        while (resultSet.next()) {
            Position position = Position.of(resultSet.getString(1), resultSet.getString(2));
            PieceType pieceType = PieceType.valueOf(resultSet.getString(3));
            PieceColor pieceColor = PieceColor.valueOf(resultSet.getString(4));
            Piece piece = new Piece(pieceType, pieceColor);

            board.put(position, piece);
        }

        return Board.from(board);
    }

    public void create(XAxis xAxis, YAxis yAxis, PieceType pieceType, PieceColor pieceColor) throws SQLException {
        String query = "INSERT INTO piece VALUES ('" + BOARD_ID + "', ?, ?, ?, ?)";

        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, Integer.toString(xAxis.getValue()));
        preparedStatement.setString(2, Integer.toString(yAxis.getValue()));
        preparedStatement.setString(3, pieceType.name());
        preparedStatement.setString(4, pieceColor.name());

        preparedStatement.executeUpdate();
    }

    public void delete(XAxis xAxis, YAxis yAxis, PieceType pieceType, PieceColor pieceColor) throws SQLException {
        String query = "DELETE FROM piece WHERE board_id = '" + BOARD_ID
                + "' AND x_axis = ? AND y_axis = ? AND piece_type = ? AND piece_color = ?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, Integer.toString(xAxis.getValue()));
        preparedStatement.setString(2, Integer.toString(yAxis.getValue()));
        preparedStatement.setString(3, pieceType.name());
        preparedStatement.setString(4, pieceColor.name());

        preparedStatement.executeUpdate();
    }
}
