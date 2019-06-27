package chess.domain;

import chess.domain.coordinate.ChessCoordinate;
import chess.domain.coordinate.ChessXCoordinate;
import chess.domain.coordinate.ChessYCoordinate;
import chess.domain.factory.AbstractStateInitiatorFactory;
import chess.domain.piece.ChessPiece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TestStateInitiatorFactory implements AbstractStateInitiatorFactory {

    private Map<ChessCoordinate, ChessPiece> state;

    public TestStateInitiatorFactory(List<List<ChessPiece>> initiailState) {
        state = new HashMap<>();
        IntStream.range(0, initiailState.size())
                .forEach(y ->
                        IntStream.range(0, initiailState.get(y).size())
                                .forEach(x ->
                                        state.put(ChessCoordinate.valueOf(ChessXCoordinate.valueOf(x), ChessYCoordinate.valueOf(y)),
                                                initiailState.get(y).get(x))));
    }

    @Override
    public Map<ChessCoordinate, ChessPiece> create() {
        return new HashMap<>(state);
    }
}
