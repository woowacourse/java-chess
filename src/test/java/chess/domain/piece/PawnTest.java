package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    Team blackTeam;
    Team whiteTeam;
    Piece blackPawn;
    Piece whitePawn;

    @BeforeEach
    void setUp() {
        blackTeam = Team.BLACK;
        whiteTeam = Team.WHITE;
        blackPawn = new Pawn(blackTeam);
        whitePawn = new Pawn(whiteTeam);
    }

    @Test
    @DisplayName("폰은 target 지점에 같은 팀이 있으면 이동할 수 없다.")
    void isUnmovableToSameTeamInTarget() {
        Position source = new Position(2, 4);
        Position target = new Position(2, 3);
        assertThatThrownBy(() -> whitePawn.isMovable(source, target, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
    }

    @Test
    @DisplayName("흰팀 폰은 처음 움직이고 target 지점이 비어있다면 rank가 증가하는 방향으로 최대 2칸 움직일 수 있다.")
    void isMovableWhiteFirstTime() {
        Position source = new Position(2, 4);
        Position target = new Position(4, 4);
        assertThat(whitePawn.isMovable(source, target, Team.EMPTY)).isTrue();
    }

    @Test
    @DisplayName("검은팀 폰은 처음 움직이고 target 지점이 비어있다면 rank가 감소하는 방향으로 최대 2칸 움직일 수 있다.")
    void isMovableBlackFirstTime() {
        Position source = new Position(7, 4);
        Position target = new Position(5, 4);
        assertThat(blackPawn.isMovable(source, target, Team.EMPTY)).isTrue();
    }

    @Test
    @DisplayName("흰팀 폰은 처음 움직이는게 아니고 target 지점에 같은 팀이 없다면 rank 가 증가하는 방향으로 2칸 움직일 수 없다.")
    void isUnmovableWhiteNotInFirstTime() {
        Position source = new Position(3, 4);
        Position target = new Position(5, 4);
        assertThat(whitePawn.isMovable(source, target, Team.EMPTY)).isFalse();
        assertThat(whitePawn.isMovable(source, target, Team.BLACK)).isFalse();
    }

    @Test
    @DisplayName("검은팀 폰은 처음 움직이는게 아니고 target 지점에 같은 팀이 없다면 rank 가 증가하는 방향으로 2칸 움직일 수 없다.")
    void isUnmovableBlackNotInFirstTime() {
        Position source = new Position(6, 4);
        Position target = new Position(4, 4);
        assertThat(blackPawn.isMovable(source, target, Team.EMPTY)).isFalse();
        assertThat(blackPawn.isMovable(source, target, Team.WHITE)).isFalse();
    }

    @Test
    @DisplayName("흰팀 폰은 위 대각선 지점에 상대팀이 존재하면 움직일 수 있다.")
    void isCrossMovableWhite() {
        Position source = new Position(2, 4);
        Position target = new Position(3, 5);
        assertThat(whitePawn.isMovable(source, target, whiteTeam.reverse())).isTrue();
    }

    @Test
    @DisplayName("흰팀 폰은 위 대각선 지점이 빈공간이면 움직일 수 없다.")
    void isCrossUnmovableWhite() {
        Position source = new Position(2, 4);
        Position target = new Position(3, 5);
        assertThat(whitePawn.isMovable(source, target, Team.EMPTY)).isFalse();
    }

    @Test
    @DisplayName("검은팀 폰은 아래 대각선 지점에 상대팀이 존재하면 움직일 수 있다.")
    void isCrossMovableBlack() {
        Position source = new Position(6, 4);
        Position target = new Position(5, 3);
        assertThat(blackPawn.isMovable(source, target, blackTeam.reverse())).isTrue();
    }

    @Test
    @DisplayName("검은팀 폰은 아래 대각선 지점이 빈공간이면 움직일 수 없다.")
    void isCrossUnmovableBlack() {
        Position source = new Position(6, 4);
        Position target = new Position(5, 3);
        assertThat(blackPawn.isMovable(source, target, Team.EMPTY)).isFalse();
    }

    @Test
    @DisplayName("흰팀 폰은 아래 또는 옆으로 움직일 수 없다.")
    void isUnmovableToBackOrBesideWhite() {
        Position source = new Position(2, 4);
        Position backTarget = new Position(1, 4);
        Position besideTarget = new Position(2, 5);
        Assertions.assertAll(
                () -> assertThat(whitePawn.isMovable(source, backTarget, Team.EMPTY)).isFalse(),
                () -> assertThat(whitePawn.isMovable(source, besideTarget, Team.EMPTY)).isFalse()
        );
    }

    @Test
    @DisplayName("검은팀 폰은 위 또는 옆으로 움직일 수 없다.")
    void isUnmovableToBackOrBesideBlack() {
        Position source = new Position(6, 4);
        Position backTarget = new Position(7, 4);
        Position besideTarget = new Position(6, 3);
        Assertions.assertAll(
                () -> assertThat(blackPawn.isMovable(source, backTarget, Team.EMPTY)).isFalse(),
                () -> assertThat(blackPawn.isMovable(source, besideTarget, Team.EMPTY)).isFalse()
        );
    }

    @ParameterizedTest
    @MethodSource("findPathProvider")
    @DisplayName("타켓 위치로 갈 수 있는 모든 경로를 리스트에 담아서 반환한다.")
    void findPath(Position source, Position target, List<Position> expectedPath) {
        assertThat(whitePawn.findPath(source, target)).containsAll(expectedPath);
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
