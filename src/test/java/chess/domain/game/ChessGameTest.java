package chess.domain.game;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.EMPTY;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static chess.fixture.PositionFixture.E4;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ChessGameTest {

    private static List<PieceType> toPieceTypes(final Map<Position, Piece> board) {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .flatMap(file -> Arrays.stream(File.values()).map(rank -> Position.of(rank, file)))
                .map(board::get)
                .map(Piece::type)
                .collect(toList());
    }

    @Test
    void 체스_게임을_생성한다() {
        // given
        final ChessGame chessGame = new ChessGame();

        // when
        chessGame.initialize();

        // then
        final List<PieceType> result = toPieceTypes(chessGame.getResult().getBoard());
        assertThat(result).containsExactly(
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
    void 기물을_움직인다() {
        // given
        final ChessGame chessGame = new ChessGame();
        chessGame.initialize();

        // when
        chessGame.move("e2", "e4");

        // then
        final Map<Position, Piece> board = chessGame.getResult().getBoard();
        assertThat(board.get(E4)).isEqualTo(Pawn.from(Color.WHITE));
    }

    @Test
    void 루이로페즈_모던_슈타이니츠_바리에이션_으로_게임을_진행한다() {
        // given
        final ChessGame chessGame = new ChessGame();
        chessGame.initialize();

        // when
        chessGame.move("e2", "e4");
        chessGame.move("e7", "e5");
        chessGame.move("g1", "f3");
        chessGame.move("b8", "c6");
        chessGame.move("f1", "b5");
        chessGame.move("a7", "a6");
        chessGame.move("b5", "a4");
        chessGame.move("d7", "d6");

        // then
        final List<PieceType> result = toPieceTypes(chessGame.getResult().getBoard());
        assertThat(result).containsExactly(
                ROOK, EMPTY, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK,
                EMPTY, PAWN, PAWN, EMPTY, EMPTY, PAWN, PAWN, PAWN,
                PAWN, EMPTY, KNIGHT, PAWN, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, PAWN, EMPTY, EMPTY, EMPTY,
                BISHOP, EMPTY, EMPTY, EMPTY, PAWN, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, KNIGHT, EMPTY, EMPTY,
                PAWN, PAWN, PAWN, PAWN, EMPTY, PAWN, PAWN, PAWN,
                ROOK, KNIGHT, BISHOP, QUEEN, KING, EMPTY, EMPTY, ROOK
        );
    }

    @ParameterizedTest(name = "체스게임이 초기화 되었는지 확인한다. 초기화:{0}, 결과:{0}")
    @ValueSource(booleans = {true, false})
    void 체스게임이_초기화_되었는지_확인한다(final boolean initialize) {
        // given
        final ChessGame chessGame = new ChessGame();
        if (initialize) {
            chessGame.initialize();
        }

        // when
        final boolean result = chessGame.isInitialized();

        // then
        assertThat(result).isEqualTo(initialize);
    }
}
