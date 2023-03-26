package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    private Piece whiteTarget;
    private Piece blackTarget;
    private Piece emptyTarget;

    @BeforeEach
    void setUp() {
        whiteTarget = new Pawn(Team.WHITE);
        blackTarget = new Pawn(Team.BLACK);
        emptyTarget = new Empty(Team.EMPTY);
    }

    @Test
    @DisplayName("흰팀 폰은 처음 움직일 때(rank 2일때) rank가 증가하는 방향으로 최대 2칸 움직일 수 있다.")
    void isMovableWhiteFirstTime() {
        Piece pawn = new Pawn(Team.WHITE);
        Position source = new Position(2, 4);
        Position target = new Position(4, 4);
        assertThat(pawn.isMovable(source, target, emptyTarget)).isTrue();
    }

    @Test
    @DisplayName("검은팀 폰은 처음 움직일 때(rank 7일때) rank가 감소하는 방향으로 최대 2칸 움직일 수 있다.")
    void isMovableBlackFirstTime() {
        Piece pawn = new Pawn(Team.BLACK);
        Position source = new Position(7, 4);
        Position target = new Position(5, 4);
        assertThat(pawn.isMovable(source, target, emptyTarget)).isTrue();
    }

    @Test
    @DisplayName("흰팀 폰은 처음 움직이는게 아닐 때(rank 2가 아닐 때) rank 가 증가하는 방향으로 2칸 움직일 수 없다.")
    void isUnmovableWhiteNotInFirstTime() {
        Piece pawn = new Pawn(Team.WHITE);
        Position source = new Position(3, 4);
        Position target = new Position(5, 4);
        assertThat(pawn.isMovable(source, target, emptyTarget)).isFalse();
    }

    @Test
    @DisplayName("검은팀 폰은 처음 움직이는게 아닐 때(rank 7이 아닐 때) rank 가 감소하는 방향으로 2칸 움직일 수 없다.")
    void isUnmovableBlackNotInFirstTime() {
        Piece pawn = new Pawn(Team.BLACK);
        Position source = new Position(6, 4);
        Position target = new Position(4, 4);
        assertThat(pawn.isMovable(source, target, emptyTarget)).isFalse();
    }

    @Test
    @DisplayName("흰팀 폰은 상대팀이 있으면 위 대각선으로 한 칸 움직일 수 있다.")
    void isCrossMovableWhite() {
        Piece pawn = new Pawn(Team.WHITE);
        Position source = new Position(2, 4);
        Position target = new Position(3, 5);
        assertThat(pawn.isMovable(source, target, blackTarget)).isTrue();
    }

    @Test
    @DisplayName("검은팀 폰은 상대팀이 있으면 아래 대각선으로 한 칸 움직일 수 있다.")
    void isCrossMovableBlack() {
        Piece pawn = new Pawn(Team.BLACK);
        Position source = new Position(6, 4);
        Position target = new Position(5, 3);
        assertThat(pawn.isMovable(source, target, whiteTarget)).isTrue();
    }

    @Test
    @DisplayName("흰팀 폰은 상대팀이 없으면 위 일직선으로 한 칸 움직일 수 있다.")
    void isStraightMovableWhite() {
        Piece pawn = new Pawn(Team.WHITE);
        Position source = new Position(2, 4);
        Position target = new Position(3, 4);
        assertThat(pawn.isMovable(source, target, emptyTarget)).isTrue();
    }

    @Test
    @DisplayName("검은팀 폰은 상대팀이 없으면 아래 일직선으로 한 칸 움직일 수 있다.")
    void isStraightMovableBlack() {
        Piece pawn = new Pawn(Team.BLACK);
        Position source = new Position(6, 4);
        Position target = new Position(5, 4);
        assertThat(pawn.isMovable(source, target, emptyTarget)).isTrue();
    }

    @Test
    @DisplayName("흰팀 폰은 아래 또는 옆으로 움직일 수 없다.")
    void isUnmovableToBackOrBesideWhite() {
        Piece pawn = new Pawn(Team.WHITE);
        Position source = new Position(2, 4);
        Position backTarget = new Position(1, 4);
        Position besideTarget = new Position(2, 5);
        Assertions.assertAll(
                () -> assertThat(pawn.isMovable(source, backTarget, emptyTarget)).isFalse(),
                () -> assertThat(pawn.isMovable(source, besideTarget, emptyTarget)).isFalse()
        );
    }

    @Test
    @DisplayName("검은팀 폰은 위 또는 옆으로 움직일 수 없다.")
    void isUnmovableToBackOrBesideBlack() {
        Piece pawn = new Pawn(Team.BLACK);
        Position source = new Position(6, 4);
        Position backTarget = new Position(7, 4);
        Position besideTarget = new Position(6, 3);
        Assertions.assertAll(
                () -> assertThat(pawn.isMovable(source, backTarget, emptyTarget)).isFalse(),
                () -> assertThat(pawn.isMovable(source, besideTarget, emptyTarget)).isFalse()
        );
    }

    @ParameterizedTest
    @MethodSource("findPathProvider")
    @DisplayName("타켓 위치로 갈 수 있는 모든 경로를 리스트에 담아서 반환한다.")
    void findPath(Position source, Position target, List<Position> expectedPath) {
        Piece pawn = new Pawn(Team.WHITE);
        assertThat(pawn.findPath(source, target)).containsAll(expectedPath);
    }

    static Stream<Arguments> findPathProvider() {
        return Stream.of(
                Arguments.of(
                        new Position(2, 4),
                        new Position(4, 4),
                        List.of(
                                new Position(3, 4),
                                new Position(4, 4)
                        )
                ),
                Arguments.of(
                        new Position(2, 4),
                        new Position(3, 4),
                        List.of(
                                new Position(3, 4)
                        )
                ),
                Arguments.of(
                        new Position(2, 4),
                        new Position(3, 5),
                        List.of(
                                new Position(3, 5)
                        )
                ),
                Arguments.of(
                        new Position(5, 4),
                        new Position(6, 4),
                        List.of(
                                new Position(6, 4)
                        )
                ),
                Arguments.of(
                        new Position(4, 4),
                        new Position(5, 5),
                        List.of(
                                new Position(5, 5)
                        )
                )
        );
    }
}
