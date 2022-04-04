package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.InitialBoard;
import chess.domain.board.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @Test
    @DisplayName("start를 하면 보드가 초기화된다.")
    void start() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        assertThat(chessGame.getBoard().getValue()).isEqualTo(InitialBoard.initialize());
    }

    @Test
    @DisplayName("흰색말 부터 움직일 수 있다.")
    void move_white_turn() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(Coordinate.of("a2"), Coordinate.of("a4"));
        String team = chessGame.getBoard().getValue()
                .get(Coordinate.of("a4"))
                .getTeam();
        assertThat(team).isEqualTo("WHITE");
    }

    @Test
    @DisplayName("흰색 말이 움직인 후엔 검은색 말만 움직일 수 있다.")
    void move_black_turn() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(Coordinate.of("a2"), Coordinate.of("a4"));
        chessGame.move(Coordinate.of("a7"), Coordinate.of("a5"));
        String team = chessGame.getBoard()
                .getValue()
                .get(Coordinate.of("a5"))
                .getTeam();
        assertThat(team).isEqualTo("BLACK");
    }

    @Test
    @DisplayName("end를 하면 게임이 종료된다.")
    void end() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.end();

        assertThat(chessGame.isFinished()).isTrue();
    }
}
