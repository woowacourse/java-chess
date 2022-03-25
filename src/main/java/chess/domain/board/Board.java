package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.strategy.BoardInitializeStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board(final BoardInitializeStrategy strategy) {
        pieces = new HashMap<>(strategy.createPieces());
    }

    public void move(final Position start, final Position target, final Color currentColor) {
        final Piece movingPiece = pieces.get(start);
        validatePieceExistIn(movingPiece, currentColor);
        validateMoving(start, target);
        pieces.put(target, movingPiece);
        pieces.remove(start);
    }

    private void validateMoving(Position start, Position target) {
        final Piece movingPiece = pieces.get(start);
        if (movingPiece.isSamePiece("knight")) {
            validateKnight(start, target);
            return;
        }
        if (movingPiece.isSamePiece("pawn")) {
            validatePawn(start, target);
            return;
        }
        validateCommonPiece(start, target);
    }

    private void validateCommonPiece(final Position start, final Position target) {
        final Piece movingPiece = pieces.get(start);
        validatePath(movingPiece, start, target);

        final Piece targetPiece = pieces.get(target);
        validateTarget(movingPiece, targetPiece);
    }

    private void validateKnight(final Position start, final Position target) {
        final Piece movingPiece = pieces.get(start);
        final Piece targetPiece = pieces.get(target);
        validateTarget(movingPiece, targetPiece);
    }

    private void validatePawn(final Position start, final Position target) {
        final Piece movingPiece = pieces.get(start);
        final Piece targetPiece = pieces.get(target);
        final Direction direction = movingPiece.findValidDirection(start, target);
        if (direction.isDiagonal()) {
            validatePawnDiagonalMove(movingPiece, targetPiece);
            return;
        }
        validatePath(movingPiece, start, target);
        if (targetPiece != null) {
            throw new IllegalArgumentException("다른 말이 존재해 이동할 수 없습니다.");
        }
    }

    private void validatePawnDiagonalMove(Piece movingPiece, Piece targetPiece) {
        if (targetPiece == null || targetPiece.getColor() == movingPiece.getColor()) {
            throw new IllegalArgumentException("폰은 상대 말을 공격할 때만 대각선으로 이동할 수 있습니다.");
        }
    }

    private void validatePieceExistIn(final Piece movingPiece, final Color color) {
        if (movingPiece == null) {
            throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
        }
        if (movingPiece.getColor() != color) {
            throw new IllegalArgumentException("상대편 말은 욺직일 수 없습니다.");
        }
    }

    private void validatePath(final Piece movingPiece, final Position start, final Position target) {
        final Direction direction = movingPiece.findValidDirection(start, target);
        Position current = start.move(direction);
        while (!current.equals(target)) {
            if (pieces.get(current) != null) {
                throw new IllegalArgumentException("다른 말이 경로에 존재해 이동할 수 없습니다.");
            }
            current = current.move(direction);
        }
    }

    private void validateTarget(final Piece movingPiece, final Piece targetPiece) {
        if (targetPiece != null && movingPiece.getColor() == targetPiece.getColor()) {
            throw new IllegalArgumentException("같은 팀의 다른 말이 존재해 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
