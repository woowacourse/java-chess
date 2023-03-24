package chess.domain.command;

import chess.domain.result.Score;
import chess.domain.side.Side;
import chess.service.ChessGame;
import chess.view.OutputView;

import java.util.Map;

public class StatusCommand implements Command {
    private final OutputView outputView;
    private final ChessGame chessGame;

    public StatusCommand(final OutputView outputView, final ChessGame chessGame) {
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    @Override
    public void execute(String command) {
        Map<Side, Score> stauts = chessGame.stauts();
        outputView.printStatus(stauts);
    }
}
