package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.board.File;
import chess.constant.MoveType;
import chess.domain.piece.constant.PieceColor;
import chess.domain.board.Position;
import chess.domain.board.Rank;

public class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"THREE:B", "FOUR:B", "THREE:D", "THREE:B"}, delimiter = ':')
    @DisplayName("킹은 좌우, 대각선 방향으로 한칸만 이동이 가능하다")
    void isMovable(Rank rank, File file) {
        //given
        King king = new King(PieceColor.WHITE);

        //when
        boolean actual = king.isMovable(new Position(Rank.THREE, File.C), new Position(rank, file), MoveType.EMPTY);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"FIVE:B", "ONE:B", "ONE:C"}, delimiter = ':')
    @DisplayName("킹은 좌우, 대각선 방향으로 한 칸 이동이 아니라면 이동이 불가하다")
    void cantMovable(Rank rank, File file) {
        //given
        King king = new King(PieceColor.WHITE);

        //when
        boolean actual = king.isMovable(new Position(Rank.THREE, File.C), new Position(rank, file), MoveType.EMPTY);

        //then
        assertThat(actual).isFalse();
    }
}
