package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Squares {

    private final List<Square> squares;

    public Squares() {
        // TODO : 생성자 주입
        this.squares = generateSquares();
    }

    private List<Square> generateSquares() {
        List<Square> squares = new ArrayList<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                squares.add(new Square(file, rank));
            }
        }
        return squares;
    }

    public List<Square> findRoute(Piece piece, Square source, Square destination) {
        return null;
    }
}
