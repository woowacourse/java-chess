package chess.domain.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Start implements Command {
    private final ChessGame chessGame;

    public Start(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(String rawInputCommand, final OutputView outputView) {
        chessGame.start();
        outputView.printBoard(chessGame.board().getValue());
    }
}
