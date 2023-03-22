package chess.game.state.end;

import chess.domain.Team;
import chess.dto.SquareResponse;
import java.util.List;
import java.util.function.Supplier;

public class BlackWinState extends EndState {
    public static final EndState STATE = new BlackWinState();

    private BlackWinState() {
    }

    @Override
    public Team getWinner() {
        return Team.BLACK;
    }

    @Override
    public List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier) {
        return supplier.get();
    }

    @Override
    public boolean hasWinner() {
        return true;
    }
}
