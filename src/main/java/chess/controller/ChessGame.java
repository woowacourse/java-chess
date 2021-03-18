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

    private boolean gameOver = false;

    public void run() {
        OutputView.printStartChess();
        playRounds();
    }

    public void playRounds(){
        Grid grid = new Grid();
        while (!gameOver) {
            playRound(grid);
        }
    }

    public void playRound(Grid grid){
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

        if(command.equals("status")){
            gameOver = true;
            OutputView.printScores(true, grid.calculateScore(true));
            OutputView.printScores(false, grid.calculateScore(false));
        }
    }

    private void move(Grid grid, String command) {
        List<String> strGroup = Arrays.asList(command.split(" "));
        String sourceString = strGroup.get(1);
        String targetString = strGroup.get(2);
        Position source = new Position(sourceString.charAt(0), sourceString.charAt(1));
        Position target = new Position(targetString.charAt(0), targetString.charAt(1));
        Piece sourcePiece = grid.findPiece(source);
        Piece targetPiece = grid.findPiece(target);
        grid.move(sourcePiece, targetPiece);
        if(targetPiece instanceof King){
            gameOver = true;
        }
    }
}
