package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Line;
import domain.board.Square;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LineTest {

    private static final List<Square> BLACK_BACK_LINE_SQUARE_ORDER = List.of(
        new Square(Rook.makeBlack()),
        new Square(Knight.makeBlack()),
        new Square(Bishop.makeBlack()),
        new Square(Queen.makeBlack()),
        new Square(King.makeBlack()),
        new Square(Bishop.makeBlack()),
        new Square(Knight.makeBlack()),
        new Square(Rook.makeBlack())
    );
    private static final List<Square> WHITE_BACK_LINE_SQUARE_ORDER = List.of(
        new Square(Rook.makeWhite()),
        new Square(Knight.makeWhite()),
        new Square(Bishop.makeWhite()),
        new Square(Queen.makeWhite()),
        new Square(King.makeWhite()),
        new Square(Bishop.makeWhite()),
        new Square(Knight.makeWhite()),
        new Square(Rook.makeWhite())
    );

    @Test
    @DisplayName("검은색 진영의 앞줄을 생성한다. (폰 8개)")
    public void testBlackFront() {
        //given
        //when
        final Line line = Line.blackFront();

        //then
        assertThat(line).extracting("squares")
            .asList()
            .hasSize(8)
            .containsOnly(new Square(Pawn.makeBlack()));
    }

    @Test
    @DisplayName("흰색 진영의 앞줄을 생성한다. (폰 8개)")
    public void testWhiteFront() {
        //given
        //when
        final Line line = Line.whiteFront();

        //then
        assertThat(line).extracting("squares")
            .asList()
            .hasSize(8)
            .containsOnly(new Square(Pawn.makeWhite()));
    }

    @Test
    @DisplayName("검은색 진영의 뒷줄을 생성한다.")
    public void testBlackBack() {
        //given
        //when
        final Line line = Line.blackBack();

        //then
        assertThat(line).extracting("squares")
            .asList()
            .containsExactlyElementsOf(BLACK_BACK_LINE_SQUARE_ORDER);
    }

    @Test
    @DisplayName("하얀색 진영의 뒷줄을 생성한다.")
    public void testWhiteBack() {
        //given
        //when
        final Line line = Line.whiteBack();

        //then
        assertThat(line).extracting("squares")
            .asList()
            .containsExactlyElementsOf(WHITE_BACK_LINE_SQUARE_ORDER);
    }

    @Test
    @DisplayName("빈 라인을 생성한다.")
    public void testEmpty() {
        //given
        //when
        Line empty = Line.empty();
        //then
        Assertions.assertThat(empty).extracting("squares")
            .asList()
            .hasSize(8)
            .containsOnly(Square.empty());
    }
}
