package chess.controller.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

public class Start implements Command {

    private static final Start INSTANCE = new Start();

    private Start() {
    }

    public static Start getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(final ChessGame chessGame) {
        try {
            chessGame.start();
            OutputView.printChessBoard(chessGame.findAllPiece());
        } catch (final IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}
