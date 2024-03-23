package controller;

import domain.game.ChessBoard;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.List;
import java.util.regex.Pattern;
import view.ChessCommand;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void runChessGame() {
        ChessBoard chessBoard = new ChessBoard();

        outputView.printCommandMessage();
        ChessCommand initializeCommand = enterCommand();
        retry(() -> enterAndExecuteCommand(chessBoard, initializeCommand));
    }

    private void enterAndExecuteCommand(final ChessBoard chessBoard, ChessCommand command) {
        Pattern pattern = Pattern.compile(InputView.MOVE_POSITION_REGEX_FORMAT);
        while (command != ChessCommand.END) {
            outputView.printSquareStatus(chessBoard);
            List<String> input = inputView.enterChessCommand();
            command = ChessCommand.from(input.get(0));

            executeMoveCommand(chessBoard, input, command, pattern);
        }
    }

    private ChessCommand enterCommand() {
        List<String> input = inputView.enterChessCommand();
        return ChessCommand.from(input.get(0));
    }

    private void validateMoveCommand(final List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("올바르지 않은 move 명령어입니다.");
        }
    }

    private void executeMoveCommand(final ChessBoard chessBoard,
                                    final List<String> input,
                                    final ChessCommand command,
                                    final Pattern pattern) {
        if (command == ChessCommand.MOVE) {
            validateMoveCommand(input);
            chessBoard.move(generateSquare(input.get(1), pattern), generateSquare(input.get(2), pattern));
        }
    }

    private Position generateSquare(final String coordinate, final Pattern pattern) {
        if (isNotValidCoordinateInput(coordinate, pattern)) {
            throw new IllegalArgumentException("이동할 source와 target 정보를 다시 입력해주세요.");
        }

        List<String> coordinates = List.of(coordinate.split(""));
        return new Position(
                new File(coordinates.get(0).charAt(0)),
                new Rank(Integer.parseInt(coordinates.get(1)))
        );
    }

    private boolean isNotValidCoordinateInput(final String input, final Pattern pattern) {
        return !pattern.matcher(input).matches();
    }

    private static void retry(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
