package chess.controller;

import static chess.controller.IllegalArgumentExceptionHandler.repeat;

import chess.repository.jdbc.JdbcRoomDao;
import chess.service.RoomService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.ReadyCommandType;
import chess.view.dto.ReadyRequest;
import java.util.List;

public class RoomController extends Controller {

    private final RoomService roomService;
    private final long userId;

    public RoomController(InputView inputView, OutputView outputView, long userId) {
        super(inputView, outputView);
        this.roomService = new RoomService(new JdbcRoomDao());
        this.userId = userId;
    }

    @Override
    public void run() {
        repeat(this::selectRoom);
    }

    private void selectRoom() {
        printAskRoomNameMessages();
        ReadyRequest request = inputView.askReadyCommand();
        if (request.getCommandType() == ReadyCommandType.USE) {
            long roomId = roomService.selectRoom(userId, request.getName());
            return;
        }
        long roomId = roomService.create(userId, request.getName());
    }

    private void printAskRoomNameMessages() {
        List<String> roomNames = roomService.findOngoingRoomNames(userId);
        if (roomNames.size() > 0) {
            outputView.printSelectRoomMessage(roomNames);
        }
        outputView.printCreateRoomMessage();
    }
}
