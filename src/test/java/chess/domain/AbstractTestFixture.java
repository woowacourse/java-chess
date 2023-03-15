package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceTest;
import java.util.List;

public abstract class AbstractTestFixture {

    public static Move createMove(Direction... directions) {
        return new Move(List.of(directions));
    }

    public static Position createPosition(String position) {
        String[] split = position.split(",");
        File file = File.valueOf(split[0]);
        Rank rank = Rank.valueOf(split[1]);
        return new Position(file, rank);
    }

    public static Piece createPiece(boolean isWhite, boolean isFinite, Move... moves) {
        return new PieceTest.PieceImplement(isWhite, isFinite, List.of(moves));
    }
}
