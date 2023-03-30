package chess.game.state.end;

import chess.domain.Team;
import chess.dto.SquareResponse;
import chess.game.GameId;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class NoneWinState extends EndState {
    public static final EndState STATE = new NoneWinState();

    private NoneWinState() {
    }

    @Override
    public Team getWinner() {
        return Team.NONE;
    }

    @Override
    public List<SquareResponse> getBoard(Supplier<List<SquareResponse>> supplier) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public GameId getGameId(Supplier<GameId> supplier) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public double getTeamScore(DoubleSupplier doubleSupplier) {
        throw new IllegalStateException(END_STATE_EXCEPTION_MESSAGE);
    }
}
