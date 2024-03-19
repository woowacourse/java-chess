package chess.chessBoard;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BoardGeneratorTest {

    @Test
    void _64개의_칸을_가진_보드를_생성한다() {
        //given
        List<Square> board = BoardGenerator.generate();

        //when, then
        assertThat(board).hasSize(64);
    }

    @Test
    void 보드의_칸은_중복되지_않는다() {
        //given
        List<Square> board = BoardGenerator.generate();

        //when
        int boardSize = board.size();
        Set<Square> notDuplicatedBoard = new HashSet<>(board);

        //then
        assertThat(notDuplicatedBoard).hasSize(boardSize);
    }
}
