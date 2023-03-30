package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Pawn;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PawnTest {

    @Test
    void 타입이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new Pawn(Type.EMPTY, Side.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰의 타입이 잘못되었습니다.");
    }

    @Test
    void 진영이_잘못되면_예외를_던진다() {
        assertThatThrownBy(() -> new Pawn(Type.PAWN, Side.NEUTRALITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 중립적인 기물이 아닙니다.");
    }

    @Test
    void 갈_수_있는_포지션들을_찾을_수_있다() {
        final Pawn pawn = new Pawn(Type.PAWN, Side.WHITE);
        final Board board = BoardFactory.generateBoard();
        final Position startPosition = Position.of(File.getFile(2), Rank.getRank(2));
        final Position upPosition = Position.of(File.getFile(2), Rank.getRank(3));
        final Position doubleUpPosition = Position.of(File.getFile(2), Rank.getRank(4));

        Assertions.assertThat(pawn.findMovablePositions(startPosition, board))
                .isEqualTo(new Path(List.of(upPosition, doubleUpPosition)));
    }
}
