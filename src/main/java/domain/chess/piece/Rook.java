package domain.chess.piece;

import static domain.chess.piece.PieceMoveResult.FAILURE;

import domain.Position;
import domain.chess.board.ChessBoard;
import java.util.Optional;

public final class Rook extends AbstractStraightMovePiece {
    public Rook(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
                                                        ChessBoard chessBoard) {
        Position nowPosition = getPosition();
        if (nowPosition.isOtherColumn(targetPosition) && nowPosition.isOtherRow(targetPosition)) {
            return Optional.of(FAILURE);
        }
        return Optional.empty();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }
}
