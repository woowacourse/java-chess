package chess.controller;

import static chess.view.InputView.*;
import static chess.view.OutputView.*;

import chess.dto.Request;
import chess.model.ChessGame;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.Position;
import chess.model.Turn;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        ChessGame game = new ChessGame(new Turn(), new DefaultArrangement());
        Request request = Request.toStart(inputCommand());

        if (request.isEnd()) {
            return;
        }

        printChessGameBoard(game.getBoardValue());
        playChess(game);
    }

    private void playChess(ChessGame game) {
        while (!game.isFinished()) {
            Request request = Request.toPlay(inputCommand());
            if (request.isEnd()) {
                break;
            }

            doMoveOrStatus(game, request);
        }
        OutputView.printScore(game.getScore());
    }

    private void doMoveOrStatus(ChessGame game, Request request) {
        if (request.isStatus()) {
            OutputView.printScore(game.getScore());
            return;
        }

        game.move(Position.of(request.getSource()), Position.of(request.getTarget()));
        printChessGameBoard(game.getBoardValue());
    }
}
