package chess.domain.piece;

import chess.domain.piece.state.BishopState;
import chess.domain.piece.state.EmptyState;
import chess.domain.piece.state.InitialPawnState;
import chess.domain.piece.state.KingState;
import chess.domain.piece.state.KnightState;
import chess.domain.piece.state.MoveState;
import chess.domain.piece.state.MovedPawnState;
import chess.domain.piece.state.QueenState;
import chess.domain.piece.state.RookState;
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

    private static final int INITIAL_STATE = 0;

    private final String name;
    private final List<MoveState> state;

    PieceType(String name, List<MoveState> state) {
        this.name = name;
        this.state = state;
    }

    public static PieceType from(String type) {
        return Arrays.stream(PieceType.values())
                .filter(it -> it.name.equals(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"));
    }

    public static PieceType getName(MoveState moveState) {
        return Arrays.stream(values())
                .filter(it -> it.state.contains(moveState))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"));
    }

    public String getName() {
        return name;
    }

    public MoveState getState() {
        return state.get(INITIAL_STATE);
    }
}
