package chess.domain;

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
                                        state.put(ChessCoordinate.valueOf(ChessXCoordinate.valueOf(x).get(), ChessYCoordinate.valueOf(y).get()).get(),
                                                initiailState.get(y).get(x))));
    }

    @Override
    public Map<ChessCoordinate, ChessPiece> create() {
        return new HashMap<>(state);
    }
}
