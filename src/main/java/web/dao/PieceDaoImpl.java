package web.dao;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import web.DBConnectionSetUp;

public class PieceDaoImpl implements PieceDao {

    private static final String INVALID_PIECE_DB_ERROR = "기물 정보가 DB에 잘못 저장되어 있습니다.";

    @Override
    public List<Piece> load() throws SQLException {
        String query = "select * from pieces";

        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        try (con; pstmt; rs) {
            if (!rs.next()) {
                return null;
            }

            List<Piece> newPiece = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("piece_name");
                String positionValue = rs.getString("position");
                Position position = ChessBoardPosition.from(positionValue);
                newPiece.add(daoToPiece(name, position));
            }
            return newPiece;
        }
    }

    private Piece daoToPiece(String name, Position position) {
        Color color = Color.WHITE;
        if ("B".equals(name.substring(0, 1))) {
            color = Color.BLACK;
        }
        if ("R".equals(name.substring(1, 2))) {
            return new Rook(color, position);
        }
        if ("N".equals(name.substring(1, 2))) {
            return new Knight(color, position);
        }
        if ("B".equals(name.substring(1, 2))) {
            return new Bishop(color, position);
        }
        if ("Q".equals(name.substring(1, 2))) {
            return new Queen(color, position);
        }
        if ("K".equals(name.substring(1, 2))) {
            return new King(color, position);
        }
        if ("P".equals(name.substring(1, 2))) {
            return new Pawn(color, position);
        }
        throw new IllegalArgumentException(INVALID_PIECE_DB_ERROR);
    }

    @Override
    public void savePiece(String position, Piece piece) {
        String query = "insert into pieces values (?, ?)";
        Connection con = DBConnectionSetUp.getConnection();
        try (con; PreparedStatement pstmt = con.prepareStatement(query)) {
            String symbol = Symbol.findBySymbol(piece.getClass());
            if (piece.isSameColor(Color.BLACK)) {
                pstmt.setString(1, position);
                pstmt.setString(2, "B" + symbol);
            }
            if (piece.isSameColor(Color.WHITE)) {
                pstmt.setString(1, position);
                pstmt.setString(2, "W" + symbol);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("기물의 위치는 중복될 수 없습니다.");
        }
    }

    @Override
    public void removeAll() throws SQLException {
        delete("pieces");
    }

    private void delete(String tableName) throws SQLException {
        String query = "truncate table " + tableName;
        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);

        try (con; pstmt) {
            pstmt.executeUpdate();
        }
    }

    @Override
    public void initTurn() throws SQLException {
        delete("turn");
        String query = "insert into turn values (?)";
        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);

        try (con; pstmt) {
            pstmt.setString(1, Color.WHITE.name());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deletePiece(String position) {
        String query = "delete from pieces where position = ?";
        Connection con = DBConnectionSetUp.getConnection();

        try (con; PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, position);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
    }

    @Override
    public void updateTurn(Color color) throws SQLException {
        delete("turn");
        String query = "insert into turn values (?)";
        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);

        try (con; pstmt) {
            pstmt.setString(1, color.name());
            pstmt.executeUpdate();
        }
    }

    @Override
    public String findTurn() throws SQLException {
        String query = "select * from turn";
        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        try (con; pstmt; rs) {
            if (!rs.next()) {
                return null;
            }
            return rs.getString("turn");
        }
    }

    @Override
    public Map<Position, Piece> findPieces() throws SQLException {
        String query = "select * from pieces";
        Connection con = DBConnectionSetUp.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
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
}
