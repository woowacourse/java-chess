package chess.dao;

import chess.domain.ChessGame;
import chess.domain.pieces.PieceType;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.*;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.dto.ChessDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessDAO {
    private static final int EMPTY = 0;
    private static final int MIN_BOARD_COORDINATE = 1;
    private static final int MAX_BOARD_COORDINATE = 8;
    private final Connection conn;

    public ChessDAO(Connection conn) {
        this.conn = conn;
    }

    public void addChessGame(ChessDTO chessDTO) throws SQLException {
        String query = "INSERT INTO chess VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        for (int index = MIN_BOARD_COORDINATE; index <= MAX_BOARD_COORDINATE; index++) {
            pstmt.setString(index, chessDTO.getRanks().get(index - 1));
        }
        pstmt.setString(9, chessDTO.getTurn());
        pstmt.executeUpdate();
    }

    public void deleteChessGame() throws SQLException {
        String query = "DELETE FROM chess";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.executeUpdate();
    }

    public ChessGame findChessGame() throws SQLException {
        String query = "SELECT * FROM chess";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        return makeChessGame(rs);
    }

    private ChessGame makeChessGame(ResultSet rs) throws SQLException {
        return new ChessGame(new Board(makeBoard(rs)), Team.valueOf(rs.getString("turn")));
    }

    private Map<Position, Piece> makeBoard(ResultSet rs) throws SQLException {
        Map<Position, Piece> boardState = new HashMap<>();

        for (int i = MIN_BOARD_COORDINATE; i <= MAX_BOARD_COORDINATE; i++) {
            String string = rs.getString("rank" + i);
            makeBoardRank(boardState, i, string);
        }

        return boardState;
    }

    private void makeBoardRank(Map<Position, Piece> boardState, int i, String string) {
        for (int j = MIN_BOARD_COORDINATE; j <= MAX_BOARD_COORDINATE; j++) {
            Position position = Positions.matchWith(j, i);
            String symbol = String.valueOf(string.charAt(j - 1));
            Piece piece = PieceType.getPieceType(symbol).createPiece(position);
            boardState.put(position, piece);
        }
    }

    public boolean isTableEmpty() throws SQLException {
        String query = "SELECT COUNT(*) FROM chess;";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return true;
        }

        return rs.getInt(1) == EMPTY;
    }
}
