package chess.domain.state.turn;

import chess.domain.Team;
import chess.domain.piece.Piece;

public abstract class Finished implements State {

    private static final String GAME_PLAY_ERROR = "현재는 게임을 진행할 수 없습니다.";

    private final Team winner;

    public Finished(final Team winner) {
        this.winner = winner;
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public final State play(Piece target) {
        throw new IllegalArgumentException(GAME_PLAY_ERROR);
    }

    @Override
    public final Team getTeam() {
        return winner;
    }
}
