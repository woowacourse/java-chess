package chess.dto;

import chess.domain.piece.Team;
import chess.domain.state.Black;
import chess.domain.state.State;
import chess.domain.state.White;
import java.util.Map;

public class ResultDto {
    private final String winner;
    private final Map<Team, Double> score;

    private ResultDto(final String winner, final Map<Team, Double> score) {
        this.winner = winner;
        this.score = score;
    }

    public static ResultDto of(final State state) {
        String winner = "게임진행중";
        if (state instanceof White) {
            winner = "흑팀";
        }

        if (state instanceof Black) {
            winner = "백팀";
        }
        return new ResultDto(winner , state.status().getResult());
    }

    public String getWinner() {
        return winner;
    }

    public Map<Team, Double> getScore() {
        return score;
    }
}
