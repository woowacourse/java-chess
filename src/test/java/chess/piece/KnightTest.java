package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @Test
    @DisplayName("한칸 이동+한칸 대각선 이동을 할 수 있다(성공)")
    void should_move_one_straight_one_diagonal() {
        Piece piece = new Knight(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.b, Rank.THREE);

        assertThat(piece.canMove(from, to)).isTrue();
    }

    @Test
    @DisplayName("한칸 이동+한칸 대각선 이동을 할 수 있다(실패)")
    void should_not_move_not_one_straight_not_one_diagonal() {
        Piece piece = new Knight(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.b, Rank.TWO);

        assertThat(piece.canMove(from, to)).isFalse();
    }
}
