package chess.domain.piece.core;

import chess.domain.piece.*;

import java.util.function.Function;

public enum Type {
    KING("k", King::new),
    QUEEN("q", Queen::new),
    BISHOP("b", Bishop::new),
    KNIGHT("N", Knight::new),
    ROOK("r", Rook::new),
    PAWN("p", Pawn::new);

    String type;
    Function<Team, Piece> creator;

    Type(String type, Function<Team, Piece> creator) {
        this.type = type;
        this.creator = creator;
    }

    public String getType() {
        return type;
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
