package chess.console;

import java.util.NoSuchElementException;

import chess.console.dto.ChessBoardDto;
import chess.console.dto.GameResult;
import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.game.ChessGame;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        InputView.startGuideMessage();
        start(chessGame);
    }

    private void start(ChessGame chessGame) {
        while (!chessGame.isFinished()) {
            act(chessGame);
        }
    }

    private void act(ChessGame chessGame) {
        try {
            String input = InputView.requestCommand();
            GameResult gameResult = Command.act(input, chessGame);

            if (gameResult.isBoard()) {
                OutputView.printChessBoard(ChessBoardDto.of(gameResult.getBoard()));
            }
            if (gameResult.isScore()) {
                OutputView.printScore(gameResult.getScore());
            }
        } catch (NoSuchElementException
            | IllegalArgumentException
            | UnsupportedOperationException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }
}
