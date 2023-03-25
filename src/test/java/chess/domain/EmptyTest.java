package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Empty;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EmptyTest {

    @Test
    void 타입이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new Empty(Type.PAWN, Side.NEUTRALITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Empty의 타입이 잘못되었습니다.");
    }

    @Test
    void 진영이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new Empty(Type.EMPTY, Side.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Empty는 진영을 가질 수 없습니다.");
    }

    @Test
    void findMovablePositions을_호출하면_예외를_던진다() {
        final Empty empty = new Empty(Type.EMPTY, Side.NEUTRALITY);
        final Position position = Position.of(File.getFile(3), Rank.getRank(3));
        final Board board = BoardFactory.generateBoard();

        assertThatThrownBy(() -> empty.findMovablePositions(position, board))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("지원하지 않는 메서드입니다.");
    }
}
