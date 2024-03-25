package chess.domain.board;

import chess.domain.CurrentTurn;
import chess.domain.position.PathFinder;
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

    public void move(PathFinder pathFinder) {
        Square startSquare = squares.get(pathFinder.startPosition());
        validateIsFriendly(startSquare);
        validateCanMove(startSquare, pathFinder);

        squares.put(pathFinder.targetPosition(), startSquare);
        squares.put(pathFinder.startPosition(), Empty.getInstance());

        currentTurn.change();
    }

    private void validateIsFriendly(Square startSquare) {
        if (!startSquare.isColor(currentTurn.value())) {
            throw new IllegalArgumentException("시작 위치에 아군 체스말이 존재해야 합니다.");
        }
    }

    private void validateCanMove(Square startSquare, PathFinder pathFinder) {
        if (!startSquare.canArrive(pathFinder, squares)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
    }

    public Map<Position, Square> getSquares() {
        return Collections.unmodifiableMap(squares);
    }
}
