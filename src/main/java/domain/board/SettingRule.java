package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;

public class SettingRule {

    public Piece findPieceByPosition(Rank rank, File file) {
        if (rank == Rank.TWO) {
            return new Piece(Type.PAWN, Color.WHITE);
        }
        if (rank == Rank.SEVEN) {
            return new Piece(Type.PAWN, Color.BLACK);
        }
        if (file == File.E) {
            if (rank == Rank.ONE) {
                return new Piece(Type.KING, Color.WHITE);
            }
            if (rank == Rank.EIGHT) {
                return new Piece(Type.KING, Color.BLACK);
            }
        }
        if (file == File.D) {
            if (rank == Rank.ONE) {
                return new Piece(Type.QUEEN, Color.WHITE);
            }
            if (rank == Rank.EIGHT) {
                return new Piece(Type.QUEEN, Color.BLACK);
            }
        }
        if (file == File.C || file == File.F) {
            if (rank == Rank.ONE) {
                return new Piece(Type.BISHOP, Color.WHITE);
            }
            if (rank == Rank.EIGHT) {
                return new Piece(Type.BISHOP, Color.BLACK);
            }
        }
        return new Piece(Type.NONE, Color.NONE);
    }
}
