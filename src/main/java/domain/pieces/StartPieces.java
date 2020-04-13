package domain.pieces;

import static domain.coordinate.Column.A;
import static domain.coordinate.Column.B;
import static domain.coordinate.Column.C;
import static domain.coordinate.Column.D;
import static domain.coordinate.Column.E;
import static domain.coordinate.Column.F;
import static domain.coordinate.Column.G;
import static domain.coordinate.Column.H;
import static domain.coordinate.Row.EIGHT;
import static domain.coordinate.Row.ONE;
import static domain.coordinate.Row.SEVEN;
import static domain.coordinate.Row.TWO;
import static domain.team.Team.BLACK;
import static domain.team.Team.WHITE;

import domain.coordinate.Column;
import domain.coordinate.Coordinate;

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