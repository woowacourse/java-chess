package domain;

import static domain.PieceType.BISHOP;
import static domain.PieceType.KING;
import static domain.PieceType.KNIGHT;
import static domain.PieceType.NONE;
import static domain.PieceType.PAWN;
import static domain.PieceType.QUEEN;
import static domain.PieceType.ROOK;

import java.util.ArrayList;
import java.util.List;

public class RankGenerator {
    private static final List<PieceType> back = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    private static final List<PieceType> front = List.of(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN);
    private static final List<PieceType> none = List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE);


    public List<Square> generate(int row) {
        List<Square> result = new ArrayList<>();
        for (int column = 0; column < 8; column++) {
            result.add(generateSquare(column, row));
        }
        return result;
    }

    private Square generateSquare(int column, int row) {
        if (row == 0) {
            return new Square(new Piece(back.get(column), Color.BLACK), new Position(column, row));
        }
        if (row == 1) {
            return new Square(new Piece(front.get(column), Color.BLACK), new Position(column, row));
        }
        if (row == 6) {
            return new Square(new Piece(front.get(column), Color.WHITE), new Position(column, row));
        }
        if (row == 7) {
            return new Square(new Piece(back.get(column), Color.WHITE), new Position(column, row));
        }
        return new Square(new Piece(none.get(column)), new Position(column, row));
    }
}
