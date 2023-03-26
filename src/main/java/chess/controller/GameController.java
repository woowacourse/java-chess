package chess.controller;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

import static chess.controller.Command.*;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class GameController {

    private static final int MOVE_COMMAND_SIZE = 3;

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStart();
        Command command = repeatUntilValidate(this::inputCommand);
        if (command == END) {
            return;
        }
        ChessGame chessGame = new ChessGame(new Board(), WHITE);
        outputView.printChessBoard(chessGame.getBoard());
        do {
            command = repeatUntilValidate(() -> progressGame(chessGame));
        } while (command != END);
    }

    private Command inputCommand() {
        List<String> gameCommand = inputView.readGameCommand();
        Command command = Command.from(gameCommand.get(0));
        validateWrongCommand(command, MOVE);
        return command;
    }

    private Command progressGame(final ChessGame chessGame) {
        List<String> gameCommand = inputView.readGameCommand();
        Command command = Command.from(gameCommand.get(0));
        if (command == END) {
            return command;
        }
        validateWrongCommand(command, START);

        if (command == MOVE) {
            validateMoveCommandFormat(gameCommand);
            chessGame.movePiece(gameCommand.get(1), gameCommand.get(2));
            outputView.printChessBoard(chessGame.getBoard());
        }

        if (command == STATUS) {
            outputView.printTeamScore(WHITE, chessGame.calculateTeamScore(WHITE));
            outputView.printTeamScore(BLACK, chessGame.calculateTeamScore(BLACK));
            outputView.printWinnerTeam(chessGame.calculateWinnerTeam());
        }

        if (chessGame.isGameEnd()) {
            return END;
        }
        return command;
    }

    private void validateWrongCommand(final Command userCommand, final Command notExpected) {
        if (userCommand == notExpected) {
            throw new IllegalArgumentException(
                    "올바른 command를 입력해주세요. 게임 시작은 start로, 게임 진행은 move로, 게임 종료는 end로 할 수 있습니다.");
        }
    }

    private void validateMoveCommandFormat(final List<String> gameCommand) {
        if (gameCommand.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("move source위치 target위치 형태로 입력해주세요. 예) move a2 a3");
        }
    }

    private <T> T repeatUntilValidate(final Supplier<T> supplier) {
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
            outputView.printErrorMessage(e.getMessage());
            return null;
        }
    }
}
