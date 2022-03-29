package chess.console.gamestate;

import static chess.domain.piece.Piece.createBlackPiece;
import static chess.domain.piece.Piece.createWhitePiece;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.single.King;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    private static ChessBoard createNewChessBoard() {
        return new ChessBoard(PieceFactory.createNewChessBoard());
    }

    @Test
    @DisplayName("현재 상태에서 진행 가능한 커맨드가 아니면 예외발생")
    void runException() {
        GameState gameState = new WhiteRunning(createNewChessBoard());
        assertThatThrownBy(() -> gameState.run("start"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 진행상태에서 불가능한 명령어입니다.");
    }

    @Test
    @DisplayName("move커맨드 입력 시 다른 색상 상태로 변경")
    void whiteRunningMove() {
        GameState gameState = new WhiteRunning(createNewChessBoard()).run("move a2 a4");
        assertThat(gameState).isInstanceOf(BlackRunning.class);
    }

    @Test
    @DisplayName("move커맨드 입력 시 다른 색상 상태로 변경")
    void BlackRunningMove() {
        GameState gameState = new BlackRunning(createNewChessBoard()).run("move a7 a5");
        assertThat(gameState).isInstanceOf(WhiteRunning.class);
    }

    @Test
    @DisplayName("end커맨드 입력 시 End 상태로 변경")
    void runToEnd() {
        GameState gameState = new WhiteRunning(createNewChessBoard()).run("end");
        assertThat(gameState).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("킹이 잡혀 게임 종료 시 End 상태로 변경")
    void gameEnd() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                Position.of('a', '1'), createWhitePiece(new King()),
                Position.of('a', '2'), createBlackPiece(new King())
        ));
        GameState gameState = new WhiteRunning(chessBoard);

        assertThat(gameState.run("move a1 a2")).isInstanceOf(End.class);
    }
}
