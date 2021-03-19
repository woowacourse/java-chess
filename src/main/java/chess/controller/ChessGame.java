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
        OutputView.printChessInstruction();
        playRounds();
    }

    public void playRounds() {
        Grid grid = new Grid();
        while (!gameOver) {
            playRound(grid);
        }
    }

    public void playRound(Grid grid) {
        String command = InputView.command();
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
            double blackScore = grid.score(true);
            double whiteScore = grid.score(false);
            OutputView.printScores(true, blackScore);
            OutputView.printScores(false, whiteScore);
            OutputView.printWinner(blackScore > whiteScore);
        }
    }

    private void move(Grid grid, String command) {
        List<String> moveInput = Arrays.asList(command.split(" "));
        Piece sourcePiece = grid.piece(new Position(moveInput.get(1).charAt(0), moveInput.get(1).charAt(1)));
        Piece targetPiece = grid.piece(new Position(moveInput.get(2).charAt(0), moveInput.get(2).charAt(1)));
        validateTurn(sourcePiece);
        grid.move(sourcePiece, targetPiece);
        changeTurn();
        if (targetPiece instanceof King) {
            gameOver = true;
            OutputView.printWinner(!targetPiece.isBlack());
        }
    }

    private void validateTurn(Piece sourcePiece) {
        if (!sourcePiece.isEmpty() && isBlackTurn() != sourcePiece.isBlack()) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }

    private void changeTurn() {
        isBlackTurn = !isBlackTurn;
    }
}
