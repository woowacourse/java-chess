package chess.game;

import static chess.domain.piece.vo.TeamColor.WHITE;

import chess.domain.board.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalScoreTest {

    @Test
    @DisplayName("기물들의 총합 점수를 구한다.")
    void getTotalScore() {
        // given
        List<Piece> pieces = Arrays.asList(
                new Rook(WHITE, Position.from("a1")),
                new Bishop(WHITE, Position.from("b1")),
                new Knight(WHITE, Position.from("c1")),
                new Pawn(WHITE, Position.from("d1")),
                new Queen(WHITE, Position.from("f1")),
                new King(WHITE, Position.from("g1"))
        );
        // when
        double actual = TotalScore.getTotalScore(pieces);
        // then
        Assertions.assertThat(actual).isEqualTo(20.5);
    }

    @Test
    @DisplayName("같은 랭크에 2개의 Pawn이 있을 땐 Pawn 하나당 점수를 0.5점으로 총합 점수를 구한다.")
    void getTotalScoreWithSameRankPawn() {
        // given
        List<Piece> pieces = Arrays.asList(
                new Pawn(WHITE, Position.from("a1")),
                new Pawn(WHITE, Position.from("a2")),
                new Pawn(WHITE, Position.from("a3"))
        );
        // when
        double actual = TotalScore.getTotalScore(pieces);
        // then
        Assertions.assertThat(actual).isEqualTo(1.5);
    }
}
