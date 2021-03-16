package chess.domain.board;

import java.util.Arrays;
import java.util.List;

public class Line {
    private final List<Square> squares;

    public Line(final List<Square> squares){
        this.squares = squares;
    }

    public Line(final Square... squares){
        this.squares = Arrays.asList(squares);
    }

    public void addSquare(Square square){
        squares.add(square);
    }

    public Square get(){
        return squares.get(0);
    }
}
