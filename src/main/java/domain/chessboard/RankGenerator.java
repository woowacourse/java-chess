package domain.chessboard;

import static domain.piece.PieceRole.BISHOP;
import static domain.piece.PieceRole.KING;
import static domain.piece.PieceRole.KNIGHT;
import static domain.piece.PieceRole.NONE;
import static domain.piece.PieceRole.PAWN;
import static domain.piece.PieceRole.QUEEN;
import static domain.piece.PieceRole.ROOK;

import domain.piece.Color;
import domain.piece.PieceRole;
import domain.piece.PieceType;
import domain.piece.Position;
import java.util.ArrayList;
import java.util.List;

public class RankGenerator {
    private static final List<PieceRole> back = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    private static final List<PieceRole> front = List.of(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN);
    private static final List<PieceRole> none = List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE);


    public List<Square> generate(int row) {
        List<Square> result = new ArrayList<>();
        for (int column = 0; column < 8; column++) {
            result.add(generateSquare(column, row));
        }
        return result;
    }

    private Square generateSquare(int column, int row) {
        if (row == 0) {
            return new Square(new PieceType(back.get(column), Color.BLACK), new Position(column, row));
        }
        if (row == 1) {
            return new Square(new PieceType(front.get(column), Color.BLACK), new Position(column, row));
        }
        if (row == 6) {
            return new Square(new PieceType(front.get(column), Color.WHITE), new Position(column, row));
        }
        if (row == 7) {
            return new Square(new PieceType(back.get(column), Color.WHITE), new Position(column, row));
        }
        return new Square(new PieceType(none.get(column)), new Position(column, row));
    }
}
