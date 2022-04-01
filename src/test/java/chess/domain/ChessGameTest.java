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
        ChessBoardPosition sourcePosition = ChessBoardPosition.from("c1");
        ChessBoardPosition targetPosition = ChessBoardPosition.from("e3");

        assertThatThrownBy(() -> chessGame.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("우승자를 반환한다.")
    void judgeWinner() {
        ChessGame chessGame = ChessGame.create();
        chessGame.move(ChessBoardPosition.of('a', 2), ChessBoardPosition.of('a', 4));
        chessGame.move(ChessBoardPosition.of('f', 7), ChessBoardPosition.of('f', 6));

        chessGame.move(ChessBoardPosition.of('a', 4), ChessBoardPosition.of('a', 5));
        chessGame.move(ChessBoardPosition.of('f', 6), ChessBoardPosition.of('f', 5));

        chessGame.move(ChessBoardPosition.of('a', 5), ChessBoardPosition.of('a', 6));
        chessGame.move(ChessBoardPosition.of('f', 5), ChessBoardPosition.of('f', 4));

        chessGame.move(ChessBoardPosition.of('a', 6), ChessBoardPosition.of('b', 7));
        chessGame.move(ChessBoardPosition.of('f', 4), ChessBoardPosition.of('f', 3));

        chessGame.move(ChessBoardPosition.of('b', 7), ChessBoardPosition.of('c', 8));
        chessGame.move(ChessBoardPosition.of('h', 7), ChessBoardPosition.of('h', 6));
        assertThat(chessGame.judgeWinner()).isEqualTo(Team.WHITE);
    }
}
