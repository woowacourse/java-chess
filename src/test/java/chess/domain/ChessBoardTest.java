package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    List<Character> aToH = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g');

    @Test
    void 초기_체스판_폰위치_확인() {
        for (char chr : aToH) {
            assertThat(Board.at(new Position(new Coordinate(chr), new Coordinate(2))))
                    .isEqualTo(new Pawn());
            assertThat(Board.at(new Position(new Coordinate(chr), new Coordinate(7))))
                    .isEqualTo(new Pawn());
        }
    }

    @Test
    void 초기_체스판_폰_이외의_말_위치_확인() {
        List<Object> pieces = Arrays.asList(new Rook(), new Knight(), new Bishop(), new Queen(),
                new King(), new Bishop(), new Knight(), new Rook());
        for (int i = 0; i < aToH.size(); i++) {
            assertThat(Board.at(new Position(new Coordinate(aToH.get(i)), new Coordinate(1))))
                    .isEqualTo(pieces.get(i));
            assertThat(Board.at(new Position(new Coordinate(aToH.get(i)), new Coordinate(8))))
                    .isEqualTo(pieces.get(i));
        }
    }
}
