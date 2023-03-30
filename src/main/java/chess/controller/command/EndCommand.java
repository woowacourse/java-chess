package chess.controller.command;

import chess.dao.ChessDao;
import chess.service.ChessService;
import chess.service.RoomNumber;
import chess.view.OutputView;

public class EndCommand implements Command {

    private final RoomNumber roomNumber;
    private final ChessDao chessDao;

    public EndCommand(RoomNumber roomNumber) {
        this.roomNumber = roomNumber;
        this.chessDao = ChessDao.getInstance();
    }

    @Override
    public void execute(final ChessService chessService) {
        chessService.end(roomNumber);
        if (chessService.isFirstTurn(roomNumber)) {
            return;
        }
        OutputView.printSaveMessage();
        chessDao.save(chessService.getGame(roomNumber).getChessBoard(), roomNumber);
    }
}
