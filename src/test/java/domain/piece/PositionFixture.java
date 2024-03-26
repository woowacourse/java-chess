package domain.piece;

import static domain.position.File.A;
import static domain.position.File.B;
import static domain.position.File.C;
import static domain.position.File.D;
import static domain.position.File.E;
import static domain.position.File.F;
import static domain.position.File.G;
import static domain.position.File.H;
import static domain.position.Rank.EIGHT;
import static domain.position.Rank.FIVE;
import static domain.position.Rank.FOUR;
import static domain.position.Rank.ONE;
import static domain.position.Rank.SEVEN;
import static domain.position.Rank.SIX;
import static domain.position.Rank.THREE;
import static domain.position.Rank.TWO;

import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PositionFixture {

    public static final Position A1 = PositionGenerator.generate(A, ONE);
    public static final Position A2 = PositionGenerator.generate(A, TWO);
    public static final Position A3 = PositionGenerator.generate(A, THREE);
    public static final Position A4 = PositionGenerator.generate(A, FOUR);
    public static final Position A5 = PositionGenerator.generate(A, FIVE);
    public static final Position A6 = PositionGenerator.generate(A, SIX);
    public static final Position A7 = PositionGenerator.generate(A, SEVEN);
    public static final Position A8 = PositionGenerator.generate(A, EIGHT);
    public static final Position B1 = PositionGenerator.generate(B, ONE);
    public static final Position B2 = PositionGenerator.generate(B, TWO);
    public static final Position B3 = PositionGenerator.generate(B, THREE);
    public static final Position B4 = PositionGenerator.generate(B, FOUR);
    public static final Position B5 = PositionGenerator.generate(B, FIVE);
    public static final Position B6 = PositionGenerator.generate(B, SIX);
    public static final Position B7 = PositionGenerator.generate(B, SEVEN);
    public static final Position B8 = PositionGenerator.generate(B, EIGHT);
    public static final Position C1 = PositionGenerator.generate(C, ONE);
    public static final Position C2 = PositionGenerator.generate(C, TWO);
    public static final Position C3 = PositionGenerator.generate(C, THREE);
    public static final Position C4 = PositionGenerator.generate(C, FOUR);
    public static final Position C5 = PositionGenerator.generate(C, FIVE);
    public static final Position C6 = PositionGenerator.generate(C, SIX);
    public static final Position C7 = PositionGenerator.generate(C, SEVEN);
    public static final Position C8 = PositionGenerator.generate(C, EIGHT);
    public static final Position D1 = PositionGenerator.generate(D, ONE);
    public static final Position D2 = PositionGenerator.generate(D, TWO);
    public static final Position D3 = PositionGenerator.generate(D, THREE);
    public static final Position D4 = PositionGenerator.generate(D, FOUR);
    public static final Position D5 = PositionGenerator.generate(D, FIVE);
    public static final Position D6 = PositionGenerator.generate(D, SIX);
    public static final Position D7 = PositionGenerator.generate(D, SEVEN);
    public static final Position D8 = PositionGenerator.generate(D, EIGHT);
    public static final Position E1 = PositionGenerator.generate(E, ONE);
    public static final Position E2 = PositionGenerator.generate(E, TWO);
    public static final Position E3 = PositionGenerator.generate(E, THREE);
    public static final Position E4 = PositionGenerator.generate(E, FOUR);
    public static final Position E5 = PositionGenerator.generate(E, FIVE);
    public static final Position E6 = PositionGenerator.generate(E, SIX);
    public static final Position E7 = PositionGenerator.generate(E, SEVEN);
    public static final Position E8 = PositionGenerator.generate(E, EIGHT);
    public static final Position F1 = PositionGenerator.generate(F, ONE);
    public static final Position F2 = PositionGenerator.generate(F, TWO);
    public static final Position F3 = PositionGenerator.generate(F, THREE);
    public static final Position F4 = PositionGenerator.generate(F, FOUR);
    public static final Position F5 = PositionGenerator.generate(F, FIVE);
    public static final Position F6 = PositionGenerator.generate(F, SIX);
    public static final Position F7 = PositionGenerator.generate(F, SEVEN);
    public static final Position F8 = PositionGenerator.generate(F, EIGHT);
    public static final Position G1 = PositionGenerator.generate(G, ONE);
    public static final Position G2 = PositionGenerator.generate(G, TWO);
    public static final Position G3 = PositionGenerator.generate(G, THREE);
    public static final Position G4 = PositionGenerator.generate(G, FOUR);
    public static final Position G5 = PositionGenerator.generate(G, FIVE);
    public static final Position G6 = PositionGenerator.generate(G, SIX);
    public static final Position G7 = PositionGenerator.generate(G, SEVEN);
    public static final Position G8 = PositionGenerator.generate(G, EIGHT);
    public static final Position H1 = PositionGenerator.generate(H, ONE);
    public static final Position H2 = PositionGenerator.generate(H, TWO);
    public static final Position H3 = PositionGenerator.generate(H, THREE);
    public static final Position H4 = PositionGenerator.generate(H, FOUR);
    public static final Position H5 = PositionGenerator.generate(H, FIVE);
    public static final Position H6 = PositionGenerator.generate(H, SIX);
    public static final Position H7 = PositionGenerator.generate(H, SEVEN);
    public static final Position H8 = PositionGenerator.generate(H, EIGHT);
    private static final Set<Position> positions;

    static {
        positions = Set.of(
                A1, A2, A3, A4, A5, A6, A7, A8,
                B1, B2, B3, B4, B5, B6, B7, B8,
                C1, C2, C3, C4, C5, C6, C7, C8,
                D1, D2, D3, D4, D5, D6, D7, D8,
                E1, E2, E3, E4, E5, E6, E7, E8,
                F1, F2, F3, F4, F5, F6, F7, F8,
                G1, G2, G3, G4, G5, G6, G7, G8,
                H1, H2, H3, H4, H5, H6, H7, H8
        );
    }

    public static Set<Position> otherPositions(Position position) {
        Set<Position> allPositions = new HashSet<>(allPositions());
        allPositions.remove(position);
        return allPositions;
    }

    public static Set<Position> otherPositions(Set<Position> positions) {
        Set<Position> allPositions = new HashSet<>(allPositions());
        allPositions.removeAll(positions);
        return allPositions;
    }

    private static Set<Position> allPositions() {
        return Collections.unmodifiableSet(positions);
    }
}
