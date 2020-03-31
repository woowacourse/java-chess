package chess.domain;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {
    @Test
    @DisplayName("팀별로 말 이름을 잘 출력하는지 확인")
    void teamRepresentationDisplayTest() {
        Piece blackPawn = new Pawn(new Position("a2"), Team.BLACK);
        Piece whiteKnight = new Knight(new Position("d6"), Team.WHITE);
        assertThat(blackPawn.toString()).isEqualTo("P");
        assertThat(whiteKnight.toString()).isEqualTo("n");
    }
}
