package chess.domain.board;

import chess.domain.CurrentTurn;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Square> squares;
    private final CurrentTurn currentTurn;

    public ChessBoard(Map<Position, Square> squares, CurrentTurn currentTurn) {
        this.squares = new LinkedHashMap<>(squares);
        this.currentTurn = currentTurn;
    }

    public void move(Path path) {
        validateStartSquare(path);
        if (squares.get(path.getTargetPosition()) == Empty.getInstance()) {
            moveWithoutAttack(path);
            return;
        }
        moveWithAttack(path);
    }

    private void validateStartSquare(Path path) {
        Square startSquare = squares.get(path.getStartPosition());
        if (!startSquare.isColor(currentTurn.value())) {
            throw new IllegalArgumentException("시작 위치에 아군 체스말이 존재해야 합니다.");
        }
    }

    // TODO: 예외 로직 세분화
    private void moveWithoutAttack(Path path) {
        Square startSquare = squares.get(path.getStartPosition());
        if (!startSquare.canMove(path, squares)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
        // TODO: moveWithAttack과 추상화할 수 있을지 고민하기
        startSquare.move();
        squares.put(path.getTargetPosition(), startSquare);
        squares.put(path.getStartPosition(), Empty.getInstance());
        currentTurn.change();
    }

    private void moveWithAttack(Path path) {
        Square startSquare = squares.get(path.getStartPosition());
        if (!startSquare.canAttack(path, squares)) {
            throw new IllegalArgumentException("해당 위치를 공격할 수 없습니다.");
        }
        startSquare.move();
        squares.put(path.getTargetPosition(), startSquare);
        squares.put(path.getStartPosition(), Empty.getInstance());
        currentTurn.change();
    }

    public Map<Position, Square> getSquares() {
        return Collections.unmodifiableMap(squares);
    }
}
