package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    @DisplayName("상하좌우로 칸 수 제한 없이 움직일 수 있다.")
    void should_move_up_down_left_right_unlimited() {
        Piece piece = new Queen(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.a, Rank.THREE);

        assertThat(piece.canMove(from, to)).isTrue();
    }

    @Test
    @DisplayName("대각선 방향으로 칸 수 제한 없이 움직일 수 있다")
    void should_move_diagonal_unlimited() {
        Piece piece = new Queen(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.c, Rank.THREE);

        assertThat(piece.canMove(from, to)).isTrue();
    }

    @Test
    @DisplayName("상하좌우와 대각선외에는 움직일 수 없다")
    void should_not_move_not_upDownLeftRight_not_diagonal() {
        Piece piece = new Queen(Color.WHITE);
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.b, Rank.THREE);

        assertThat(piece.canMove(from, to)).isFalse();
    }
}
