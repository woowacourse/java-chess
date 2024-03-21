package controller;

import domain.game.ChessBoard;
import domain.game.Square;
import domain.piece.PieceGenerator;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.List;
import java.util.regex.Pattern;
import view.ChessCommand;
import view.InputView;
import view.OutputView;

public class ChessController {

    public static final String REGEX_FORMAT = "^[a-h][1-8]$";

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessBoard mover = new ChessBoard();
        PieceGenerator.generate(mover);

        outputView.printCommandMessage();
        List<String> input = inputView.enterAnything();
        ChessCommand command = ChessCommand.from(input.get(0));
        while (command != ChessCommand.END) {
            outputView.printSquareStatus(mover);
            input = inputView.enterAnything();
            command = ChessCommand.from(input.get(0));

            if (command == ChessCommand.MOVE) {
                if (input.size() != 3) {
                    throw new IllegalArgumentException("올바르지 않은 move 명령어입니다.");
                }
                mover.move(generateSquare(input.get(1)), generateSquare(input.get(2)));
            }
        }
    }

    private Square generateSquare(final String coordinate) {
        if (isNotValidCoordinateInput(coordinate)) {
            throw new IllegalArgumentException("이동할 source와 target 정보를 다시 입력해주세요.");
        }

        List<String> coordinates = List.of(coordinate.split(""));
        return new Square(new Position(
                new File(coordinates.get(0).charAt(0)),
                new Rank(Integer.parseInt(coordinates.get(1)))
        ));
    }

    private boolean isNotValidCoordinateInput(final String input) {
        return !Pattern.compile(REGEX_FORMAT).matcher(input).matches();
    }
}
