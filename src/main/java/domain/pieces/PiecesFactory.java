package domain.pieces;

import static domain.point.Column.A;
import static domain.point.Column.B;
import static domain.point.Column.C;
import static domain.point.Column.D;
import static domain.point.Column.E;
import static domain.point.Column.F;
import static domain.point.Column.G;
import static domain.point.Column.H;
import static domain.point.Row.EIGHT;
import static domain.point.Row.ONE;
import static domain.point.Row.SEVEN;
import static domain.point.Row.TWO;
import static domain.team.Team.BLACK;
import static domain.team.Team.WHITE;

import domain.point.Column;
import domain.point.Point;
import java.util.HashSet;
import java.util.Set;

public class
PiecesFactory {
    private static Set<Piece> pieces;

    static {
        pieces = new HashSet<>();
        pieces.add(new King(BLACK, new Point(EIGHT, E)));
        pieces.add(new King(WHITE, new Point(ONE, E)));
        pieces.add(new Queen(BLACK, new Point(EIGHT, D)));
        pieces.add(new Queen(WHITE, new Point(ONE, D)));
        pieces.add(new Bishop(BLACK, new Point(EIGHT, C)));
        pieces.add(new Bishop(BLACK, new Point(EIGHT, F)));
        pieces.add(new Bishop(WHITE, new Point(ONE, C)));
        pieces.add(new Bishop(WHITE, new Point(ONE, F)));
        pieces.add(new Knight(BLACK, new Point(EIGHT, B)));
        pieces.add(new Knight(BLACK, new Point(EIGHT, G)));
        pieces.add(new Knight(WHITE, new Point(ONE, B)));
        pieces.add(new Knight(WHITE, new Point(ONE, G)));
        pieces.add(new Rook(BLACK, new Point(EIGHT, A)));
        pieces.add(new Rook(BLACK, new Point(EIGHT, H)));
        pieces.add(new Rook(WHITE, new Point(ONE, A)));
        pieces.add(new Rook(WHITE, new Point(ONE, H)));

        for (Column column: Column.values()) {
            pieces.add(new Pawn(BLACK, new Point(SEVEN, column)));
            pieces.add(new Pawn(WHITE, new Point(TWO, column)));
        }
    }

    public Set<Piece> getInstance() {
        return pieces;
    }
}