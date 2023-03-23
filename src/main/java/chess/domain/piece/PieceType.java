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
    ROOK(5, List.of(RookState.getInstance())),
    KNIGHT(2.5, List.of(KnightState.getInstance())),
    BISHOP(3, List.of(BishopState.getInstance())),
    QUEEN(9, List.of(QueenState.getInstance())),
    KING(0, List.of(KingState.getInstance())),
    PAWN(1, List.of(InitialPawnState.getInstance(), MovedPawnState.getInstance())),
    EMPTY(0, List.of(EmptyState.getInstance()));

    private static final int INITIAL_STATE = 0;

    private final double score;
    private final List<MoveState> state;


    PieceType(double score, List<MoveState> state) {
        this.score = score;
        this.state = state;
    }

    public static PieceType getName(MoveState moveState) {
        return Arrays.stream(values())
                .filter(it -> it.state.contains(moveState))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다"));
    }

    public MoveState getState() {
        return state.get(INITIAL_STATE);
    }

    public double getScore() {
        return score;
    }
}
