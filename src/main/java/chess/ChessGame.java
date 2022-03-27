package chess;

import java.util.List;

import chess.domain.Board;
import chess.domain.Status;
import chess.domain.piece.Color;
import chess.domain.position.Square;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void run() {
        OutputView.announceStart();
        Board board = initGame();
        inGame(board);
        endGame(board);
    }

    private Board initGame() {
        if (!InputView.isStart()) {
            System.exit(0);
        }

        Board board = new Board();
        OutputView.showBoard(board.splitByRank());
        return board;
    }

    private void inGame(Board board) {
        List<String> squares = inputSquaresToMove();

        while (!board.isKingDie()) {
            movePiece(board, squares);
            squares = inputSquaresToMove();
        }
        OutputView.printKingDieMessage();
    }

    private void movePiece(Board board, List<String> squares) {
        try {
            String source = squares.get(0);
            String target = squares.get(1);
            board = board.move(new Square(source), new Square(target));
            OutputView.showBoard(board.splitByRank());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
    }

    private List<String> inputSquaresToMove() {
        List<String> squares = InputView.requireCommand();

        if(squares.isEmpty()){
            System.exit(0);
        }

        return squares;
    }

    private void endGame(Board board) {
        if (InputView.isGameEnd()) {
            System.exit(0);
        }

        printStatus(board);
    }

    private void printStatus(Board board) {
        Status status = new Status(board);
        OutputView.showScore(status, Color.WHITE);
        OutputView.showScore(status, Color.BLACK);
    }
}
