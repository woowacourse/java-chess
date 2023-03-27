package chess.controller.command;

import chess.domain.ChessGame;
import chess.dto.BoardDto;
import chess.view.OutputView;

public class StartCommand implements Command {
    @Override
    public void execute(ChessGame chessGame, OutputView outputView) {
        chessGame.start();
        BoardDto boardDto = BoardDto.from(chessGame.getBoard());
        outputView.printBoard(boardDto);
    }
}
