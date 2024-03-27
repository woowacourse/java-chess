package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;
import chess.domain.strategy.MoveStrategy;

import java.util.List;
import java.util.Objects;

public abstract class ChessPiece implements Piece {
    final PieceInfo pieceInfo;
    final MoveStrategy moveStrategy;

    protected ChessPiece(final PieceInfo pieceInfo, final MoveStrategy moveStrategy) {
        this.pieceInfo = pieceInfo;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public abstract ChessPiece move(final Position newPosition, final boolean isDisturbed,
                                    final boolean isOtherPieceExist, final boolean isSameTeamExist);

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
    public Position getPosition() {
        return pieceInfo.getPosition();
    }

    @Override
    public boolean isSameTeam(final Team otherTeam) {
        return pieceInfo.isSameTeam(otherTeam);
    }

    @Override
    public boolean isSamePieceWithSameTeam(final List<Piece> otherPieces) {
        return otherPieces.stream().anyMatch(this::isSamePieceWithSameTeamCondition);
    }

    private boolean isSamePieceWithSameTeamCondition(final Piece otherPiece) {
        return otherPiece.getType() == this.getType() && otherPiece.isSameTeam(this.getTeam());
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
