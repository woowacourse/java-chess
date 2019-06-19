package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PawnTest {
    @Test
    void 직선_경로_내역() {
        Pawn pawn = new Pawn(Team.BLACK);
        List<Position> positions = Arrays.asList(new Position(1, 1), new Position(1, 2));

        assertThat(pawn.assemblePath(new Position(1, 1), new Position(1, 2)))
                .isEqualTo(positions);
    }

    @Test
    void 대각선_경로_내역() {

    }

    @Test
    void 최초로_두_칸_움직일_경우() {

    }

    @Test
    void 최초가_아닐_때_두_칸_움직이는_경우() {

    }

    @Test
    void 뒤로_갈_경우() {

    }




}
