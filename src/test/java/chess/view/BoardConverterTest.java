package chess.view;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.output.BoardConverter;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BoardConverterTest {

    @Test
    void 입력받은_보드를_문자열로_변환한다() {
        // given
        final Map<Position, Piece> board = BoardGenerator.generate();
        final String nextLine = System.lineSeparator();

        // when
        final String result = BoardConverter.convert(board);

        // then
        assertThat(result).isEqualTo(
                "RNBQKBNR" + nextLine
                        + "PPPPPPPP" + nextLine
                        + "........" + nextLine
                        + "........" + nextLine
                        + "........" + nextLine
                        + "........" + nextLine
                        + "pppppppp" + nextLine
                        + "rnbqkbnr" + nextLine
        );
    }
}
