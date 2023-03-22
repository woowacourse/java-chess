package chess.domain.board.search;

import chess.helper.BoardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardSearchTest {

    @Test
    @DisplayName("isKingDead() : King 이 죽으면 true를 반환한다.")
    void test_isKingDead() throws Exception {
        //given
        final BoardSearch kingDeadBoardSearch = BoardSearch.countPiecePerClassTypeFrom(BoardFixture.createKingDeadBoard());
        final BoardSearch kingNotDeadBoardSearch = BoardSearch.countPiecePerClassTypeFrom(BoardFixture.createBoard());

        //when & then
        assertAll(
                () -> assertTrue(kingDeadBoardSearch.isKingDead()),
                () -> assertFalse(kingNotDeadBoardSearch.isKingDead())
        );
    }
}
