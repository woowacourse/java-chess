package chess.controller;

import static chess.domain.piece.Color.WHITE;

import chess.domain.ChessBoard;
import chess.domain.Turn;
import chess.util.ChessBoardInitializer;
import chess.domain.Command;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void runChess() {
        final Turn turn = new Turn(WHITE);
        final Command initCommand = Command.from(inputView.readInitCommand());

        if (!initCommand.isStart()) {
            return;
        }

        final ChessBoard chessBoard = ChessBoardInitializer.init();
        outputView.printChessBoard(chessBoard.getPieces());

        List<String> command = inputView.readMoveCommand();

        while (isNotEndCommand(command)) {
            playTurn(command, turn, chessBoard);

            command = inputView.readMoveCommand();
        }
    }

    private boolean isNotEndCommand(final List<String> command) {
        return !command.isEmpty();
    }

    private void playTurn(final List<String> command, final Turn turn, final ChessBoard chessBoard) {
        final Position current = new Position(command.get(0));
        final Position destination = new Position(command.get(1));

        chessBoard.move(turn, current, destination);
        turn.change();
        outputView.printChessBoard(chessBoard.getPieces());
    }
}
