package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountTest {

    Count count;

    @BeforeEach
    void setUp() {
        count = new Count();
    }

    @Test
    void 초기_상태_테스트() {
        assertThat(count.score(new Pawn(Team.BLACK))).isEqualTo(0);
    }

    @Test
    void 점수_계산_테스트() {
        count.add();
        count.add();
        count.add();

        assertThat(count.score(new Bishop(Team.BLACK))).isEqualTo(9);
    }
}