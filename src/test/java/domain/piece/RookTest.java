package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.PieceCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @DisplayName("source position이 target position까지 수직 위 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenRookDirectionIsPerpendicular() {
        Rook whiteRook = new Rook(Side.WHITE);
        boolean movable = whiteRook.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("c", "4"));

        assertThat(movable).isTrue();
    }

    @DisplayName("source position이 target position까지 수직 방향이 아니면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenRookDirectionIsNotPerpendicular() {
        Rook whiteRook = new Rook(Side.WHITE);
        boolean movable = whiteRook.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("d", "4"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 수직 방향이고, target piece가 같은 진영이면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenRookDirectionIsPerpendicularButTargetPieceIsSameSide() {
        Rook whiteRook = new Rook(Side.WHITE);
        boolean movable = whiteRook.isMovable(new Bishop(Side.WHITE), Position.of("c", "2"), Position.of("c", "4"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 수직 방향이고, target piece가 다른 진영이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenRookDirectionIsPerpendicularAndTargetPieceIsOpponentSide() {
        Rook blackRook = new Rook(Side.BLACK);
        boolean movable = blackRook.isMovable(new Bishop(Side.WHITE), Position.of("c", "2"), Position.of("c", "4"));

        assertThat(movable).isTrue();
    }

    @DisplayName("Rook이 수직 위로 이동하면 해당 결로를 반환한다.")
    @Test
    void shouldReturnPathWhenRookMoveUpward() {
        Rook whiteRook = new Rook(Side.WHITE);
        List<Position> path = whiteRook.collectPath(Position.of("c", "1"), Position.of("c", "4"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "2"), Position.of("c", "3"));
    }

    @DisplayName("Rook이 수직 아래로 이동하면 해당 결로를 반환한다.")
    @Test
    void shouldReturnPathWhenRookMoveDownward() {
        Rook whiteRook = new Rook(Side.WHITE);
        List<Position> path = whiteRook.collectPath(Position.of("c", "4"), Position.of("c", "1"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "3"), Position.of("c", "2"));
    }

    @DisplayName("Rook이 왼쪽으로 이동하면 해당 결로를 반환한다.")
    @Test
    void shouldReturnPathWhenRookMoveLeft() {
        Rook whiteRook = new Rook(Side.WHITE);
        List<Position> path = whiteRook.collectPath(Position.of("e", "3"), Position.of("b", "3"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("d", "3"), Position.of("c", "3"));
    }

    @DisplayName("Rook이 오른쪽으로 이동하면 해당 결로를 반환한다.")
    @Test
    void shouldReturnPathWhenRookMoveRight() {
        Rook whiteRook = new Rook(Side.WHITE);
        List<Position> path = whiteRook.collectPath(Position.of("c", "3"), Position.of("f", "3"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("d", "3"), Position.of("e", "3"));
    }

    @DisplayName("black rook의 경우 black rook카테고리를 반환한다.")
    @Test
    void blackBishopCategoryTest() {
        assertThat(new Rook(Side.BLACK).getCategory()).isEqualTo(PieceCategory.BLACK_ROOK);
    }

    @DisplayName("white rook의 경우 white rook카테고리를 반환한다.")
    @Test
    void whiteBishopCategoryTest() {
        assertThat(new Rook(Side.WHITE).getCategory()).isEqualTo(PieceCategory.WHITE_ROOK);
    }
}