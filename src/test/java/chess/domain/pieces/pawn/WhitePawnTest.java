package chess.domain.pieces.pawn;

import static chess.domain.pieces.Piece.INVALID_DIRECTION;
import static chess.domain.pieces.Piece.INVALID_MOVE_EXIST_ALLY;
import static chess.domain.pieces.pawn.Pawn.INVALID_DEFAULT_DISTANCE;
import static chess.domain.pieces.pawn.Pawn.INVALID_FIRST_DISTANCE;
import static chess.domain.pieces.pawn.Pawn.INVALID_MOVE_DIAGONAL;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.Team;
import chess.domain.math.Direction;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.IntStream;

class WhitePawnTest {

    private WhitePawn whitePawn;

    @BeforeEach
    void setUp() {
        whitePawn = new WhitePawn();
    }

    @Test
    @DisplayName("화이트 폰은 화이트팀이어야 합니다.")
    void 화이트_폰은_화이트팀이어야_합니다() {
        assertThat(whitePawn.getTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    @DisplayName("화이트팀 폰은 12시, 11시, 1시 방향으로만 갈 수 있습니다.")
    void 화이트팀_폰은_12시_11시_1시_방향으로만_갈_수_있습니다() {
        assertThat(whitePawn.getMovableDirections()).containsOnly(Direction.UP_LEFT, Direction.UP, Direction.UP_RIGHT);
    }

    @Test
    @DisplayName("화이트팀 폰은 아래 방향으로 갈 수 없습니다")
    void 화이트팀_폰은_아래_방향으로_갈_수_없습니다() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> whitePawn.validateMove(Direction.DOWN, List.of(new EmptyPiece()))
        ).withMessage(INVALID_DIRECTION);
    }

    @Test
    @DisplayName("대각선으로 가는 경우는 상대방이 있어야만 갈 수 있습니다.")
    void 대각선으로_가는_경우_상대방이_있어야만_갈_수_있습니다() {
        assertThatNoException().isThrownBy(
                () -> whitePawn.validateMove(Direction.UP_RIGHT, List.of(new BlackPawn()))
        );
    }

    @Test
    @DisplayName("대각선으로 가는 경우 빈칸이면 갈 수 없습니다.")
    void 대각선으로_가는_경우_빈칸이면_갈_수_없습니다() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> whitePawn.validateMove(Direction.UP_RIGHT, List.of(new EmptyPiece()))
        ).withMessage(INVALID_MOVE_DIAGONAL);
    }

    @Test
    @DisplayName("대각선으로 가는 경우 아군이 있을 수 없습니다.")
    void 대각선으로_가는_경우_아군이_있을_수_없습니다() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> whitePawn.validateMove(Direction.UP_LEFT, List.of(new WhitePawn()))
        ).withMessage(INVALID_MOVE_EXIST_ALLY);
    }

    @ParameterizedTest(name = "처음 움직이는 폰이면 1칸 또는 2칸 움직일 수 있습니다. 이동거리: {0}")
    @ValueSource(ints = {1, 2})
    void 처음_움직이는_폰이면_최대_2칸_움직일_수_있습니다(int distance) {
        List<Piece> pieces = IntStream.range(0, distance)
                .mapToObj(x -> new EmptyPiece())
                .collect(toList());

        assertThatNoException().isThrownBy(
                () -> whitePawn.validateMove(Direction.UP, pieces)
        );
    }

    @Test
    @DisplayName("처음 움직이는 폰은 3칸 이상 갈 수 없습니다.")
    void 처음_움직이는_폰은_3칸_이상_갈_수_없습니다() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> whitePawn.validateMove(Direction.UP, List.of(new EmptyPiece(), new EmptyPiece(), new EmptyPiece()))
        ).withMessage(INVALID_FIRST_DISTANCE);
    }

    @Test
    @DisplayName("처음 움직이는 폰이 아니어도 3칸 이상 갈 수 없습니다.")
    void 처음_움직이는_폰이_아니어도_3칸_이상_갈_수_없습니다() {
        whitePawn.validateMove(Direction.UP, List.of(new EmptyPiece()));

        assertThatIllegalArgumentException().isThrownBy(
                () -> whitePawn.validateMove(Direction.UP, List.of(new EmptyPiece(), new EmptyPiece(), new EmptyPiece()))
        ).withMessage(INVALID_DEFAULT_DISTANCE);
    }

    @Test
    @DisplayName("움직인 적이 있는 폰은 1칸만 갈 수 있습니다.")
    void 움직인_적이_있는_폰은_1칸만_갈_수_있습니다() {
        whitePawn.validateMove(Direction.UP, List.of(new EmptyPiece()));

        assertThatNoException().isThrownBy(
                () -> whitePawn.validateMove(Direction.UP, List.of(new EmptyPiece()))
        );
    }

    @Test
    @DisplayName("움직인 적이 있는 폰은 2칸 이상 갈 수 없습니다.")
    void 움직인_적이_있는_폰은_2칸_이상_갈_수_없습니다() {
        whitePawn.validateMove(Direction.UP, List.of(new EmptyPiece()));

        assertThatIllegalArgumentException().isThrownBy(
                () -> whitePawn.validateMove(Direction.UP, List.of(new EmptyPiece(), new EmptyPiece()))
        ).withMessage(INVALID_DEFAULT_DISTANCE);
    }

}
