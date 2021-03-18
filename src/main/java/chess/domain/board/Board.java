package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;

import java.util.Map;

public class Board {
    private final Map<Position, Square> squares;

    public Board(Map<Position, Square> squares){
        this.squares = squares;
    }

    public Square of(Vertical vertical, Horizontal horizontal) {
        return squares.get(new Position(vertical, horizontal));
    }

    public Map<Position, Square> getSquares() {
        return squares;
    }

    public void move(Square source, Square target){
        // 중간에 아무것도 없다는거 확인
        source.move(target);
    }
}
