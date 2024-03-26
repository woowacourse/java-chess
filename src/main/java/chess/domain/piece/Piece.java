package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    // TODO : NullPiece만들기
    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    // TODO : 보드가 아닌 도착지의 piece를 넘기는 방안
    public abstract boolean canMove(Position start, Position destination, Piece pieceAtDestination);

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public boolean isSameTeam(Piece otherPiece) {
        return this.team == otherPiece.team;
    }

    public boolean isOtherTeam(Piece otherPiece) {
        return !isSameTeam(otherPiece);
    }

    public boolean isOtherTeam(Team team) {
        return this.team != team;
    }

    public boolean isEmpty() {
        return false;
    }
}
