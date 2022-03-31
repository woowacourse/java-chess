package chess.domain.gamecommand;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Status implements GameCommand {

    private final ChessGame chessGame;

    public Status(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(final String rawInputCommand) {
        OutputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
    }
}
