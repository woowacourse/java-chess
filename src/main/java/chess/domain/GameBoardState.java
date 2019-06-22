package chess.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GameBoardState {
    private final Map<CoordinatePair, ChessPiece> state;

    private GameBoardState(Map<CoordinatePair, ChessPiece> state) {
        this.state = Collections.unmodifiableMap(state);
    }

    public static GameBoardState of(Map<CoordinatePair, ChessPiece> state) {
        return new GameBoardState(state);
    }

    public ChessPiece at(CoordinatePair coordinate) {
        return state.get(coordinate);
    }

    public GameBoardState movePiece(CoordinatePair from, CoordinatePair to) {
        Map<CoordinatePair, ChessPiece> newState = new HashMap<>(state);
        newState.put(to, newState.get(from));
        newState.put(from, EmptyCell.getInstance());
        return of(newState);
    }

    public Stream<Map.Entry<CoordinatePair, ChessPiece>> entryStream() {
        return state.entrySet().stream();
    }
}
