package chess.controller;

import chess.service.RoomService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public final class FrontController {

    private static final int NEW_ROOM_CREATION_ID = 0;
    private final ErrorController errorController;
    private final RoomService roomService;

    public FrontController(final ErrorController errorController, final RoomService roomService) {
        this.errorController = errorController;
        this.roomService = roomService;
    }

    public void run() {
        List<Integer> ids = roomService.getRoomIds();
        OutputView.printRoomId(ids);
        selectRoom(ids);
        ChessController chessController = new ChessController(new ErrorController(), roomService);
        chessController.run();
    }

    private void selectRoom(List<Integer> id) {
        int selectedId = errorController.RetryIfThrowsException(InputView::readRoomId);
        while (!id.contains(selectedId) && selectedId != NEW_ROOM_CREATION_ID) {
            OutputView.printNonExistRoomMessage();
            selectedId = errorController.RetryIfThrowsException(InputView::readRoomId);
        }
        if (selectedId == NEW_ROOM_CREATION_ID) {
            roomService.createRoom();
            return;
        }
        roomService.connectRoom(selectedId);
    }
}
