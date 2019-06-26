package chess.dao;

import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.*;
import chess.domain.position.Position;
import chess.domain.position.PositionManager;
import chess.dto.ChessDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ChessDAO {
    private final Connection conn;
    private static Map<String, BiFunction<Position, String, Piece>> aaa = new HashMap<>();

    public ChessDAO(Connection conn) {
        this.conn = conn;
    }

    public void addChessGame(ChessDTO chessDTO) throws SQLException {
        String query = "INSERT INTO chess VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, chessDTO.getRanks().get(0));
        pstmt.setString(2, chessDTO.getRanks().get(1));
        pstmt.setString(3, chessDTO.getRanks().get(2));
        pstmt.setString(4, chessDTO.getRanks().get(3));
        pstmt.setString(5, chessDTO.getRanks().get(4));
        pstmt.setString(6, chessDTO.getRanks().get(5));
        pstmt.setString(7, chessDTO.getRanks().get(6));
        pstmt.setString(8, chessDTO.getRanks().get(7));
        pstmt.setString(9, chessDTO.getTurn());
        pstmt.executeUpdate();
    }

    public void deleteChessGame() throws SQLException {
        String query = "DELETE FROM chess";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void updateChessGame(ChessDTO chessDTO) throws SQLException {
        deleteChessGame();
        addChessGame(chessDTO);
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
        Map<Position, Piece> boardState = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            String string = rs.getString("rank" + i);
            for (int j = 0; j < 8; j++) {
                String symbol = String.valueOf(string.charAt(j));
                Position position = PositionManager.getMatchPosition(j + 1 , i);
                Piece piece = makePiece(position, symbol);
                boardState.put(position, piece);
            }
        }
        String turn = rs.getString("turn");
        return new ChessGame(new Board(boardState), Team.valueOf(turn));
    }

    private Piece makePiece(Position position, String symbol) {
        if (symbol.equals("p")) {
            return new Pawn(position, Team.WHITE);
        }
        if (symbol.equals("r")) {
            return new Rook(position, Team.WHITE);
        }
        if (symbol.equals("n")) {
            return new Knight(position, Team.WHITE);
        }
        if (symbol.equals("k")) {
            return new King(position, Team.WHITE);
        }
        if (symbol.equals("q")) {
            return new Queen(position, Team.WHITE);
        }
        if (symbol.equals("b")) {
            return new Bishop(position, Team.WHITE);
        }
        if (symbol.equals("P")) {
            return new Pawn(position, Team.BLACK);
        }
        if (symbol.equals("R")) {
            return new Rook(position, Team.BLACK);
        }
        if (symbol.equals("N")) {
            return new Knight(position, Team.BLACK);
        }
        if (symbol.equals("K")) {
            return new King(position, Team.BLACK);
        }
        if (symbol.equals("Q")) {
            return new Queen(position, Team.BLACK);
        }
        if (symbol.equals("B")) {
            return new Bishop(position, Team.BLACK);
        }
        return new Blank(position, Team.BLANK);
    }

    public boolean isTableEmpty() throws SQLException {
        String query = "SELECT COUNT(*) FROM chess;";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return true;
        }
        return rs.getInt(1) == 0;
    }
}
