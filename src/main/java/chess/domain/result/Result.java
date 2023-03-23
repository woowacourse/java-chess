package chess.domain.result;

import chess.domain.Team;

public interface Result {

	Team getWinner();
	int getWhiteScoreMultipliedBy10();
	int getBlackScoreMultipliedBy10();
}
