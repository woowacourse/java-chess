package chess.domain.command;

import chess.service.ChessGame;
import chess.view.OutputView;

public class StartCommand implements Command {
    private final OutputView outputView;
    private final ChessGame chessGame;

    public StartCommand(final OutputView outputView, final ChessGame chessGame) {
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    @Override
    public void execute(final String command) {
        chessGame.initializeBoard();
        outputView.printBoard(chessGame.findChessBoard());
    }

}
