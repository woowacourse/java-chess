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

    public void add(int gameId, PieceDto pieceDto) {
        try (Connection conn = dataSource.getConnection()) {
            executeAdd(conn, gameId, pieceDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeAdd(Connection conn, int gameId, PieceDto pieceDto) {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_PIECE)) {
            stmt.setInt(1, gameId);
            stmt.setString(2, pieceDto.getName());
            stmt.setInt(3, pieceDto.getX());
            stmt.setInt(4, pieceDto.getY());
            stmt.setBoolean(5, pieceDto.isTeam());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PieceDto> findPieceById(int gameId) {
        try (Connection conn = dataSource.getConnection()) {
            return executeFindPiece(conn, gameId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PieceDto> executeFindPiece(Connection conn, int gameId) {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_PIECE)) {
            stmt.setInt(1, gameId);
            return getPiece(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PieceDto> getPiece(PreparedStatement stmt) {
        try (ResultSet rs = stmt.executeQuery()) {

            return getPieceVo(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PieceDto> getPieceVo(ResultSet rs) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                boolean team = rs.getBoolean("team");
                PieceDto pieceDto = new PieceDto(x, y, name, team);
                pieceDtos.add(pieceDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pieceDtos;
    }

    public void updatePosition(int gameId, Point start, Point end) {
        try (Connection conn = dataSource.getConnection()) {
            executeUpdatePosition(conn, gameId, start, end);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeUpdatePosition(Connection conn, int gameId, Point start, Point end) {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_PIECE_BY_POSITION)) {
            stmt.setInt(1, end.getPositionX());
            stmt.setInt(2, end.getPositionY());
            stmt.setInt(3, gameId);
            stmt.setInt(4, start.getPositionX());
            stmt.setInt(5, start.getPositionY());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertBlank(int gameId, Point target) {
        try (Connection conn = dataSource.getConnection()) {
            executeInsertBlank(conn, gameId, target);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeInsertBlank(Connection conn, int gameId, Point target) {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_BLANK_BY_POSITION)) {
            stmt.setInt(1, gameId);
            stmt.setString(2, "BLANK");
            stmt.setInt(3, target.getPositionX());
            stmt.setInt(4, target.getPositionY());
            stmt.setBoolean(5, false);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePieceByPosition(int gameId, Point target) {
        try (Connection conn = dataSource.getConnection()) {
            executeDeletePiece(conn, gameId, target);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeDeletePiece(Connection conn, int gameId, Point target) {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_PIECE_BY_POSITION)) {
            stmt.setInt(1, gameId);
            stmt.setInt(2, target.getPositionX());
            stmt.setInt(3, target.getPositionY());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
