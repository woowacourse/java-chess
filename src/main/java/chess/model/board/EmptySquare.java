package chess.model.board;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Direction;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.position.Distance;
import chess.model.position.Position;

public class EmptySquare extends AbstractSquare {

    public EmptySquare(final Position position) {
        super(position);
    }

    @Override
    public Square movePiece(final Position position) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isSameTeam(final PieceColor pieceColor) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public Type getType() {
        return DefaultType.EMPTY;
    }

    @Override
    public Color getColor() {
        return DefaultColor.EMPTY;
    }

    @Override
    public Piece pick() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public void validateMovable(final Distance distance) {
    }

    @Override
    public void validateExistence(final PieceColor pieceColor) {
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    @Override
    public void validateEnemyPiece(final PieceColor pieceColor) {
    }

    @Override
    public void validatePassable() {
    }
}
