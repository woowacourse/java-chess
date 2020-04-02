package chess.controller.command;

import chess.domain.gamestatus.GameStatus;
import chess.domain.score.Score;
import chess.view.OutputView;

public class Status implements Command {

    public static final String command = "status";

    @Override
    public GameStatus execute(GameStatus gameStatus) {
        Score score = gameStatus.scoring();

        if (score.isDraw()) {
            OutputView.printRunningStatus(score.getScores());
            return gameStatus;
        }
        OutputView.printRunningStatus(score.getScores(), score.getWinner());
        return gameStatus;
    }
}
