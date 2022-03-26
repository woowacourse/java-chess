package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("경로에 체스피스가 있는 경우 움직일 수 없다.")
    void moveTest() {
        ChessGame chessGame = ChessGame.create();
        ChessBoardPosition sourcePosition = ChessBoardPosition.of("c1");
        ChessBoardPosition targetPosition = ChessBoardPosition.of("e3");

        assertThatThrownBy(() -> chessGame.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("우승자를 반환한다.")
    void judgeWinner() {
        ChessGame chessGame = ChessGame.create();
        chessGame.move(new ChessBoardPosition('f', 7), new ChessBoardPosition('f', 6));
        chessGame.move(new ChessBoardPosition('a', 2), new ChessBoardPosition('a', 4));

        chessGame.move(new ChessBoardPosition('f', 6), new ChessBoardPosition('f', 5));
        chessGame.move(new ChessBoardPosition('a', 4), new ChessBoardPosition('a', 5));

        chessGame.move(new ChessBoardPosition('f', 5), new ChessBoardPosition('f', 4));
        chessGame.move(new ChessBoardPosition('a', 5), new ChessBoardPosition('a', 6));

        chessGame.move(new ChessBoardPosition('f', 4), new ChessBoardPosition('f', 3));
        chessGame.move(new ChessBoardPosition('a', 6), new ChessBoardPosition('b', 7));

        chessGame.move(new ChessBoardPosition('h', 7), new ChessBoardPosition('h', 6));
        chessGame.move(new ChessBoardPosition('b', 7), new ChessBoardPosition('c', 8));
        assertThat(chessGame.judgeWinner()).isEqualTo(Team.BLACK);
    }
}
