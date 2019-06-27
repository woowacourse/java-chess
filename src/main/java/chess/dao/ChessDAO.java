package chess.dao;

import chess.database.DBConnector;
import chess.domain.PieceSymbol;
import chess.domain.ChessGame;
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
    private static final int MIN_BOARD_COORDINATE = 1;
    private static final int MAX_BOARD_COORDINATE = 8;
    private static final int MIN_BOARD_INDEX = 0;
    private static final int MAX_BOARD_INDEX = 7;

    private static ChessDAO chessDAO = new ChessDAO();

    public static ChessDAO getInstance() {
        return chessDAO;
    }

    public void addChessGame(ChessDTO chessDTO) throws SQLException {
        String query = "INSERT INTO chess VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);

            for (int i = MIN_BOARD_COORDINATE; i <= MAX_BOARD_COORDINATE; i++) {
                pstmt.setString(i, chessDTO.getRanks().get(i - 1));
            }

            pstmt.setString(9, chessDTO.getTurn());
            pstmt.executeUpdate();
        }
    }

    public void deleteChessGame() throws SQLException {
        String query = "DELETE FROM chess";

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }

    public void updateChessGame(ChessDTO chessDTO) throws SQLException {
        deleteChessGame();
        addChessGame(chessDTO);
    }

    public ChessGame findChessGame() throws SQLException {
        String query = "SELECT * FROM chess";

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return makeChessGame(rs);
        }
    }

    private ChessGame makeChessGame(ResultSet rs) throws SQLException {
        Map<Position, Piece> boardState = new HashMap<>();
        for (int i = MIN_BOARD_COORDINATE; i <= MAX_BOARD_COORDINATE; i++) {
            String string = rs.getString("rank" + i);
            for (int j = MIN_BOARD_INDEX; j <= MAX_BOARD_INDEX; j++) {
                String symbol = String.valueOf(string.charAt(j));
                Position position = Positions.matchWith(j + 1 , i);
                Piece piece = makePiece(position, symbol);
                boardState.put(position, piece);
            }
        }
        String turn = rs.getString("turn");
        return new ChessGame(new Board(boardState), Team.valueOf(turn));
    }

    private Piece makePiece(Position position, String symbol) {
        return PieceSymbol.getPieceSymbol(symbol).getPiece(position);
    }

    public boolean isTableEmpty() throws SQLException {
        String query = "SELECT COUNT(*) FROM chess;";

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return true;
            }

            return rs.getInt(1) == 0;
        }
    }
}
