package chess.dto;

import chess.domain.ChessResult;
import chess.domain.piece.Team;

import java.util.Arrays;
import java.util.List;

public class StatusDto {
    private final String blackScore;
    private final String whiteScore;
    private final String turn;

    public StatusDto(ChessResult result, Team currentTurn) {
        this.blackScore = Double.toString(result.totalScore(Team.BLACK));
        this.whiteScore = Double.toString(result.totalScore(Team.WHITE));
        this.turn = currentTurn.teamName();
    }

    public List<String> status() {
        return Arrays.asList(blackScore, whiteScore, turn);
    }
}
