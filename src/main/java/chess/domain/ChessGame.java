package chess.domain;

import chess.dao.GameDao;
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
    private final int gameId;
    private State state;

    private ChessGame(final State state, final GameDao gameDao, final int gameId) {
        this.state = state;
        this.gameDao = gameDao;
        this.gameId = gameId;
    }

    public static ChessGame from(GameDao gameDao) throws SQLException {
        GameDto gameDto = gameDao.findByLastGame();

        if (gameDto.isEnd()) {
            final State state = new Ready(Board.create(), Color.EMPTY);
            gameDao.createGame(Board.create().getBoard());
            return new ChessGame(state, gameDao, gameDto.getId() + 1);
        }

        Board board = Board.from(gameDao.findByLastGameBoard(gameDto.getId()));
        final State state = new Ready(board, Color.valueOf(gameDto.getColor()));
        return new ChessGame(state, gameDao, gameDto.getId());
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
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

    public void move(final CommandDto commandDto) {
        State newState = state.move(commandDto.getSource(), commandDto.getTarget());
        if (newState.getClass() == GameEnd.class) {
            gameDao.updateGameStatus(true, gameId);
        }

        gameDao.movePiece(newState.getBoard(), gameId, newState.getColor());
        state = newState;
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public void identity() {
    }

    public double calculateScore(Color color) {
        return Arrays.stream(Row.values())
                .flatMap(row -> calculateColumnScore(color, row))
                .mapToDouble(Map.Entry::getValue)
                .sum();
    }

    private Stream<Map.Entry<PieceType, Double>> calculateColumnScore(final Color color, final Row row) {
        Map<PieceType, Double> columnScore = Arrays.stream(Column.values())
                .map(column -> state.getBoard().get(Position.of(row, column)))
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

    public Color getColor() {
        return state.getColor();
    }
}
