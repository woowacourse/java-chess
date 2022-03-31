package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ChessGame {
    private final Board board;
    private Color turn = Color.WHITE;

    private ChessGame(Board board) {
        this.board = board;
    }

    public static ChessGame newGame() {
        return new ChessGame(Board.initialBoard());
    }

    public void movePiece(String sourceCommand, String targetCommand) {
        Position source = parseToPosition(sourceCommand);
        Position target = parseToPosition(targetCommand);

        if (board.isCastable(turn, source, target)) {
            board.castle(source, target);
            changeTurn();
            return;
        }

        if (board.isEnPassantAvailable(turn, source, target)) {
            board.doEnPassant(turn, source, target);
            changeTurn();
            return;
        }

        board.movePieceIfValid(turn, source, target);
        checkPromotion(target);
        changeTurn();
    }

    private Position parseToPosition(String command) {
        return new Position(PositionX.of(command.substring(0, 1)), PositionY.of(command.substring(1)));
    }

    private void changeTurn() {
        turn = turn.nextTurn();
    }

    private void checkPromotion(Position target) {
        if (board.isPromotable(target)) {
            board.promoteTo(target, new Queen(turn));
        }
    }

    public boolean isRunning() {
        return board.isBothKingsAlive();
    }

    public Map<Color, Double> calculateScore() {
        return Stream.of(Color.BLACK, Color.WHITE)
                .collect(Collectors.toMap(color -> color, board::calculateScoreOf));
    }

    public Color decideWinner() {
        Map<Color, Double> scores = calculateScore();
        Comparator<Color> comparator = Comparator.comparing(scores::get);
        return getColorWithMaxScore(comparator);
    }

    private Color getColorWithMaxScore(Comparator<Color> comparator) {
        return Arrays.stream(Color.values())
                .max(comparator)
                .orElse(Color.NONE);
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
