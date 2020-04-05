package chess.dto;

import chess.domain.board.TeamScore;
import chess.domain.piece.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultDto {

    private final String winner;
    private final String blackScore;
    private final String whiteScore;

    public ResultDto(TeamScore teamScore) {
        List<String> winners = new ArrayList<>();
        for (Color winner : teamScore.getWinners()) {
            winners.add(winner.getName());
        }
        this.winner = String.join(", ", winners);

        Map<Color, Double> teamScores = teamScore.getTeamScore();
        this.blackScore = String.valueOf(teamScores.get(Color.BLACK));
        this.whiteScore = String.valueOf(teamScores.get(Color.WHITE));
    }

    public String getWinner() {
        return winner;
    }

    public String getBlackScore() {
        return blackScore;
    }

    public String getWhiteScore() {
        return whiteScore;
    }
}
