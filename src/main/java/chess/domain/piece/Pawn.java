package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.BlackPawnNotFirstMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.WhitePawnNotFirstMoveStrategy;

public class Pawn extends ChessPiece {

    public Pawn(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public Pawn move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return this;
        }
        if (isDisturbed || isOtherPieceExist) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Pawn(newPieceInfo, changeMovedStrategy());
    }

    public Pawn move(Position newPosition,
                     boolean isDisturbed,
                     boolean isOtherPieceExist,
                     boolean isDifferentTeam) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return this;
        }
        if (isDisturbed || !(isOtherPieceExist && isDifferentTeam)) {
            return this;
        }

        PieceInfo newPieceInfo = pieceInfo.renewPosition(newPosition);
        return new Pawn(newPieceInfo, changeMovedStrategy());
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    private MoveStrategy changeMovedStrategy() {
        if (pieceInfo.getTeam() == Team.WHITE) {
            return new WhitePawnNotFirstMoveStrategy();
        }
        return new BlackPawnNotFirstMoveStrategy();
    }
}
