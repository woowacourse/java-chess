package chess.controller;

import chess.domain.game.MoveCommand;
import chess.domain.userAccess.GameConnectionService;
import chess.domain.userAccess.room.Room;
import chess.domain.userAccess.room.RoomDao;
import chess.domain.userAccess.user.User;
import chess.domain.userAccess.user.UserDao;
import chess.view.GameConnectionInputView;
import chess.view.GameConnectionOutputView;

import java.util.List;

public class GameConnectionController {

    private static GameConnectionController gameConnectionController;
    private static final int NEW_ROOM_COMMAND = 0;

    private final GameConnectionInputView inputView;
    private final GameConnectionOutputView outputView;
    private final GameConnectionService gameConnectionService;

    private GameConnectionController(GameConnectionInputView inputView, GameConnectionOutputView outputView, GameConnectionService gameConnectionService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameConnectionService = gameConnectionService;
    }

    public static GameConnectionController getInstance() {
        if (gameConnectionController == null) {
            gameConnectionController = new GameConnectionController(
                    new GameConnectionInputView(),
                    new GameConnectionOutputView(),
                    new GameConnectionService(new UserDao(), new RoomDao()));
        }
        return gameConnectionController;
    }

    public void run() {
        outputView.printInputIdMessage();
        User user = gameConnectionService.findUserByIdOrElseCreateUser(inputView.readUserId());
        Room room = selectRoomToPlay(user);
        MoveCommand moveCommand = startChessGame(room);
        gameConnectionService.updateRoomById(room.roomId(), moveCommand.getCommands());
    }

    private Room selectRoomToPlay(User user) {
        if (gameConnectionService.hasSavedRoom(user)) {
            return findRoom(user);
        }
        return makeNewRoomToPlay(user);
    }

    private Room findRoom(User user) {
        List<Room> rooms = gameConnectionService.findRoomsByUser(user);
        outputView.printSavedRooms(rooms);
        outputView.printSelectRoomMessage();
        try {
            int roomId = inputView.readRoomId();
            return findUserSelectionRoom(roomId, user);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return findRoom(user);
        }
    }

    private Room makeNewRoomToPlay(User user) {
        outputView.printMakeNewRoomMessage();
        return gameConnectionService.makeRoomByUser(user);
    }

    private Room findUserSelectionRoom(int roomId, User user) {
        if (roomId == NEW_ROOM_COMMAND) {
            return makeNewRoomToPlay(user);
        }
        Room room = gameConnectionService.findUserSelectedRoom(roomId, user);
        outputView.printPlaySavedRoomMessage();
        return room;
    }

    private MoveCommand startChessGame(Room room) {
        if (room.commands().isEmpty()) {
            MoveCommand moveCommand = new MoveCommand();
            ChessController.getInstance().run(moveCommand);
            return moveCommand;
        }
        MoveCommand moveCommand = new MoveCommand(room.commands());
        ChessController.getInstance().reStart(moveCommand);
        return moveCommand;
    }
}
