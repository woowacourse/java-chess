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
        Position source = Position.of(Rank.ONE, File.A);
        Position target = Position.of(Rank.FOUR, File.D);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(Rank.TWO, File.B), Position.of(Rank.THREE, File.C));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 우측).")
    void traceGroupRank_right() {
        //given
        Position source = Position.of(Rank.ONE, File.A);
        Position target = Position.of(Rank.ONE, File.D);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(Rank.ONE, File.B), Position.of(Rank.ONE, File.C));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 좌측).")
    void traceGroupRank_left() {
        //given
        Position source = Position.of(Rank.ONE, File.D);
        Position target = Position.of(Rank.ONE, File.A);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(Rank.ONE, File.B), Position.of(Rank.ONE, File.C));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 위쪽).")
    void traceGroupFile_up() {
        //given
        Position source = Position.of(Rank.ONE, File.A);
        Position target = Position.of(Rank.FOUR, File.A);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(Rank.TWO, File.A), Position.of(Rank.THREE, File.A));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 아래쪽).")
    void traceGroupFile_down() {
        //given
        Position source = Position.of(Rank.FOUR, File.A);
        Position target = Position.of(Rank.ONE, File.A);

        List<Position> possiblePositions = new Path(source, target).possiblePositions();

        //then
        assertThat(possiblePositions).contains(Position.of(Rank.TWO, File.A), Position.of(Rank.THREE, File.A));
    }
}
