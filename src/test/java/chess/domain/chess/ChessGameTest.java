package chess.domain.chess;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessGameTest {

    @ParameterizedTest(name = "첫 턴은 백진영의 폰과 나이트만 움직이는지 검증한다.")
    @CsvSource(value = {"6:0", "0:3"}, delimiter = ':')
    void playFirstTurnMovableFail(final int rank, final int file) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(rank, file);
        final Position target = new Position(5, 0);

        // when, then
        assertThatThrownBy(() -> chessGame.playFirstTurn(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처음은 백진영의 폰과 나이트만 움직일 수 있습니다.");
    }

    @ParameterizedTest(name = "첫 턴의 폰은 2칸 이내만 전진할 수 있는지 검증한다.")
    @ValueSource(ints = {4, 5, 0})
    void playFirstTurnMovePawnFail(final int rank) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(1, 0);
        final Position target = new Position(rank, 0);

        // when, then
        assertThatThrownBy(() -> chessGame.playFirstTurn(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 처음에 2칸 이내만 전진할 수 있습니다.");
    }

    @ParameterizedTest(name = "첫 턴의 나이트는 L자로 전진할 수 있는지 검증한다.")
    @CsvSource(value = {"2:1", "3:2", "2:3"}, delimiter = ':')
    void playFirstTurnMoveKnightFail(final int rank, final int file) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(0, 1);
        final Position target = new Position(rank, file);

        // when, then
        assertThatThrownBy(() -> chessGame.playFirstTurn(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 나이트의 이동 경로입니다.");
    }

    @ParameterizedTest(name = "첫 턴은 백진영의 폰과 나이트만 움직이는지 검증한다.")
    @CsvSource(value = {"1:3:2:3", "0:6:2:5"}, delimiter = ':')
    void playFirstTurnMovableSuccess(final int sourceRank, final int sourceFile,
                                     final int targetRank, final int targetFile) {
        // given
        final ChessGame chessGame = new ChessGame();
        final Position source = new Position(sourceRank, sourceFile);
        final Position target = new Position(targetRank, targetFile);
        final Piece piece = chessGame.getChessBoard().get(source);

        // when
        chessGame.playFirstTurn(source, target);

        // then
        assertThat(chessGame.getChessBoard().get(target))
                .isEqualTo(piece);
    }
}
