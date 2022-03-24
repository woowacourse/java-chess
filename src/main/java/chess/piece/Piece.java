package chess.piece;

import chess.Position;
import chess.Team;

public abstract class Piece implements Comparable<Piece>{
    protected final Position position;
    protected final Team team;

    protected Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public abstract boolean isMovable(Position position);

    @Override
    public int compareTo(Piece p) {
        if(this.position.isLessRankThan(p.position)) {
            return 1;
        }
        else if(this.position.getRank() == p.position.getRank()) {
            if(this.position.isBiggerFileThan(p.position)) {
                return 1;
            }
        }
        return -1;
    }

    public abstract String getName();
}
