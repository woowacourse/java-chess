package chess.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathTest {

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다")
    void traceGroupDiagonal() {
        //given
        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.D, Rank.FOUR);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(File.B, Rank.TWO), Position.of(File.C, Rank.THREE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 우측).")
    void traceGroupRank_right() {
        //given
        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.D, Rank.ONE);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(File.B, Rank.ONE), Position.of(File.C, Rank.ONE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 좌측).")
    void traceGroupRank_left() {
        //given
        Position source = Position.of(File.D, Rank.ONE);
        Position target = Position.of(File.A, Rank.ONE);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(File.B, Rank.ONE), Position.of(File.C, Rank.ONE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 위쪽).")
    void traceGroupFile_up() {
        //given
        Position source = Position.of(File.A, Rank.ONE);
        Position target = Position.of(File.A, Rank.FOUR);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.THREE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 아래쪽).")
    void traceGroupFile_down() {
        //given
        Position source = Position.of(File.A, Rank.FOUR);
        Position target = Position.of(File.A, Rank.ONE);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(File.A, Rank.TWO), Position.of(File.A, Rank.THREE));
    }
}
