package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.pieces.piece.Color;
import chess.domain.square.Square;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Game {
    private final InputView inputView;
    private final OutputView outputView;

    public Game(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printGameStartMessage();
        Board board = BoardFactory.createBoard();
        String command = inputView.readStartCommand();

        Color nowTurn = Color.WHITE;
        while (command.equals("start")) {
            outputView.printBoard(board.createBoardStatus());
            List<String> movement = inputView.readMovement();
            Square from = Square.from(movement.get(0));
            Square to = Square.from(movement.get(1));
            board.move(from, to, nowTurn);
            nowTurn = nowTurn.exchangeTurn();
        }
    }
}
