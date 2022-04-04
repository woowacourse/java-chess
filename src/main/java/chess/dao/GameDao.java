package chess.dao;

import chess.domain.ChessGame;
import chess.domain.Member;
import chess.domain.Participant;
import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;
import chess.domain.state.StateType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GameDao {

    private final MemberDao memberDao;

    public GameDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void save(ChessGame game) {
        final Connection connection = MysqlConnector.getConnection();
        Long savedId = saveGame(game, connection);
        savePieces(new ChessGame(savedId, game.getState(), game.getParticipant()), connection);
    }

    private Long saveGame(ChessGame game, Connection connection) {
        final String sql = "insert into Game (state, white_member_id, black_member_id) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, game.getStateString());
            statement.setLong(2, game.getWhiteId());
            statement.setLong(3, game.getBlackId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public ChessGame findById(final Long id) {
        final Connection connection = MysqlConnector.getConnection();
        final String sql = "select id, state, white_member_id, black_member_id from Game where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Member white = memberDao.findById(resultSet.getLong("white_member_id"));
            Member black = memberDao.findById(resultSet.getLong("black_member_id"));
            return new ChessGame(id,
                    StateType.createState(
                            resultSet.getString("state"),
                            loadBoard(id, connection)
                    ), new Participant(white, black)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChessGame> findAll() {
        final Connection connection = MysqlConnector.getConnection();
        final String sql = "select id, state, white_member_id, black_member_id from Game";
        List<ChessGame> games = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                games.add(makeChessGame(connection, resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    private ChessGame makeChessGame(Connection connection, ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Member white = memberDao.findById(resultSet.getLong("white_member_id"));
        Member black = memberDao.findById(resultSet.getLong("black_member_id"));
        String stateName = resultSet.getString("state");
        return new ChessGame(id,
                StateType.createState(stateName, loadBoard(id, connection)),
                new Participant(white, black));
    }

    private Board loadBoard(Long gameId, Connection connection) {
        final String sql = "select line_number,"
                + " position_x, position_y, team, type, first_turn "
                + "from Piece "
                + "where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, gameId);
            ResultSet pieceResultSet = statement.executeQuery();
            Map<Integer, List<Piece>> rankValues = new HashMap<>();
            for (int i = 0; i < 8; i++) {
                rankValues.put(i, new ArrayList<>());
            }
            while (pieceResultSet.next()) {
                rankValues.get(pieceResultSet.getInt("line_number"))
                        .add(PieceFactory.createPiece(
                                pieceResultSet.getString("team"),
                                pieceResultSet.getString("type"),
                                new Position(pieceResultSet.getInt("position_x"),
                                        pieceResultSet.getInt("position_y")
                                ), pieceResultSet.getBoolean("first_turn")
                        ));
            }
            Map<Integer, Rank> ranks = new HashMap<>();
            for (Entry<Integer, List<Piece>> pieces : rankValues.entrySet()) {
                ranks.put(pieces.getKey(), new Rank(pieces.getValue()));
            }

            return new Board(ranks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(ChessGame game) {
        final Connection connection = MysqlConnector.getConnection();
        updateGame(game, connection);
        movePieces(game, connection);
    }

    private void updateGame(ChessGame game, Connection connection) {
        final String stateSql = "update Game set state = ? where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(stateSql);
            statement.setString(1, game.getStateString());
            statement.setLong(2, game.getId());
            statement.executeUpdate();
            savePieces(game, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void movePieces(ChessGame game, Connection connection) {
        final String deleteSql = "delete from Piece where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(deleteSql);
            statement.setLong(1, game.getId());
            statement.executeUpdate();
            savePieces(game, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePieces(ChessGame game, Connection connection) {
        for (int i = 0; i < 8; i++) {
            Rank rank = game.getBoard().getRank(i);
            final String sql = "insert into "
                    + "Piece(line_number, position_x, position_y, team, type, first_turn, game_id) "
                    + "values (?, ?, ?, ?, ?, ?, ?);";
            for (Piece piece : rank.getPieces()) {
                try {
                    final PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, i);
                    statement.setInt(2, piece.getPosition().getX());
                    statement.setInt(3, piece.getPosition().getY());
                    statement.setString(4, piece.getTeam().getName());
                    statement.setString(5, piece.getType().getName());
                    statement.setBoolean(6, piece.isFirstTurn());
                    statement.setLong(7, game.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
