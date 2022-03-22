package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("createInitializedBoard 메소드를 호출하여 말이 초기화된 Board 를 생성할 수 있다.")
    @Test
    void constructor() {
        // given
        Board board = Board.createInitializedBoard();

        // when & then
        assertThat(board).isNotNull();
    }

    @DisplayName("말의 위치로 보드의 말을 받아올 수 있다.")
    @Test
    void findPieceByPosition() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        Optional<Piece> actual = board.find(Position.from(XAxis.A, YAxis.TWO));
        Class<Pawn> expected = Pawn.class;

        // when & then
        assertThat(actual.get()).isExactlyInstanceOf(expected);
    }

    @DisplayName("전달된 위치에 말이 없는 경우 빈 옵셔널을 반환한다.")
    @Test
    void findPieceByPosition_returnsEmptyOptionalOnEmpty() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        Optional<Piece> actual = board.find(Position.from(XAxis.A, YAxis.THREE));
        Optional<Piece> expected = Optional.empty();

        // when & then
        assertThat(actual).isEqualTo(expected);
    }
}