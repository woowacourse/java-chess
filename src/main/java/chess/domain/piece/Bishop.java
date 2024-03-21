package chess.domain.piece;

import chess.domain.Position;
import chess.domain.board.ChessBoard;
import java.util.Optional;

public final class Bishop extends AbstractStraightMovePiece {
    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
                                                        ChessBoard chessBoard) {
        Position nowPosition = getPosition();
        int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
        int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
        if (absColDistance != absRowDistance) {
            return Optional.of(PieceMoveResult.FAILURE);
        }
        return Optional.empty();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }
}
