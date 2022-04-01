package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.State.Finish;
import chess.domain.State.State;
import chess.domain.State.WhiteTurn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.fixedmovablepiece.King;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    private final Map<Position, Piece> testBoard = new HashMap<>();

    @BeforeEach
    void init() {
        testBoard.put(Position.of("d4"), new King(Color.WHITE));
        testBoard.put(Position.of("d5"), new WhitePawn());
        testBoard.put(Position.of("e3"), new BlackPawn());
    }

    @Test
    @DisplayName("start 명령어로 게임을 시작하면 흰색 턴이다.")
    void playGameByCommandWithStart() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        final State state = chessGame.playGameByCommand(new GameCommand("start"));

        assertThat(state).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("end 명령어로 게임을 종료한다.")
    void playGameByCommandWithEnd() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        final State state = chessGame.playGameByCommand(new GameCommand("end"));

        assertThat(state).isInstanceOf(Finish.class);
    }

    @Test
    @DisplayName("move 명령어를 입력하면 예외가 발생한다.")
    void playGameByCommandWithMove() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));

        assertThatThrownBy(() ->
                chessGame.playGameByCommand(new GameCommand("move", "b2", "b4")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("준비 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("status 명령어를 입력하면 예외가 발생한다.")
    void playGameByCommandWithStatus() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));

        assertThatThrownBy(() ->
                chessGame.playGameByCommand(new GameCommand("status")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("준비 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("킹이 없을 때, 게임이 종료됐는지 확인한다.")
    void isFinished() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        chessGame.playGameByCommand(new GameCommand("start"));
        final boolean finished = chessGame.isFinished();

        assertThat(finished).isEqualTo(false);
    }


    @Test
    @DisplayName("점수를 계산한다.")
    void calculateScore() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        final double score = chessGame.calculateScore(Color.WHITE);

        assertThat(score).isEqualTo(1.0);
    }
}
