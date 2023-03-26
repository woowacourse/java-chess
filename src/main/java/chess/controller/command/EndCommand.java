package chess.controller.command;

import chess.dao.ChessDao;
import chess.domain.GameRoom;

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
        chessDao.save(gameRoom);
    }
}
