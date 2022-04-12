package web.dao;

import static chess.domain.piece.Color.*;
import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.domain.piece.Symbol;
import chess.domain.position.ChessBoardPosition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import web.DBConnectionSetUp;

public class PieceDaoImpl implements PieceDao {

    private static final String INVALID_PIECE_DB_ERROR = "기물 정보가 DB에 잘못 저장되어 있습니다.";

    @Override
    public Map<Position, Piece> findPieces(Long gameId) throws SQLException {
        String query = "SELECT * FROM pieces WHERE game_id = ?";
        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setLong(1, gameId);
        ResultSet rs = pstmt.executeQuery();

        try (con; pstmt; rs) {
            Map<Position, Piece> result = new HashMap<>();
            while (rs.next()) {
                Position position = ChessBoardPosition.from(rs.getString("position"));
                result.put(position, daoToPiece(rs.getString("piece_name"), position));
            }
            return result;
        }
    }

    private Piece daoToPiece(String name, Position position) {
        Color color = from(name.substring(0, 5));
        if ("R".equals(name.substring(5, 6))) {
            return new Rook(color, position);
        }
        if ("N".equals(name.substring(5, 6))) {
            return new Knight(color, position);
        }
        if ("B".equals(name.substring(5, 6))) {
            return new Bishop(color, position);
        }
        if ("Q".equals(name.substring(5, 6))) {
            return new Queen(color, position);
        }
        if ("K".equals(name.substring(5, 6))) {
            return new King(color, position);
        }
        if ("P".equals(name.substring(5, 6))) {
            return new Pawn(color, position);
        }
        throw new IllegalArgumentException(INVALID_PIECE_DB_ERROR);
    }

    @Override
    public void savePiece(String position, Piece piece, Long gameId) {
        String query = "INSERT INTO pieces (position, piece_name, game_id) VALUES (?, ?, ?)";
        Connection con = DBConnectionSetUp.getConnection();
        try (con; PreparedStatement pstmt = con.prepareStatement(query)) {
            String symbol = Symbol.findBySymbol(piece.getClass());
            if (piece.isSameColor(BLACK)) {
                pstmt.setString(1, position);
                pstmt.setString(2, BLACK.name() + symbol);
                pstmt.setLong(3, gameId);
            }
            if (piece.isSameColor(WHITE)) {
                pstmt.setString(1, position);
                pstmt.setString(2, WHITE.name() + symbol);
                pstmt.setLong(3, gameId);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("기물의 위치는 중복될 수 없습니다.");
        }
    }

    @Override
    public void deletePiece(String position, Long id) {
        String query = "DELETE FROM pieces WHERE position = ? AND game_id = ?";
        Connection con = DBConnectionSetUp.getConnection();

        try (con; PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, position);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
    }
}
