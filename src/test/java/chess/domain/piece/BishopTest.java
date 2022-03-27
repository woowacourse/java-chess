package chess.domain.piece;

import static chess.domain.board.File.*;
import static chess.domain.board.Rank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.board.File;
import chess.constant.MoveType;
import chess.domain.piece.constant.PieceColor;
import chess.domain.board.Position;
import chess.domain.board.Rank;

class BishopTest {

    @DisplayName("비숍은 오로지 대각선 방향으로만 이동이 가능하다")
    @ParameterizedTest
    @CsvSource(value = {"D:FOUR", "E:FIVE", "B:FOUR", "B:TWO"}, delimiter = ':')
    void isMovable(File file, Rank rank) {
        //given
        Bishop bishop = new Bishop(PieceColor.WHITE);
        Position source = new Position(C, THREE);
        Position target = new Position(file, rank);

        //when
        boolean actual = bishop.isMovable(source, target, MoveType.EMPTY);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"D:THREE", "E:FOUR"}, delimiter = ':')
    @DisplayName("비숍은 대각선이 아니면 이동이 불가능하다")
    void cantMovable(File file, Rank rank) {
        //given
        Bishop bishop = new Bishop(PieceColor.WHITE);
        Position source = new Position(C, THREE);
        Position target = new Position(file, rank);

        //when
        boolean actual = bishop.isMovable(source, target, MoveType.EMPTY);

        //then
        assertThat(actual).isFalse();
    }
}
