package chess.controller;

import chess.domain.game.MoveCommand;
import chess.domain.userAccess.UserAccessService;
import chess.domain.userAccess.room.Room;
import chess.domain.userAccess.user.User;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class UserAccessController {

    private static final int NEW_ROOM_COMMAND = 0;

    private final InputView inputView;
    private final OutputView outputView;
    private final UserAccessService userAccessService;

    public UserAccessController(InputView inputView, OutputView outputView, UserAccessService userAccessService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.userAccessService = userAccessService;
    }

    public void run() {
        outputView.printInputIdMessage();
        User user = userAccessService.findUserById(inputView.readUserId());
        Room room = enterRoom(user);
        MoveCommand moveCommand = startChessGame(room);
        userAccessService.updateRoomById(room.roomId(), moveCommand.getCommands());
    }

    private Room enterRoom(User user) {
        List<Room> rooms = userAccessService.findRoomsByUser(user);
        if (rooms.isEmpty()) {
            outputView.printMakeNewRoomMessage();
            return userAccessService.makeRoomByUser(user);
        }
        return findRoom(rooms, user);
    }

    private Room findRoom(List<Room> rooms, User user) {
        outputView.printSavedRooms(rooms);
        outputView.printSelectRoomMessage();
        try {
            int roomId = inputView.readRoomId();
            return findUserSelectionRoom(rooms, roomId, user);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return findRoom(rooms, user);
        }
    }

    private Room findUserSelectionRoom(List<Room> rooms, int roomId, User user) {
        if (roomId == NEW_ROOM_COMMAND) {
            return userAccessService.makeRoomByUser(user);
        }
        Room foundRoom = rooms.stream()
                .filter(room -> room.roomId() == roomId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 방 이름입니다."));
        return foundRoom;
    }

    private MoveCommand startChessGame(Room room) {
        if (room.commands().isEmpty()) {
            MoveCommand moveCommand = new MoveCommand();
            new ChessController(inputView, outputView).run(moveCommand);
            return moveCommand;
        }
        MoveCommand moveCommand = new MoveCommand(room.commands());
        new ChessController(inputView, outputView).reStart(moveCommand);
        return moveCommand;
    }
}
