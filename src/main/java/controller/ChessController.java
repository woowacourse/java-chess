package controller;

import dto.GameBoardDto;
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
        Camp camp = Camp.WHITE;
        boolean start = false;

        while (true) {
            List<String> input = inputController.getCommand();
            Command command = Command.from(input.get(0));

            if (command == Command.START && start) {
                throw new IllegalArgumentException("이미 게임이 진행중입니다.");
            }

            if (command == Command.START) {
                start = true;
            }

            if (!start) {
                throw new IllegalArgumentException("start하고 시작하세요");
            }

            if (command == Command.MOVE) {
                Moving moving = getMoving(input.get(1), input.get(2));
                gameBoard.move(moving, camp);
                camp = camp.toggle();
            }
            if (command == Command.END) {
                break;
            }

            outputView.printGameBoard(GameBoardDto.from(gameBoard));
            outputView.printCurrentCame(camp);
        }
    }

    private static Moving getMoving(final String currentPosition, final String nextPosition) {
        return new Moving(Position.from(currentPosition), Position.from(nextPosition));
    }
}
