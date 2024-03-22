package chess.domain.board;

import chess.domain.CurrentTurn;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import chess.domain.square.piece.Piece;

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
        if (squares.get(path.getEnd()) == Empty.getInstance()) {
            tryExchange(path);
            return;
        }
        tryAttack(path);
    }

    private void validateStartSquare(Path path) {
        Square startSquare = squares.get(path.getStart());
        if (isEmpty(startSquare) || isEnemy(startSquare)) {
            throw new IllegalArgumentException("시작 위치에 아군 체스말이 존재해야 합니다.");
        }
    }

    private boolean isEmpty(Square square) {
        return square == Empty.getInstance();
    }

    // TODO: 하위 타입 캐스팅 대신 Map<Position, Piece>로 변경할지 고려
    private boolean isEnemy(Square square) {
        Piece piece = (Piece) square;
        return !piece.isColor(currentTurn.value());
    }

    // TODO: 예외 로직 세분화
    private void tryExchange(Path path) {
        Square startSquare = squares.get(path.getStart());
        if (!startSquare.canMove(path, squares)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
        startSquare.move();
        Square tmp = squares.get(path.getEnd());
        squares.put(path.getEnd(), startSquare);
        squares.put(path.getStart(), tmp);
        currentTurn.change();
    }

    private void tryAttack(Path path) {
        Square startSquare = squares.get(path.getStart());
        if (!startSquare.canAttack(path, squares)) {
            throw new IllegalArgumentException("해당 위치를 공격할 수 없습니다.");
        }
        startSquare.move();
        squares.put(path.getEnd(), startSquare);
        squares.put(path.getStart(), Empty.getInstance());
        currentTurn.change();
    }

    public Map<Position, Square> squares() {
        return Collections.unmodifiableMap(squares);
    }
}
