package chess.dao;

import chess.domain.PieceFactory;
import chess.domain.Point;
import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.vo.PieceVo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
    private static final String INSERT_PIECE = "INSERT INTO piece(game_id, name, x, y, team) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PIECE = "SELECT name, x, y, team FROM piece WHERE game_id = ?";
    private static final String UPDATE_PIECE_BY_POSITION = "UPDATE piece SET x = ?, y = ? WHERE game_id = ? and x = ? and y = ?";
    private static final String DELETE_PIECE_BY_POSITION = "DELETE FROM piece WHERE game_id = ? and x = ? and y = ?";
    private static final String INSERT_BLANK_BY_POSITION = "INSERT INTO piece(game_id, name, x, y, team) VALUES (?, ?, ?, ?, ?)";

    private final DataSource dataSource;

    public PieceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(int gameId, PieceVo pieceVo) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeAdd(con, gameId, pieceVo);
        }
    }

    private void executeAdd(Connection con, int gameId, PieceVo pieceVo) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_PIECE)) {
            pstmt.setInt(1, gameId);
            Piece piece = pieceVo.getPiece();
            Point point = pieceVo.getPoint();
            pstmt.setString(2, piece.getType().name());
            pstmt.setInt(3, point.getPositionX());
            pstmt.setInt(4, point.getPositionY());
            pstmt.setBoolean(5, piece.getTeam().equals(Team.WHITE));
            pstmt.executeUpdate();
        }
    }

    public List<PieceVo> findPieceById(int gameId) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindPiece(con, gameId);
        }
    }

    private List<PieceVo> executeFindPiece(Connection con, int gameId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_PIECE)) {
            pstmt.setInt(1, gameId);
            return getPiece(pstmt);
        }
    }

    private List<PieceVo> getPiece(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {

            return getPieceVo(rs);
        }
    }

    private List<PieceVo> getPieceVo(ResultSet rs) throws SQLException {
        List<PieceVo> pieceVos = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            boolean team = rs.getBoolean("team");
            Point point = new Point(x, y);
            Piece piece = PieceFactory.of(name, team ? Team.WHITE : Team.BLACK);
            PieceVo pieceVo = new PieceVo(point, piece);
            pieceVos.add(pieceVo);
        }
        return pieceVos;
    }

    public void updatePosition(int gameId, Point start, Point end) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeUpdatePosition(con, gameId, start, end);
        }
    }

    private void executeUpdatePosition(Connection con, int gameId, Point start, Point end) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_PIECE_BY_POSITION)) {
            pstmt.setInt(1, end.getPositionX());
            pstmt.setInt(2, end.getPositionY());
            pstmt.setInt(3, gameId);
            pstmt.setInt(4, start.getPositionX());
            pstmt.setInt(5, start.getPositionY());
            pstmt.executeUpdate();
        }
    }

    public void insertBlank(int gameId, Point target) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeInsertBlank(con, gameId, target);
        }
    }

    private void executeInsertBlank(Connection con, int gameId, Point target) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_BLANK_BY_POSITION)) {
            pstmt.setInt(1, gameId);
            pstmt.setString(2, "BLANK");
            pstmt.setInt(3, target.getPositionX());
            pstmt.setInt(4, target.getPositionY());
            pstmt.setBoolean(5, false);
            pstmt.executeUpdate();
        }
    }

    public void deletePieceByPosition(int gameId, Point target) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeDeletePiece(con, gameId, target);
        }
    }

    private void executeDeletePiece(Connection con, int gameId, Point target) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_PIECE_BY_POSITION)) {
            pstmt.setInt(1, gameId);
            pstmt.setInt(2, target.getPositionX());
            pstmt.setInt(3, target.getPositionY());
            pstmt.executeUpdate();
        }
    }
}
