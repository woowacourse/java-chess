package domain.board;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.None;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.File;
import domain.position.Rank;
import java.util.List;

public class SettingRule {

    public Piece decidePieceByPosition(Rank rank, File file) {
        Color color = decideColor(rank);
        if (List.of(Rank.TWO, Rank.SEVEN).contains(rank) && List.of(File.values()).contains(file)) {
            return new Pawn(color);
        }
        if (List.of(Rank.ONE, Rank.EIGHT).contains(rank) && List.of(File.A, File.H).contains(file)) {
            return new Rook(color);
        }
        if (List.of(Rank.ONE, Rank.EIGHT).contains(rank) && List.of(File.B, File.G).contains(file)) {
            return new Knight(color);
        }
        if (List.of(Rank.ONE, Rank.EIGHT).contains(rank) && List.of(File.C, File.F).contains(file)) {
            return new Bishop(color);
        }
        if (List.of(Rank.ONE, Rank.EIGHT).contains(rank) && file.isSame(File.D)) {
            return new Queen(color);
        }
        if (List.of(Rank.ONE, Rank.EIGHT).contains(rank) && file.isSame(File.E)) {
            return new King(color);
        }
        return new None(color);
    }

    private Color decideColor(Rank rank) {
        if (rank == Rank.ONE || rank == Rank.TWO) {
            return Color.WHITE;
        }
        if (rank == Rank.SEVEN || rank == Rank.EIGHT) {
            return Color.BLACK;
        }
        return Color.NONE;
    }
}
