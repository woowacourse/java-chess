package controller;

import constant.ErrorCode;
import dto.GameBoardDto;
import exception.InvalidStatusException;
import java.util.List;
import model.Camp;
import model.Command;
import model.GameBoard;
import model.position.Moving;
import model.position.Position;
import view.OutputView;

public class ChessController {

    private final InputController inputController;
    private final OutputView outputView;


    public ChessController(final InputController inputController, final OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();
        outputView.printStartMessage();
        play(gameBoard);
    }

    private void play(final GameBoard gameBoard) {
        boolean isStart = false;
        Camp camp = Camp.WHITE;

        while (true) {
            List<String> input = inputController.getCommand();
            Command command = Command.from(input.get(0));
            isStart = checkStart(command, isStart);

            if (!isStart) {
                throw new InvalidStatusException(ErrorCode.INVALID_STATUS); // TODO 시작하지 않았는데 진행
            }
            if (command == Command.MOVE) {
                Moving moving = getMoving(input.get(1), input.get(2));
                gameBoard.move(moving, camp);
                camp = camp.toggle();
            } else if (command == Command.END) {
                break;
            }
            printCurrentStatus(gameBoard, camp);
        }
    }

    private void printCurrentStatus(final GameBoard gameBoard, final Camp camp) {
        outputView.printGameBoard(GameBoardDto.from(gameBoard));
        outputView.printCurrentCame(camp);
    }

    private boolean checkStart(final Command command, boolean isStart) {
        if (command == Command.START && isStart) {
            throw new InvalidStatusException(ErrorCode.INVALID_STATUS); //TODO 시작인데 재시작
        }
        if (command == Command.START) {
            isStart = true;
        }
        return isStart;
    }

    private Moving getMoving(final String currentPosition, final String nextPosition) {
        return new Moving(Position.from(currentPosition), Position.from(nextPosition));
    }
}
