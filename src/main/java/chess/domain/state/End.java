package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.square.Square;
import chess.domain.square.Team;

public class End implements State {
    private static final String END_EXCEPTION_MESSAGE = "게임이 이미 종료되었습니다.";

    private final Team winnerTeam;

    public End(final Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    @Override
    public State start() {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }

    @Override
    public State end() {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }

    @Override
    public State move(final Square source, final Square target) {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }

    @Override
    public double calculateScore(final Team team) {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isKingDead() {
        return false;
    }

    @Override
    public Team getWinner() {
        return winnerTeam;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }
}
