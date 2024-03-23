package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.PieceColor;
import chess.domain.square.Square;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ChessGame {

    public void run() {
        OutputView.printStartMessage();
        Command command = Command.START;
        PieceColor turn = PieceColor.WHITE;
        Board board = new Board(Map.of());

        while (command != Command.END) {
            final List<String> arguments = InputView.readCommand();
            command = requestUntilValid(() -> Command.from(arguments.get(0)));

            if (command == Command.START) {
                board = BoardFactory.createBoard();
                OutputView.printBoard(board.getPieces());
            }
            if (command == Command.MOVE) {
                turn = tryMove(board, arguments.get(1), arguments.get(2), turn);
            }
        }
    }

    private PieceColor tryMove(Board board, String source, String target, PieceColor turn) {
        try {
            if (isNotTurn(board, source, turn)) {
                throw new IllegalArgumentException("현재는 " + turn + "팀의 턴입니다.");
            }
            board.move(
                    Square.from(source),
                    Square.from(target));
            OutputView.printBoard(board.getPieces());
            return turn.next();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return turn;
    }

    private boolean isNotTurn(Board board, String source, PieceColor turn) {
        return !board.isExistPieceWithColor(Square.from(source), turn);
    }

    public <T> T requestUntilValid(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryGet(supplier);
        }
        return result.get();
    }

    private <T> Optional<T> tryGet(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
