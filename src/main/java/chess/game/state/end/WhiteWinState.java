package chess.game.state.end;

import chess.domain.Team;
import chess.dto.SquareResponse;
import java.util.List;
import java.util.function.Supplier;

public class WhiteWinState extends EndState {
    public static final EndState STATE = new WhiteWinState();

    private WhiteWinState() {
    }

    @Override
    public Team getWinner() {
        return Team.WHITE;
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
