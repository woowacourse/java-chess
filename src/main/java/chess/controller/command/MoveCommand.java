package chess.controller.command;

import chess.controller.ChessBoardDto;
import chess.domain.position.Position;
import chess.service.ChessService;
import chess.service.RoomNumber;
import chess.view.OutputView;
import java.util.List;

public class MoveCommand implements Command {

    private static final int SOURCE_INDEX = 0;
    private static final int DESTINATION_INDEX = 1;

    private final RoomNumber roomNumber;
    private final List<String> parameters;

    public MoveCommand(final RoomNumber roomNumber, final List<String> parameters) {
        this.roomNumber = roomNumber;
        this.parameters = parameters;
    }

    @Override
    public void execute(final ChessService chessService) {
        final Position source = Position.from(parameters.get(SOURCE_INDEX));
        final Position destination = Position.from(parameters.get(DESTINATION_INDEX));
        chessService.executeMove(roomNumber, source, destination);
        OutputView.printChessBoard(new ChessBoardDto(chessService.getGame(roomNumber).getChessBoard()));
    }
}
