package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PawnTest {

    private final Pawn white = new Pawn(Team.WHITE);
    private final Pawn black = new Pawn(Team.BLACK);

    @ParameterizedTest
    @DisplayName("앞으로 한칸 이동 가능한 지 판단하는 기능")
    @ValueSource(strings = {"a,2,a,3,WHITE", "a,7,a,6,BLACK"})
    void canMoveOneBlock(final String input) {
        final String[] inputs = input.split(",");
        final Position start = new Position(inputs[0], inputs[1]);
        final Position end = new Position(inputs[2], inputs[3]);
        final Team team = Team.valueOf(inputs[4]);
        final Pawn pawn = new Pawn(team);

        assertThat(pawn.canMove(start, end, Blank.getInstance()))
            .isTrue();
        assertThat(pawn.canMove(start, end, new King(team.oppositeTeam())))
            .isFalse();
    }

    @ParameterizedTest
    @DisplayName("대각선 방향으로 한 칸 이동 가능한 지 판단하는 기능")
    @ValueSource(strings = {"a,2,b,3,WHITE", "a,7,b,6,BLACK"})
    void canMoveBlack(final String input) {
        final String[] inputs = input.split(",");
        final Position start = new Position(inputs[0], inputs[1]);
        final Position end = new Position(inputs[2], inputs[3]);
        final Team team = Team.valueOf(inputs[4]);
        final Pawn pawn = new Pawn(team);

        assertThat(pawn.canMove(start, end, new Pawn(team.oppositeTeam())))
            .isTrue();
        assertThat(pawn.canMove(start, end, new Pawn(team)))
            .isFalse();
    }

    @ParameterizedTest
    @DisplayName("처음 움직이는 경우 두칸 이동 가능 판단 기능")
    @ValueSource(strings = {"a,2,a,4,WHITE", "a,7,a,5,BLACK"})
    void checkInitialMove(final String input) {
        final String[] inputs = input.split(",");
        final Position start = new Position(inputs[0], inputs[1]);
        final Position end = new Position(inputs[2], inputs[3]);
        final Team team = Team.valueOf(inputs[4]);
        final Pawn pawn = new Pawn(team);

        assertThat(pawn.canMove(start, end, Blank.getInstance()))
            .isTrue();
        assertThat(pawn.canMove(start, end, Blank.getInstance()))
            .isTrue();
    }
}