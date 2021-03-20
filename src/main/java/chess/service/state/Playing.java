package chess.service.state;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public abstract class Playing implements GameState {
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public GameState run(ChessService chessService) {
        List<Piece> moveInput = pieces(chessService, InputView.command());
        Piece sourcePiece = moveInput.get(0);
        Piece targetPiece = moveInput.get(1);

        validateSourcePieceIsEmpty(sourcePiece);
        GameState gameState = move(chessService, sourcePiece, targetPiece);
        OutputView.printGridStatus(chessService.grid().lines());
        return gameState;
    }

    public List<Piece> pieces(ChessService chessService, String command){
        List<String> moveInput = Arrays.asList(command.split(" "));

        Piece sourcePiece = chessService.piece(new Position(moveInput.get(1).charAt(0), moveInput.get(1).charAt(1)));
        Piece targetPiece = chessService.piece(new Position(moveInput.get(2).charAt(0), moveInput.get(2).charAt(1)));

        return Arrays.asList(sourcePiece, targetPiece);
    }

    public void validateSourcePieceIsEmpty(Piece sourcePiece){
        if (sourcePiece.isEmpty()){
            throw new IllegalArgumentException("움직이려는 대상이 빈칸입니다");
        }
    }

    public boolean catchedKing(Piece targetPiece){
        return targetPiece instanceof King;
    }

    abstract GameState move(ChessService chessService, Piece sourcePiece, Piece targetPiece);
}
