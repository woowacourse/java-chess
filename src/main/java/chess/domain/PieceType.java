package chess.domain;

import chess.domain.state.BishopState;
import chess.domain.state.EmptyState;
import chess.domain.state.InitialPawnState;
import chess.domain.state.KingState;
import chess.domain.state.KnightState;
import chess.domain.state.MoveState;
import chess.domain.state.MovedPawnState;
import chess.domain.state.QueenState;
import chess.domain.state.RookState;
import java.util.Arrays;
import java.util.List;

public enum PieceType {
    ROOK("r", List.of(RookState.getInstance())),
    KNIGHT("n", List.of(KnightState.getInstance())),
    BISHOP("b", List.of(BishopState.getInstance())),
    QUEEN("q", List.of(QueenState.getInstance())),
    KING("k", List.of(KingState.getInstance())),
    PAWN("p", List.of(InitialPawnState.getInstance(), MovedPawnState.getInstance())),
    EMPTY(".", List.of(EmptyState.getInstance()));
    public static final int INITIAL_STATE = 0;

    private final String type;
    private final List<MoveState> state;

    PieceType(String type, List<MoveState> state) {
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
                .filter(it -> it.state.contains(moveState))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"));
    }

    public String getType() {
        return type;
    }

    public MoveState getState() {
        return state.get(INITIAL_STATE);
    }
}
