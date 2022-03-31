package chess.domain.gamecommand;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Start implements GameCommand {

    private final ChessGame chessGame;

    public Start(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execute(String rawInputCommand) {
        chessGame.start();

        OutputView.printBoard(chessGame.board().getValue());
    }
}
