package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {
    //1칸 앞에 기물이 존재하지 않으면 이동한다.
    //2칸 앞에 전진할 때 중간에 기물이 없으면 이동한다.
    //대각선에 상대팀 기물이 있으면 이동한다.

    @ParameterizedTest
    @DisplayName("첫번째 움직임에선 1칸 또는 2칸 전진할 수 있다.")
    @CsvSource({"7b, BLACK, 6b", "7b, BLACK, 5b", "2b, WHITE, 3b", "2b, WHITE, 3b"})
    void moveFirst(final String initialPosition, final TeamColor teamColor, final String targetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        final Piece moved = pawn.move(new ArrayList<>(), Position.from(targetPosition));
        //when
        boolean actual = moved.hasPosition(Position.from(targetPosition));
        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("두번째 움직임부터는 1칸 움직일 수 있다.")
    @CsvSource({"BLACK, 7b, 6b, 5b", "WHITE, 2b, 3b, 4b"})
    void moveFirst(final TeamColor teamColor, final String initialPosition,
                   final String firstTargetPosition, final String secondTargetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        final Piece firstMoved = pawn.move(new ArrayList<>(), Position.from(firstTargetPosition));
        final Piece secondMoved = firstMoved.move(new ArrayList<>(), Position.from(secondTargetPosition));
        //when
        boolean actual = secondMoved.hasPosition(Position.from(secondTargetPosition));
        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("첫번째 움직임이 아닌데, 두 칸 이동하려 하면 예외를 발생시킨다.")
    @CsvSource({"BLACK, 7b, 6b, 4b", "WHITE, 2b, 3b, 5b"})
    void moveExceptionNotFirst(final TeamColor teamColor, final String initialPosition,
                               final String firstTargetPosition, final String secondTargetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        final Piece firstMoved = pawn.move(new ArrayList<>(), Position.from(firstTargetPosition));
        //when, then
        assertThatThrownBy(() -> firstMoved.move(new ArrayList<>(), Position.from(secondTargetPosition)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @DisplayName("적 기물이 대각선 앞에 있으면 적 기물을 제거하면서 이동할 수 있다.")
    @CsvSource({"BLACK, 7b, WHITE, 6c", "WHITE, 2b, BLACK, 3c"})
    void moveDiagonal(final TeamColor teamColor, final String initialPosition,
                      final TeamColor enemyTeamColor, final String targetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        final Piece enemy = new Rook(enemyTeamColor, Position.from(targetPosition));
        final Piece moved = pawn.move(Collections.singletonList(enemy), Position.from(targetPosition));
        //when
        boolean actual = moved.hasPosition(Position.from(targetPosition));
        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("적 기물이 대각선 앞에 없는데 대각선으로 이동하려고 하면 예외를 발생시킨다.")
    @CsvSource({"BLACK, 7b, 6c", "WHITE, 2b, 3c"})
    void moveDiagonalException(final TeamColor teamColor, final String initialPosition, final String targetPosition) {
        //given
        final Piece pawn = new Pawn(teamColor, Position.from(initialPosition));
        //when, then
        assertThatThrownBy(() -> pawn.move(new ArrayList<>(), Position.from(targetPosition)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }
}
