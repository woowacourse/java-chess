package chess.repository;

import static chess.domain.piece.PieceType.INITIAL_PAWN;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.PAWN;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.GameState;
import chess.domain.piece.Pieces;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.square.Squares;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveGame(final ChessGame game) {
        final String query = "INSERT INTO game (team) VALUES (?)";
        final int gameId = jdbcTemplate.save(query, game.team().name());
        saveBoard(gameId, game.getBoard());
        return gameId;
    }

    public void saveBoard(int gameId, Board board) {
        for (Entry<Square, Piece> entry : board.getBoard().entrySet()) {
            final Square square = entry.getKey();
            final Piece piece = entry.getValue();
            saveOneSquare(gameId, square, piece);
        }
    }

    private void saveOneSquare(int gameId, Square square, Piece piece) {
        final String query = "INSERT INTO board (game_id, square_file, square_rank, piece_team, piece_type) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(query,
                gameId,
                square.file().name(),
                square.rank().name(),
                piece.team().name(),
                piece.type().name());
    }

    public ChessGame findGameById(int gameId) {
        final Team team = findTeamById(gameId);
        final Board board = findBoardById(gameId);
        return new ChessGame(board, team);
    }

    public Team findTeamById(int gameId) {
        final var query = "SELECT team FROM game WHERE game_id = ?";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            if (resultSet.next()) {
                return Team.valueOf(resultSet.getString("team"));
            }
            throw new IllegalArgumentException("차례를 확인할 수 없습니다.");
        }, gameId);
    }

    public void deleteGameById(final int gameId) {
        final var query = "DELETE FROM game WHERE game_id = ?";
        jdbcTemplate.executeUpdate(query, gameId);
    }

    public void deleteBoard(int gameId) {
        final var query = "DELETE FROM board WHERE game_id = ?";
        jdbcTemplate.executeUpdate(query, gameId);
    }

    public Board findBoardById(int gameId) {
        final var query = "SELECT square_file, square_rank, piece_team, piece_type FROM board WHERE game_id = ?";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            final Map<Square, Piece> board = new HashMap<>();
            while (resultSet.next()) {
                final Square square = Squares.of(File.valueOf(resultSet.getString("square_file")),
                        Rank.valueOf(resultSet.getString("square_rank")));
                final Piece piece = Pieces.of(Team.valueOf(resultSet.getString("piece_team")),
                        PieceType.valueOf(resultSet.getString("piece_type")));
                board.put(square, piece);
            }
            return new Board(board);
        }, gameId);
    }

    public List<Integer> findAllRooms() {
        final var query = "SELECT game_id FROM game";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            List<Integer> rooms = new ArrayList<>();
            while (resultSet.next()) {
                rooms.add(resultSet.getInt("game_id"));
            }
            return rooms;
        });
    }

    public int countKing(int gameId) {
        final var query = "SELECT COUNT(*) FROM board WHERE game_id =? AND piece_type = ?";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return null;
        }, gameId, KING.name());
    }

    public List<Integer> countPawnInSameFileByTeam(int gameId, Team team) {
        final var query = "SELECT COUNT(square_file) FROM board WHERE game_id = ? AND piece_team = ? AND piece_type IN (? , ?) GROUP BY square_file";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            final List<Integer> count = new ArrayList<>();
            while (resultSet.next()) {
                count.add(resultSet.getInt(1));
            }
            return count;
        }, gameId, team.name(), INITIAL_PAWN.name(), PAWN.name());
    }

    public List<Piece> findPiecesByTeamExceptPawn(int gameId, Team team) {
        final var query = "SELECT piece_type FROM board WHERE game_id = ? AND piece_team = ? AND piece_type NOT IN (? , ?)";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            final List<Piece> pieces = new ArrayList<>();
            while (resultSet.next()) {
                pieces.add(Pieces.of(team,
                        PieceType.valueOf(resultSet.getString("piece_type"))));
            }
            return pieces;
        }, gameId, team.name(), INITIAL_PAWN.name(), PAWN.name());
    }

    public void saveState(int gameId, GameState state) {
        final String query = "INSERT INTO state (game_id, state) VALUES (?, ?)";
        jdbcTemplate.executeUpdate(query, gameId, state.name());
    }

    public void deleteState(int gameId) {
        final String query = "DELETE FROM state WHERE game_id = ?";
        jdbcTemplate.executeUpdate(query, gameId);
    }

    public GameState findState(int gameId) {
        final var query = "SELECT state FROM state WHERE game_id = ?";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            if (resultSet.next()) {
                return GameState.valueOf(resultSet.getString("state"));
            }
            throw new IllegalArgumentException("방을 찾을 수 없습니다.");
        }, gameId);
    }

    public void updateTeamById(final int gameId, Team team) {
        final var query = "UPDATE game SET team = ? WHERE game_id = ?";
        jdbcTemplate.executeUpdate(query, team.name(), gameId);
    }
}
