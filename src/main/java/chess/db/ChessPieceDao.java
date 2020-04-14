package chess.db;

import chess.domains.piece.Piece;
import chess.domains.position.Position;
import chess.util.JdbcTemplate;
import chess.util.ParameterSetter;
import chess.util.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// TODO: 2020-04-11 SavedPiece 클래스 추가 후 select 결과로 객체 리턴?
public class ChessPieceDao implements PieceDao {
    @Override
    public int countSavedPieces(String gameId) throws SQLException {
        String query = "SELECT COUNT(*) FROM board_status WHERE game_id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        ParameterSetter parameterSetter = new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameId);
            }
        };

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return 0;
                }
                return rs.getInt("count(*)");
            }
        };

        return (Integer) jdbcTemplate.executeQuery(query, parameterSetter, rowMapper);
    }

    @Override
    public void addInitialPieces(List<ChessPiece> chessPieces) throws SQLException {
        for (ChessPiece piece : chessPieces) {
            addPiece(piece);
        }
    }

    @Override
    public void addPiece(ChessPiece piece) throws SQLException {
        String query = "INSERT INTO board_status (game_id, position, piece) VALUES (?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        ParameterSetter parameterSetter = new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, piece.getGameId());
                pstmt.setString(2, piece.getPosition());
                pstmt.setString(3, piece.getPiece());
            }
        };

        jdbcTemplate.executeUpdate(query, parameterSetter);
    }

    @Override
    public String findPieceNameByPosition(String gameId, Position position) throws SQLException {
        String query = "SELECT piece FROM board_status WHERE game_id = ? AND position = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        ParameterSetter parameterSetter = new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameId);
                pstmt.setString(2, position.name());
            }
        };

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return null;
                }
                return rs.getString("piece");
            }
        };

        return (String) jdbcTemplate.executeQuery(query, parameterSetter, rowMapper);
    }

    @Override
    public void updatePiece(String gameId, Position position, Piece piece) throws SQLException {
        String query = "UPDATE board_status SET piece = ? WHERE game_id = ? AND position = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        ParameterSetter parameterSetter = new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, piece.name());
                pstmt.setString(2, gameId);
                pstmt.setString(3, position.name());
            }
        };

        jdbcTemplate.executeUpdate(query, parameterSetter);
    }

    @Override
    public void deleteBoardStatus(String gameId) throws SQLException {
        String query = "DELETE FROM board_status WHERE game_id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        ParameterSetter parameterSetter = new ParameterSetter() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameId);
            }
        };

        jdbcTemplate.executeUpdate(query, parameterSetter);
    }
}
