package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void run() {
        String inputCommand = InputView.readGameCommand();
        GameCommand command = GameCommand.from(inputCommand);
        Board board = BoardFactory.createInitialBoard();
        OutputView.printGameStartMessage();
        OutputView.printChessBoard(board);
    }
}
