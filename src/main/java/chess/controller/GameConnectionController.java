package chess.controller;

import chess.domain.game.MoveCommand;
import chess.domain.userAccess.UserAccessService;
import chess.domain.userAccess.room.Room;
import chess.domain.userAccess.user.User;
import chess.view.GameConnectionInputView;
import chess.view.GameConnectionOutputView;
import chess.view.ChessInputView;
import chess.view.ChessOutputView;

import java.util.List;

public class GameConnectionController {

    private static final int NEW_ROOM_COMMAND = 0;

    private final GameConnectionInputView inputView;
    private final GameConnectionOutputView outputView;
    private final UserAccessService userAccessService;

    public GameConnectionController(GameConnectionInputView inputView, GameConnectionOutputView outputView, UserAccessService userAccessService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userAccessService = userAccessService;
    }

    public void run() {
        outputView.printInputIdMessage();
        User user = userAccessService.findUserByIdOrElseCreateUser(inputView.readUserId());
        Room room = selectRoomToPlay(user);
        MoveCommand moveCommand = startChessGame(room);
        userAccessService.updateRoomById(room.roomId(), moveCommand.getCommands());
    }

    private Room selectRoomToPlay(User user) {
        if (userAccessService.hasSavedRoom(user)) {
            return findRoom(user);
        }
        return makeNewRoomToPlay(user);
    }

    private Room findRoom(User user) {
        List<Room> rooms = userAccessService.findRoomsByUser(user);
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
        return userAccessService.makeRoomByUser(user);
    }

    private Room findUserSelectionRoom(int roomId, User user) {
        if (roomId == NEW_ROOM_COMMAND) {
            return makeNewRoomToPlay(user);
        }
        Room room = userAccessService.findUserSelectedRoom(roomId, user);
        outputView.printPlaySavedRoomMessage();
        return room;
    }

    private MoveCommand startChessGame(Room room) {
        if (room.commands().isEmpty()) {
            MoveCommand moveCommand = new MoveCommand();
            new ChessController(new ChessInputView(), new ChessOutputView()).run(moveCommand);
            return moveCommand;
        }
        MoveCommand moveCommand = new MoveCommand(room.commands());
        new ChessController(new ChessInputView(), new ChessOutputView()).reStart(moveCommand);
        return moveCommand;
    }
}
