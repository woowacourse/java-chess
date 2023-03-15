package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성하고, 체스판 말의 수가 32개인지 확인한다.")
    void create() {
        // given
        final ChessBoard chessBoard = new ChessBoard();

        // when
        final Map<Position, Piece> board = chessBoard.getBoard();

        // then
        assertThat(board.size())
                .isEqualTo(32);
    }

    @ParameterizedTest(name = "체스판의 해당 위치에 말이 존재하는지 확인한다.")
    @CsvSource(value = {"0:0:true", "3:5:false"}, delimiter = ':')
    void contains(final int rank, final int file, final boolean expected) {
        // given
        final ChessBoard chessBoard = new ChessBoard();

        // when, then
        assertThat(chessBoard.contains(new Position(rank, file)))
                .isSameAs(expected);
    }
}
