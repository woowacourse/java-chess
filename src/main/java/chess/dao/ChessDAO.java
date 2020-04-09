package chess.dao;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.Turn;
import chess.domain.dto.BoardStatusDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceGenerator;
import chess.domain.piece.Team;
import chess.template.JdbcTemplate;
import chess.template.SelectJdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessDAO {
    private static ChessDAO instance;

    private ChessDAO() {
    }

    public static ChessDAO getInstance() {
        if (instance == null) {
            instance = new ChessDAO();
        }
        return instance;
    }

    public void saveGame(BoardStatusDto boardStatusDto) throws SQLException {
        savePieces(boardStatusDto.getPieces());
        saveTurn(boardStatusDto.getTurn());
    }

    private void savePieces(Map<String, Piece> pieces) throws SQLException {
        clearPieces();
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                for (Piece piece : pieces.values()) {
                    statement.setString(1, piece.getPosition().toString());
                    statement.setString(2, piece.toString());
                    statement.setString(3, piece.getTeam().toString());
                    statement.addBatch();
                }
            }
        };
        String query = "INSERT INTO Pieces (position, representation, team) VALUES (?, ?, ?)";
        template.insertBatch(query);
    }

    private void saveTurn(Turn turn) throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                String team = turn.getTeam().toString();
                statement.setString(1, team);
                statement.setString(2, team);
            }
        };
        String query = "INSERT INTO Turn (turn) VALUES (?) ON DUPLICATE KEY UPDATE turn=?";
        template.insert(query);
    }

    private void clearPieces() throws SQLException {
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) {
            }
        };
        String query = "DELETE FROM Pieces";
        template.apply(query);
    }

    public Board loadGame() throws SQLException {
        return new Board(loadPieces(), loadTurn());
    }

    private Pieces loadPieces() throws SQLException {
        SelectJdbcTemplate template = new SelectJdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) {
            }

            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                Map<Position, Piece> pieces = new HashMap<>();
                while (rs.next()) {
                    pieces.put(new Position(rs.getString("position")), generatePiece(rs));
                }
                return new Pieces(pieces);
            }
        };
        String query = "SELECT * FROM Pieces";
        return (Pieces) template.select(query);
    }

    private Turn loadTurn() throws SQLException {
        SelectJdbcTemplate template = new SelectJdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) {
            }

            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return null;
                }
                return new Turn(Team.valueOf(rs.getString("turn")));
            }
        };
        String query = "SELECT * FROM Turn";
        return (Turn) template.select(query);
    }

    private Piece generatePiece(ResultSet rs) throws SQLException {
        return PieceGenerator.generate(
                rs.getString("representation"),
                rs.getString("team"),
                rs.getString("position")
        );
    }
}
