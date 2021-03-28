package chess.domain.result;

import chess.domain.board.Coordinate;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ScoreTest {

    @DisplayName("Score 생성 테스트")
    @Test
    void getScore() {
        Score score = new Score(10.0);

        double scoreValue = score.getScore();

        assertThat(scoreValue).isEqualTo(10.0);
    }

    @DisplayName("Score는 음수값을 가질 수 없다.")
    @Test
    void cannotMakeScore() {
        assertThatCode(() -> new Score(-10.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수는 0 이상이어야 합니다.");
    }

    @DisplayName("Score는 폰을 제외한 기물들의 점수의 합 및 폰의 개수에 따른 점수를 계산하여 반환한다.")
    @Test
    void createScoreByPieces() {
        Map<Coordinate, Piece> pieces = new HashMap<>();
        pieces.put(Coordinate.from("d2"), new Pawn(TeamType.WHITE));
        pieces.put(Coordinate.from("d3"), new Pawn(TeamType.WHITE));
        pieces.put(Coordinate.from("d4"), new Pawn(TeamType.WHITE));
        pieces.put(Coordinate.from("c6"), new Rook(TeamType.WHITE));

        Score whiteTeamScore = Score.from(pieces);
        double score = whiteTeamScore.getScore();

        assertThat(score).isEqualTo(6.5);
    }

    @DisplayName("Score는 기물들의 점수를 합산할 때 다른 팀이 있으면 예외를 발생시킨다.")
    @Test
    void cannotCalculateScoreWhenOppositeTeamExists() {
        Map<Coordinate, Piece> pieces = new HashMap<>();
        pieces.put(Coordinate.from("d2"), new Pawn(TeamType.WHITE));
        pieces.put(Coordinate.from("d3"), new Pawn(TeamType.WHITE));
        pieces.put(Coordinate.from("d4"), new Pawn(TeamType.WHITE));
        pieces.put(Coordinate.from("c6"), new Rook(TeamType.BLACK));

        assertThatCode(() -> Score.from(pieces))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 팀의 기물이 포함되어 있어서 점수를 집계할 수 없습니다.");
    }
}
