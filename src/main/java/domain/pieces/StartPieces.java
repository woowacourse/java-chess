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
import domain.point.Coordinate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class StartPieces {
    private static Set<Piece> pieces;

    static {
        pieces = new HashSet<>();
        pieces.add(new King(BLACK, new Coordinate(EIGHT, E)));
        pieces.add(new King(WHITE, new Coordinate(ONE, E)));
        pieces.add(new Queen(BLACK, new Coordinate(EIGHT, D)));
        pieces.add(new Queen(WHITE, new Coordinate(ONE, D)));
        pieces.add(new Bishop(BLACK, new Coordinate(EIGHT, C)));
        pieces.add(new Bishop(BLACK, new Coordinate(EIGHT, F)));
        pieces.add(new Bishop(WHITE, new Coordinate(ONE, C)));
        pieces.add(new Bishop(WHITE, new Coordinate(ONE, F)));
        pieces.add(new Knight(BLACK, new Coordinate(EIGHT, B)));
        pieces.add(new Knight(BLACK, new Coordinate(EIGHT, G)));
        pieces.add(new Knight(WHITE, new Coordinate(ONE, B)));
        pieces.add(new Knight(WHITE, new Coordinate(ONE, G)));
        pieces.add(new Rook(BLACK, new Coordinate(EIGHT, A)));
        pieces.add(new Rook(BLACK, new Coordinate(EIGHT, H)));
        pieces.add(new Rook(WHITE, new Coordinate(ONE, A)));
        pieces.add(new Rook(WHITE, new Coordinate(ONE, H)));

        for (Column column: Column.values()) {
            pieces.add(new Pawn(BLACK, new Coordinate(SEVEN, column)));
            pieces.add(new Pawn(WHITE, new Coordinate(TWO, column)));
        }
    }

    public Set<Piece> getInstance() {
        return Collections.unmodifiableSet(pieces);
    }
}