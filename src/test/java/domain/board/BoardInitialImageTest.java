package domain.board;

import domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardInitialImageTest {

    @Test
    @DisplayName("항상 다른 참조 값을 가진 객체를 반환한다")
    void getSquareByCoordinate() {
        Square firstSquare = BoardInitialImage.getSquareByCoordinate(0, 0);
        Square secondSquare = BoardInitialImage.getSquareByCoordinate(0 , 0);

        assertThat(firstSquare == secondSquare).isFalse();
    }
}
