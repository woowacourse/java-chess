package chess.domain.winningstatus;

import chess.domain.piece.Team;

import java.util.Map;

public interface WinningStatus {

    boolean isWinnerDetermined();

    Map<Team, Score> getScores();

    Team getWinner();

}
