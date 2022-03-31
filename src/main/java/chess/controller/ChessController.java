package chess.controller;

import chess.controller.result.EndResult;
import chess.controller.result.MoveResult;
import chess.controller.result.StartResult;
import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.position.Position;
import chess.view.OutputView;

public class ChessController {

    private final ChessGame chessGame;

    public ChessController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public boolean canPlay() {
        return chessGame.canPlay();
    }

    public void start() {
        final StartResult result = chessGame.start();
        OutputView.printChessBoard(result.getPieceByPosition());
    }

    public void move(final Position from, final Position to) {
        final MoveResult result = chessGame.move(from, to);
        OutputView.printChessBoard(result.getPieceByPosition());
        if (result.isKingDie()) {
            OutputView.printResult(result.score());
        }
    }

    public void status() {
        final Score score = chessGame.calculateScore();
        OutputView.printStatus(score);
    }

    public void end() {
        final EndResult result = chessGame.end();
        OutputView.printResult(result.getScore());
    }
}
