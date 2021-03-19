package chess.controller;

import chess.service.ChessService;
import chess.domain.grid.Grid;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {
    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        OutputView.printChessInstruction();
        playRounds();
    }

    private void playRounds() {
        while (!chessService.isGameOver()) {
            playRound();
        }
    }

    private void playRound() {
        Grid grid = chessService.grid();
        String command = InputView.command();
        if (command.equals("start")) {
            OutputView.printGridStatus(grid);
        }
        if (command.startsWith("move")) {
            move(command);
            OutputView.printGridStatus(grid);
        }
        if (command.equals("end")) {
            chessService.setGameOver(true);
        }
        if (command.equals("status")) {
            chessService.setGameOver(true);
            double blackScore = chessService.score(true);
            double whiteScore = chessService.score(false);
            OutputView.printScores(true, blackScore);
            OutputView.printScores(false, whiteScore);
            OutputView.printWinner(blackScore > whiteScore);
        }
    }

    private void move(String command) {
        List<String> moveInput = Arrays.asList(command.split(" "));

        Piece sourcePiece = chessService.piece(new Position(moveInput.get(1).charAt(0), moveInput.get(1).charAt(1)));
        Piece targetPiece = chessService.piece(new Position(moveInput.get(2).charAt(0), moveInput.get(2).charAt(1)));

        chessService.move(sourcePiece, targetPiece);
        chessService.changeTurn();
        if (targetPiece instanceof King) {
            chessService.setGameOver(true);
            OutputView.printWinner(!targetPiece.isBlack());
        }
    }

}
