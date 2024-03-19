package domain;

import static domain.PieceType.*;

import java.util.List;

public class RankGenerator {
    private static final List<String> SAVED_RANKS = List.of(
    );

    List<PieceType> back = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    List<PieceType> front = List.of(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN);
    List<PieceType> none = List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE);


    public RankGenerator() {

    }

    public List<Square> generate(int index) {

//        for (int column = 0; column < 8; column++) {
//            for (int row = 0; row < 8; row++) {
//                if (column == 0)
//            }
//        }
        return null;
    }
}
