package chess.controller.command;

import chess.domain.PieceDto;
import chess.domain.chessGame.ChessGameState;
import chess.domain.position.Position;
import chess.view.OutputView;

import java.util.Map;

public class MoveCommandExecute implements CommandExecute {
    private final ChessGameState chessGameState;

    public MoveCommandExecute(ChessGameState chessGameState) {
        this.chessGameState = chessGameState;
    }

    @Override
    public ChessGameState execute(String current, String next) {
        Map<Position, PieceDto> board = chessGameState.move(current, next);
        OutputView.getInstance().printBoard(board);
        return chessGameState;
    }
}
