package chess.domain.manager;

import chess.domain.pieces.Piece;
import chess.machine.Result;
import chess.domain.pieces.Color;
import chess.domain.position.Position;

import java.util.*;

public final class Game {

    private final Board board;
    private Color turn = Color.WHITE;

    public Game(final Initializer initiator) {
        board = new Board(initiator);
    }

    public Optional<Piece> piece(final Position position) {
        return board.piece(position);
    }

    public void move(final String source, final String target) {
        final Position sourcePosition = Position.of(source);
        final Position targetPosition = Position.of(target);
        validateNotEquals(sourcePosition, targetPosition);
        validateCorrectTurn(sourcePosition);
        changeTurn(board.move(sourcePosition, targetPosition));
    }

    private void validateNotEquals(final Position sourcePosition, final Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발지와 목적지가 동일할 수 없습니다.");
        }
    }

    private void validateCorrectTurn(final Position sourcePosition) {
        final Optional<Piece> wrappedPiece = board.piece(sourcePosition);
        if (wrappedPiece.isPresent() && !wrappedPiece.get().isSameColor(turn)) {
            throw new IllegalArgumentException("[ERROR] 지금은 " + turn.value() + "의 턴입니다.");
        }
    }

    private void changeTurn(final boolean moveSuccess) {
        if (moveSuccess) {
            turn = Color.opposite(turn);
        }
    }

    public boolean isEnd() {
        return board.isEnd();
    }

    public double calculateScore(final Color color) {
        return board.calculateScore(color);
    }

    public Map<Result, Color> whoIsWin() {
        return board.whoIsWin();
    }
}
