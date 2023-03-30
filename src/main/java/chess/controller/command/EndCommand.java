package chess.controller.command;

import chess.service.ChessService;
import chess.service.RoomNumber;
import chess.view.OutputView;

public class EndCommand implements Command {

    private final RoomNumber roomNumber;

    public EndCommand(RoomNumber roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void execute(final ChessService chessService) {
        chessService.end(roomNumber);
        if (chessService.isFirstTurn(roomNumber)) {
            return;
        }
        OutputView.printSaveMessage();
        chessService.save(roomNumber);
    }
}
