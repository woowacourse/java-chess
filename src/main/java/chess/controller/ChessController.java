package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandFactory;
import chess.dao.ChessDao;
import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.domain.GameRoom;
import chess.domain.RoomNumber;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private static final int NEW_ROOM_NUMBER = 0;

    private final ChessDao chessDao;

    public ChessController(final ChessDao chessDao) {
        this.chessDao = chessDao;
    }

    public void run() {
        List<Integer> roomNumbers = ChessDao.getInstance().fetchAllRoomNumbers();
        OutputView.printRoomList(roomNumbers);
        RoomNumber roomNumber = createRoomNumber(roomNumbers);
        GameRoom gameRoom = createGameRoom(roomNumber, roomNumbers.size());
        joinGameRoom(gameRoom);
        clearRoomIfKingDead(gameRoom, roomNumbers.size());
    }

    private RoomNumber createRoomNumber(List<Integer> roomNumbers) {
        try {
            int rawRoomNumber = InputView.readRoomNumber();
            validateExistRoomNumber(rawRoomNumber, roomNumbers);
            return new RoomNumber(rawRoomNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return createRoomNumber(roomNumbers);
        }
    }

    private void validateExistRoomNumber(final int roomNumber, final List<Integer> roomNumbers) {
        if (roomNumber == NEW_ROOM_NUMBER || roomNumbers.contains(roomNumber)) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 방 번호입니다.");
    }

    private GameRoom createGameRoom(final RoomNumber roomNumber, final int numberOfExistRooms) {
        if (roomNumber.getRoomNumber() == NEW_ROOM_NUMBER) {
            return new GameRoom(new RoomNumber(numberOfExistRooms + 1), new ChessGame(ChessBoardFactory.create()));
        }
        return chessDao.fetchGameRoom(roomNumber);
    }

    public void joinGameRoom(GameRoom gameRoom) {
        OutputView.printGameGuide();
        while (gameRoom.isGameNotEnd()) {
            executeCommand(gameRoom);
        }
        OutputView.printWinningTeam(gameRoom.findWinningTeam());
    }

    private void executeCommand(final GameRoom gameRoom) {
        try {
            Command command = CommandFactory.from(InputView.readCommandAndParameters());
            command.execute(gameRoom);
        } catch (IllegalArgumentException | UnsupportedOperationException | IllegalStateException e) {
            OutputView.printError(e.getMessage());
            executeCommand(gameRoom);
        }
    }

    private void clearRoomIfKingDead(final GameRoom gameRoom, final int numberOfExistRooms) {
        if (gameRoom.isKingDead() && isNotNewRoom(gameRoom, numberOfExistRooms)) {
            chessDao.clearByRoomNumber(gameRoom.getRoomNumber());
        }
    }

    private boolean isNotNewRoom(final GameRoom gameRoom, final int numberOfExistRooms) {
        return !gameRoom.isSameRoom(new RoomNumber(numberOfExistRooms + 1));
    }
}
