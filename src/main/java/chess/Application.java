package chess;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.SquareResponse;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

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
            Map<Position, Piece> squares = BoardFactory.create();
            Board board = new Board(squares);
            List<SquareResponse> squareResponses = board.getBoard().entrySet().stream()
                    .map(entry -> SquareResponse.of(entry.getKey(), entry.getValue()))
                    .collect(toList());
            OutputView.printBoard(squareResponses);
        }
    }
}
