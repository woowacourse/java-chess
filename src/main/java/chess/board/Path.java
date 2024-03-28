package chess.board;

import chess.piece.Color;
import chess.position.UnitMovement;
import java.util.List;

public class Path {

    private static final int MIN_PATH_LENGTH = 2;

    private final List<Square> squares;
    private final UnitMovement unitMovement;

    public Path(List<Square> squares, UnitMovement unitMovement) {
        validatePathLength(squares);
        this.squares = squares;
        this.unitMovement = unitMovement;
    }

    private void validatePathLength(List<Square> squares) {
        if (squares.size() < MIN_PATH_LENGTH) {
            throw new IllegalArgumentException("경로의 길이는 2 이상이어야 합니다.");
        }
    }

    public Square traverse(Color color) {
        Square source = getSourceSquare();
        Square destination = getDestinationSquare();
        validateTraverseRoute(color);

        if (source.hasOpponentPieceOn(destination)) {
            validatePieceAttack();
            return source.movePieceTo(destination);
        }
        validatePieceMove();
        return source.movePieceTo(destination);
    }

    private void validatePieceMove() {
        Square source = getSourceSquare();
        if (!source.canPieceMoveToward(unitMovement, getPathLength())) {
            throw new IllegalStateException("움직일 수 없는 경로입니다.");
        }
    }

    private void validatePieceAttack() {
        Square source = getSourceSquare();
        if (!source.canPieceAttackToward(unitMovement, getPathLength())) {
            throw new IllegalStateException("움직일 수 없는 경로입니다.");
        }
    }

    private void validateTraverseRoute(Color color) {
        if (hasPieceOnRoute()) {
            throw new IllegalStateException("경로에 기물이 있습니다.");
        }

        if (getSourceSquare().hasPieceOpponentColored(color)) {
            throw new IllegalArgumentException("상대방의 말을 움직일 수 없습니다.");
        }
    }

    private int getPathLength() {
        return squares.size() - 1;
    }

    private boolean hasPieceOnRoute() {
        return getMiddleRoute().stream()
                .anyMatch(Square::hasPiece);
    }

    private List<Square> getMiddleRoute() {
        return squares.subList(1, squares.size() - 1);
    }

    private Square getSourceSquare() {
        return squares.get(0);
    }

    private Square getDestinationSquare() {
        return squares.get(squares.size() - 1);
    }
}
