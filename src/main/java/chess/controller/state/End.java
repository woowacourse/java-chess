package chess.controller.state;

import chess.controller.Command;
import chess.controller.ScoreDto;
import chess.domain.game.ChessGame;
import chess.domain.piece.TeamColor;
import chess.domain.result.Score;
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
        if (chessGame.isEnd()) {
            TeamColor winner = chessGame.getCurrentTeamColor();
            OutputView.print(winner.name());
        }
        return new End();
    }
}
