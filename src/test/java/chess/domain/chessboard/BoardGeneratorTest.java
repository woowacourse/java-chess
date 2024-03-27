package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.ChessPiece;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BoardGeneratorTest {

    BoardGenerator boardGenerator = BoardGenerator.getInstance();

    @Test
    void _64개의_칸을_가진_보드를_생성한다() {
        //given, when
        Map<Square, ChessPiece> board = boardGenerator.generate();

        //then
        assertThat(board).hasSize(64);
    }

    @Test
    void 보드의_칸은_중복되지_않는다() {
        //given
        Map<Square, ChessPiece> board = boardGenerator.generate();

        //when
        int boardSize = board.size();
        Set<Square> squares = board.keySet();

        //then
        assertThat(squares).hasSize(boardSize);
    }

    @Test
    void 생성된_보드에_말은_32개가_올려져_있어야_한다() {
        //given
        Map<Square, ChessPiece> board = boardGenerator.generate();

        //when
        int chessPieceCount = (int) board.keySet().stream()
                .filter(square -> !board.get(square).isEmptyChessPiece())
                .count();

        //then
        assertThat(chessPieceCount).isEqualTo(32);
    }

    @Test
    void 생성된_보드에_말이_없는_칸은_32개이어야_한다() {
        //given
        Map<Square, ChessPiece> board = boardGenerator.generate();

        //when
        int chessPieceCount = (int) board.keySet().stream()
                .filter(square -> board.get(square).isEmptyChessPiece())
                .count();

        //then
        assertThat(chessPieceCount).isEqualTo(32);
    }
}
