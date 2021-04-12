package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Pieces;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.MovePieceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public final class PiecesDAO extends AbstractDAO {

    private static final PiecesDAO PIECES_DAO = new PiecesDAO();

    private PiecesDAO() {
    }

    public static PiecesDAO instance() {
        return PIECES_DAO;
    }

    public Pieces joinPieces(final int boardId) throws SQLException {
        Connection connection = connection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT piece_position, piece_symbol FROM piece WHERE board_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, boardId);
            resultSet = preparedStatement.executeQuery();
            Map<Position, Piece> pieces = getPieces(resultSet);
            return new Pieces(pieces);
        } finally {
            closeConnection(connection);
            disconnect(preparedStatement, resultSet);
        }
    }

    private Map<Position, Piece> getPieces(final ResultSet resultSet) throws SQLException {
        Map<Position, Piece> pieces = new Pieces().pieces();
        while (resultSet.next()) {
            String piecePosition = resultSet.getString("piece_position");
            Position position = Position.of(piecePosition);
            String pieceSymbol = resultSet.getString("piece_symbol");
            Piece piece = AbstractPiece.of(pieceSymbol, position);
            pieces.put(position, piece);
        }
        return pieces;
    }

    public void addPieces(final int boardId, final Map<Position, Piece> pieces,
        final Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        connection.setAutoCommit(false);
        try {
            for (Piece piece : pieces.values()) {
                if (Objects.isNull(piece)) {
                    continue;
                }
                String query = "INSERT INTO piece(board_id, piece_position, piece_symbol) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                addPieceDataInit(boardId, preparedStatement, piece);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        } finally {
            disconnect(preparedStatement, null);
        }
    }

    private void addPieceDataInit(final int boardId, final PreparedStatement preparedStatement,
        final Piece piece) throws SQLException {
        preparedStatement.setInt(1, boardId);
        preparedStatement.setString(2, piece.position().changedPositionToString());
        preparedStatement.setString(3, piece.symbol());
    }

    public void updatePiece(final Board board, final MovePieceDTO movePieceDTO,
        final Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            Map<Position, Piece> pieces = board.pieces();
            Piece source = pieces.get(Position.of(movePieceDTO.getTarget()));
            connection.setAutoCommit(false);
            String deleteQuery = "DELETE FROM piece WHERE board_id = ? AND (piece_position = ? OR piece_position = ?)";
            preparedStatement = connection.prepareStatement(deleteQuery);
            deleteBoardDataInit(movePieceDTO.getBoardId(), movePieceDTO, preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String insertQuery = "INSERT INTO piece(board_id, piece_position, piece_symbol) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            updatePieceDataInit(movePieceDTO, preparedStatement, source);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } finally {
            disconnect(preparedStatement, null);
        }
    }

    private void deleteBoardDataInit(final int boardId, final MovePieceDTO movePieceDTO,
        final PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, boardId);
        preparedStatement.setString(2, movePieceDTO.getSource());
        preparedStatement.setString(3, movePieceDTO.getTarget());
    }

    private void updatePieceDataInit(final MovePieceDTO movePieceDTO,
        final PreparedStatement preparedStatement, final Piece source) throws SQLException {
        preparedStatement.setInt(1, movePieceDTO.getBoardId());
        preparedStatement.setString(2, movePieceDTO.getTarget());
        preparedStatement.setString(3, source.symbol());
    }
}
