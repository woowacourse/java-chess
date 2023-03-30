package chess.controller;

import static chess.controller.IllegalArgumentExceptionHandler.repeat;

import chess.service.RoomService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.category.CategoryCommandType;
import chess.view.dto.ready.ReadyCommandType;
import chess.view.dto.ready.ReadyRequest;
import java.util.List;

public class RoomController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RoomService roomService;
    private final GameController gameController;

    public RoomController(InputView inputView, OutputView outputView, RoomService roomService,
            GameController gameController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.roomService = roomService;
        this.gameController = gameController;
    }

    public void joinRoom(long userId) {
        repeat(() -> selectCategory(userId));
        long roomId = repeat(() -> selectRoom(userId));
        gameController.play(roomId);
    }

    private void selectCategory(long userId) {
        outputView.printSelectCategoryMessage();
        CategoryCommandType commandType = inputView.askCategory();
        while (commandType == CategoryCommandType.RECORD) {
            outputView.printRecords(roomService.findEndRooms(userId));
            outputView.printSelectCategoryMessage();
            commandType = inputView.askCategory();
        }
    }

    private long selectRoom(long userId) {
        printAskRoomNameMessages(userId);
        ReadyRequest request = inputView.askReadyCommand();
        if (request.getCommandType() == ReadyCommandType.USE) {
            return roomService.selectRoom(userId, request.getName());
        }
        return roomService.create(userId, request.getName());
    }

    private void printAskRoomNameMessages(long userId) {
        List<String> roomNames = roomService.findOngoingRoomNames(userId);
        if (roomNames.size() > 0) {
            outputView.printSelectRoomMessage(roomNames);
        }
        outputView.printCreateRoomMessage();
    }
}
