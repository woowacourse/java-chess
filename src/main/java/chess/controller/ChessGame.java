package chess.controller;

import chess.BeforeGameCommand;
import chess.Board;
import chess.InGameCommand;
import chess.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private final Board board = Board.createInitialBoard();

    public void run() {
        OutputView.printGameIntro();
        BeforeGameCommand beforeGameCommand = BeforeGameCommand.of(InputView.requestCommand());
        if (beforeGameCommand.isEnd()) {
            System.exit(0);
        }
        OutputView.printBoard(board);

        while (board.isNotGameFinished()) {
            InGameCommand inGameCommand = InGameCommand.of(InputView.requestCommand());
            if (inGameCommand.isMove()) {
                Position startPosition = Position.of(InputView.requestPosition());
                Position endPosition = Position.of(InputView.requestPosition());
                board.move(startPosition, endPosition);
                OutputView.printBoard(board);
            }
            else if (inGameCommand.isStatus()) {
                //점수계산
            }

        }
    }
}
