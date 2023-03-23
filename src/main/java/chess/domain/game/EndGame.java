package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.exception.IllegalGameStateException;

public class EndGame extends Game {

    public EndGame(Board board) {
        super(board);
    }

    @Override
    public Game move(Position from, Position to) {
        throw new IllegalGameStateException();
    }

}
