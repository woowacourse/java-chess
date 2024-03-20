package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.PieceColor;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@DisplayName("나이트")
class KnightTest {

    @Test
    @DisplayName("에 대한 이동 루트가 한칸 전진 후 대각선 한칸 전진 하는지 판단한다.")
    void canMove() {
        Board board = new Board(Map.of());
        Knight knight = new Knight(PieceColor.BLACK);

        boolean actual = knight.canMove(Position.from("c6"), Position.from("a7"), board);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("이동 경로에 기물이 존재하여도 뛰어넘어 갈 수 있다.")
    void canMoveIfPieceExistsOnPath() {
        Board board = new Board(Map.of(Position.from("c5"), new Rook(PieceColor.BLACK)));
        Knight knight = new Knight(PieceColor.BLACK);

        boolean actual = knight.canMove(Position.from("c6"), Position.from("b4"), board);

        assertThat(actual).isTrue();
    }
}
