package chess.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.chessboard.ChessBoard;
import chess.domain.command.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.position.Position;
import chess.domain.state.BlackRunning;
import chess.domain.state.Finish;
import chess.domain.state.Running;
import chess.domain.state.State;
import chess.domain.state.WhiteRunning;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    private final Map<Position, Piece> testBoard = new HashMap<>();

    @BeforeEach
    void init() {
        testBoard.put(Position.of("d4"), Piece.of(Color.WHITE, Symbol.KING));
        testBoard.put(Position.of("d5"), Piece.of(Color.WHITE, Symbol.PAWN));
        testBoard.put(Position.of("e3"), Piece.of(Color.BLACK, Symbol.PAWN));
        PiecesGenerator.fillEmptyPiece(testBoard);
    }

    @Test
    @DisplayName("start 명령어로 게임을 시작하면 흰색 턴이다.")
    void playGameByCommandWithStart() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        final State state = chessGame.playGameByCommand(GameCommand.of("start"));

        assertThat(state).isInstanceOf(WhiteRunning.class);
    }

    @Test
    @DisplayName("end 명령어로 게임을 종료한다.")
    void playGameByCommandWithEnd() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        final State state = chessGame.playGameByCommand(GameCommand.of("end"));

        assertThat(state).isInstanceOf(Finish.class);
    }

    @Test
    @DisplayName("move 명령어를 사용하면 턴이 바뀐다.")
    void playGameByCommandWithMove() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        chessGame.playGameByCommand(GameCommand.of("start"));

        final State state = chessGame.playGameByCommand(GameCommand.of("move", "d4", "e3"));

        assertThat(state).isInstanceOf(BlackRunning.class);

    }

    @Test
    @DisplayName("status 명령어를 입력하면 게임 진행 상태이다.")
    void playGameByCommandWithStatus() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        chessGame.playGameByCommand(GameCommand.of("start"));

        final State state = chessGame.playGameByCommand(GameCommand.of("status"));

        assertThat(state).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("킹이 없을 때, 게임이 종료됐는지 확인한다.")
    void isFinished() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        chessGame.playGameByCommand(GameCommand.of("start"));
        final boolean finished = chessGame.isFinished();

        assertThat(finished).isEqualTo(false);
    }


    @Test
    @DisplayName("점수를 계산한다.")
    void calculateScore() {
        final ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        final Map<Color, Double> scores = chessGame.calculateScore();
        final double actual = scores.get(Color.WHITE);

        assertThat(actual).isEqualTo(1.0);
    }
}
