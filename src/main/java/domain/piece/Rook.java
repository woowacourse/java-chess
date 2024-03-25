package domain.piece;

import static domain.PieceMoveResult.FAILURE;
import static domain.piece.PieceType.ROOK;

import domain.PieceMoveResult;
import domain.PiecesOnChessBoard;
import domain.Position;
import domain.Team;
import java.util.Optional;

public final class Rook extends AbstractStraightMovePiece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
                                                        PiecesOnChessBoard piecesOnChessBoard) {
        Position nowPosition = getPosition();
        if (nowPosition.isOtherColumn(targetPosition) && nowPosition.isOtherRow(targetPosition)) {
            return Optional.of(FAILURE);
        }
        return Optional.empty();
    }

    @Override
    public PieceType getPieceType() {
        return ROOK;
    }
}
