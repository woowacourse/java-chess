package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PiecesTest {

    @DisplayName("King의 갯수가 2개 미만이면 true를 반환한다.")
    @Test
    void hasLessThanTotalKingCount_true() {
        Pieces pieces = new Pieces(List.of(new King(WHITE, Position.of("e1"))));

        boolean actual = pieces.hasLessThanTotalKingCount();

        assertThat(actual).isTrue();
    }

    @DisplayName("King의 갯수가 2개면 false를 반환한다.")
    @Test
    void hasLessThanTotalKingCount_false() {
        Pieces pieces = new Pieces(List.of(new King(WHITE, Position.of("e1")),
            new King(BLACK, Position.of("e8"))));

        boolean actual = pieces.hasLessThanTotalKingCount();

        assertThat(actual).isFalse();
    }

    @DisplayName("positions리스트에 해당하는 piece가 있으면 true를 반환한다.")
    @Test
    void isAnyPieceExistInPositions_true() {
        List<Position> positions = List.of(Position.of("a1"), Position.of("e1"));
        Pieces pieces = new Pieces(List.of(new King(WHITE, Position.of("e1"))));

        boolean actual = pieces.isAnyPieceExistInPositions(positions);

        assertThat(actual).isTrue();
    }

    @DisplayName("positions리스트에 해당하는 piece가 없으면 false를 반환한다.")
    @Test
    void isAnyPieceExistInPositions_false() {
        List<Position> positions = List.of(Position.of("a1"), Position.of("b1"));
        Pieces pieces = new Pieces(List.of(new King(WHITE, Position.of("e1"))));

        boolean actual = pieces.isAnyPieceExistInPositions(positions);

        assertThat(actual).isFalse();
    }

    @DisplayName("가지고 있는 piece들을 리스트로 반환한다.")
    @Test
    void getPieces() {
        Pieces pieces = new Pieces(List.of(new King(WHITE, Position.of("e1"))));

        List<Piece> actual = pieces.getPieces();
        List<Piece> expected =  List.of(new King(WHITE, Position.of("e1")));

        assertThat(actual).isEqualTo(expected);
    }

}
