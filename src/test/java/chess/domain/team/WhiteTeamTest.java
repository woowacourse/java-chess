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
}
