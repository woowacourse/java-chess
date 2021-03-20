package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhiteTeamTest {
    @Test
    @DisplayName("화이트팀을 생성하면, 하얀색 기물이 정상 배치된다.")
    void init() {
        final WhiteTeam whiteTeam = new WhiteTeam();
        final Map<Position, Piece> whiteTeamPiecePosition = whiteTeam.getPiecePosition();

        assertThat(whiteTeamPiecePosition.get(Position.of("a2"))).isInstanceOf(Pawn.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("b2"))).isInstanceOf(Pawn.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("c2"))).isInstanceOf(Pawn.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("d2"))).isInstanceOf(Pawn.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("e2"))).isInstanceOf(Pawn.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("f2"))).isInstanceOf(Pawn.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("g2"))).isInstanceOf(Pawn.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("h2"))).isInstanceOf(Pawn.class);

        assertThat(whiteTeamPiecePosition.get(Position.of("a1"))).isInstanceOf(Rook.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("b1"))).isInstanceOf(Knight.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("c1"))).isInstanceOf(Bishop.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("d1"))).isInstanceOf(Queen.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("e1"))).isInstanceOf(King.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("f1"))).isInstanceOf(Bishop.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("g1"))).isInstanceOf(Knight.class);
        assertThat(whiteTeamPiecePosition.get(Position.of("h1"))).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("화이트의 초기화 상태에서 점수 계산을 요청하면, 38.0점을 반환한다.")
    void calculate_score() {
        final WhiteTeam whiteTeam = new WhiteTeam();
        double whiteTeamScore = whiteTeam.calculateScore();
        assertThat(whiteTeamScore).isEqualTo(38.0);
    }

    @Test
    @DisplayName("화이트의 초기화 상태에서 점수 계산을 요청하면, 38.0점을 반환한다.")
    void calculate_score_pawn_same_y_axis() {
        final WhiteTeam whiteTeam = new WhiteTeam();
        whiteTeam.move(Position.of("b2"), Position.of("a3"));
        double whiteTeamScore = whiteTeam.calculateScore();
        assertThat(whiteTeamScore).isEqualTo(37.0);
    }
}
