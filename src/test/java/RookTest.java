import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @DisplayName("source position이 target position까지 수직 위 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenRookDirectionIsPerpendicular() {
        Rook whiteRook = Rook.createOfWhite();
        boolean movable = whiteRook.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("c", "4"));

        assertThat(movable).isTrue();
    }

    @DisplayName("source position이 target position까지 수직 방향이 아니면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenRookDirectionIsNotPerpendicular() {
        Rook whiteRook = Rook.createOfWhite();
        boolean movable = whiteRook.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("d", "4"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 수직 방향이고, target piece가 같은 진영이면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenRookDirectionIsPerpendicularButTargetPieceIsSameSide() {
        Rook whiteRook = Rook.createOfWhite();
        boolean movable = whiteRook.isMovable(Bishop.createOfWhite(), Position.of("c", "2"), Position.of("c", "4"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 수직 방향이고, target piece가 다른 진영이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenRookDirectionIsPerpendicularAndTargetPieceIsOpponentSide() {
        Rook blackRook = Rook.createOfBlack();
        boolean movable = blackRook.isMovable(Bishop.createOfWhite(), Position.of("c", "2"), Position.of("c", "4"));

        assertThat(movable).isTrue();
    }
}