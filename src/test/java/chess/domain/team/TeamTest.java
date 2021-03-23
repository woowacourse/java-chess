package chess.domain.team;

import chess.domain.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TeamTest {
    @Test
    @DisplayName("블랙팀을 생성하면, 검은색 기물이 정상 배치된다.")
    void init_black_team() {
        final Team blackTeam = Team.blackTeam();
        final Map<Position, Piece> blackTeamPiecePosition = blackTeam.currentPiecePosition();

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
    @DisplayName("화이트팀을 생성하면, 하얀색 기물이 정상 배치된다.")
    void init_white_team() {
        final Team whiteTeam = Team.whiteTeam();
        final Map<Position, Piece> whiteTeamPiecePosition = whiteTeam.currentPiecePosition();

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
}
