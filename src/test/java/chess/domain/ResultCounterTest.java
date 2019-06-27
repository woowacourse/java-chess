package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCounterTest {
    private ResultCounter resultCounter;

    @BeforeEach
    void setUp() {
        resultCounter = ResultCounter.init();
    }

    @Test
    void 초기_생성_테스트() {
        Count pawnBlackCount = resultCounter.pieceCount(new Pawn(Team.BLACK));
        Count bishopWhiteCount = resultCounter.pieceCount(new Pawn(Team.WHITE));

        assertThat(pawnBlackCount).isEqualTo(new Count());
        assertThat(bishopWhiteCount).isEqualTo(new Count());
    }

    @Test
    void 카운터_1_추가_테스트() {
        Rook rook = new Rook(Team.BLACK);
//        resultCounter.addCount(rook);
//        resultCounter.addCount(rook);
        ResultCounter resultCounter = this.resultCounter.addCount(rook).addCount(rook);

        assertThat(resultCounter.pieceCount(rook)).isEqualTo(new Count().add().add());
    }

    @Test
    void 총_점수_블랙팀_계산_테스트() {
//        resultCounter.addCount(new Rook(Team.BLACK)); // 5
//        resultCounter.addCount(new Bishop(Team.WHITE)); // ignored
//        resultCounter.addCount(new Queen(Team.BLACK)); // 9
//        resultCounter.addCount(new Pawn(Team.WHITE)); // ignored
//        resultCounter.addCount(new Knight(Team.BLACK)); // 2.5


        ResultCounter resultCounter = this.resultCounter
                .addCount(new Rook(Team.BLACK)) // 5
                .addCount(new Bishop(Team.WHITE)) // ignored
                .addCount(new Queen(Team.BLACK)) // 9
                .addCount(new Pawn(Team.WHITE)) // ignored
                .addCount(new Knight(Team.BLACK)); // 2.5

        assertThat(resultCounter.totalScore(Team.BLACK)).isEqualTo(16.5);
    }

    @Test
    void 총_점수_화이트팀_계산_테스트() {
//        resultCounter.addCount(new Rook(Team.BLACK)); // ignored
//        resultCounter.addCount(new Bishop(Team.WHITE)); // 3
//        resultCounter.addCount(new Queen(Team.BLACK)); // ignored
//        resultCounter.addCount(new Pawn(Team.WHITE)); // 1
//        resultCounter.addCount(new Knight(Team.BLACK)); // ignored

        ResultCounter resultCounter = this.resultCounter
                .addCount(new Rook(Team.BLACK)) // ignored
                .addCount(new Bishop(Team.WHITE)) // 3
                .addCount(new Queen(Team.BLACK)) // ignored
                .addCount(new Pawn(Team.WHITE)) // 1
                .addCount(new Knight(Team.BLACK)); // ignored

        assertThat(resultCounter.totalScore(Team.WHITE)).isEqualTo(4);
    }
}
