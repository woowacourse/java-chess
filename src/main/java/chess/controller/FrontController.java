package chess.controller;

import chess.dao.DBBoardDao;
import chess.dao.DBChessGameDao;
import chess.domain.room.Room;
import chess.domain.room.Rooms;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class FrontController {


    private static final int NEW_ROOM_CREATION_ID = 0;
    private final Rooms rooms;

    public FrontController(Rooms rooms) {
        this.rooms = rooms;
    }

    public void run() {
        List<Integer> id = rooms.getRooms();
        OutputView.printRoomId(id);
        int selectedId = selectRoomId(id);
        ChessController chessController = new ChessController(new ErrorController(), new Room(new DBChessGameDao(new DBBoardDao()), selectedId));
        chessController.run();
    }

    private int selectRoomId(List<Integer> id) {
        int selectedId = InputView.readRoomId();
        while (!id.contains(selectedId) && selectedId != NEW_ROOM_CREATION_ID) {
            OutputView.printNonExistRoomMessage();
            selectedId = InputView.readRoomId();
        }
        if (selectedId == NEW_ROOM_CREATION_ID) {
            selectedId = rooms.createNewRoom();
        }
        return selectedId;
    }
}
