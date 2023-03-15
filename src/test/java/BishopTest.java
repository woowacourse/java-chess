import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("source position이 target position까지 대각선 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenBishopDirectionIsDiagonal() {
        Bishop whiteBishop = Bishop.createOfWhite();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("e", "4"));

        assertThat(movable).isTrue();
    }

    @DisplayName("source position이 target position까지 대각선 방향이 아니면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenBishopDirectionIsNotDiagonal() {
        Bishop whiteBishop = Bishop.createOfBlack();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("e", "3"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 대각선 방향이고, target position에 같은 진영 말이 있으면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenBishopDirectionIsDiagonalButTargetPieceIsSameSide() {
        Bishop blackBishop = Bishop.createOfBlack();
        boolean movable = blackBishop.isMovable(Pawn.createOfBlack(), Position.of("c", "2"), Position.of("e", "4"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 대각선 방향이고, target position에 상대 진영 말이 있으면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenBishopDirectionIsDiagonalAndTargetPieceIsOpponentSide() {
        Bishop whiteBishop = Bishop.createOfWhite();
        boolean movable = whiteBishop.isMovable(Pawn.createOfBlack(), Position.of("c", "2"), Position.of("e", "4"));

        assertThat(movable).isTrue();
    }
}