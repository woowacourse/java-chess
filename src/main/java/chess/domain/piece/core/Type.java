package chess.domain.piece.core;

import chess.domain.piece.*;

import java.util.function.Function;

public enum Type {
    KING("k", 0, King::new),
    QUEEN("q", 9, Queen::new),
    BISHOP("b", 3, Bishop::new),
    KNIGHT("N", 2.5, Knight::new),
    ROOK("r", 5, Rook::new),
    PAWN("p", 1, Pawn::new),
    MOVEDPAWN("mp",1,MovedPawn::new);

    String type;
    double score;
    Function<Team, Piece> creator;

    Type(String type, double score, Function<Team, Piece> creator) {
        this.type = type;
        this.score = score;
        this.creator = creator;
    }

    public String getType() {
        return type;
    }

    public double getScore() {
        return score;
    }

    public Piece create(Team team) {
        return creator.apply(team);
    }

    @Override
    public String toString() {
        return "Type{" +
                "type='" + type + '\'' +
                ", creator=" + creator +
                '}';
    }
}
