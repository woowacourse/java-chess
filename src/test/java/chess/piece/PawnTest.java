package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("첫 이동은 두 칸 움직일 수 있다.")
    void should_move_two_space_when_first_move() {
        Piece piece = new Pawn(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.b, Rank.THREE);

        assertThat(piece.canMove(from, to)).isTrue();
    }

    @Test
    @DisplayName("두번째 이동부터는 두 칸 움직일 수 없다.")
    void should_not_move_two_space_when_second_move() {
        Piece piece = new Pawn(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to1 = new Position(File.b, Rank.THREE);
        Position to2 = new Position(File.b, Rank.FIVE);
        piece.canMove(from, to1);

        assertThat(piece.canMove(from, to2)).isFalse();
    }
}
