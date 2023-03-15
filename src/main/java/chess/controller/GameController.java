package chess.controller;

import chess.domain.Board;
import chess.domain.Command;
import chess.domain.Square;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        outputView.printGameStart();
        outputView.printGameCommand();

        while (true) {
            String gameCommand = inputView.readGameCommand();
            if (Command.isEnd(gameCommand)) {
                break;
            }
            Board board = new Board();
            Map<Square, Piece> pieces = board.getPieces();
            outputView.printChessBoard(pieces);
        }
    }
}
