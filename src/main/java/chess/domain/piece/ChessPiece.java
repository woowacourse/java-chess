package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.strategy.MoveStrategy;
import java.util.Objects;

public abstract class ChessPiece implements Piece {
    protected final PieceInfo pieceInfo;
    protected final MoveStrategy moveStrategy;

    public ChessPiece(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        this.pieceInfo = pieceInfo;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public abstract ChessPiece move(Position newPosition, boolean isObstacleInRange, boolean isOtherPieceExist,
                                    boolean isSameTeamExist);

    @Override
    public abstract boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                                          boolean isSameTeam);

    @Override
    public abstract PieceType getType();

    @Override
    public PieceInfo getPieceInfo() {
        return pieceInfo;
    }

    @Override
    public Team getTeam() {
        return pieceInfo.getTeam();
    }

    @Override
    public boolean isSameTeam(Team otherTeam) {
        return pieceInfo.isSameTeam(otherTeam);
    }

    @Override
    public Position getPosition() {
        return pieceInfo.getPosition();
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
        return Objects.equals(pieceInfo, that.pieceInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceInfo);
    }
}
