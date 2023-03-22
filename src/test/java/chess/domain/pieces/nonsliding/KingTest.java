package chess.domain.pieces.nonsliding;

import static chess.domain.pieces.Piece.INVALID_MOVE_EXIST_ALLY;
import static chess.domain.pieces.nonsliding.King.INVALID_MOVE_DISTANCE;
import static chess.domain.pieces.nonsliding.NonSlidingPiece.INVALID_TEAM;
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

class KingTest {

    private King king;

    @BeforeEach
    void setUp() {
        king = new King(Team.BLACK);
    }

    @Test
    @DisplayName("킹은 중립일 수 없다")
    void 킹은_중립일_수_없다() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new King(team)
        ).withMessage(INVALID_TEAM);
    }

    @Test
    @DisplayName("킹은 8 방향으로 갈 수 있다.")
    void 킹은_8_방향으로_갈_수_있다() {
        List<Direction> directions = Direction.ofAllExpectKnight();

        for (Direction direction : directions) {
            assertThatNoException().isThrownBy(() -> king.validateMove(direction, List.of(new EmptyPiece())));
        }
    }

    @Test
    @DisplayName("킹은 나이트처럼 움직이지 못한다.")
    void 킹은_나이트처럼_움직이지_못한다() {
        List<Direction> directions = Direction.ofKnight();

        assertThatIllegalArgumentException().isThrownBy(
                () -> king.validateMove(directions.get(0), List.of(new EmptyPiece()))
        ).withMessage("해당 기물이 갈 수 없는 방향입니다.");
    }

    @Test
    @DisplayName("킹은 2칸 이상 움직일 경우 예외가 터진다.")
    void 킹은_2칸_이상_움직일_경우_예외가_발생한다() {
        List<Piece> pieces = List.of(new EmptyPiece(), new EmptyPiece());

        assertThatIllegalArgumentException().isThrownBy(
                () -> king.validateMove(Direction.UP, pieces)
        ).withMessage(INVALID_MOVE_DISTANCE);
    }

    @Test
    @DisplayName("킹은 1칸만 갈 수 있다.")
    void 킹은_1칸만_갈_수_있다() {
        List<Piece> pieces = List.of(new EmptyPiece());

        assertThatNoException().isThrownBy(
                () -> king.validateMove(Direction.UP, pieces)
        );
    }

    @Test
    @DisplayName("킹은 아군이 있는 곳으로 갈 수 없다.")
    void 킹은_아군이_있는_곳으로_갈_수_없다() {
        List<Piece> pieces = List.of(new BlackPawn());

        assertThatIllegalArgumentException().isThrownBy(
                () -> king.validateMove(Direction.UP, pieces)
        ).withMessage(INVALID_MOVE_EXIST_ALLY);
    }

    @Test
    @DisplayName("킹은 적군이 있는 곳이면 갈 수 있다")
    void 킹은_적군이_있는_곳이면_갈_수_있다() {
        List<Piece> pieces = List.of(new WhitePawn());

        assertThatNoException().isThrownBy(
                () -> king.validateMove(Direction.UP, pieces)
        );
    }
}
