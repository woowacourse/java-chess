package chess.model.board;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.position.Distance;
import chess.model.position.Position;

public class PieceSquare extends AbstractSquare {

    private final Piece piece;

    public PieceSquare(final Position position, final Piece piece) {
        super(position);
        this.piece = piece;
    }

    @Override
    public Square movePiece(final Position position) {
        return new PieceSquare(position, this.piece);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isSameTeam(final PieceColor pieceColor) {
        return piece.isSameTeam(pieceColor);
    }

    @Override
    public Type getType() {
        return piece.getType();
    }

    @Override
    public Color getColor() {
        return piece.getColor();
    }

    @Override
    public Piece pick() {
        return piece.pick();
    }

    @Override
    public boolean hasPawn() {
        return piece.isPawn();
    }

    @Override
    public void validateMovable(final Distance distance) {
        if (cannotMove(distance)) {
            throw new IllegalArgumentException("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
        }
    }

    private boolean cannotMove(final Distance distance) {
        return !piece.movable(distance);
    }

    @Override
    public void validateExistence(final PieceColor pieceColor) {
        if (isEmpty()) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }

        if (!isSameTeam(pieceColor)) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }
    }

    @Override
    public void validateEnemyPiece(final PieceColor pieceColor) {
        if (isSameTeam(pieceColor)) {
            throw new IllegalArgumentException("해당 경로로 이동할 수 없습니다");
        }
    }

    @Override
    public void validatePassable() {
        throw new IllegalArgumentException("해당 경로로 이동할 수 없습니다");
    }
}
