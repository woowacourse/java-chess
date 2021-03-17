package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @DisplayName("notation이 검은팀일 때 대문자, 하얀팀일 때 소문자로 출력되는지 확인")
    @Test
    void getNotationTest() {
        Pawn blackPawn = new Pawn(Color.BLACK, Position.of("a1"));
        Pawn whitePawn = new Pawn(Color.WHITE, Position.of("b1"));

        assertThat(blackPawn.getNotation()).isEqualTo("P");
        assertThat(whitePawn.getNotation()).isEqualTo("p");
    }
}
