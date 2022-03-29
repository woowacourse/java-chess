package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.model.File;
import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Rank;
import chess.model.piece.King;
import chess.model.piece.Piece;

public class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"THREE:B", "FOUR:B", "THREE:D", "THREE:B"}, delimiter = ':')
    @DisplayName("킹은 좌우, 대각선 방향으로 한칸만 이동이 가능하다")
    void isMovable(Rank rank, File file) {
        //given
        Piece king = King.colorOf(PieceColor.WHITE);

        //when
        boolean actual = king.isMovable(new Path(new Position(Rank.THREE, File.C), new Position(rank, file)),
            MoveType.EMPTY);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"FIVE:B", "ONE:B", "ONE:C"}, delimiter = ':')
    @DisplayName("킹은 좌우, 대각선 방향으로 한 칸 이동이 아니라면 이동이 불가하다")
    void cantMovable(Rank rank, File file) {
        //given
        Piece king = King.colorOf(PieceColor.WHITE);

        //when
        boolean actual = king.isMovable(new Path(new Position(Rank.THREE, File.C), new Position(rank, file)),
            MoveType.EMPTY);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isKing의 return 값이 true이다.")
    void isKing() {
        assertThat(King.colorOf(PieceColor.WHITE).isKing()).isTrue();
    }
}
