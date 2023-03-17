package chess.domain;

import chess.domain.state.*;

import java.util.Arrays;
import java.util.function.Supplier;

public enum PieceType {
    ROOK("r", RookState::new),
    KNIGHT("n", KnightState::new),
    BISHOP("b", BishopState::new),
    QUEEN("q", QueenState::new),
    KING("k", KingState::new),
    PAWN("p", PawnState::new),
    EMPTY(".", EmptyState::new);

    private final String type;
    private final Supplier<MoveState> state;

    PieceType(String type, Supplier<MoveState> state) {
        this.type = type;
        this.state = state;
    }

    public static PieceType from(String type) {
        return Arrays.stream(PieceType.values())
                .filter(it -> it.type.equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"));
    }

    public static PieceType getType(MoveState moveState) {
        return Arrays.stream(values())
                .filter(it -> it.getState().getClass().equals(moveState.getClass()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"));
    }

    public String getType() {
        return type;
    }

    public MoveState getState() {
        return state.get();
    }
}
