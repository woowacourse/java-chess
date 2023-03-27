package chess.dao;

import chess.database.DatabaseConnector;
import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.dto.ChessGameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MySqlChessGameDao implements ChessGameDao {

    private static final int STRING_FIRST_INDEX = 0;

    private final DatabaseConnector databaseConnector;

    public MySqlChessGameDao(final DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public void save(final ChessGameDto chessGameDto) {
        try (final Connection connection = databaseConnector.getConnection()) {
            int chessGameId = saveChessGame(chessGameDto, connection);
            savePiece(chessGameDto.getBoard(), connection, chessGameId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int saveChessGame(final ChessGameDto chessGameDto, final Connection connection) throws SQLException {
        final String query = "INSERT INTO chess_game(turn) VALUES (?)";
        final PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, chessGameDto.getTurn());
        ps.executeUpdate();

        return ps.getGeneratedKeys().getInt(1);
    }

    private void savePiece(final Map<Square, Piece> board, final Connection connection, final int chessGameId)
            throws SQLException {
        final String query =
                "INSERT INTO piece(chess_game_id, name, file, `rank`, color) VALUES (?, ?, ?, ?, ?);";

        for (Square square : board.keySet()) {
            final PreparedStatement ps = connection.prepareStatement(query);

            final Piece piece = board.get(square);

            ps.setInt(1, chessGameId);
            ps.setString(2, piece.getPieceType().name());
            ps.setString(3, square.getFile().name());
            ps.setString(4, square.getRank().name());
            ps.setString(5, piece.getColor().name());

            ps.executeUpdate();
        }
    }

    @Override
    public ChessGame findById(final int id) {
        try (final Connection connection = databaseConnector.getConnection()) {
            Map<Square, Piece> board = createBoard(id, connection);
            final Turn turn = getTurn(id, connection);
            return new ChessGame(new Board(board), turn);
        } catch (SQLException e) {
            return null;
        }
    }

    private Map<Square, Piece> createBoard(final int id, final Connection connection) throws SQLException {
        final String query = "SELECT name, file, `rank`, color " +
                "FROM piece " +
                "WHERE chess_game_id = ?";
        final PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        final ResultSet resultSet = ps.executeQuery();

        Map<Square, Piece> board = new HashMap<>();
        while (resultSet.next()) {
            final String name = resultSet.getString("name");
            final String file = resultSet.getString("file");
            final String rank = resultSet.getString("rank");
            final String color = resultSet.getString("color");

            final Piece piece = PieceFactory.create(PieceType.valueOf(name), Color.valueOf(color));

            board.put(
                    Square.of(
                            File.from(file.charAt(STRING_FIRST_INDEX)),
                            Rank.from(rank.charAt(STRING_FIRST_INDEX))),
                    piece);
        }

        return board;
    }

    private Turn getTurn(final int id, final Connection connection) throws SQLException {
        final String query = "SELECT turn FROM chess_game WHERE id = ?";
        final PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        final ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            final String color = resultSet.getString(1);
            return new Turn(Color.valueOf(color));
        }
        throw new SQLException();
    }
}
