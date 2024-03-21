package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.MoveStrategy;
import java.util.Objects;

public abstract class ChessPiece implements Piece {
    final PieceInfo pieceInfo;
    final MoveStrategy moveStrategy;

    public ChessPiece(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        this.pieceInfo = pieceInfo;
        this.moveStrategy = moveStrategy;
    }

    public Position getPosition() {
        return pieceInfo.getPosition();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return Objects.equals(pieceInfo, that.pieceInfo) && Objects.equals(moveStrategy,
                that.moveStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceInfo, moveStrategy);
    }
}
