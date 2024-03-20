package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardFactoryTest {

    private static final Board BOARD = BoardFactory.startGame();

    @ParameterizedTest
    @CsvSource({
            "TWO, A", "TWO, B", "TWO, C", "TWO, D", "TWO, E", "TWO, F", "TWO, G", "TWO, H",
            "SEVEN, A", "SEVEN, B", "SEVEN, C", "SEVEN, D", "SEVEN, E", "SEVEN, F", "SEVEN, G", "SEVEN, H"
    })
    @DisplayName("폰이 초기 위치에 있다.")
    void pawnTest(Rank rank, File file) {
        Piece piece = BOARD.find(new Position(file, rank)).get();

        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @CsvSource({
            "ONE, B", "ONE, G", "EIGHT, B", "EIGHT, G"
    })
    @DisplayName("나이트가 초기 위치에 있다.")
    void knightTest(Rank rank, File file) {
        Piece piece = BOARD.find(new Position(file, rank)).get();

        assertThat(piece).isInstanceOf(Knight.class);
    }

    @ParameterizedTest
    @CsvSource({
            "ONE, C", "ONE, F", "EIGHT, C", "EIGHT, F"
    })
    @DisplayName("비숍이 초기 위치에 있다.")
    void BishopTest(Rank rank, File file) {
        Piece piece = BOARD.find(new Position(file, rank)).get();

        assertThat(piece).isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @CsvSource({
            "ONE, A", "ONE, H", "EIGHT, A", "EIGHT, H"
    })
    @DisplayName("륙이 초기 위치에 있다.")
    void rookTest(Rank rank, File file) {
        Piece piece = BOARD.find(new Position(file, rank)).get();

        assertThat(piece).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource({
            "ONE, D", "EIGHT, D"
    })
    @DisplayName("퀸이 초기 위치에 있다.")
    void queenTest(Rank rank, File file) {
        Piece piece = BOARD.find(new Position(file, rank)).get();

        assertThat(piece).isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @CsvSource({
            "ONE, E", "EIGHT, E"
    })
    @DisplayName("킹이 초기 위치에 있다.")
    void kingTest(Rank rank, File file) {
        Piece piece = BOARD.find(new Position(file, rank)).get();

        assertThat(piece).isInstanceOf(King.class);
    }
}
