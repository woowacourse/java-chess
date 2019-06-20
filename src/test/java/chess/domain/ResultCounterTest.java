package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCounterTest {

    @Test
    void 초기_생성_테스트() {
        Count pawnBlackCount = ResultCounter.pieceCount(new Pawn(Team.BLACK));
        Count bishopWhiteCount = ResultCounter.pieceCount(new Pawn(Team.WHITE));

        assertThat(pawnBlackCount).isEqualTo(new Count());
        assertThat(bishopWhiteCount).isEqualTo(new Count());
    }

    @Test
    void 카운터_1_추가_테스트() {
        Rook rook = new Rook(Team.BLACK);
        ResultCounter.addCount(rook);
        ResultCounter.addCount(rook);

        assertThat(ResultCounter.pieceCount(rook)).isEqualTo(new Count().add().add());
    }

    @Test
    void 총_점수_블랙팀_계산_테스트() {
        ResultCounter.addCount(new Rook(Team.BLACK)); // 5
        ResultCounter.addCount(new Bishop(Team.WHITE)); // ignored
        ResultCounter.addCount(new Queen(Team.BLACK)); // 9
        ResultCounter.addCount(new Pawn(Team.WHITE)); // ignored
        ResultCounter.addCount(new Knight(Team.BLACK)); // 2.5

        assertThat(ResultCounter.totalScore(Team.BLACK)).isEqualTo(16.5);

    }

    @Test
    void 총_점수_화이트팀_계산_테스트() {
        ResultCounter.addCount(new Rook(Team.BLACK)); // ignored
        ResultCounter.addCount(new Bishop(Team.WHITE)); // 3
        ResultCounter.addCount(new Queen(Team.BLACK)); // ignored
        ResultCounter.addCount(new Pawn(Team.WHITE)); // 1
        ResultCounter.addCount(new Knight(Team.BLACK)); // ignored

        assertThat(ResultCounter.totalScore(Team.WHITE)).isEqualTo(4);

    }
}
