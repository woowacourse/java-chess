package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;

public final class Rook extends Piece {

    private static final String ROOK_NAME = "R";

    public Rook(Color color, Position position) {
        super(color, ROOK_NAME, position);
    }

    public void move(Position position, ChessBoard chessBoard) {
        validateMovablePosition(position);
        
        validateSameColor(position, chessBoard);
    }

    private void validateMovablePosition(final Position position) {
        if (!isPossibleToMove(position)) {
            throw new IllegalStateException("룩은 상하좌우 방향으로만 움직일 수 있습니다.");
        }
    }

    private boolean isPossibleToMove(Position position) {
        return position.equalsColumnOrRow(getPosition());
    }

    private void validateSameColor(final Position position, final ChessBoard chessBoard) {
        if (!chessBoard.isPositionEmpty(position) && isEqualColor(chessBoard.pieceByPosition(position))) {
            throw new IllegalStateException("해당 위치에 같은 색상의 기물이 존재하여 움직일 수 없습니다.");
        }
    }
}
