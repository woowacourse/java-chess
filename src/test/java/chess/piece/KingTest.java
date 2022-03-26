package chess.piece;

import chess.*;
import chess.piece.King;
import chess.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    @DisplayName("target 위치로 움직일 수 있으면 false를 반환한다.")
    @Test
    void canMove_false() {
        Map<Position, Piece> board = new Board().getBoard();
        Piece king = board.get(new Position(Rank.EIGHT, File.E));
        Boolean actual = king.canMove(new Position(Rank.EIGHT, File.E), new Position(Rank.SEVEN, File.E), board);

        assertThat(actual).isFalse();
    }

    @DisplayName("target 위치로 움직일 수 있으면 true를 반환한다.")
    @ParameterizedTest()
    @CsvSource(value = {"SIX,D", "FOUR,D", "FIVE,C", "FIVE,E", "SIX,E", "FOUR,E", "SIX,C", "FOUR,C"})
    void canMove_true(Rank rank, File file) {
        Map<Position, Piece> board = new Board().getBoard();
        Piece king = new King(Player.BLACK, "K");
        Boolean actual = king.canMove(new Position(Rank.FIVE, File.D), new Position(rank, file), board);

        assertThat(actual).isTrue();
    }
}
