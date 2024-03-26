package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    @DisplayName("상하좌우로 한 칸 움직일 수 있다.")
    void should_move_up_down_left_right() {
        Piece piece = new King(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.a, Rank.TWO);

        assertThat(piece.canMove(from, to)).isTrue();
    }

    @Test
    @DisplayName("대각선 방향으로 한 칸 움직일 수 있다")
    void should_move_diagonal() {
        Piece piece = new King(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.b, Rank.TWO);

        assertThat(piece.canMove(from, to)).isTrue();
    }

    @Test
    @DisplayName("두 칸 이상 움직일 수 없다")
    void should_not_move_more_than_two_space() {
        Piece piece = new King(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.a, Rank.THREE);

        assertThat(piece.canMove(from, to)).isFalse();
    }
}
