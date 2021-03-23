package chess.domain;

import chess.domain.piece.Position;
import chess.domain.state.State;

public class PlayGame {

    public static State killKingOfBlack(State state) {
        state = state.movePiece(Position.of("b2"), Position.of("b4"));
        state = state.movePiece(Position.of("c7"), Position.of("c5"));

        state = state.movePiece(Position.of("b4"), Position.of("c5"));
        state = state.movePiece(Position.of("d7"), Position.of("d6"));

        state = state.movePiece(Position.of("c5"), Position.of("d6"));
        state = state.movePiece(Position.of("b7"), Position.of("b6"));

        state = state.movePiece(Position.of("d6"), Position.of("d7"));
        state = state.movePiece(Position.of("a7"), Position.of("a6"));
        state = state.movePiece(Position.of("d7"), Position.of("e8"));
        return state;
    }

    public static State killKingOfWhite(State state) {
        state = state.movePiece(Position.of("c2"), Position.of("c4"));
        state = state.movePiece(Position.of("b7"), Position.of("b5"));

        state = state.movePiece(Position.of("d2"), Position.of("d3"));
        state = state.movePiece(Position.of("b5"), Position.of("c4"));

        state = state.movePiece(Position.of("a2"), Position.of("a3"));
        state = state.movePiece(Position.of("c4"), Position.of("d3"));

        state = state.movePiece(Position.of("a3"), Position.of("a4"));
        state = state.movePiece(Position.of("d3"), Position.of("d2"));

        state = state.movePiece(Position.of("a4"), Position.of("a5"));
        state = state.movePiece(Position.of("d2"), Position.of("e1"));
        return state;
    }
}
