package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.NoSuchElementException;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(Board::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("source 좌표에 기물이 없으면 기물을 움직일 수 없다.")
    @Test
    void noSource() {
        Board emptyBoard = new Board(new HashMap<>());
        Coordinate source = new Coordinate(2, 'a');
        Coordinate target = new Coordinate(3, 'b');

        assertThatThrownBy(() -> emptyBoard.move(source, target))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("기물이 갈 수 있는 곳이라면, 보드를 업데이트한다.")
    @Test
    void move() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Piece sourcePiece = new Pawn(Team.WHITE);
        Coordinate source = new Coordinate(2, 'a');
        Coordinate target = new Coordinate(4, 'a');
        pieces.put(source, sourcePiece);
        Board board = new Board(pieces);

        board.move(source, target);

        Piece result = board.findByCoordinate(target);
        assertThat(result).isEqualTo(sourcePiece);
    }

    @DisplayName("현재 턴에 해당하는 진영에 소속된 기물만 움직일 수 있다.")
    @Test
    void validateInvalidTurn() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        Piece sourcePiece = new Pawn(Team.WHITE);
        Coordinate source = new Coordinate(4, 'a');
        Coordinate middle = new Coordinate(5, 'a');
        Coordinate target = new Coordinate(6, 'a');
        pieces.put(source, sourcePiece);
        Board board = new Board(pieces);
        board.move(source, middle);

        assertThatThrownBy(() -> board.move(middle, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("상대방이 기물을 둘 차례입니다.");
    }
}
