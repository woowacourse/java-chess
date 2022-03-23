package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Abscissa;
import chess.domain.Ordinate;
import chess.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @BeforeEach
    void setUp() {
        whitePawn = new Pawn(Color.WHITE);
        blackPawn = new Pawn(Color.BLACK);
    }

    @Test
    @DisplayName("Pawn이 움직일 수 있는지 여부를 확인한다")
    void isWhiteMovableTest() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.ONE);
        Position to = Position.valueOf(Abscissa.a, Ordinate.TWO);

        assertThat(whitePawn.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Pawn이 움직일 수 있는지 여부를 확인한다")
    void isBlackMovableTest() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.SEVEN);
        Position to = Position.valueOf(Abscissa.a, Ordinate.SIX);

        assertThat(blackPawn.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Pawn이 움직일 수 없는 경우에 false를 반환한다.")
    void isNotMovableTest() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.TWO);
        Position to = Position.valueOf(Abscissa.b, Ordinate.TWO);

        assertThat(whitePawn.isMovable(from, to));
    }
}
