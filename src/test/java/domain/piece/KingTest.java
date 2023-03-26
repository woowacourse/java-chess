package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import view.PieceCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @DisplayName("source position이 target position까지 1칸 이면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,5", "c,3", "d,4", "b,4", "b,5", "d,5", "d,3", "b,3"})
    void shouldReturnTrueWhenMovementIsOneStep(String targetFile, String targetRank) {
        King whiteKing = new King(Side.WHITE);
        boolean movable = whiteKing.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isTrue();
    }

    @DisplayName("source position이 target position까지 1칸이 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,6", "c,2", "e,4", "a,4"})
    void shouldReturnFalseWhenMovementIsNotOneStep(String targetFile, String targetRank) {
        King blackKing = new King(Side.BLACK);
        boolean movable = blackKing.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 1칸이지만, target piece가 같은 진영의 말인 경우 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenMovementIsOneStepButTargetPieceIsSameSide() {
        King whiteKing = new King(Side.WHITE);
        boolean movable = whiteKing.isMovable(new Pawn(Side.WHITE), Position.of("c", "4"), Position.of("c", "5"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 1칸이고, target piece가 상대 진영의 말인 경우 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenMovementIsOneStepAndTargetPieceIsOpponentSide() {
        King whiteKing = new King(Side.WHITE);
        boolean movable = whiteKing.isMovable(new Pawn(Side.BLACK), Position.of("c", "4"), Position.of("c", "5"));

        assertThat(movable).isTrue();
    }

    @DisplayName("king은 모든 방향으로의 움직임에 있어서 거쳐가는 경로의 길이가 0이다.")
    @ParameterizedTest
    @CsvSource(value = {"c,5", "c,3", "d,4", "b,4", "b,5", "d,5", "d,3", "b,3"})
    void shouldReturnEmptyPathWhenMovementIsOneStep(String targetFile, String targetRank) {
        King whiteKing = new King(Side.WHITE);
        List<Position> path = whiteKing.collectPath(Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(path).isEmpty();
    }

    @DisplayName("black king의 경우 black king카테고리를 반환한다.")
    @Test
    void blackBishopCategoryTest() {
        assertThat(new King(Side.BLACK).getCategory()).isEqualTo(PieceCategory.BLACK_KING);
    }

    @DisplayName("white king의 경우 white king카테고리를 반환한다.")
    @Test
    void whiteBishopCategoryTest() {
        assertThat(new King(Side.WHITE).getCategory()).isEqualTo(PieceCategory.WHITE_KING);
    }
}
