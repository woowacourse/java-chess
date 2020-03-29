package chess.domain.gamestate;

import chess.domain.Team;

import java.util.List;

public class Running extends Started {
    private static final String IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE = "게임 진행 중에는 점수를 계산할 수 없습니다.";

    private Team teamInTurn;

    public Running() {
        this.teamInTurn = Team.WHITE;
    }

    @Override
    public GameState move(String keyFromPosition, String keyToPosition) {
        board.move(keyFromPosition, keyToPosition, teamInTurn);

        if (board.checkIfOppositeKingIsDead(teamInTurn)) {
            return finish();
        }

        teamInTurn = teamInTurn.opposite();

        return this;
    }

    @Override
    public List<List<String>> getBoard() {
        return board.getBoard();
    }

    @Override
    public GameState finish() {
        return new Finished(board);
    }

    @Override
    public double getWhiteTeamScore() {
        throw new UnsupportedOperationException(IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE);
    }

    @Override
    public double getBlackTeamScore() {
        throw new UnsupportedOperationException(IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE);

    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
