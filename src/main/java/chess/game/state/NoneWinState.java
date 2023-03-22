package chess.game.state;

import chess.domain.Team;
import chess.dto.SquareResponse;
import java.util.List;
import java.util.function.Supplier;

public class NoneWinState extends EndState {
    public static final GameState STATE = new NoneWinState();

    @Override
    public Team getWinner() {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean hasWinner() {
        return false;
    }
}
