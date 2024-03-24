package chess.controller;

import chess.domain.ChessBoard;
import chess.util.ChessBoardInitalizer;
import chess.domain.Command;
import chess.domain.piece.Position;
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
        final Command command = Command.from(inputView.readInitCommand());

        if (!command.isStartCommand()) {
            return;
        }

        final ChessBoard chessBoard = ChessBoardInitalizer.init();
        outputView.printChessBoard(chessBoard.getPieces());

        List<String> positions = inputView.readMoveCommand();

        while (isNotEndCommand(positions)) {
            playTurn(chessBoard, positions);

            positions = inputView.readMoveCommand();
        }
    }

    private boolean isNotEndCommand(final List<String> validInputPositions) {
        return !validInputPositions.isEmpty();
    }

    private void playTurn(final ChessBoard chessBoard, final List<String> positions) {
        final Position current = new Position(positions.get(0));
        final Position destination = new Position(positions.get(1));

        chessBoard.move(current, destination);
        outputView.printChessBoard(chessBoard.getPieces());
    }
}
