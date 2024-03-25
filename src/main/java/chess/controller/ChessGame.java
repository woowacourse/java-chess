package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.PieceColor;
import chess.domain.square.Square;
import chess.view.CommandType;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public class ChessGame {

    public void run() {
        OutputView.printStartMessage();
        CommandType commandType = CommandType.START;
        PieceColor turn = PieceColor.WHITE;
        Board board = new Board(Set.of());

        while (commandType != CommandType.END) {
            final List<String> arguments = InputView.readCommand();
            commandType = requestUntilValid(() -> CommandType.from(arguments.get(0)));

            if (commandType == CommandType.START) {
                board = BoardFactory.createBoard();
                OutputView.printBoard(board.generatePieceDrawings());
            }
            if (commandType == CommandType.MOVE) {
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
            OutputView.printBoard(board.generatePieceDrawings());
            return turn.opposite();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return turn;
    }

    private boolean isNotTurn(Board board, String source, PieceColor turn) {
        // TODO: Turn 객체 만들고 더욱 체게화
        return false;
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
