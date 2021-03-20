package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardInitializer;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BoardInitializerTest {

    private final Map<Position, Piece> chessBoard = BoardInitializer.initializeBoard();

    @Test
    @DisplayName("폰 초기화 기능")
    void initiatePawn() {
        assertThat(chessBoard.get(new Position("a", "7")))
            .isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @DisplayName("룩 초기화 기능")
    @ValueSource(strings = {"a,1", "h,1", "a,8", "h,8"})
    void initiateRook(final String input) {
        final String[] inputs = input.split(",");
        assertThat(chessBoard.get(new Position(inputs[0], inputs[1])))
            .isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @DisplayName("킹 초기화 기능")
    @ValueSource(strings = {"e,1", "e,8"})
    void initiateKing(final String input) {
        final String[] inputs = input.split(",");
        assertThat(chessBoard.get(new Position(inputs[0], inputs[1])))
            .isInstanceOf(King.class);
    }

    @ParameterizedTest
    @DisplayName("퀸 초기화 기능")
    @ValueSource(strings = {"d,1", "d,8"})
    void initiateQueen(final String input) {
        final String[] inputs = input.split(",");
        assertThat(chessBoard.get(new Position(inputs[0], inputs[1])))
            .isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @DisplayName("비숍 초기화 기능")
    @ValueSource(strings = {"c,1", "c,8", "f,1", "f,8"})
    void initiateBishop(final String input) {
        final String[] inputs = input.split(",");
        assertThat(chessBoard.get(new Position(inputs[0], inputs[1])))
            .isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @DisplayName("나이트 초기화 기능")
    @ValueSource(strings = {"b,1", "b,8", "g,1", "g,8"})
    void initiateKnight(final String input) {
        final String[] inputs = input.split(",");
        assertThat(chessBoard.get(new Position(inputs[0], inputs[1])))
            .isInstanceOf(Knight.class);
    }
}