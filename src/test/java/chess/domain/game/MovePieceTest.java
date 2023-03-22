package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.board.Turn;
import chess.domain.game.state.ChessGameState;
import chess.domain.game.state.MovePiece;
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
@DisplayName("MovePiece 은")
class MovePieceTest {

    @Test
    void 폰을_움직인_후_차례를_바꾼다() {
        // given
        final ChessBoard chessBoard = new ChessBoardFactory().create();
        final ChessGameState step = new MovePiece(new Turn(Color.WHITE));

        // when
        final ChessGameState next = step.movePiece(chessBoard, PiecePosition.of("e2"), PiecePosition.of("e4"));

        // then
        assertAll(
                () -> assertThatThrownBy(() ->
                        next.movePiece(chessBoard, PiecePosition.of("e2"), PiecePosition.of("e3"))
                ).isInstanceOf(IllegalArgumentException.class),

                () -> assertDoesNotThrow(() ->
                        next.movePiece(chessBoard, PiecePosition.of("e7"), PiecePosition.of("e6")))
        );
    }

    @Test
    void 왕을_죽인_경우_승자를_EndGame_에_전달한다() {
        // given
        final ChessBoard chessBoard = new ChessBoardFactory().create();
        final ChessGameState step = new MovePiece(new Turn(Color.WHITE));

        // when
        final ChessGameState next = 흰색_왕을_죽인다(chessBoard, step);

        // then
        assertThat(next.winColor()).isEqualTo(Color.BLACK);
    }

    private ChessGameState 흰색_왕을_죽인다(ChessBoard chessBoard, ChessGameState step) {
        step = step.movePiece(chessBoard, PiecePosition.of("e2"), PiecePosition.of("e4"));
        step = step.movePiece(chessBoard, PiecePosition.of("d7"), PiecePosition.of("d5"));
        step = step.movePiece(chessBoard, PiecePosition.of("e1"), PiecePosition.of("e2"));
        step = step.movePiece(chessBoard, PiecePosition.of("d5"), PiecePosition.of("d4"));
        step = step.movePiece(chessBoard, PiecePosition.of("e2"), PiecePosition.of("e3"));
        step = step.movePiece(chessBoard, PiecePosition.of("d4"), PiecePosition.of("e3"));
        return step;
    }

    @Test
    void 진행중인_상태에서_end_실행_시_무승부_상태로_EndGame_으로_넘어간다() {
        // given
        final ChessGameState step = new MovePiece(new Turn(Color.WHITE));

        // when
        final ChessGameState next = step.end();

        // then
        assertThat(next.winColor()).isEqualTo(Color.NONE);
    }
}
