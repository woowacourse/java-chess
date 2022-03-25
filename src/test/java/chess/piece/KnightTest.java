package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.File;
import chess.PieceColor;
import chess.Position;
import chess.Rank;

public class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"FIVE:D", "FIVE:B", "ONE:D", "ONE:B", "FOUR:A", "FOUR:E", "TWO:A", "TWO:E"}, delimiter = ':')
    @DisplayName("나이트는 앞 2칸, 옆 1칸 이동만 가능하다.")
    void isMovable(Rank rank, File file) {
        //given
        Knight knight = new Knight(PieceColor.WHITE);

        //when
        boolean actual = knight.isMovable(new Position(Rank.THREE, File.C), new Position(rank, file));

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"FIVE:C", "ONE:C", "FOUR:B", "THREE:E"}, delimiter = ':')
    @DisplayName("나이트는 이동불가능 한 곳으로 이동하면 false를 반환한다")
    void knight_cant_move(Rank rank, File file) {
        //given
        Knight knight = new Knight(PieceColor.WHITE);

        //when
        boolean actual = knight.isMovable(new Position(Rank.THREE, File.C), new Position(rank, file));

        //then
        assertThat(actual).isFalse();
    }
}
