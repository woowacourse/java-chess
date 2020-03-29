package chess.domain.chesspiece;

import chess.domain.move.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.game.Team.BLACK;
import static chess.domain.game.Team.WHITE;
import static chess.domain.move.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceTest {
    @Test
    @DisplayName("isSameTeam - 같은 팀일 경우")
    public void isSameTeam() {
        King king = new King(WHITE);
        assertThat(king.isSameTeam(WHITE)).isTrue();
    }

    @Test
    @DisplayName("isSameTeam - 다른 팀일 경우")
    public void isSameTeam_IfOtherTeam_ThrowException() {
        King king = new King(WHITE);
        assertThat(king.isSameTeam(BLACK)).isFalse();
    }

    @Test
    @DisplayName("getName 테스트")
    public void getName() {
        King king = new King(WHITE);
        assertThat(king.getName()).isEqualTo("k");
    }

    @Test
    @DisplayName("getMoveDirections 테스트")
    public void getMoveDirections() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.getMoveDirections()).isEqualTo(new Direction[]{UP, LEFT_UP, RIGHT_UP});
    }

    @Test
    @DisplayName("getTeam 테스트")
    public void getTeam() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.getTeam()).isEqualTo(WHITE);
    }

    @Test
    @DisplayName("getPoint 테스트")
    public void getPoint() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.getPoint()).isEqualTo(1);
    }
}
