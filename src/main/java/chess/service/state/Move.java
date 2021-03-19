package chess.service.state;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.service.ChessService;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class Move extends Playing {
    private String command;

    public Move(String command) {
        this.command = command;
    }

    @Override
    public void playRound(ChessService chessService) {
        move(chessService);
        OutputView.printGridStatus(chessService.grid());
    }

    private void move(ChessService chessService) {
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
