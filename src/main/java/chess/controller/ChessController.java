package chess.controller;

import java.util.NoSuchElementException;

import chess.domain.game.ChessGame;
import chess.dto.ChessBoardDto;
import chess.dto.GameResult;
import chess.view.InputView;
import chess.view.OutputView;

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
        } catch (NoSuchElementException | IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }
}
