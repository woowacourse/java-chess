package domain.board;

import domain.piece.Empty;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Vector;
import java.util.List;
import java.util.Map;

public class Board {
    private Color currentPlayerColor;
    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.currentPlayerColor = Color.WHITE;
        this.squares = squares;
    }

    public void move(final Position source, final Position target) {
        final Piece currentPiece = squares.get(source);
        final Piece targetPiece = squares.get(target);
        final Vector vector = new Vector(source, target);

        validateMovement(currentPiece, vector, targetPiece);

        if (isPiecesPossiblyExistOnPath(vector)) {
            validateNoPieceExistInPath(vector, source);
        }
        updateBoard(source, target, currentPiece);
        switchTurn();
    }

    private void validateMovement(final Piece currentPiece, final Vector vector, final Piece targetPiece) {
//        validateTurn(currentPiece);
        validateReachability(vector, currentPiece, targetPiece);
    }

    private boolean isPiecesPossiblyExistOnPath(final Vector vector) {
        return vector.hasAbsoluteValueMoreOrEqualThan(2) && vector.isStraightOrDiagonal();
    }

    private void validateReachability(final Vector vector, final Piece currentPiece,
                                      final Piece targetPiece) {
        if (!currentPiece.isReachable(vector, targetPiece)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNoPieceExistInPath(final Vector vector, final Position source) {

        final List<Position> path = vector.generatePathExcludingEndpoints(source);
        final boolean isPieceExistInPath = !path.stream()
                .map(squares::get)
                .allMatch(Piece::isEmpty);

        if (isPieceExistInPath) {
            throw new IllegalArgumentException("시작위치와 도착위치 사이에 다른 말이 놓져 있습니다");
        }
    }

    private void validateTurn(final Piece currentPiece) {
        if (!currentPiece.hasColor(currentPlayerColor)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }
    }

    private void updateBoard(final Position source, final Position target, final Piece currentPiece) {
        overwriteSourceToTarget(target, currentPiece);

        squares.put(source, Empty.INSTANCE);
    }

    private void overwriteSourceToTarget(final Position target, final Piece currentPiece) {
        if (currentPiece.isInitPawn()) {
            squares.put(target, new Pawn(currentPlayerColor));
            return;
        }
        squares.put(target, currentPiece);
    }

    private void switchTurn() {
        currentPlayerColor = currentPlayerColor.reverse();
    }

    public Map<Position, Piece> squares() {
        return squares;
    }
}
