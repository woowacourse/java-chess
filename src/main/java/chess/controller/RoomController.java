package chess.controller;

import static chess.controller.IllegalArgumentExceptionHandler.repeat;

import chess.repository.jdbc.JdbcRoomDao;
import chess.service.RoomService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.ready.ReadyCommandType;
import chess.view.dto.ready.ReadyRequest;
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
        long roomId = repeat(this::selectRoom);
        Controller controller = new GameController(inputView, outputView, roomId);
        controller.run();
    }

    private long selectRoom() {
        printAskRoomNameMessages();
        ReadyRequest request = inputView.askReadyCommand();
        if (request.getCommandType() == ReadyCommandType.USE) {
            return roomService.selectRoom(userId, request.getName());
        }
        return roomService.create(userId, request.getName());
    }

    private void printAskRoomNameMessages() {
        List<String> roomNames = roomService.findOngoingRoomNames(userId);
        if (roomNames.size() > 0) {
            outputView.printSelectRoomMessage(roomNames);
        }
        outputView.printCreateRoomMessage();
    }
}
