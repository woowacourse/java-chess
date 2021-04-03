package chess.dao;

import chess.SymbolToPieceConvert;
import chess.domain.board.Pieces;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.WebPiecesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public final class PiecesDAO extends AbstractDAO {

    private static final PiecesDAO piecesDAO = new PiecesDAO();

    private PiecesDAO() {
    }

    public static PiecesDAO instance() {
        return piecesDAO;
    }

    public Pieces joinPieces(final int boardId, Connection connection) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String query = "SELECT piece_position, piece_symbol FROM piece WHERE board_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, boardId);
            ResultSet resultSet = pstmt.executeQuery();
            Map<Position, Piece> pieces = getPieces(resultSet);
            return new Pieces(pieces);
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 보드 정보입니다.");
        } finally {
            closePreparedStatemnt(pstmt);
        }
    }

    private void closePreparedStatemnt(PreparedStatement pstmt) throws SQLException {
        if (!Objects.isNull(pstmt)) {
            pstmt.close();
        }
    }

    private Map<Position, Piece> getPieces(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> pieces = new Pieces().pieces();
        while (resultSet.next()) {
            String piecePosition = resultSet.getString("piece_position");
            Position position = Position.of(piecePosition);
            String pieceSymbol = resultSet.getString("piece_symbol");
            Piece piece = SymbolToPieceConvert.convert(pieceSymbol, position);
            pieces.put(position, piece);
        }
        return pieces;
    }

    public void addPieces(final int boardId, final Map<Position, Piece> pieces, Connection connection)
        throws SQLException {
        PreparedStatement pstmt = null;
        connection.setAutoCommit(false);
        try {
            for (Piece piece : pieces.values()) {
                if (Objects.isNull(piece)) {
                    continue;
                }
                String query = "INSERT INTO piece(board_id, piece_position, piece_symbol) VALUES (?, ?, ?)";
                pstmt = connection.prepareStatement(query);
                addPieceDataInit(boardId, pstmt, piece);
                pstmt.executeUpdate();
                pstmt.close();
            }
        } finally {
            closePreparedStatemnt(pstmt);
        }
    }

    private void addPieceDataInit(int boardId, PreparedStatement pstmt, Piece piece)
        throws SQLException {
        pstmt.setInt(1, boardId);
        pstmt.setString(2, piece.position().positionToString());
        pstmt.setString(3, piece.symbol());
    }

    public void updatePiece(final int boardId, WebPiecesDTO webPiecesDTO, Connection connection) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            Map<Position, Piece> pieces = webPiecesDTO.getPieces();
            Piece source = pieces.get(webPiecesDTO.getTarget());
            connection.setAutoCommit(false);
            String deleteQuery = "DELETE FROM piece WHERE board_id = ? AND (piece_position = ? OR piece_position = ?)";
            pstmt = connection.prepareStatement(deleteQuery);
            deleteBoardDataInit(boardId, webPiecesDTO, pstmt);
            pstmt.executeUpdate();
            pstmt.close();

            String insertQuery = "INSERT INTO piece(board_id, piece_position, piece_symbol) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(insertQuery);
            updatePieceDataInit(boardId, webPiecesDTO, pstmt, source);
            pstmt.executeUpdate();
            pstmt.close();
        } finally {
            closePreparedStatemnt(pstmt);
        }
    }

    private void deleteBoardDataInit(int boardId, WebPiecesDTO webPiecesDTO,
        PreparedStatement pstmt) throws SQLException {
        pstmt.setInt(1, boardId);
        pstmt.setString(2, webPiecesDTO.getSource().positionToString());
        pstmt.setString(3, webPiecesDTO.getTarget().positionToString());
    }

    private void updatePieceDataInit(int boardId, WebPiecesDTO webPiecesDTO,
        PreparedStatement pstmt, Piece source) throws SQLException {
        pstmt.setInt(1, boardId);
        pstmt.setString(2, webPiecesDTO.getTarget().positionToString());
        pstmt.setString(3, source.symbol());
    }
}
