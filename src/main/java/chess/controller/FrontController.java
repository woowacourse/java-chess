package chess.controller;

import chess.controller.dto.OutputRenderer;
import chess.dao.DBBoardDao;
import chess.dao.DBRoomDao;
import chess.domain.Room;
import chess.service.ChessGameService;
import chess.service.RoomConnectionService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public final class FrontController {

    private static final int NEW_ROOM_CREATION_ID = 0;
    private final ErrorController errorController;
    private final RoomConnectionService roomConnectionService;

    public FrontController(final ErrorController errorController, final RoomConnectionService roomConnectionService) {
        this.errorController = errorController;
        this.roomConnectionService = roomConnectionService;
    }

    public void run() {
        List<Room> rooms = roomConnectionService.getRooms();
        OutputView.printRoomId(OutputRenderer.toRoomDto(rooms));
        Room room = selectRoom(rooms);

        ChessGameService chessGameService = new ChessGameService(new DBRoomDao(), new DBBoardDao(), room);
        ChessController chessController = new ChessController(new ErrorController(), chessGameService);
        chessController.run();
    }

    private Room selectRoom(List<Room> rooms) {
        int selectedId = errorController.RetryIfThrowsException(InputView::readRoomId);

        List<Integer> ids = rooms.stream().map(Room::getId).collect(Collectors.toList());
        while (!ids.contains(selectedId) && selectedId != NEW_ROOM_CREATION_ID) {
            OutputView.printNonExistRoomMessage();
            selectedId = errorController.RetryIfThrowsException(InputView::readRoomId);
        }
        if (selectedId == NEW_ROOM_CREATION_ID) {
            return roomConnectionService.createRoom();
        }
        return roomConnectionService.connectRoom(selectedId);
    }
}
