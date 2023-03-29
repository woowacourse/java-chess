package chess.domain.pieces.nonsliding;

import static chess.domain.pieces.Piece.INVALID_DIRECTION;
import static chess.domain.pieces.Piece.INVALID_MOVE_EXIST_ALLY;
import static chess.domain.pieces.nonsliding.NonSlidingPiece.INVALID_TEAM;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.pawn.BlackPawn;
import chess.domain.pieces.pawn.WhitePawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.IntStream;

class KnightTest {

    private Knight knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(Team.BLACK);
    }

    @Test
    @DisplayName("나이트는 중립일 수 없다")
    void 나이트는_중립일_수_없다() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Knight(team)
        ).withMessage(INVALID_TEAM);
    }

    @Test
    @DisplayName("나이트는 나이트 방향으로만 갈 수 있다.")
    void 나이트는_나이트_방향으로만_갈_수_있다() {
        assertThatNoException().isThrownBy(() -> knight.validateMove(Direction.KNIGHT, List.of(new EmptyPiece())));
    }

    @Test
    @DisplayName("나이트는 나이트를 제외한 방향으로 움직이지 못한다.")
    void 나이트는_나이트를_제외한_방향으로_움직이지_못한다() {
        List<Direction> directions = Direction.ofAllExpectKnight();

        for (Direction direction : directions) {
            assertThatIllegalArgumentException().isThrownBy(
                    () -> knight.validateMove(direction, List.of(new EmptyPiece()))
            ).withMessage(INVALID_DIRECTION);
        }
    }

    @Test
    @DisplayName("나이트는 방향만 맞으면 칸 수는 상관없다.")
    void 나이트는_2칸_이상_움직일_경우_예외가_발생한다() {
        List<Piece> pieces = IntStream.range(0, 30)
                .mapToObj(x -> new EmptyPiece())
                .collect(toList());

        assertThatNoException().isThrownBy(
                () -> knight.validateMove(Direction.KNIGHT, pieces)
        );
    }

    @Test
    @DisplayName("나이트는 아군이 있는 곳으로 갈 수 없다.")
    void 나이트는_아군이_있는_곳으로_갈_수_없다() {
        List<Piece> pieces = List.of(new BlackPawn());

        assertThatIllegalArgumentException().isThrownBy(
                () -> knight.validateMove(Direction.KNIGHT, pieces)
        ).withMessage(INVALID_MOVE_EXIST_ALLY);
    }

    @Test
    @DisplayName("나이트는 적군이 있는 곳이면 갈 수 있다")
    void 나이트는_적군이_있는_곳이면_갈_수_있다() {
        List<Piece> pieces = List.of(new WhitePawn());

        assertThatNoException().isThrownBy(
                () -> knight.validateMove(Direction.KNIGHT, pieces)
        );
    }

    @Test
    @DisplayName("킹이 아니다")
    void 킹이_아니다() {
        assertThat(knight.isKing()).isFalse();
    }
}
