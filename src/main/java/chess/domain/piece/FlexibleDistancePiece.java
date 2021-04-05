package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.feature.Color;

public abstract class FlexibleDistancePiece extends Piece {
    public FlexibleDistancePiece(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean isMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
        Position nextPosition = this.nextPosition(direction);
        Piece nextPiece = chessBoard.getPiece(this.nextPosition(direction));
        while (!nextPosition.equals(targetPosition)) {
            if (!nextPiece.isBlank()) {
                return false;
            }
            nextPosition = nextPosition.nextPosition(direction);
            nextPiece = chessBoard.getPiece(nextPosition);
        }
        return this.isNotAlly(nextPiece);
    }
}
