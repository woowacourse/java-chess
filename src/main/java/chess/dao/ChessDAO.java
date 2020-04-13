package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessDAO {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private JDBCTemplate jdbcTemplate = new JDBCTemplate(SERVER, DATABASE, USERNAME, PASSWORD);

    public long createChessGame(ChessGame chessGame) {
        PreparedStatementSetter setter = (preparedStatement) -> {
            preparedStatement.setString(1, chessGame.getTurn().toString());
        };

        ResultSetMapper<Long> mapper = (resultSet) -> {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return 0L;
        };

        String query = "INSERT INTO chessGameTable (turn) VALUES (?)";
        Long id;
        try {
            id = jdbcTemplate.executeUpdate(query, setter, mapper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        addBoard(id, chessGame);
        return id;
    }

    public void addBoard(long chessGameId, ChessGame chessGame) {
        deleteBoard(chessGameId);
        updateTurn(chessGameId, chessGame);
        insertBoard(chessGameId, chessGame);
    }

    private void deleteBoard(final long chessGameId) {
        String deleteBoardQuery = "DELETE FROM boardTable WHERE gameId = (?)";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setLong(1, chessGameId);
        };
        try {
            jdbcTemplate.executeUpdate(deleteBoardQuery, preparedStatementSetter);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    private void updateTurn(long chessGameId, ChessGame chessGame) {
        String updateTurnQuery = "UPDATE chessGameTable SET turn = ? where id = ?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, chessGame.getTurn().toString());
            preparedStatement.setLong(2, chessGameId);
        };
        try {
            jdbcTemplate.executeUpdate(updateTurnQuery, preparedStatementSetter);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    private void insertBoard(final long chessGameId, final ChessGame chessGame) {
        Map<Position, PieceState> board = chessGame.getBoard();

        String query = "INSERT INTO boardTable VALUES ((SELECT id FROM positionTable WHERE position = ?)," +
                " (SELECT id FROM pieceTable WHERE piece = ?), (SELECT id FROM teamTable WHERE team = ?), ?);";
        try {
            for (Map.Entry<Position, PieceState> entry : board.entrySet()) {
                String position = entry.getKey().getName();
                String piece = entry.getValue().getPieceType().toString();
                String team = entry.getValue().getTeam().toString();
                PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
                    preparedStatement.setString(1, position);
                    preparedStatement.setString(2, piece);
                    preparedStatement.setString(3, team);
                    preparedStatement.setLong(4, chessGameId);
                };
                jdbcTemplate.executeUpdate(query, preparedStatementSetter);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public void deleteGame(final long chessGameId) {
        deleteBoard(chessGameId);
        String deleteGameQuery = "DELETE FROM chessGameTable WHERE id = (?);";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setLong(1, chessGameId);
        };
        try {
            jdbcTemplate.executeUpdate(deleteGameQuery, preparedStatementSetter);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }

        String alterGameQuery = "ALTER TABLE chessGameTable AUTO_INCREMENT = ?;";
        PreparedStatementSetter autoIncrementSetter = preparedStatement -> {
            preparedStatement.setInt(1, (int) chessGameId);
        };
        try {
            jdbcTemplate.executeUpdate(alterGameQuery, autoIncrementSetter);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }


    public Optional<ChessGame> findGameById(long id) {
        try {
            Board board = getCurrentBoard(id);
            Turn turn = getCurrentTurn(id);
            return Optional.of(ChessGame.of(board, turn));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Board getCurrentBoard(final long id) throws SQLException {
        String query = "SELECT position, piece, team FROM boardTable board " +
                "inner join positionTable po on po.id=board.positionId " +
                "inner join pieceTable pi on pi.id=board.pieceId  " +
                "inner join teamTable team on team.id = board.teamId " +
                "where gameId = (?);";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setLong(1, id);
        };

        ResultSetMapper<Board> resultSetMapper = resultSet -> {
            Map<Position, PieceState> board = new HashMap<>();
            while (resultSet.next()) {
                Position position = Position.of(resultSet.getString("position"));
                Team team = Team.valueOf(resultSet.getString("team"));
                PieceState pieceState = createPieceState(resultSet.getString("piece"), position, team);
                board.put(position, pieceState);
            }
            return Board.of(board);
        };

        return jdbcTemplate.executeQuery(query, preparedStatementSetter, resultSetMapper);
    }

    private Turn getCurrentTurn(final long id) throws SQLException {
        String query = "SELECT turn FROM chessGameTable WHERE id = ?;";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setLong(1, id);
        };
        ResultSetMapper<Turn> resultSetMapper = resultSet -> {
            if (resultSet.next()) {
                String team = resultSet.getString("turn");
                return Turn.from(Team.valueOf(team));
            }
            throw new IllegalArgumentException("턴 정보가 없습니다.");
        };
        return jdbcTemplate.executeQuery(query, preparedStatementSetter, resultSetMapper);
    }

    private PieceState createPieceState(final String piece, final Position position, final Team team) {
        PieceType type = PieceType.valueOf(piece);
        return type.apply(position, team);
    }

    public List<Long> getRoomId() {
        String query = "SELECT id FROM chessGameTable";
        ResultSetMapper<List<Long>> resultSetMapper = resultSet -> {
            List<Long> roomIds = new ArrayList<>();
            while (resultSet.next()) {
                roomIds.add(resultSet.getLong("id"));
            }
            return roomIds;
        };
        try {
            return jdbcTemplate.executeQuery(query, resultSetMapper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }
}
