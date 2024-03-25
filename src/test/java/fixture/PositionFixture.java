package fixture;

import chess.domain.square.File;
import chess.domain.square.Square;
import chess.domain.square.Rank;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PositionFixture {

    public static final Square A1 = new Square(File.A, Rank.ONE);
    public static final Square A2 = new Square(File.A, Rank.TWO);
    public static final Square A3 = new Square(File.A, Rank.THREE);
    public static final Square A4 = new Square(File.A, Rank.FOUR);
    public static final Square A5 = new Square(File.A, Rank.FIVE);
    public static final Square A6 = new Square(File.A, Rank.SIX);
    public static final Square A7 = new Square(File.A, Rank.SEVEN);
    public static final Square A8 = new Square(File.A, Rank.EIGHT);

    public static final Square B1 = new Square(File.B, Rank.ONE);
    public static final Square B2 = new Square(File.B, Rank.TWO);
    public static final Square B3 = new Square(File.B, Rank.THREE);
    public static final Square B4 = new Square(File.B, Rank.FOUR);
    public static final Square B5 = new Square(File.B, Rank.FIVE);
    public static final Square B6 = new Square(File.B, Rank.SIX);
    public static final Square B7 = new Square(File.B, Rank.SEVEN);
    public static final Square B8 = new Square(File.B, Rank.EIGHT);

    public static final Square C1 = new Square(File.C, Rank.ONE);
    public static final Square C2 = new Square(File.C, Rank.TWO);
    public static final Square C3 = new Square(File.C, Rank.THREE);
    public static final Square C4 = new Square(File.C, Rank.FOUR);
    public static final Square C5 = new Square(File.C, Rank.FIVE);
    public static final Square C6 = new Square(File.C, Rank.SIX);
    public static final Square C7 = new Square(File.C, Rank.SEVEN);
    public static final Square C8 = new Square(File.C, Rank.EIGHT);

    public static final Square D1 = new Square(File.D, Rank.ONE);
    public static final Square D2 = new Square(File.D, Rank.TWO);
    public static final Square D3 = new Square(File.D, Rank.THREE);
    public static final Square D4 = new Square(File.D, Rank.FOUR);
    public static final Square D5 = new Square(File.D, Rank.FIVE);
    public static final Square D6 = new Square(File.D, Rank.SIX);
    public static final Square D7 = new Square(File.D, Rank.SEVEN);
    public static final Square D8 = new Square(File.D, Rank.EIGHT);

    public static final Square E1 = new Square(File.E, Rank.ONE);
    public static final Square E2 = new Square(File.E, Rank.TWO);
    public static final Square E3 = new Square(File.E, Rank.THREE);
    public static final Square E4 = new Square(File.E, Rank.FOUR);
    public static final Square E5 = new Square(File.E, Rank.FIVE);
    public static final Square E6 = new Square(File.E, Rank.SIX);
    public static final Square E7 = new Square(File.E, Rank.SEVEN);
    public static final Square E8 = new Square(File.E, Rank.EIGHT);

    public static final Square F1 = new Square(File.F, Rank.ONE);
    public static final Square F2 = new Square(File.F, Rank.TWO);
    public static final Square F3 = new Square(File.F, Rank.THREE);
    public static final Square F4 = new Square(File.F, Rank.FOUR);
    public static final Square F5 = new Square(File.F, Rank.FIVE);
    public static final Square F6 = new Square(File.F, Rank.SIX);
    public static final Square F7 = new Square(File.F, Rank.SEVEN);
    public static final Square F8 = new Square(File.F, Rank.EIGHT);

    public static final Square G1 = new Square(File.G, Rank.ONE);
    public static final Square G2 = new Square(File.G, Rank.TWO);
    public static final Square G3 = new Square(File.G, Rank.THREE);
    public static final Square G4 = new Square(File.G, Rank.FOUR);
    public static final Square G5 = new Square(File.G, Rank.FIVE);
    public static final Square G6 = new Square(File.G, Rank.SIX);
    public static final Square G7 = new Square(File.G, Rank.SEVEN);
    public static final Square G8 = new Square(File.G, Rank.EIGHT);

    public static final Square H1 = new Square(File.H, Rank.ONE);
    public static final Square H2 = new Square(File.H, Rank.TWO);
    public static final Square H3 = new Square(File.H, Rank.THREE);
    public static final Square H4 = new Square(File.H, Rank.FOUR);
    public static final Square H5 = new Square(File.H, Rank.FIVE);
    public static final Square H6 = new Square(File.H, Rank.SIX);
    public static final Square H7 = new Square(File.H, Rank.SEVEN);
    public static final Square H8 = new Square(File.H, Rank.EIGHT);

    public static Stream<Arguments> movablePositions(List<Square> movableSquares) {
        return movableSquares.stream()
                .map(Arguments::arguments);
    }

    public static Stream<Arguments> immovablePositions(List<Square> movableSquares, Square source) {
        return Square.allPositions().stream()
                .filter(position -> !movableSquares.contains(position) && !position.equals(source))
                .map(Arguments::arguments);
    }
}
