package chess.controller;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public static final String STATUS = "status";
    public static final String MOVE = "move";
    public static final String END = "end";
    public static final String START = "start";

    public void run() {
        OutputView.printStartGuideMessage();
        while (isCorrectStart()) {
            playChessGame();
        }
    }

    private boolean isCorrectStart() {
        try {
            return InputView.inputStart();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return InputView.inputStart();
        }
    }

    private void playChessGame() {
        ChessGame chessGame = new ChessGame();
        OutputView.printBoard(chessGame);
        while (chessGame.isNotEnd() && isPlayingInput(chessGame))
            ;
        OutputView.noticeGameFinished();
    }

    private boolean isPlayingInput(ChessGame chessGame) {
        try {
            return isCorrectInput(chessGame, makeInput());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return isCorrectInput(chessGame, makeInput());
        }
    }

    private boolean isCorrectInput(ChessGame chessGame, List<String> input) {
        try {
            return makeScoreOrMove(chessGame, input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private List<String> makeInput() {
        try {
            return InputView.inputMoveOrStatus();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return InputView.inputMoveOrStatus();
        }
    }

    private boolean makeScoreOrMove(ChessGame chessGame, List<String> userInput) {
        if (userInput.get(0).equals(END)) {
            return false;
        }
        if (userInput.get(0).equals(STATUS)) {
            Score blackScore = chessGame.calculateScore(Color.BLACK);
            OutputView.printScore(Color.BLACK, blackScore);
            Score whiteScore = chessGame.calculateScore(Color.WHITE);
            OutputView.printScore(Color.WHITE, whiteScore);
            OutputView.printWinner(blackScore.biggerScoreColor(whiteScore));
        }
        if (userInput.get(0).equals(MOVE)) {
            move(chessGame, userInput);
            OutputView.printBoard(chessGame);
        }
        if (userInput.get(0).equals(START)) {
            playChessGame();
        }
        return true;
    }

    private void move(ChessGame chessGame, List<String> userInput) {
        Point source = createPoint(userInput.get(1));
        Point target = createPoint(userInput.get(2));
        playTurn(chessGame, source, target);
    }

    private Point createPoint(String userInput) {
        try {
            return Point.of(userInput);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void playTurn(ChessGame chessGame, Point source, Point target) {
        try {
            chessGame.playTurn(source, target);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
