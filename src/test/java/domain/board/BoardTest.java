package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @DisplayName("Board를 초기화해 생성할 수 있다.")
    @Test
    void createTest() {
        //given
        Board board = Board.create();
        final List<Position> rooksPosition = Position.of("A1", "A8", "H1", "H8");
        final List<Position> knightsPosition = Position.of("B1", "B8", "G1", "G8");
        final List<Position> bishopsPosition = Position.of("C1", "C8", "F1", "F8");
        final List<Position> pawnsPosition = Position.of("A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2",
                "A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7");
        final List<Position> kingsPosition = Position.of("E1", "E8");
        final List<Position> queensPosition = Position.of("D1", "D8");

        //when
        final Map<Position, Piece> pieces = board.getBoard();

        //then
        assertThat(rooksPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Rook.class));
        assertThat(knightsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Knight.class));
        assertThat(bishopsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Bishop.class));
        assertThat(pawnsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Pawn.class));
        assertThat(kingsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(King.class));
        assertThat(queensPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Queen.class));
    }
}
