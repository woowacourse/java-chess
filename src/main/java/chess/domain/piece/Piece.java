package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

import java.util.Collections;
import java.util.List;

public abstract class Piece {
    private final Owner owner;
    private final List<Direction> directions;
    private final Score score;
    private final int distance;
    private final String symbol;

    // XXX :: 빌더 패턴 공부하기
    // XXX :: Symbol은 view로 빼기
    public Piece(final Owner owner,
                 final Score score,
                 final List<Direction> directions,
                 final int distance,
                 final String symbol) {

        this.owner = owner;
        this.score = score;
        this.directions = directions;
        this.distance = distance;
        this.symbol = symbol;
    }

    public abstract boolean isReachable(final Position source, final Position target, final Piece targetPiece);

    public final String getSymbol(){
        return symbol;
    };

    public final int getMaxDistance(){
        return distance;
    }

    public final boolean isEnemy(final Piece other) {
        return this.owner.isEnemy(other.owner);
    }

    public final boolean isSameTeam(final Piece other) {
        return this.owner.isSameTeam(other.owner);
    }

    public final List<Direction> getDirections() {
        return Collections.unmodifiableList(directions);
    }

    public boolean isOwner(final Owner owner) {
        return this.owner.isSameTeam(owner);
    }

     /*
       XXX :: instance of 사용? 오버라이드?
        상위 클래스에서 하위 클래스를 사용하는 것은 잘못되었다고 생각한다.
     */

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public final Score score() {
        return this.score;
    }
}
