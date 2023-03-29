package chess.domain.board;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.EMPTY;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static chess.fixture.PositionFixture.D1;
import static chess.fixture.PositionFixture.D2;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.E7;
import static chess.fixture.PositionFixture.E8;
import static chess.fixture.PositionFixture.F5;
import static chess.fixture.PositionFixture.F7;
import static chess.fixture.PositionFixture.G6;
import static chess.fixture.PositionFixture.G7;
import static chess.fixture.PositionFixture.H5;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
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
public class BoardTest {

    @Test
    void 체스판을_초기화한다() {
        // given
        Board board = new Board();

        // when
        board.initialize();

        // then
        final Map<Position, Piece> result = board.getResult().getBoard();
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
    void 보드가_초기화되지_않은_상태에서_이동_커맨드를_입력받는_경우_예외를_던진다() {
        // given
        Board board = new Board();

        // expect
        assertThatThrownBy(() -> board.move(D2, D5))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 진행할 수 있는 상태가 아닙니다.");
    }

    @Test
    void 왕이_잡힌_상태에서_이동_커맨드를_입력받는_경우_예외를_던진다() {
        // given
        final Board board = new Board();
        board.initialize();
        board.move(E2, E4);
        board.move(E7, E5);
        board.move(D1, H5);
        board.move(F7, F5);
        board.move(H5, E8);

        // expect
        assertThatThrownBy(() -> board.move(D2, D5))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 진행할 수 있는 상태가 아닙니다.");
    }

    @Test
    void 불가능한_이동_커맨드를_입력받는_경우_예외를_던진다() {
        // given
        Board board = new Board();
        board.initialize();

        // expect
        assertThatThrownBy(() -> board.move(D2, D5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 이동 명령어 입니다.");
    }

    @Test
    void 이동_가능한_커맨드를_입력받는_경우_기물을_이동한다() {
        // given
        Board board = new Board();
        board.initialize();

        // when
        board.move(E2, E4);

        // then
        final Map<Position, Piece> result = board.getResult().getBoard();
        assertThat(result.get(E4)).isEqualTo(Pawn.from(Color.WHITE));
    }

    @Test
    void 상대편의_기물을_이동하려는_경우_예외를_던진다() {
        // given
        Board board = new Board();
        board.initialize();

        // expect
        assertThatThrownBy(() -> board.move(G7, G6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 기물을 움직일 수 없습니다.");
    }

    @Test
    void 이동_경로에_기물이_있는_경우_예외를_던진다() {
        // given
        Board board = new Board();
        board.initialize();

        // expect
        assertThatThrownBy(() -> board.move(D1, D4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 기물이 있을 수 없습니다.");
    }

    @Test
    void 왕이_잡히는_경우_게임이_종료된다() {
        // given
        Board board = new Board();
        board.initialize();
        board.move(E2, E4);
        board.move(E7, E5);
        board.move(D1, H5);
        board.move(F7, F5);

        // when
        board.move(H5, E8);

        // then
        assertThat(board.isGameOver()).isTrue();
    }
}
