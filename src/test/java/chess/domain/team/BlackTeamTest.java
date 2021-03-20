package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackTeamTest {
    @Test
    @DisplayName("블랙팀을 생성하면, 검은색 기물이 정상 배치된다.")
    void init() {
        final BlackTeam blackTeam = new BlackTeam();
        final Map<Position, Piece> blackTeamPiecePosition = blackTeam.getPiecePosition();

        assertThat(blackTeamPiecePosition.get(Position.of("a7"))).isInstanceOf(Pawn.class);
        assertThat(blackTeamPiecePosition.get(Position.of("b7"))).isInstanceOf(Pawn.class);
        assertThat(blackTeamPiecePosition.get(Position.of("c7"))).isInstanceOf(Pawn.class);
        assertThat(blackTeamPiecePosition.get(Position.of("d7"))).isInstanceOf(Pawn.class);
        assertThat(blackTeamPiecePosition.get(Position.of("e7"))).isInstanceOf(Pawn.class);
        assertThat(blackTeamPiecePosition.get(Position.of("f7"))).isInstanceOf(Pawn.class);
        assertThat(blackTeamPiecePosition.get(Position.of("g7"))).isInstanceOf(Pawn.class);
        assertThat(blackTeamPiecePosition.get(Position.of("h7"))).isInstanceOf(Pawn.class);

        assertThat(blackTeamPiecePosition.get(Position.of("a8"))).isInstanceOf(Rook.class);
        assertThat(blackTeamPiecePosition.get(Position.of("b8"))).isInstanceOf(Knight.class);
        assertThat(blackTeamPiecePosition.get(Position.of("c8"))).isInstanceOf(Bishop.class);
        assertThat(blackTeamPiecePosition.get(Position.of("d8"))).isInstanceOf(Queen.class);
        assertThat(blackTeamPiecePosition.get(Position.of("e8"))).isInstanceOf(King.class);
        assertThat(blackTeamPiecePosition.get(Position.of("f8"))).isInstanceOf(Bishop.class);
        assertThat(blackTeamPiecePosition.get(Position.of("g8"))).isInstanceOf(Knight.class);
        assertThat(blackTeamPiecePosition.get(Position.of("h8"))).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("블랙팀의 초기화 상태에서 점수 계산을 요청하면, 38.0점을 반환한다.")
    void calculate_score() {
        final BlackTeam blackTeam = new BlackTeam();
        double blackTeamScore = blackTeam.calculateScore();
        assertThat(blackTeamScore).isEqualTo(38.0);
    }

    @Test
    @DisplayName("블랙팀의 초기화 상태에서 점수 계산을 요청하면, 38.0점을 반환한다.")
    void calculate_score_pawn_same_y_axis() {
        final BlackTeam blackTeam = new BlackTeam();
        blackTeam.move(Position.of("b7"), Position.of("a6"));
        double blackTeamScore = blackTeam.calculateScore();
        assertThat(blackTeamScore).isEqualTo(37.0);
    }
}
