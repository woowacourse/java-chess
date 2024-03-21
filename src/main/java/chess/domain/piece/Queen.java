package chess.domain.piece;

import static chess.domain.piece.PieceMoveResult.FAILURE;
import static chess.domain.piece.PieceType.QUEEN;

import chess.domain.Position;
import chess.domain.board.ChessBoard;
import java.util.Optional;

public final class Queen extends AbstractStraightMovePiece {
    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
                                                        ChessBoard chessBoard) {
        Position nowPosition = getPosition();
        int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
        int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
        if (absColDistance != absRowDistance && nowPosition.isOtherColumn(targetPosition) && nowPosition.isOtherRow(
                targetPosition)) {
            return Optional.of(FAILURE);
        }
        return Optional.empty();
    }

    @Override
    public PieceType getPieceType() {
        return QUEEN;
    }
}
