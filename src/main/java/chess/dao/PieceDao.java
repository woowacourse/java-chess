package chess.dao;

import chess.domain.Point;
import chess.dto.PieceDto;

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
    private static PieceDao pieceDao;

    private PieceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static PieceDao getInstance(DataSource dataSource) {
        if (pieceDao == null) {
            pieceDao = new PieceDao(dataSource);
        }
        return pieceDao;
    }

    public void add(int gameId, PieceDto pieceDto) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            executeAdd(con, gameId, pieceDto);
        }
    }

    private void executeAdd(Connection con, int gameId, PieceDto pieceDto) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_PIECE)) {
            pstmt.setInt(1, gameId);
            pstmt.setString(2, pieceDto.getName());
            pstmt.setInt(3, pieceDto.getX());
            pstmt.setInt(4, pieceDto.getY());
            pstmt.setBoolean(5, pieceDto.isTeam());
            pstmt.executeUpdate();
        }
    }

    public List<PieceDto> findPieceById(int gameId) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            return executeFindPiece(con, gameId);
        }
    }

    private List<PieceDto> executeFindPiece(Connection con, int gameId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_PIECE)) {
            pstmt.setInt(1, gameId);
            return getPiece(pstmt);
        }
    }

    private List<PieceDto> getPiece(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {

            return getPieceVo(rs);
        }
    }

    private List<PieceDto> getPieceVo(ResultSet rs) throws SQLException {
        List<PieceDto> pieceDtos = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            boolean team = rs.getBoolean("team");
            PieceDto pieceDto = new PieceDto(x, y, name, team);
            pieceDtos.add(pieceDto);
        }
        return pieceDtos;
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
