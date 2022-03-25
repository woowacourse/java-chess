package chess.domain.game.state;

import chess.domain.Board;
import chess.domain.Position;

public class Running implements GameState{

    @Override
    public GameState movePiece(Board board, Position fromPosition, Position toPosition) {
        board.movePiece(fromPosition,toPosition);
        if (!board.isAllKingExist()) {
            return new End();
        }
        return new Running();
    }
}
