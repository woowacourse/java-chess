package chess.model.board;

import chess.model.Color;
import chess.model.Type;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.position.Distance;
import chess.model.position.Position;

public class EmptySquare extends AbstractSquare {

    private static final UnsupportedOperationException UNSUPPORTED_OPERATION_EXCEPTION =
            new UnsupportedOperationException("지원하지 않는 기능입니다.");

    EmptySquare(final Position position) {
        super(position);
    }

    @Override
    public void validateMovable(final Distance distance) {
        // 체스 판에서 비어 있는 체스 칸이기 때문에 기물이 어떤 방향으로 움직이는지 검증할 필요가 없음
    }

    @Override
    public void validateExistence(final PieceColor pieceColor) {
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    @Override
    public void validateEnemyPiece(final PieceColor pieceColor) {
        // 체스 판에서 비어 있는 체스 칸이기 때문에 적 기물인지 검증할 필요가 없음
    }

    @Override
    public void validateWaypoint() {
        // 체스 판에서 비어 있는 체스 칸이기 때문에 이동 경로에 기물이 있는지 검증할 필요가 없음
    }

    @Override
    public Square movePiece(final Position position) {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public Piece pick() {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isSameTeam(final PieceColor pieceColor) {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public Type getType() {
        return DefaultType.EMPTY;
    }

    @Override
    public Color getColor() {
        return DefaultColor.EMPTY;
    }
}
