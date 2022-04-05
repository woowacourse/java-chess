package chess.controller;

import static chess.vo.Command.*;

import chess.dto.Request;
import chess.model.ChessGame;
import chess.model.TurnDecider;
import chess.model.boardinitializer.defaultInitializer;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        ChessGame game = new ChessGame(new TurnDecider(), new defaultInitializer());

        if (InputView.inputCommandInStart() == END) {
            return;
        }

        OutputView.printChessGameBoard(game.getBoardValue());
        playChess(game);
    }

    private void playChess(ChessGame game) {
        while (!game.isFinished()) {
            Request request = InputView.inputCommandInGaming();

            if (request.getCommand().isEnd()) {
                break;
            }

            doMoveOrStatus(game, request);
        }
        OutputView.printScore(game.getScore());
    }

    private void doMoveOrStatus(ChessGame game, Request request) {
        if (request.getCommand().isStatus()) {
            OutputView.printScore(game.getScore());
            return;
        }

        game.move(request.getSource(), request.getTarget());
        OutputView.printChessGameBoard(game.getBoardValue());
    }
}
