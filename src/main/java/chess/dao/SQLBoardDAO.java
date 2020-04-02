package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SQLBoardDAO implements BoardDAO {
    private Connection connection;

    // 나중에 수정
    public SQLBoardDAO(Connection connection) {
        this.connection = connection;
    }

    //INSERT ON DUPLICATE KEY UPDATE로 개선하기
    @Override
    public void placePieceOn(Position position, Piece piece) throws SQLException {
        String query = "INSERT INTO board VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, position.toString());
        preparedStatement.setString(2, piece.toString());
        preparedStatement.executeUpdate();
    }

    @Override
    public Optional<Piece> findPieceOn(Position position) throws SQLException {
        String query = "SELECT * FROM board WHERE position = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, position.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return Optional.empty();

        return Optional.of(Piece.of(resultSet.getString("piece")));
    }

    @Override
    public Map<Position, Piece> findAllPieces() throws SQLException {
        String query = "SELECT * FROM board ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<Position, Piece> output = new HashMap<>();
        while (resultSet.next()) {
            Position position = Position.of(resultSet.getString("position"));
            Piece piece = Piece.of(resultSet.getString("piece"));

            output.put(position, piece);
        }

        return output;
    }

    @Override
    public void removePieceOn(Position position) throws SQLException {

    }
}
