package domain.board;

import domain.board.position.Position;
import domain.board.position.Vector;
import domain.piece.Empty;
import domain.piece.Piece;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Board {
    private Color currentTurnColor;
    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.currentTurnColor = Color.WHITE;
        this.squares = squares;
    }

    public void move(final String source, final String target) {
        move(Position.from(source), Position.from(target));
    }

    public void move(final Position source, final Position target) {
        validateMovement(source, target);
        updateBoard(source, target);
        switchTurn();
    }

    private void validateMovement(final Position source, final Position target) {
        final Piece currentPiece = squares.get(source);
        final Piece targetPiece = squares.get(target);
        final Vector vector = new Vector(source, target);

        validateEmptiness(currentPiece);
        validateCurrentTurn(currentPiece);
        validateReachability(vector, currentPiece, targetPiece);
        validateNoPieceOnPath(source, vector);
    }

    private void validateEmptiness(final Piece currentPiece) {
        if (currentPiece.isEmpty()) {
            throw new IllegalArgumentException("이동할 말이 선택되지 않았습니다");
        }
    }

    private void validateNoPieceOnPath(final Position source, final Vector vector) {
        if (isPiecesPossiblyExistOnPath(vector)) {
            validateNoPieceExistInPath(vector, source);
        }
    }

    private boolean isPiecesPossiblyExistOnPath(final Vector vector) {
        return vector.allAbsoluteValueMoreOrEqualThan(2) && vector.isStraightOrDiagonal();
    }

    private void validateReachability(final Vector vector, final Piece currentPiece, final Piece targetPiece) {
        if (!currentPiece.isReachable(vector, targetPiece)) {
            throw new IllegalArgumentException("해당 말로 해당 위치를 갈 수 없습니다");
        }
    }

    private void validateNoPieceExistInPath(final Vector vector, final Position source) {
        final List<Position> path = vector.generatePathExcludingEndpoints(source);
        final boolean isPieceExistInPath = !path.stream().map(squares::get).allMatch(Piece::isEmpty);

        if (isPieceExistInPath) {
            throw new IllegalArgumentException("이동 경로에 다른 말이 놓져 있습니다");
        }
    }

    private void validateCurrentTurn(final Piece currentPiece) {
        if (!currentPiece.hasColor(currentTurnColor)) {
            throw new IllegalArgumentException(
                    String.format("현재 차례: %s, 현재 차례의 말만 움직일 수 있습니다", currentTurnColor.name()));
        }
    }

    private void updateBoard(final Position source, final Position target) {
        squares.put(target, squares.get(source).move());
        squares.put(source, Empty.INSTANCE);
    }


    private void switchTurn() {
        currentTurnColor = currentTurnColor.reverse();
    }

    public Map<Position, Piece> squares() {
        return Collections.unmodifiableMap(squares);
    }
}
