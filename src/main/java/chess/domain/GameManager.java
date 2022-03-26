package chess.domain;

import chess.domain.position.Position;

import java.util.*;

public final class GameManager {

    private final Board board;
    private Color turn = Color.WHITE;

    public GameManager(final Initializer initiator) {
        board = new Board(initiator);
    }

    public Optional<Piece> piece(final Position position) {
        return board.piece(position);
    }

    public void move(String source, String target) {
        Position sourcePosition = Position.of(source);
        Position targetPosition = Position.of(target);
        validateNotEquals(sourcePosition, targetPosition);
        validateCorrectTurn(sourcePosition);
        changeTurn(board.move(sourcePosition, targetPosition));
    }

    private void validateNotEquals(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발지와 목적지가 동일할 수 없습니다.");
        }
    }

    private void validateCorrectTurn(Position sourcePosition) {
        Optional<Piece> wrappedPiece = board.piece(sourcePosition);
        if (wrappedPiece.isPresent() && !wrappedPiece.get().isSameColor(turn)) {
            throw new IllegalArgumentException("[ERROR] 지금은 " + turn.value() + "의 턴입니다.");
        }
    }

    private void changeTurn(boolean moveSuccess) {
        if (moveSuccess) {
            turn = Color.opposite(turn);
        }
    }

    public boolean isEnd() {
        return board.isEnd();
    }

    public double calculateScore(Color color) {
        return board.calculateScore(color);
    }

    public Map<Result, Color> whoIsWin() {
        return board.whoIsWin();
    }
}
