package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathValidatorTest {

    private final PathValidator pathValidator = new PathValidator();

    @Test
    @DisplayName("경로 이동 가능 테스트")
    public void testValidate() {
        //given
        final List<Square> paths = List.of(Square.empty(), Square.empty());
        final Square start = new Square(Rook.makeBlack());

        //when
        //then
        assertDoesNotThrow(() -> pathValidator.validateNormal(start, paths));
    }

    @Test
    @DisplayName("마지막에 상대편 말이 있을 때 경로 이동 가능 테스트")
    public void testValidateWhenEnemyInDestination() {
        //given
        final List<Square> paths = List.of(Square.empty(), new Square(Rook.makeWhite()));
        final Square start = new Square(Rook.makeBlack());

        //when
        //then
        assertDoesNotThrow(() -> pathValidator.validateNormal(start, paths));
    }

    @Test
    @DisplayName("경로가 막혔을 때 이동 불가능 테스트")
    public void testNotValidate() {
        //given
        final List<Square> paths = List.of(Square.empty(), new Square(Rook.makeBlack()), Square.empty());
        final Square start = new Square(Rook.makeWhite());

        //when
        boolean result = pathValidator.validateNormal(start, paths);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("도착지가 막혔을 때 이동 불가능 테스트")
    public void testNotValidateWhenDestinationBlocked() {
        //given
        final List<Square> paths = List.of(Square.empty(), new Square(Rook.makeBlack()));
        final Square start = new Square(Rook.makeBlack());

        //when
        boolean result = pathValidator.validateNormal(start, paths);

        //then
        assertThat(result).isFalse();
    }
}
