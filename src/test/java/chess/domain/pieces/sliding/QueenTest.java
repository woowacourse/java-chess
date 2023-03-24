package chess.domain.pieces.sliding;

import static chess.domain.pieces.Piece.INVALID_DIRECTION;
import static chess.domain.pieces.Piece.INVALID_MOVE_EXIST_ALLY;
import static chess.domain.pieces.sliding.SlidingPiece.INVALID_TEAM;
import static java.util.stream.Collectors.toList;
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

class QueenTest {

    private Queen queen;

    @BeforeEach
    void setUp() {
        queen = new Queen(Team.BLACK);
    }

    @Test
    @DisplayName("퀸은 중립일 수 없다")
    void 퀸은_중립일_수_없다() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Queen(team)
        ).withMessage(INVALID_TEAM);
    }

    @Test
    @DisplayName("퀸은 모든 방향으로 갈 수 있다.")
    void 퀸은_모든_방향으로_갈_수_있다() {
        List<Direction> directions = Direction.ofAllExpectKnight();

        for (Direction direction : directions) {
            assertThatNoException().isThrownBy(
                    () -> queen.validateMove(direction, List.of(new EmptyPiece()))
            );
        }
    }

    @Test
    @DisplayName("퀸은 나이트처럼 움직이지 못한다.")
    void 퀸은_나이트처럼_움직이지_못한다() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> queen.validateMove(Direction.KNIGHT, List.of(new EmptyPiece()))
        ).withMessage(INVALID_DIRECTION);
    }

    @Test
    @DisplayName("퀸은 이동 거리가 상관없다.")
    void 퀸은_이동_거리가_상관없다() {
        List<Piece> pieces = IntStream.range(0, 30)
                .mapToObj(x -> new EmptyPiece())
                .collect(toList());

        assertThatNoException().isThrownBy(
                () -> queen.validateMove(Direction.UP_LEFT, pieces)
        );
    }

    @Test
    @DisplayName("퀸은 아군이 있는 곳으로 갈 수 없다.")
    void 퀸은_아군이_있는_곳으로_갈_수_없다() {
        List<Piece> pieces = List.of(new EmptyPiece(), new EmptyPiece(), new BlackPawn());

        assertThatIllegalArgumentException().isThrownBy(
                () -> queen.validateMove(Direction.UP_LEFT, pieces)
        ).withMessage(INVALID_MOVE_EXIST_ALLY);
    }

    @Test
    @DisplayName("퀸은 적군이 있는 곳이면 갈 수 있다")
    void 퀸은_적군이_있는_곳이면_갈_수_있다() {
        List<Piece> pieces = List.of(new EmptyPiece(), new WhitePawn());

        assertThatNoException().isThrownBy(
                () -> queen.validateMove(Direction.UP_RIGHT, pieces)
        );
    }
}
