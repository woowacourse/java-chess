package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @DisplayName("Piece는 적군을 kill하면 해당 위치로 이동한다.")
    @Test
    void attack_likeMove() {
        Piece piece = new Queen(Color.WHITE, Position.of("d1"));
        Position d2 = Position.of("d2");
        Piece enemy = new Queen(Color.BLACK, d2);

        piece.kill(enemy);

        assertThat(piece.getPosition()).isEqualTo(d2);
    }

    @DisplayName("Piece는 아군을 kill하면 예외가 발생한다.")
    @Test
    void killFriendly_exception() {
        Piece piece = new Queen(Color.WHITE, Position.of("d1"));
        Piece targetPiece = new Queen(Color.WHITE, Position.of("d2"));

        assertThatCode(() -> piece.kill(targetPiece))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이동하려는 위치에 아군이 있습니다.");
    }

    @DisplayName("흑색 piece는 getColor시 black을 보여준다.")
    @Test
    void getColor_black() {
        Piece piece = new Queen(Color.BLACK, Position.of("d1"));

        Color actual = piece.getColor();
        Color expected = Color.BLACK;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("백색 piece는 getColor시 black을 보여준다.")
    @Test
    void getColor_white() {
        Piece piece = new Queen(Color.WHITE, Position.of("d1"));

        Color actual = piece.getColor();
        Color expected = Color.WHITE;

        assertThat(actual).isEqualTo(expected);
    }

}
