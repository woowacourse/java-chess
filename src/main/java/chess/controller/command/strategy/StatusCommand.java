package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.team.Team;
import chess.view.OutputView;

import static chess.controller.ChessState.*;
import static chess.domain.team.Team.*;

public class StatusCommand implements StrategyCommand {

    private static final String CANNOT_STATUS_BEFORE_START_ERROR_MESSAGE = "게임이 시작되기 전에 점수를 확인할 수 없습니다";

    private StatusCommand() {
    }

    public static StatusCommand create() {
        return new StatusCommand();
    }

    @Override
    public ChessState execute(final ChessState state, final ChessGame chessGame) {
        if (state == START || state == PROGRESS) {
            Score white = chessGame.calculateScore(WHITE);
            Score black = chessGame.calculateScore(BLACK);

            OutputView.printStatus(white, black);
            OutputView.printScoreWinning(white, black);
            OutputView.printBoard(chessGame.getBoard());
            return PROGRESS;
        }

        throw new IllegalArgumentException(CANNOT_STATUS_BEFORE_START_ERROR_MESSAGE);
    }
}
