package chess.controller;

import static chess.domain.Command.END;
import static chess.domain.Command.MOVE;
import static chess.domain.Command.START;
import static chess.domain.Team.WHITE;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class GameController {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStart();
        Command command = readUntilValidate(this::startGame);
        if (command == END) {
            return;
        }
        ChessGame chessGame = new ChessGame(new Board(), WHITE);
        Board board = chessGame.getBoard();
        outputView.printChessBoard(board.getBoardResult());

        do {
            command = readUntilValidate(() -> progressGame(chessGame));
        } while (command != END);
    }

    private Command startGame() {
        List<String> gameCommand = inputView.readGameCommand();
        Command command = Command.from(gameCommand.get(COMMAND_INDEX));
        checkWrongCommand(command, MOVE);
        return command;
    }

    private Command progressGame(ChessGame chessGame) {
        List<String> gameCommand = inputView.readGameCommand();
        Command command = Command.from(gameCommand.get(COMMAND_INDEX));
        if (command == END) {
            return command;
        }
        checkWrongCommand(command, START);
        validateMoveCommandFormat(gameCommand);

        chessGame.movePiece(gameCommand.get(SOURCE_INDEX), gameCommand.get(TARGET_INDEX));
        outputView.printChessBoard(chessGame.getBoard().getBoardResult());
        return command;
    }

    private void checkWrongCommand(final Command userCommand, final Command notExpected) {
        if (userCommand == notExpected) {
            throw new IllegalArgumentException(
                    "올바른 command를 입력해주세요. 게임 시작은 start로, 게임 진행은 move로, 게임 종료는 end로 할 수 있습니다.");
        }
    }

    private void validateMoveCommandFormat(final List<String> gameCommand) {
        if (gameCommand.size() != 3) {
            throw new IllegalArgumentException("move source위치 target위치 형태로 입력해주세요. 예) move a2 a3");
        }
    }

    private <T> T readUntilValidate(Supplier<T> supplier) {
        T input;
        do {
            input = readUserInput(supplier);
        } while (input == null);
        return input;
    }

    private <T> T readUserInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
