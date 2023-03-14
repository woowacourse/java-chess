package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LineTest {

    private static final Square BLACK_ROOK = new Square(ChessPiece.makeBlack(Piece.ROOK));
    private static final Square BLACK_KNIGHT = new Square(ChessPiece.makeBlack(Piece.KNIGHT));
    private static final Square BLACK_BISHOP = new Square(ChessPiece.makeBlack(Piece.BISHOP));
    private static final Square BLACK_QUEEN = new Square(ChessPiece.makeBlack(Piece.QUEEN));
    private static final Square BLACK_KING = new Square(ChessPiece.makeBlack(Piece.KING));
    private static final Square WHITE_ROOK = new Square(ChessPiece.makeWhite(Piece.ROOK));
    private static final Square WHITE_KNIGHT = new Square(ChessPiece.makeWhite(Piece.KNIGHT));
    private static final Square WHITE_BISHOP = new Square(ChessPiece.makeWhite(Piece.BISHOP));
    private static final Square WHITE_QUEEN = new Square(ChessPiece.makeWhite(Piece.QUEEN));
    private static final Square WHITE_KING = new Square(ChessPiece.makeWhite(Piece.KING));

    @Test
    @DisplayName("검은색 진영의 앞줄을 생성한다(폰 8개)")
    public void testBlackFront() {
        //given
        //when
        final Line line = Line.blackFront();

        //then
        assertThat(line).extracting("squares")
            .asList()
            .hasSize(8)
            .containsOnly(new Square(ChessPiece.makeBlack(Piece.PAWN)));
    }

    @Test
    @DisplayName("흰색 진영의 앞줄을 생성한다(폰 8개)")
    public void testWhiteFront() {
        //given
        //when
        final Line line = Line.whiteFront();

        //then
        assertThat(line).extracting("squares")
            .asList()
            .hasSize(8)
            .containsOnly(new Square(ChessPiece.makeWhite(Piece.PAWN)));
    }

    @Test
    @DisplayName("검은색 진영의 뒷줄을 생성한다")
    public void testBlackBack() {
        //given
        //when
        final Line line = Line.blackBack();

        //then
        assertThat(line).extracting("squares")
            .asList()
            .containsExactly(
                BLACK_ROOK,
                BLACK_KNIGHT,
                BLACK_BISHOP,
                BLACK_QUEEN,
                BLACK_KING,
                BLACK_BISHOP,
                BLACK_KNIGHT,
                BLACK_ROOK
            );
    }

    @Test
    @DisplayName("하얀색 진영의 뒷줄을 생성한다")
    public void testWhiteBack() {
        //given
        //when
        final Line line = Line.whiteBack();

        //then
        assertThat(line).extracting("squares")
            .asList()
            .containsExactly(
                WHITE_ROOK,
                WHITE_KNIGHT,
                WHITE_BISHOP,
                WHITE_QUEEN,
                WHITE_KING,
                WHITE_BISHOP,
                WHITE_KNIGHT,
                WHITE_ROOK
            );
    }

    @Test
    @DisplayName("빈 라인을 생성한다")
    public void testEmpty() {
        //given
        //when
        Line empty = Line.empty();
        //then
        Assertions.assertThat(empty).extracting("squares")
            .asList()
            .hasSize(8)
            .containsOnly(new Square(null));
    }
}
