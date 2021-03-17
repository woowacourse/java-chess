package chess;

import chess.domain.ChessBoard;
import chess.domain.Turn;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Controller {

    public void run() {
        ChessGame chessGame = new ChessGame(new ChessBoard(), new Turn(Color.WHITE));
        OutputView.gameStart();
        if (InputView.isStart()) {
            startGame(chessGame);
        }
    }

    public void startGame(ChessGame chessGame) {
        chessGame.start();
        OutputView.printChessBoard(chessGame.getChessBoard());
        playGame(chessGame);
    }

    public void playGame(ChessGame chessGame) {
        List<String> input = InputView.moveOrStatus();
        if ("move".equals(input.get(0))) {
            chessGame.run(input);
            OutputView.printChessBoard(chessGame.getChessBoard());
            playGame(chessGame);
        }
    }

    public void endGame() {

    }
}
