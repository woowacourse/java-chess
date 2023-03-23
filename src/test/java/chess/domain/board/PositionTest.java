package chess.domain.board;

import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void 대각선으로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findPath(F_7);
        List<Position> reversePath = F_7.findPath(C_4);

        assertThat(path).containsOnly(D_5, E_6);
        assertThat(reversePath).containsOnly(D_5, E_6);
    }

    @Test
    void 위로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findPath(C_5);

        assertThat(path).isEmpty();
    }

    @Test
    void 대각선_위로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findPath(D_6);

        assertThat(path).isEmpty();
    }

    @Test
    void 앞으로_한두칸_혹은_대각선_한칸_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = B_1.findPath(B_2);
        List<Position> path2 = B_1.findPath(C_2);
        List<Position> path3 = B_1.findPath(B_3);

        assertThat(path).isEmpty();
        assertThat(path2).isEmpty();
        assertThat(path3).contains(B_2);
    }


    @Test
    void 양옆으로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findPath(F_4);
        List<Position> reversePath = F_4.findPath(C_4);

        assertThat(path).containsOnly(D_4, E_4);
        assertThat(reversePath).containsOnly(D_4, E_4);
    }

    @Test
    void 룩이_파일로_움직일_수_있는_경로를_알_수_있다() {
        List<Position> path = C_4.findPath(C_7);
        List<Position> reversePath = C_7.findPath(C_4);

        assertThat(path).containsOnly(C_5, C_6);
        assertThat(reversePath).containsOnly(C_5, C_6);
    }
}
