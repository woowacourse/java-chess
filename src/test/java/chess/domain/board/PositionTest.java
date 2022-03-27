package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.THREE;
import static chess.domain.board.Rank.TWO;
import static org.assertj.core.api.Assertions.*;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @Test
    @DisplayName("위치 값의 동일성을 테스트한다.")
    void position_equals() {
        //given
        Position position = new Position(A, ONE);

        //then
        assertThat(position).isEqualTo(new Position(A, ONE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "i7", "a0", "A1"})
    @DisplayName("위치 범위를 벗어나면 예외를 던진다.")
    void position_exception() {
        assertThatThrownBy(() -> new Position("a9"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(대각).")
    void traceGroupDiagonal() {
        //given
        Position source = new Position(A, ONE);
        Position target = new Position(D, FOUR);

        List<Position> traceGroup = source.positionsToMove(target);

        //then
        assertThat(traceGroup).contains(new Position(B, TWO), new Position(C, THREE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 우측).")
    void traceGroupRank_right() {
        //given
        Position source = new Position(A, ONE);
        Position target = new Position(D, ONE);

        List<Position> traceGroup = source.positionsToMove(target);

        //then
        assertThat(traceGroup).contains(new Position(B, ONE), new Position(C, ONE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 좌측).")
    void traceGroupRank_left() {
        //given
        Position source = new Position(D, ONE);
        Position target = new Position(A, ONE);

        List<Position> traceGroup = source.positionsToMove(target);

        //then
        assertThat(traceGroup).contains(new Position(B, ONE), new Position(C, ONE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 위쪽).")
    void traceGroupFile_up() {
        //given
        Position source = new Position(A, ONE);
        Position target = new Position(A, FOUR);

        List<Position> traceGroup = source.positionsToMove(target);

        //then
        assertThat(traceGroup).contains(new Position(A, TWO), new Position(A, THREE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 아래쪽).")
    void traceGroupFile_down() {
        //given
        Position source = new Position(A, FOUR);
        Position target = new Position(A, ONE);

        List<Position> traceGroup = source.positionsToMove(target);

        //then
        assertThat(traceGroup).contains(new Position(A, TWO), new Position(A, THREE));
    }
}
