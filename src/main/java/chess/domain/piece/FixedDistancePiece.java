package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.feature.Color;

public abstract class FixedDistancePiece extends Piece {
    public FixedDistancePiece(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean isMovable(ChessBoard chessBoard, Direction direction, Position targetPosition) {
        Position nextPosition = this.nextPosition(direction);
        Piece targetPiece = chessBoard.getPiece(this.nextPosition(direction));
        return nextPosition.equals(targetPosition) && this.isNotAlly(targetPiece);
    }
}
