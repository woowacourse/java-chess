package chess.dao;

import chess.domain.board.BoardSquare;
import chess.domain.board.CastlingSetting;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChessBoardDAO {

    public void insert(String gameId, Map<BoardSquare, Piece> board,
        Set<CastlingSetting> castlingElements) throws SQLException {
        String query = "INSERT INTO CHESS_BOARD_TB(GAME_ID, BOARDSQUARE_NM, PIECE_NM, CASTLING_ELEMENT_YN) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        for (BoardSquare boardSquare : board.keySet()) {
            pstmt.setString(1, gameId);
            pstmt.setString(2, boardSquare.getName());
            pstmt.setString(3, PieceFactory.getName(board.get(boardSquare)));
            pstmt.setString(4,
                getCastlingElement(boardSquare, board.get(boardSquare), castlingElements));
            pstmt.executeUpdate();
        }
    }

    private String getCastlingElement(BoardSquare boardSquare, Piece piece,
        Set<CastlingSetting> castlingElements) {
        boolean containCastling = castlingElements.stream()
            .anyMatch(castlingSetting -> castlingSetting.isCastlingBefore(boardSquare, piece));
        return containCastling ? "Y" : "N";
    }

    public void insertBoard(String gameId, BoardSquare boardSquare, Piece piece)
        throws SQLException {
        String query = "INSERT INTO CHESS_BOARD_TB(GAME_ID, BOARDSQUARE_NM, PIECE_NM, CASTLING_ELEMENT_YN) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.setString(2, boardSquare.getName());
        pstmt.setString(3, PieceFactory.getName(piece));
        pstmt.setString(4, "N");
        pstmt.executeUpdate();
    }

    public void deleteBoard(String gameId, BoardSquare boardSquare)
        throws SQLException {
        String query = "DELETE FROM CHESS_BOARD_TB WHERE GAME_ID = ? AND BOARDSQUARE_NM = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.setString(2, boardSquare.getName());
        pstmt.executeUpdate();
    }

    public Set<CastlingSetting> getCastlingElements(String gameId)
        throws SQLException, IllegalAccessException {
        String query = "SELECT BOARDSQUARE_NM, PIECE_NM FROM CHESS_BOARD_TB WHERE GAME_ID = ? AND CASTLING_ELEMENT_YN = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.setString(2, "Y");
        ResultSet rs = pstmt.executeQuery();
        Set<CastlingSetting> castlingElements = new HashSet<>();

        if (!rs.next()) {
            throw new IllegalAccessException("인자 없음");
        }
        do {
            castlingElements.add(CastlingSetting.of(BoardSquare.of(rs.getString("BOARDSQUARE_NM")),
                PieceFactory.of(rs.getString("PIECE_NM"))));
        } while (rs.next());
        return castlingElements;
    }

    public Map<BoardSquare, Piece> getEnpassantBoard(String gameId) throws SQLException {
        String query = "SELECT EN_PASSANT_NM, PIECE_NM FROM CHESS_BOARD_TB WHERE GAME_ID = ? AND EN_PASSANT_NM IS NOT NULL";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();
        Map<BoardSquare, Piece> board = new HashMap<>();

        if (!rs.next()) {
            return board;
        }
        do {
            board.put(BoardSquare.of(rs.getString("EN_PASSANT_NM")),
                PieceFactory.of(rs.getString("PIECE_NM")));
        } while (rs.next());
        return board;
    }

    public Map<BoardSquare, Piece> getBoard(String gameId)
        throws SQLException, IllegalAccessException {
        String query = "SELECT BOARDSQUARE_NM, PIECE_NM FROM CHESS_BOARD_TB WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();
        Map<BoardSquare, Piece> board = new HashMap<>();

        if (!rs.next()) {
            throw new IllegalAccessException("인자 없음");
        }
        do {
            board.put(BoardSquare.of(rs.getString("BOARDSQUARE_NM")),
                PieceFactory.of(rs.getString("PIECE_NM")));
        } while (rs.next());
        return board;
    }

    public void delete(String gameId) throws SQLException {
        String query = "DELETE FROM CHESS_BOARD_TB WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.executeUpdate();
    }
}
