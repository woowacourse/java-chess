package chess.domain.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static chess.domain.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void 대각선으로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findStraightPaths(F_7);
        List<Position> reversePath = F_7.findStraightPaths(C_4);

        assertThat(path).containsOnly(D_5, E_6);
        assertThat(reversePath).containsOnly(D_5, E_6);
    }

    @Test
    void 위로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findStraightPaths(C_5);

        assertThat(path).isEmpty();
    }

    @Test
    void 대각선_위로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findStraightPaths(D_6);

        assertThat(path).isEmpty();
    }

    @Test
    void 앞으로_한두칸_혹은_대각선_한칸_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = B_1.findStraightPaths(B_2);
        List<Position> path2 = B_1.findStraightPaths(C_2);
        List<Position> path3 = B_1.findStraightPaths(B_3);

        assertThat(path).isEmpty();
        assertThat(path2).isEmpty();
        assertThat(path3).contains(B_2);
    }


    @Test
    void 양옆으로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findStraightPaths(F_4);
        List<Position> reversePath = F_4.findStraightPaths(C_4);

        assertThat(path).containsOnly(D_4, E_4);
        assertThat(reversePath).containsOnly(D_4, E_4);
    }

    @Test
    void 룩이_파일로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findStraightPaths(C_7);
        List<Position> reversePath = C_7.findStraightPaths(C_4);

        assertThat(path).containsOnly(C_5, C_6);
        assertThat(reversePath).containsOnly(C_5, C_6);
    }

    @ParameterizedTest
    @CsvSource(value = {"B:TWO:B:THREE:0", "B:TWO:D:FOUR:2", "D:FOUR:B:TWO:2"}, delimiter = ':')
    void 열의_거리를_구할_수_있다(FileCoordinate fileCoordinate1, RankCoordinate rankCoordinate1,
                        FileCoordinate fileCoordinate2, RankCoordinate rankCoordinate2, int expect) {
        Position position1 = new Position(fileCoordinate1, rankCoordinate1);
        Position position2 = new Position(fileCoordinate2, rankCoordinate2);
        assertThat(position1.calculateColumnDistance(position2)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"B:TWO:B:THREE:1", "B:TWO:D:FOUR:2", "D:FOUR:B:TWO:2"}, delimiter = ':')
    void 행의_거리를_구할_수_있다(FileCoordinate fileCoordinate1, RankCoordinate rankCoordinate1,
                        FileCoordinate fileCoordinate2, RankCoordinate rankCoordinate2, int expect) {
        Position position1 = new Position(fileCoordinate1, rankCoordinate1);
        Position position2 = new Position(fileCoordinate2, rankCoordinate2);
        assertThat(position1.calculateRowDistance(position2)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"B:TWO:B:THREE:false", "B:TWO:D:FOUR:true", "D:FOUR:B:TWO:true", "B:TWO:A:ONE:true", "B:TWO:A:THREE:true", "B:TWO:C:THREE:true"}, delimiter = ':')
    void 대각선인지_알_수_있다(FileCoordinate fileCoordinate1, RankCoordinate rankCoordinate1,
                        FileCoordinate fileCoordinate2, RankCoordinate rankCoordinate2, boolean expect) {
        Position position1 = new Position(fileCoordinate1, rankCoordinate1);
        Position position2 = new Position(fileCoordinate2, rankCoordinate2);
        assertThat(position1.isDiagonal(position2)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"B:TWO:B:THREE:true", "B:TWO:D:TWO:true", "D:TWO:B:TWO:true", "B:TWO:B:EIGHT:true", "A:TWO:H:TWO:true", "B:TWO:A:ONE:false"}, delimiter = ':')
    void 직선인지_알_수_있다(FileCoordinate fileCoordinate1, RankCoordinate rankCoordinate1,
                      FileCoordinate fileCoordinate2, RankCoordinate rankCoordinate2, boolean expect) {
        Position position1 = new Position(fileCoordinate1, rankCoordinate1);
        Position position2 = new Position(fileCoordinate2, rankCoordinate2);
        assertThat(position1.isStraight(position2)).isEqualTo(expect);
    }
}
