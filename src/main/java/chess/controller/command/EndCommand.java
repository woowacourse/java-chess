package chess.controller.command;

import chess.dao.ChessDao;
import chess.domain.GameRoom;
import chess.view.OutputView;

public class EndCommand implements Command {

    private final ChessDao chessDao;

    public EndCommand() {
        chessDao = ChessDao.getInstance();
    }

    @Override
    public void execute(GameRoom gameRoom) {
        gameRoom.end();
        if (gameRoom.isFirstTurn()) {
            return;
        }
        OutputView.printSaveMessage();
        chessDao.save(gameRoom);
    }
}
