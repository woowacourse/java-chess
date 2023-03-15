package chess.domain;

import chess.domain.state.InitialPawnState;
import chess.domain.state.MoveState;

import java.util.Arrays;

public enum PieceType {
    ROOK("r", new InitialPawnState()),
    KNIGHT("n", new InitialPawnState()),
    BISHOP("b", new InitialPawnState()),
    QUEEN("q", new InitialPawnState()),
    KING("k", new InitialPawnState()),
    PAWN("p", new InitialPawnState()),
    EMPTY(".", new InitialPawnState());
    private final String type;
    private final MoveState state;

    PieceType(String type, MoveState state) {
        this.type = type;
        this.state = state;
    }

    public static PieceType from(String type) {
        return Arrays.stream(PieceType.values())
                .filter(it -> it.type.equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"));
    }

    public String getType() {
        return type;
    }

    public MoveState getState() {
        return state;
    }
}
