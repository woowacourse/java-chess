package chess;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Square;
import chess.dto.SquareResponse;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        OutputView.printStartMessage();
        Command command = null;
        while (command != Command.END) {
            command = InputView.readCommand();
            startGame(command);
        }
    }

    private static void startGame(Command command) {
        if (command == Command.START) {
            List<Square> squares = BoardFactory.create();
            Board board = new Board(squares);
            List<SquareResponse> squareResponses = board.getSquares().stream()
                    .map(SquareResponse::of)
                    .collect(toList());
            OutputView.printBoard(squareResponses);
        }
    }
}
