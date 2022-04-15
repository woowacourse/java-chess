package chess.state;

import chess.chessboard.Board;
import chess.chessboard.position.Position;
import chess.piece.Piece;
import chess.game.Player;

import java.util.Map;

public abstract class Started implements State {

    protected final Board board;

    protected Started(final Board board) {
        this.board = board;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public Player getPlayer() {
        return Player.NONE;
    }
}
