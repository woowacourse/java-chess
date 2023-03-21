package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.PiecesPosition;
import chess.domain.piece.Camp;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningChessGameTest {

    private ChessGame runningGame;

    @BeforeEach
    void setRunningGame() {
        PiecesPosition piecesPosition = new PiecesPosition();
        runningGame = new RunningChessGame(piecesPosition, Camp.WHITE);
    }

    @Test
    @DisplayName("기물이 가로막고 있으면 통과 할 수 없다.")
    void moveBlockTest() {
        Position whiteRookPosition = Position.of(File.A, Rank.ONE);
        Position toPosition = Position.of(File.A, Rank.THREE);

        ChessGameCommand gameCommand = new ChessGameCommand(ChessCommandType.MOVE, whiteRookPosition, toPosition);
        assertThatThrownBy(() -> runningGame.playByCommand(gameCommand))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("나이트는 기물의 유무와 관계없이 이동할 수 있다.")
    void movePassKnightTest() {
        Position whiteKnightPosition = Position.of(File.B, Rank.ONE);
        Position toPosition = Position.of(File.A, Rank.THREE);

        ChessGameCommand gameCommand = new ChessGameCommand(ChessCommandType.MOVE, whiteKnightPosition, toPosition);
        assertDoesNotThrow(() -> runningGame.playByCommand(gameCommand));
    }

    @Test
    @DisplayName("빈칸을 이동 시킬 수 없다.")
    void moveEmptyExceptionTest() {
        Position emptyPosition = Position.of(File.A, Rank.THREE);
        Position toPosition = Position.of(File.A, Rank.FOUR);

        ChessGameCommand gameCommand = new ChessGameCommand(ChessCommandType.MOVE, emptyPosition, toPosition);

        assertThatThrownBy(() -> runningGame.playByCommand(gameCommand))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("동일한 위치로 이동 시킬 수 없다.")
    void moveEqualPositionTest() {
        Position toPosition = Position.of(File.A, Rank.FOUR);

        ChessGameCommand gameCommand = new ChessGameCommand(ChessCommandType.MOVE, toPosition, toPosition);
        assertThatThrownBy(() -> runningGame.playByCommand(gameCommand))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임의 현재 턴과 다른 진영의 기물을 선택할 수 없다.")
    void moveDifferentCampPieceAndTurnTest() {
        assertThat(runningGame).extracting("turnCamp")
                .isEqualTo(Camp.WHITE);

        Position blackPawnPosition = Position.of(File.A, Rank.SEVEN);
        Position toPosition = Position.of(File.A, Rank.SIX);
        ChessGameCommand gameCommand = new ChessGameCommand(ChessCommandType.MOVE, blackPawnPosition, toPosition);

        assertThatThrownBy(() -> runningGame.playByCommand(gameCommand))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임 실행 여부 확인.")
    void isRunnableGameTest() {
        assertThat(runningGame.isGameRunnable())
                .isTrue();
    }
}
