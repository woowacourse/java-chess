package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ChessGame 은")
class ChessGameTest {

    @Test
    void 기물을_움직일_수_있다() {
        // given
        final ChessBoard chessBoard = new ChessBoardFactory().create();
        final ChessGame chessGame = ChessGame.start(chessBoard);

        // when & then
        assertDoesNotThrow(() ->
                chessGame.movePiece(PiecePosition.of("e2"),
                        PiecePosition.of("e4"))
        );
    }

    @Test
    void 움직임_이후_턴을_바꾼다() {
        // given
        final ChessBoard chessBoard = new ChessBoardFactory().create();
        final ChessGame chessGame = ChessGame.start(chessBoard);
        final Color before = chessGame.turnColor();

        // when
        final ChessGame after = chessGame.movePiece(PiecePosition.of("e2"), PiecePosition.of("e4"));

        // then
        assertThat(after.turnColor()).isNotEqualTo(before);
    }

    @Test
    void 왕이_죽으면_게임이_끝난다() {
        // given
        final ChessBoard chessBoard = new ChessBoardFactory().create();
        final ChessGame chessGame = ChessGame.start(chessBoard);

        // when
        final ChessGame after = 흰색_왕을_죽인다(chessGame);

        // then
        assertAll(
                () -> assertThat(after.state()).isEqualTo(GameState.END),
                () -> assertThat(after.winColor()).isEqualTo(Color.BLACK)
        );
    }

    private ChessGame 흰색_왕을_죽인다(final ChessGame chessGame) {
        return chessGame.movePiece(PiecePosition.of("e2"), PiecePosition.of("e4"))
                .movePiece(PiecePosition.of("d7"), PiecePosition.of("d5"))
                .movePiece(PiecePosition.of("e1"), PiecePosition.of("e2"))
                .movePiece(PiecePosition.of("d5"), PiecePosition.of("d4"))
                .movePiece(PiecePosition.of("e2"), PiecePosition.of("e3"))
                .movePiece(PiecePosition.of("d4"), PiecePosition.of("e3"));
    }

    @Test
    void 게임_종료_시_기물을_움직일_수_없다() {
        // given
        final ChessBoard chessBoard = new ChessBoardFactory().create();
        final ChessGame chessGame = ChessGame.start(chessBoard);
        final ChessGame after = 흰색_왕을_죽인다(chessGame);

        // when & then
        assertThatThrownBy(() ->
                after.movePiece(PiecePosition.of("e2"),
                        PiecePosition.of("e4"))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상대_기물을_잡으면_예외() {
        // given
        final ChessBoard chessBoard = new ChessBoardFactory().create();
        final ChessGame chessGame = ChessGame.start(chessBoard);

        // when & then
        assertThatThrownBy(() ->
                chessGame.movePiece(PiecePosition.of("e7"),
                        PiecePosition.of("e6"))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 게임이_진행중일_때_우승자를_물으면_예외() {
        // given
        final ChessBoard chessBoard = new ChessBoardFactory().create();
        final ChessGame chessGame = ChessGame.start(chessBoard);

        // when & then
        assertThatThrownBy(chessGame::winColor)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
