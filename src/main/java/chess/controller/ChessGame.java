package chess.controller;

import chess.domain.grid.Grid;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessGame {

    private static boolean isBlackTurn = false;
    private boolean gameOver = false;

    public static boolean isBlackTurn() {
        return isBlackTurn;
    }

    public void run() {
        OutputView.printStartChess();
        playRounds();
    }

    public void playRounds() {
        Grid grid = new Grid();
        while (!gameOver) {
            playRound(grid);
        }
    }

    public void playRound(Grid grid) {
        String command = InputView.inputCommand();
        if (command.equals("start")) {
            OutputView.printGridStatus(grid);
        }
        if (command.startsWith("move")) {
            move(grid, command);
            OutputView.printGridStatus(grid);
        }
        if (command.equals("end")) {
            gameOver = true;
        }
        if (command.equals("status")) {
            gameOver = true;
            OutputView.printScores(true, grid.calculateScore(true));
            OutputView.printScores(false, grid.calculateScore(false));
        }
    }

    private void move(Grid grid, String command) {
        List<String> moveInput = Arrays.asList(command.split(" "));
        Piece sourcePiece = grid.findPiece(new Position(moveInput.get(1).charAt(0), moveInput.get(1).charAt(1)));
        Piece targetPiece = grid.findPiece(new Position(moveInput.get(2).charAt(0), moveInput.get(2).charAt(1)));
        grid.move(sourcePiece, targetPiece);
        isBlackTurn = !isBlackTurn;
        if (targetPiece instanceof King) {
            gameOver = true;
        }
    }
}
