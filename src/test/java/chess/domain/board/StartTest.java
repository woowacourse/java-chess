package chess.domain.board;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.EMPTY;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class StartTest {

    @Test
    void 체스판을_초기화한다() {
        // given
        Board board = new Start();

        // when
        board = board.initialize();

        // then
        final Map<Position, Piece> result = board.getBoard();
        final List<PieceType> pieceTypes = Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .flatMap(file -> Arrays.stream(File.values()).map(rank -> Position.of(rank, file)))
                .map(result::get)
                .map(Piece::type)
                .collect(toList());
        assertThat(pieceTypes).containsExactly(
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK,
                PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK
        );
    }

    @Test
    void 보드가_초기화_되었는지_확인한다() {
        // given
        Board board = new Start();

        // when
        final boolean result = board.isInitialized();

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 게임이_종료_되었는지_확인한다() {
        // given
        Board board = new Start();

        // when
        final boolean result = board.isEnd();

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 초기화_되지_않은_상태에서_기물을_움직이려는_경우_예외를_던진다() {
        // given
        final Start start = new Start();

        // expect
        assertThatThrownBy(() -> start.move("b2", "b3"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    void 초기화_되지_않은_상태에서_점수를_확인하려는_경우_예외를_던진다() {
        // given
        final Start start = new Start();

        // expect
        assertThatThrownBy(() -> start.score(Color.WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }
}
