package chess.domain.piece;

import static chess.domain.board.position.File.C;
import static chess.domain.board.position.Rank.THREE;
import static org.assertj.core.api.Assertions.*;

import chess.domain.board.position.Positions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.board.position.File;
import chess.constant.SquareType;
import chess.domain.board.position.Rank;

public class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"E:FIVE", "C:FIVE"}, delimiter = ':')
    @DisplayName("퀸은 상하좌우, 대각선 이동만 가능하다")
    void isMovable(File file, Rank rank) {

        //given
        Queen queen = new Queen(PieceTeam.WHITE);

        //when
        boolean actual = queen.isMovable(Positions.findPositionBy(C, THREE), Positions.findPositionBy(file, rank), SquareType.EMPTY);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"D:FIVE", "B:ONE"}, delimiter = ':')
    @DisplayName("퀸은 상하좌우, 대각선 이동이 아니면 false를 반환한다")
    void cantMovable(File file, Rank rank) {
        //given
        Queen queen = new Queen(PieceTeam.WHITE);

        //when
        boolean actual = queen.isMovable(Positions.findPositionBy(C, THREE), Positions.findPositionBy(file, rank), SquareType.EMPTY);

        //then
        assertThat(actual).isFalse();
    }
}
