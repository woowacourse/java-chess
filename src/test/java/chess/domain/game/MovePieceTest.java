package chess.domain.game;

import chess.domain.board.ChessBoardFactory;
import chess.domain.game.state.ChessGameState;
import chess.domain.game.state.InitializeGame;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.position.PiecePosition.of;
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
        ChessGameState step = new InitializeGame(new ChessBoardFactory().create());
        step = step.initialize();

        // when
        final ChessGameState next = step.movePiece(of("e2"), of("e4"));

        // then
        assertAll(
                () -> assertThatThrownBy(() ->
                        next.movePiece(of("e2"), of("e3"))
                ).isInstanceOf(IllegalArgumentException.class),

                () -> assertDoesNotThrow(() ->
                        next.movePiece(of("e7"), of("e6")))
        );
    }

    @Test
    void 왕을_죽인_경우_승자를_EndGame_에_전달한다() {
        // given
        ChessGameState step = new InitializeGame(new ChessBoardFactory().create());
        step = step.initialize();

        // when
        final ChessGameState next = 흰색_왕을_죽인다(step);

        // then
        assertThat(next.winColor()).isEqualTo(Color.BLACK);
    }

    private ChessGameState 흰색_왕을_죽인다(ChessGameState step) {
        step = step.movePiece(of("e2"), of("e4"));
        step = step.movePiece(of("d7"), of("d5"));
        step = step.movePiece(of("e1"), of("e2"));
        step = step.movePiece(of("d5"), of("d4"));
        step = step.movePiece(of("e2"), of("e3"));
        step = step.movePiece(of("d4"), of("e3"));
        return step;
    }

    @Test
    void 진행중인_상태에서_end_실행_시_무승부_상태로_EndGame_으로_넘어간다() {
        // given
        ChessGameState step = new InitializeGame(new ChessBoardFactory().create());
        step = step.initialize();

        // when
        final ChessGameState next = step.end();

        // then
        assertThat(next.winColor()).isEqualTo(Color.NONE);
    }
}
