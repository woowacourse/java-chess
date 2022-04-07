package chess.db;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAO extends CommonDAO {

    public static final String FIND_ALL_SQL = "select location, name, color from board where roomID = ?";
    public static final String INSERT_ONE_PIECE_SQL = "insert into board (location, name, color, roomID) values (?, ?, ?, ?)";
    public static final String DELETE_ONE_PIECE_SQL = "delete from board where location = ? and roomID = ?";


    public BoardDAO(String roomId) {
        super(roomId);
    }

    public void initializePieces(State state) {
        Map<Position, Piece> pieces = state.getBoard().getPieces();
        for (Position position : pieces.keySet()) {
            insert(position, pieces.get(position));
        }
    }

    public Map<Position, Piece> findAllPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
            setStrings(statement, List.of(roomId));
            return getPieces(pieces, statement);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private Map<Position, Piece> getPieces(Map<Position, Piece> pieces, PreparedStatement statement)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            pieces.put(new Position(resultSet.getString("location")),
                    PieceGenerator.of(resultSet.getString("name"), resultSet.getString("color")));
        }
        return pieces;
    }

    public void insert(Position position, Piece piece) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_ONE_PIECE_SQL);
            setStrings(statement, List.of(position.getPosition(), piece.getName(), piece.getColor(), roomId));
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void delete(Position position) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_ONE_PIECE_SQL);
            setStrings(statement, List.of(position.getPosition(), roomId));
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
