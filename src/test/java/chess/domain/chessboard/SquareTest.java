package chess.domain.chessboard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SquareTest {

    @Test
    void 스퀘어는_비어있는_상태를_가질_수_있다(){
        Square square = new Square();

        Assertions.assertThat(square.isEmpty()).isTrue();
    }
}
