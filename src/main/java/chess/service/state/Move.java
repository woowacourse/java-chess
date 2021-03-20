package chess.service.state;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.service.ChessService;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class Move extends Playing {
    private static final int INDEX = 1;
    private static final int ROW_INDEX = INDEX;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int COLUMN_INDEX = 0;
    private String command;

    public Move(String command) {
        this.command = command;
    }

    @Override
    public GameState playRound(ChessService chessService) {
        GameState gameState = move(chessService);
        OutputView.printGridStatus(chessService.grid());
        return gameState;
    }

    private GameState move(ChessService chessService) {
        List<String> moveInput = Arrays.asList(command.split(" "));
        String source = moveInput.get(SOURCE_INDEX);
        String target = moveInput.get(TARGET_INDEX);

        Piece sourcePiece = chessService.piece(new Position(source.charAt(COLUMN_INDEX), source.charAt(ROW_INDEX)));
        Piece targetPiece = chessService.piece(new Position(target.charAt(COLUMN_INDEX), target.charAt(ROW_INDEX)));

        chessService.move(sourcePiece, targetPiece);
        chessService.changeTurn();
        if (targetPiece instanceof King) {
            chessService.setGameOver(true);
            OutputView.printWinner(!targetPiece.isBlack());
            return new Finished();
        }
        return new Playing();
    }
}
