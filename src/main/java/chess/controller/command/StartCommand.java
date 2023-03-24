package chess.controller.command;

import chess.controller.ChessBoardDto;
import chess.domain.ChessGame;
import chess.view.OutputView;

public class StartCommand implements Command {

    public StartCommand() {
    }

    @Override
    public void execute(ChessGame chessGame, final OutputView outputView) {
        chessGame.initialize();
        outputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
    }
}
