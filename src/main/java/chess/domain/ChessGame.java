package chess.domain;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.dao.Transaction;
import chess.domain.piece.Piece;
import chess.domain.state.*;
import chess.dto.CommandDto;
import chess.dto.GameDto;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ChessGame {
    private final GameDao gameDao;
    private final BoardDao boardDao;
    private final Transaction jdbcTemplate;
    private final int gameId;
    private final Board board;
    private State state;

    private ChessGame(final State state, final GameDao gameDao, final BoardDao boardDao, final Transaction jdbcTemplate, final Board board, final int gameId) {
        this.state = state;
        this.gameDao = gameDao;
        this.boardDao = boardDao;
        this.jdbcTemplate = jdbcTemplate;
        this.gameId = gameId;
        this.board = board;
    }

    public static ChessGame from(final GameDao gameDao, final BoardDao boardDao, final Transaction jdbcTemplate) throws SQLException {
        GameDto gameDto = gameDao.findByLastGame();

        if (gameDto.isEnd()) {
            final State state = new Ready(Color.EMPTY);
            final Board board = Board.create();
            final int nextId = gameDto.getId() + 1;

            jdbcTemplate.batchTransaction(() -> {
                int gameId = gameDao.create().getId();
                boardDao.create(board.getBoard(), gameId);
            });

            return new ChessGame(state, gameDao, boardDao, jdbcTemplate, board, nextId);
        }

        final Board board = Board.from(boardDao.findByLastGameBoard(gameDto.getId()));
        final State state = new Ready(Color.valueOf(gameDto.getColor()));
        return new ChessGame(state, gameDao, boardDao, jdbcTemplate, board, gameDto.getId());
    }

    public void move(final CommandDto commandDto) {
        State newState = state.move(commandDto.getSource(), commandDto.getTarget(), board);

        if (newState.getClass() == GameEnd.class) {
            gameDao.update(true, gameId);
        }

        jdbcTemplate.batchTransaction(() -> {
            boardDao.deleteAll(gameId);
            gameDao.update(state.getColor().reverse(), gameId);
            boardDao.create(board.getBoard(), gameId);
        });

        state = newState;
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public double calculateScore(Color color) {
        return Arrays.stream(Row.values())
                .flatMap(row -> calculateColumnScore(color, row))
                .mapToDouble(Map.Entry::getValue)
                .sum();
    }

    private Stream<Map.Entry<PieceType, Double>> calculateColumnScore(final Color color, final Row row) {
        Map<PieceType, Double> columnScore = Arrays.stream(Column.values())
                .map(column -> board.getBoard().get(Position.of(row, column)))
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.groupingBy(Piece::getPieceType, Collectors.summingDouble(Piece::getScore)));

        return columnScore.entrySet()
                .stream()
                .peek(this::calculatePawnScore);
    }

    private void calculatePawnScore(final Map.Entry<PieceType, Double> entry) {
        final double duplicatesPawnScore = 0.5;

        if (isVerticalDuplicatesPawn(entry)) {
            entry.setValue(entry.getValue() * duplicatesPawnScore);
        }
    }

    private boolean isVerticalDuplicatesPawn(final Map.Entry<PieceType, Double> entry) {
        final int pawnCount = 1;

        return entry.getKey() == PieceType.PAWN && entry.getValue() > pawnCount;
    }

    public boolean isNotEnd() {
        return state.getClass() != End.class;
    }

    public boolean isGameEnd() {
        return state.getClass() == GameEnd.class;
    }

    public boolean isRunning() {
        return state.getClass() == Running.class;
    }

    public boolean isNotGameEnd() {
        return state.getClass() != GameEnd.class;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public Color getColor() {
        return state.getColor();
    }
}
