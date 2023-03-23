package chess.controller.command;

import chess.domain.game.ChessGame;
import chess.view.OutputView;
import chess.view.dto.ChessBoardDto;

public class StartCommand implements ExecuteCommand {

    @Override
    public void execute(final ChessGame chessGame, final OutputView outputView) {
        chessGame.start();
        outputView.printChessBoard(ChessBoardDto.from(chessGame));
    }
}
