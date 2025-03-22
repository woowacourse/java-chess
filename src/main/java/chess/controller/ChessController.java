package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.game.ChessGame;
import chess.domain.game.WhiteTurn;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        ChessBoard board = ChessBoard.createInitialBoard();
        outputView.displayBoard(board);

        ChessGame game = new ChessGame(new WhiteTurn(board));

        while (!game.isFinished()) {
            String[] command = inputView.readMoveCommand(game.getTurnColor().getTeamName());
            String startInput = command[0];
            String targetInput = command[1];

            Position start = stringToPosition(startInput);
            Position target = stringToPosition(targetInput);

            game.move(start, target);
            outputView.displayBoard(board);
        }

    }

    private Position stringToPosition(String input) {
        char col = input.charAt(0);
        Column column = Column.from(col);
        char rowChar = input.charAt(1);
        int rowNum = Integer.parseInt(String.valueOf(rowChar));
        Row row = Row.from(rowNum);

        return new Position(row, column);
    }
}
