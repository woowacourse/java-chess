package chess.piece;

import static chess.model.File.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.model.File;
import chess.model.MoveType;
import chess.model.Path;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Rank;
import chess.model.piece.Bishop;
import chess.model.piece.Piece;

class BishopTest {

    @DisplayName("비숍은 오로지 대각선 방향으로만 이동이 가능하다")
    @ParameterizedTest
    @CsvSource(value = {"FOUR:D", "FIVE:E", "FOUR:B", "TWO:B"}, delimiter = ':')
    void isMovable(Rank rank, File file) {
        //given
        Piece bishop = Bishop.colorOf(PieceColor.WHITE);
        Position source = new Position(THREE, C);
        Position target = new Position(rank, file);

        //when
        boolean actual = bishop.isMovable(new Path(source, target), MoveType.EMPTY);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"THREE:D", "FOUR:E"}, delimiter = ':')
    @DisplayName("비숍은 대각선이 아니면 이동이 불가능하다")
    void cantMovable(Rank rank, File file) {
        //given
        Piece bishop = Bishop.colorOf(PieceColor.WHITE);
        Position source = new Position(THREE, C);
        Position target = new Position(rank, file);

        //when
        boolean actual = bishop.isMovable(new Path(source, target), MoveType.EMPTY);

        //then
        assertThat(actual).isFalse();
    }

}
