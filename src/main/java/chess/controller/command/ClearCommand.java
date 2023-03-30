package chess.controller.command;

import chess.domain.ChessGame;
import chess.dto.BoardDto;
import chess.view.OutputView;

public class ClearCommand implements Command {
    @Override
    public void execute(final ChessGame chessGame, final OutputView outputView) {
        chessGame.clear();
        BoardDto boardDto = BoardDto.from(chessGame.getBoard());
        outputView.printBoard(boardDto);
    }
}
