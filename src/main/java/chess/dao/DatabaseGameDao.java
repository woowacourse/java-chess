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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseGameDao implements GameDao {

    private final PieceDao pieceDao = new PieceDao();
    private final SQLExecutor executor = SQLExecutor.getInstance();
    private final MemberDao memberDao;

    public DatabaseGameDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public Long save(ChessGame game) {
        Long gameId = saveGame(game);
        savePieces(gameId, game.getBoard());
        return gameId;
    }

    private Long saveGame(ChessGame game) {
        final String sql = "insert into Game (state, white_member_id, black_member_id) values (?, ?, ?)";
        return executor.insertAndGetGeneratedKey(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, game.getStateString());
            statement.setLong(2, game.getWhiteId());
            statement.setLong(3, game.getBlackId());
            return statement;
        });
    }

    @Override
    public Optional<ChessGame> findById(Long id) {
        final String sql = "select id, state, white_member_id, black_member_id from Game where id = ?";
        ChessGame game = executor.select(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            return statement;
        }, resultSet -> loadChessGame(id, resultSet));
        return Optional.ofNullable(game);
    }

    private ChessGame loadChessGame(Long id, ResultSet resultSet)
            throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        return makeChessGame(id, resultSet);
    }

    @Override
    public List<ChessGame> findAll() {
        final String sql = "select id, state, white_member_id, black_member_id from Game";
        return executor.select(connection -> connection.prepareStatement(sql), this::makeAllChessGame);
    }

    private List<ChessGame> makeAllChessGame(ResultSet resultSet) throws SQLException {
        List<ChessGame> games = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            games.add(makeChessGame(id, resultSet));
        }
        return games;
    }

    @Override
    public List<ChessGame> findHistorysByMemberId(Long memberId) {
        return findAll().stream()
                .filter(ChessGame::isEnd)
                .filter(game -> Objects.equals(game.getParticipant().getBlackId(), memberId)
                        || Objects.equals(game.getParticipant().getWhiteId(), memberId))
                .collect(Collectors.toList());
    }

    @Override
    public void update(ChessGame game) {
        updateGame(game);
        movePieces(game);
    }

    private void updateGame(ChessGame game) {
        final String sql = "update Game set state = ? where id = ?";
        executor.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, game.getStateString());
            statement.setLong(2, game.getId());
            return statement;
        });
        savePieces(game.getId(), game.getBoard());
    }

    private void movePieces(ChessGame game) {
        final String deleteSql = "delete from Piece where game_id = ?";
        executor.delete(connection -> {
            final PreparedStatement statement = connection.prepareStatement(deleteSql);
            statement.setLong(1, game.getId());
            return statement;
        });
        savePieces(game.getId(), game.getBoard());
    }

    private void savePieces(Long gameId, Board board) {
        for (int i = 0; i < 8; i++) {
            saveRank(gameId, board, i);
        }
    }

    private void saveRank(Long gameId, Board board, int lineNumber) {
        Rank rank = board.getRank(lineNumber);
        for (Piece piece : rank.getPieces()) {
            pieceDao.save(gameId, piece, lineNumber);
        }
    }

    private ChessGame makeChessGame(Long id, ResultSet resultSet) throws SQLException {
        Member white = memberDao.findById(resultSet.getLong("white_member_id"))
                .orElseThrow(() -> new RuntimeException("찾는 멤버가 존재하지 않습니다."));
        Member black = memberDao.findById(resultSet.getLong("black_member_id"))
                .orElseThrow(() -> new RuntimeException("찾는 멤버가 존재하지 않습니다."));
        String stateName = resultSet.getString("state");
        return new ChessGame(id, StateType.createState(stateName, loadBoard(id)),
                new Participant(white, black));
    }

    private Board loadBoard(Long gameId) {
        final String sql = "select line_number, position_x, position_y, team, type, first_turn "
                + "from Piece "
                + "where game_id = ?";
        Map<Integer, Rank> boardValues = executor.select(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, gameId);
            return statement;
        }, this::makeRanks);
        return new Board(boardValues);
    }

    private Map<Integer, Rank> makeRanks(ResultSet resultSet) throws SQLException {
        Map<Integer, List<Piece>> rankValues = loadRankValues(resultSet);
        Map<Integer, Rank> ranks = new HashMap<>();
        for (Entry<Integer, List<Piece>> pieces : rankValues.entrySet()) {
            ranks.put(pieces.getKey(), new Rank(pieces.getValue()));
        }
        return ranks;
    }

    private Map<Integer, List<Piece>> loadRankValues(ResultSet pieceResultSet) throws SQLException {
        Map<Integer, List<Piece>> rankValues = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            rankValues.put(i, new ArrayList<>());
        }
        while (pieceResultSet.next()) {
            fillRankValue(pieceResultSet, rankValues);
        }
        return rankValues;
    }

    private void fillRankValue(ResultSet pieceResultSet, Map<Integer, List<Piece>> rankValues) throws SQLException {
        int lineNumber = pieceResultSet.getInt("line_number");
        String teamName = pieceResultSet.getString("team");
        String typeName = pieceResultSet.getString("type");
        Position position = new Position(pieceResultSet.getInt("position_x"),
                pieceResultSet.getInt("position_y"));
        boolean firstTurn = pieceResultSet.getBoolean("first_turn");
        rankValues.get(lineNumber)
                .add(PieceFactory.createPiece(teamName, typeName, position, firstTurn));
    }

    private class PieceDao {
        public void save(Long gameId, Piece piece, int lineNumber) {
            final String sql = "insert into "
                    + "Piece(line_number, position_x, position_y, team, type, first_turn, game_id) "
                    + "values (?, ?, ?, ?, ?, ?, ?);";
            executor.insert(connection -> {
                final PreparedStatement statement = connection.prepareStatement(sql);
                setPieceSaveParams(gameId, piece, lineNumber, statement);
                return statement;
            });
        }

        private void setPieceSaveParams(Long gameId, Piece piece, int lineNumber, PreparedStatement statement)
                throws SQLException {
            statement.setInt(1, lineNumber);
            statement.setInt(2, piece.getPosition().getX());
            statement.setInt(3, piece.getPosition().getY());
            statement.setString(4, piece.getTeam().getName());
            statement.setString(5, piece.getType().getName());
            statement.setBoolean(6, piece.isFirstTurn());
            statement.setLong(7, gameId);
        }
    }
}
