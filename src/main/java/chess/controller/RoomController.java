package chess.controller;

import chess.dto.RoomCommand;
import chess.dto.RoomRequest;
import chess.service.RoomService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class RoomController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RoomService roomService;
    private final GameController gameController;

    public RoomController(final InputView inputView, final OutputView outputView, final RoomService roomService,
                          final GameController gameController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.roomService = roomService;
        this.gameController = gameController;
    }

    public void enterRoom(final long userId) {
        printRoomNames(userId);
        long roomId = requestUntilValidated(() -> selectRoom(userId));
        gameController.start(roomId);
    }

    private long selectRoom(final long userId) {
        RoomRequest request = inputView.readRoomRequest();
        if (request.getCommand() == RoomCommand.ENTER) {
            return roomService.selectRoom(userId, request.getName());
        }
        return roomService.create(userId, request.getName());
    }


    private void printRoomNames(final long userId) {
        List<String> roomNames = roomService.getRoomNames(userId);
        outputView.printRoomEntranceMessage();
        outputView.printRoomNames(roomNames);
    }

    private <T> T requestUntilValidated(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return requestUntilValidated(supplier);
        }
    }
}
