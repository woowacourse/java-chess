package chess.service.state;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class WhiteTurn extends Playing {

    @Override
    public GameState run(ChessService chessService) {
        GameState gameState = move(chessService);
        OutputView.printGridStatus(chessService.grid());
        return gameState;
    }

    private GameState move(ChessService chessService) {
        String command = InputView.command();
        List<String> moveInput = Arrays.asList(command.split(" "));

        Piece sourcePiece = chessService.piece(new Position(moveInput.get(1).charAt(0), moveInput.get(1).charAt(1)));
        Piece targetPiece = chessService.piece(new Position(moveInput.get(2).charAt(0), moveInput.get(2).charAt(1)));

        chessService.move(sourcePiece, targetPiece);
        chessService.changeTurn();
        if (targetPiece instanceof King) {
            OutputView.printWinner(!targetPiece.isBlack());
            return new End();
        }
        return new BlackTurn();
    }
}
