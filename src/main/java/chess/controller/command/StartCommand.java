package chess.controller.command;

import chess.controller.ChessBoardDto;
import chess.service.ChessService;
import chess.service.RoomNumber;
import chess.view.OutputView;

public class StartCommand implements Command {

    private final RoomNumber roomNumber;

    public StartCommand(final RoomNumber roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void execute(final ChessService chessService) {
        chessService.start(roomNumber);
        OutputView.printChessBoard(new ChessBoardDto(chessService.getGame(roomNumber).getChessBoard()));
    }
}
