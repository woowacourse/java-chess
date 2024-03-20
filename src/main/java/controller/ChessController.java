package controller;

import domain.chessboard.Square;
import domain.game.PieceMover;
import domain.piece.PieceGenerator;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.List;
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

    // TODO: START 이전에 MOVE가 들어오면 예외 처리한다.
    public void run() {
        PieceMover mover = new PieceMover();
        PieceGenerator.generate(mover);

        outputView.printCommandMessage();
        List<String> input = inputView.enterAnything();
        ChessCommand command = ChessCommand.from(input.get(0));
        while (command != ChessCommand.END) {
            outputView.printPieceStatus(mover);
            input = inputView.enterAnything();
            command = ChessCommand.from(input.get(0));
            if (command == ChessCommand.MOVE) {
                mover.move(generateSquare(input.get(1)), generateSquare(input.get(2)));
            }
        }
    }

    private Square generateSquare(final String coordinate) {
        List<String> coordinates = List.of(coordinate.split(""));
        return new Square(new Position(
                new File(coordinates.get(0).charAt(0)),
                new Rank(Integer.parseInt(coordinates.get(1)))
        ));
    }
}
