package chess.domain.pieces.sliding;

import static chess.domain.pieces.Piece.INVALID_DIRECTION;
import static chess.domain.pieces.Piece.INVALID_MOVE_EXIST_ALLY;
import static chess.domain.pieces.sliding.SlidingPiece.INVALID_MOVE_JUMP_OTHER_PIECE;
import static chess.domain.pieces.sliding.SlidingPiece.INVALID_TEAM;
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

class BishopTest {

    private Bishop bishop;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Team.BLACK);
    }

    @Test
    @DisplayName("비숍은 중립일 수 없다")
    void 비숍은_중립일_수_없다() {
        Team team = Team.NEUTRALITY;

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Bishop(team)
        ).withMessage(INVALID_TEAM);
    }

    @Test
    @DisplayName("비숍은 대각선으로만 갈 수 있다.")
    void 비숍은_대각선으로만_갈_수_있다() {
        List<Direction> directions = Direction.ofDiagonal();

        for (Direction direction : directions) {
            assertThatNoException().isThrownBy(
                    () -> bishop.validateMove(direction, List.of(new EmptyPiece()))
            );
        }
    }

    @Test
    @DisplayName("비숍은 수직 방향으로 움직이지 못한다.")
    void 비숍은_수직_방향으로_움직이지_못한다() {
        for (Direction direction : Direction.ofCross()) {
            assertThatIllegalArgumentException().isThrownBy(
                    () -> bishop.validateMove(direction, List.of(new EmptyPiece()))
            ).withMessage(INVALID_DIRECTION);
        }
    }

    @Test
    @DisplayName("비숍은 나이트처럼 움직이지 못한다.")
    void 비숍은_나이트처럼_움직이지_못한다() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> bishop.validateMove(Direction.KNIGHT, List.of(new EmptyPiece()))
        ).withMessage(INVALID_DIRECTION);
    }

    @Test
    @DisplayName("비숍은 이동 거리가 상관없다.")
    void 비숍은_이동_거리가_상관없다() {
        List<Piece> pieces = IntStream.range(0, 30)
                .mapToObj(x -> new EmptyPiece())
                .collect(toList());

        assertThatNoException().isThrownBy(
                () -> bishop.validateMove(Direction.UP_LEFT, pieces)
        );
    }

    @Test
    @DisplayName("비숍은 아군이 있는 곳으로 갈 수 없다.")
    void 비숍은_아군이_있는_곳으로_갈_수_없다() {
        List<Piece> pieces = List.of(new BlackPawn());

        assertThatIllegalArgumentException().isThrownBy(
                () -> bishop.validateMove(Direction.UP_LEFT, pieces)
        ).withMessage(INVALID_MOVE_EXIST_ALLY);
    }

    @Test
    @DisplayName("비숍은 적군이 있는 곳이면 갈 수 있다")
    void 비숍은_적군이_있는_곳이면_갈_수_있다() {
        List<Piece> pieces = List.of(new WhitePawn());

        assertThatNoException().isThrownBy(
                () -> bishop.validateMove(Direction.UP_RIGHT, pieces)
        );
    }

    @Test
    @DisplayName("비숍은 기물을 뛰어넘을 수 없다")
    void 비숍은_기물을_뛰어넘을_수_없다() {
        var pieces = List.of(new EmptyPiece(), new WhitePawn(), new EmptyPiece());

        assertThatIllegalArgumentException().isThrownBy(
                () -> bishop.validateMove(Direction.UP_RIGHT, pieces)
        ).withMessage(INVALID_MOVE_JUMP_OTHER_PIECE);
    }

    @Test
    @DisplayName("킹이 아니다")
    void 킹이_아니다() {
        assertThat(bishop.isKing()).isFalse();
    }
}
