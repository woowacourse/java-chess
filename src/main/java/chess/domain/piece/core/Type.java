package chess.domain.piece.core;

import chess.domain.piece.*;

import java.util.function.Function;

public enum Type {
    KING("KING", 0, King::new),
    QUEEN("QUEEN", 9, Queen::new),
    BISHOP("BISHOP", 3, Bishop::new),
    KNIGHT("KNIGHT", 2.5, Knight::new),
    ROOK("ROOK", 5, Rook::new),
    PAWN("PAWN", 1, Pawn::new),
    MOVEDPAWN("MOVEDPAWN", 1, MovedPawn::new);

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
        return type;
    }
}
