package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandFactory;
import chess.service.ChessService;
import chess.service.RoomNumber;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private static final int NEW_ROOM_NUMBER = 0;

    private final ChessService chessService;
    private RoomNumber roomNumber;

    public ChessController(final ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        List<Integer> roomNumbers = chessService.fetchAllRoomNumbers();
        OutputView.printRoomList(roomNumbers);
        roomNumber = createRoomNumber(roomNumbers);

        chessService.initialize(roomNumber);
        playGame();
        chessService.clearRoomIfKingDead(roomNumber);
    }

    private RoomNumber createRoomNumber(final List<Integer> roomNumbers) {
        try {
            int roomNumber = InputView.readRoomNumber();
            validateExistRoomNumber(roomNumber, roomNumbers);
            return new RoomNumber(roomNumber);
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

    public void playGame() {
        OutputView.printGameGuide();
        while (chessService.isGameRunning(roomNumber)) {
            executeCommand();
        }
        OutputView.printWinningTeam(chessService.findWinningTeam(roomNumber));
    }

    private void executeCommand() {
        try {
            Command command = CommandFactory.of(roomNumber, InputView.readCommandAndParameters());
            command.execute(chessService);
        } catch (IllegalArgumentException | UnsupportedOperationException | IllegalStateException e) {
            OutputView.printError(e.getMessage());
            executeCommand();
        }
    }

}
