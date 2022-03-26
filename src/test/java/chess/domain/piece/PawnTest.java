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

    @Test
    @DisplayName("초기 위치에서 Pawn이 움직일 수 있는지 여부를 확인한다.")
    void isInitialPositionWhiteMovableTest() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.TWO);
        Position to = Position.valueOf(Abscissa.a, Ordinate.FOUR);

        assertThat(whitePawn.isMovable(from, to)).isTrue();
    }

    @Test
    @DisplayName("Pawn은 초기 위치가 아니면 2칸 이동 할 수 없다.")
    void isWhiteNotMovableTwoStepTest() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.THREE);
        Position to = Position.valueOf(Abscissa.a, Ordinate.FIVE);

        assertThat(whitePawn.isMovable(from, to)).isFalse();
    }

    @Test
    @DisplayName("흰 색 Pawn은 대각선 위로 움직여 말을 잡을 수 있다.")
    void isWhitePawnCatchablePosition() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.THREE);
        Position to = Position.valueOf(Abscissa.b, Ordinate.FOUR);

        assertThat(whitePawn.isCatchable(from, to)).isTrue();
    }

    @Test
    @DisplayName("흰 색 Pawn은 대각선 아래로 움직여 말을 잡을 수 없다.")
    void isNotWhitePawnCatchablePosition() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.THREE);
        Position to = Position.valueOf(Abscissa.b, Ordinate.TWO);

        assertThat(whitePawn.isCatchable(from, to)).isFalse();
    }

    @Test
    @DisplayName("검은색 Pawn은 대각선 아래로 움직여 말을 잡을 수 있다.")
    void isBlackPawnCatchablePosition() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.SIX);
        Position to = Position.valueOf(Abscissa.b, Ordinate.FIVE);

        assertThat(blackPawn.isCatchable(from, to)).isTrue();
    }

    @Test
    @DisplayName("검은색 Pawn은 대각선 위로 움직여 말을 잡을 수 없다.")
    void isBlackPawnNonCatchablePosition() {
        Position from = Position.valueOf(Abscissa.a, Ordinate.SIX);
        Position to = Position.valueOf(Abscissa.b, Ordinate.SEVEN);

        assertThat(blackPawn.isCatchable(from, to)).isFalse();
    }
}
