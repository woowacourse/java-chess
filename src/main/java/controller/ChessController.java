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

    private static final String MOVE_COMMAND_REGEX_FORMAT = "^[a-h][1-8]";
    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile(MOVE_COMMAND_REGEX_FORMAT);

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();

        outputView.printCommandMessage();
        ChessCommand command = enterCommand();
        while (command != ChessCommand.END) {
            outputView.printPositionStatus(chessBoard);
            List<String> input = inputView.enterChessCommand();
            command = ChessCommand.from(input.get(0));

            executeMoveCommand(chessBoard, input, command);
        }
    }

    private ChessCommand enterCommand() {
        List<String> input = inputView.enterChessCommand();
        return ChessCommand.from(input.get(0));
    }

    private void executeMoveCommand(final ChessBoard chessBoard, final List<String> input, final ChessCommand command) {
        if (command == ChessCommand.MOVE) {
            validateMoveCommand(input);
            chessBoard.checkRoute(generatePosition(input.get(1)), generatePosition(input.get(2)));
            chessBoard.move(generatePosition(input.get(1)), generatePosition(input.get(2)));
        }
    }

    private void validateMoveCommand(final List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("올바르지 않은 move 명령어입니다.");
        }
    }

    private Position generatePosition(final String coordinate) {
        if (isNotValidCoordinateInput(coordinate)) {
            throw new IllegalArgumentException("이동할 source와 target 정보를 다시 입력해주세요.");
        }

        List<String> coordinates = List.of(coordinate.split(""));
        return new Position(
                new File(coordinates.get(0).charAt(0)),
                new Rank(Integer.parseInt(coordinates.get(1)))
        );
    }

    private boolean isNotValidCoordinateInput(final String input) {
        return !MOVE_COMMAND_PATTERN.matcher(input).matches();
    }
}
