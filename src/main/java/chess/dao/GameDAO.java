package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMapper;
import chess.domain.piece.Pieces;

public class GameDAO {
    private static final GameDAO GAME_DAO = new GameDAO();

    public static GameDAO getInstance() {
        return GAME_DAO;
    }

    public void removeAllPiecesById(int roomId) throws SQLException {
        String query = "DELETE FROM board WHERE room_id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(final PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, roomId);
            }
        };

        jdbcTemplate.executeUpdate(query);

    }

    public void addAllPiecesById(int roomId, Pieces pieces) throws SQLException {
        String query = "INSERT INTO board(room_id, piece_name, piece_position, piece_color) VALUES (?, ?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(final PreparedStatement pstmt) throws SQLException {
                for (Position position : pieces.getPieces().keySet()) {
                    Piece piece = pieces.getPieceByPosition(position);

                    pstmt.setInt(1, roomId);
                    pstmt.setString(2, piece.getSymbol());
                    pstmt.setString(3, position.getPosition());
                    pstmt.setString(4, piece.getColor().name());
                    pstmt.addBatch();
                }
            }
        };

        jdbcTemplate.executeBatch(query);
    }

    public void updatePieceByPosition(String currentPosition, String nextPosition) throws SQLException {
        String query = "UPDATE board SET piece_position = ? WHERE piece_position = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(final PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, nextPosition);
                pstmt.setString(2, currentPosition);
            }
        };

        jdbcTemplate.executeUpdate(query);

    }

    public void deletePieceByPosition(String position) throws SQLException {
        String query = "DELETE FROM board WHERE piece_position = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(final PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, position);
            }
        };

        jdbcTemplate.executeUpdate(query);
    }

    public void addPieceByPosition(int roomId, Position position, Piece piece) throws SQLException {
        String query = "INSERT INTO board(room_id, piece_name, piece_position, piece_color) VALUES (?, ?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(final PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, roomId);
                pstmt.setString(2, piece.getSymbol());
                pstmt.setString(3, position.getPosition());
                pstmt.setString(4, piece.getColor().name());
            }
        };

        jdbcTemplate.executeUpdate(query);
    }

    public Piece findPieceByPosition(String position) throws SQLException {
        String query = "SELECT piece_name FROM board WHERE piece_position = ?";

        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public Object mapRow(final ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return Blank.getInstance();
                }
                return PieceMapper.getInstance().findDBPiece(rs.getString("piece_name"));
            }

            @Override
            public void setParameters(final PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, position);
            }
        };

        return (Piece)selectJdbcTemplate.executeQuery(query);
    }

    public Map<Position, Piece> findAllPiecesById(int roomId) throws SQLException {
        String query = "SELECT piece_name, piece_position, piece_color FROM board WHERE room_id = ?";

        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public Object mapRow(final ResultSet rs) throws SQLException {
                Map<Position, Piece> pieces = new HashMap<>();

                while (rs.next()) {
                    String name = rs.getString("piece_name");
                    String position = rs.getString("piece_position");
                    pieces.put(Position.of(position), PieceMapper.getInstance().findDBPiece(name));
                }

                return pieces;
            }

            @Override
            public void setParameters(final PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, roomId);
            }
        };

        return (Map<Position, Piece>)selectJdbcTemplate.executeQuery(query);
    }
}
