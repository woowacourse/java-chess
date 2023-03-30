package chess.domain.piece;

import chess.domain.piece.move_rule.MoveRule;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public class Piece {
    protected final MoveRule moveRule;
    private final PlayingCamp playingCamp;
    private final PieceType pieceType;

    public Piece(MoveRule moveRule, PlayingCamp playingCamp) {
        this.moveRule = moveRule;
        this.playingCamp = playingCamp;
        this.pieceType = moveRule.pieceType();
    }

    public Piece(PieceType pieceType, PlayingCamp playingCamp) {
        this.moveRule = pieceType.getRule();
        this.playingCamp = playingCamp;
        this.pieceType = pieceType;
    }

    public List<Position> move(Position currentPosition, Position nextPosition) {
        return moveRule.move(currentPosition, nextPosition);
    }

    public boolean isOpponent(Piece other) {
        return this.playingCamp != other.playingCamp;
    }

    public boolean isFriendly(Piece other) {
        return this.playingCamp == other.playingCamp;
    }

    public boolean isSameCamp(PlayingCamp playingCamp) {
        return this.playingCamp == playingCamp;
    }

    public boolean isPawn() {
        return moveRule.isPawnMove();
    }

    public boolean isKing() {
        return moveRule.isKingMove();
    }

    public PieceType getPieceType() {
        return moveRule.pieceType();
    }

    public PlayingCamp getPlayingCamp() {
        return playingCamp;
    }

    public double getScore() {
        return this.pieceType.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(moveRule.getClass(), piece.moveRule.getClass()) && playingCamp == piece.playingCamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveRule.getClass(), playingCamp);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "moveRule=" + moveRule +
                ", color=" + playingCamp +
                '}';
    }
}
