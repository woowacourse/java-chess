package chess.controller.state;

import chess.controller.Command;
import chess.controller.ScoreDto;
import chess.domain.Score;
import chess.domain.chess.ChessGame;
import chess.view.OutputView;

public final class End implements State {
    @Override
    public State checkCommand(final Command command) {
        throw new IllegalArgumentException("게임이 끝났습니다.");
    }

    @Override
    public boolean isRun() {
        return false;
    }

    public State run(ChessGame chessGame) {
        Score score = Score.calculate(chessGame.getChessBoard());
        OutputView.printStatus(new ScoreDto(score));
        return new End();
    }
}
