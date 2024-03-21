package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.MoveStrategy;

public abstract class ChessPiece implements Piece {
    final PieceInfo pieceInfo;
    final MoveStrategy moveStrategy;

    public ChessPiece(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        this.pieceInfo = pieceInfo;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public abstract ChessPiece move(Position newPosition, boolean isDisturbed, boolean isSameTeamExist);

    @Override
    public abstract PieceType getType();

    @Override
    public PieceInfo getPieceInfo() {
        return pieceInfo;
    }

    @Override
    public boolean isDifferentTeam(Team otherTeam) {
        return pieceInfo.isDifferentTeam(otherTeam);
    }
}
