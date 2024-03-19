package chess.domain.piece;

import chess.domain.Point;
import java.util.ArrayList;
import java.util.List;

public class PieceFactory {

    public static List<Piece> create() {
        List<Piece> pieces = new ArrayList<>();

        pieces.addAll(createEighthLine());
        pieces.addAll(createSeventhLine());
        pieces.addAll(createSecondLine());
        pieces.addAll(createFirstLine());
        return pieces;
    }

    private static List<Piece> createEighthLine() {
        return lineOfKing(8, Team.BLACK);
    }

    private static List<Piece> createSeventhLine() {
        return lineOfPawn(7, Team.BLACK);
    }

    private static List<Piece> createSecondLine() {
        return lineOfPawn(2, Team.WHITE);
    }

    private static List<Piece> createFirstLine() {
        return lineOfKing(1, Team.WHITE);
    }

    private static List<Piece> lineOfPawn(int rank, Team team) {
        List<Piece> line = new ArrayList<>();

        for (char c = 'a'; c <= 'h'; c++) {
            line.add(new Pawn(new Point(String.valueOf(c), rank), team));
        }
        return line;
    }

    private static List<Piece> lineOfKing(int rank, Team team) {
        return List.of(
                new Rook(new Point("a", rank), team),
                new Knight(new Point("b", rank), team),
                new Bishop(new Point("c", rank), team),
                new King(new Point("d", rank), team),
                new Queen(new Point("e", rank), team),
                new Bishop(new Point("f", rank), team),
                new Knight(new Point("g", rank), team),
                new Rook(new Point("h", rank), team)
        );
    }
}
