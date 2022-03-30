package chess;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.Status;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.view.InputView;
import chess.view.OutputView;

public final class ChessGameLauncher {
    void run() {
        OutputView.announceStart();
        ChessGame game = initGame();
        inGame(game);
        endGame(game);
    }

    private ChessGame initGame() {
        if (!InputView.isStart()) {
            System.exit(0);
        }

        ChessGame game = new ChessGame();
        OutputView.showBoard(game.getBoard());
        return game;
    }

    private void inGame(ChessGame game) {
        List<String> squares = inputSquaresToMove();

        while (!game.isKingDie()) {
            movePiece(game, squares);
            squares = inputSquaresToMove();
        }
        OutputView.printKingDieMessage();
    }

    private void movePiece(ChessGame game, List<String> squares) {
        try {
            String source = squares.get(0);
            String target = squares.get(1);
            game.move(new Square(source), new Square(target));
            OutputView.showBoard(game.getBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
    }

    private List<String> inputSquaresToMove() {
        List<String> squares = InputView.requireCommand();

        if (squares.isEmpty()) {
            System.exit(0);
        }

        return squares;
    }

    private void endGame(ChessGame game) {
        if (InputView.isGameEnd()) {
            System.exit(0);
        }

        printStatus(game);
    }

    private void printStatus(ChessGame game) {
        Status status = game.saveStatus();
        OutputView.showScore(status, Color.WHITE);
        OutputView.showScore(status, Color.BLACK);
    }
}
