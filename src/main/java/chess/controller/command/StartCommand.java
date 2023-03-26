package chess.controller.command;

import chess.controller.ChessBoardDto;
import chess.domain.GameRoom;
import chess.view.OutputView;

public class StartCommand implements Command {

    public StartCommand() {
    }

    @Override
    public void execute(GameRoom gameRoom) {
        gameRoom.start();
        OutputView.printChessBoard(new ChessBoardDto(gameRoom.getChessGame().getChessBoard()));
    }
}
