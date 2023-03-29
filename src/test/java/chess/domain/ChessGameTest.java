package chess.domain;

import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessGameTest {

    @Test
    @DisplayName("체스 보드를 입력하면 체스 게임 객체가 생성된다.")
    void shouldSucceedToGenerateChessGame() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();

        assertDoesNotThrow(() -> new ChessGame(chessBoard));
    }

    @Test
    @DisplayName("게임 시작 후 블랙 피스를 먼저 움직이면 예외가 발생한다.")
    void shouldFailToMoveBlackPieceFirst() {
        ChessGame chessGame = new ChessGame(ChessBoard.GenerateChessBoard());
        chessGame.startGame();

        assertThatThrownBy(() -> chessGame.move(Position.findPosition("a7"), Position.findPosition("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대편 기물은 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("화이트 피스를 움직이면 해당 포지션의 피스가 정상적으로 변경된다.")
    void shouldSucceedToMoveWhitePieces() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Position sourcePosition = Position.findPosition("a2");
        Position targetPosition = Position.findPosition("a3");
        chessGame.startGame();

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.getChessBoard().get(targetPosition)).isEqualTo(new Pawn(Color.WHITE));
    }

    @Test
    @DisplayName("화이트 피스를 움직인 후 다시 화이트 피스를 움직이면 예외가 발생한다.")
    void shouldFailToMoveWhitePiecesContinuously() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Position sourcePosition = Position.findPosition("a2");
        Position targetPosition = Position.findPosition("a3");
        chessGame.startGame();

        chessGame.move(sourcePosition, targetPosition);

        assertThatThrownBy(() -> chessGame.move(Position.findPosition("b2"), Position.findPosition("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대편 기물은 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("화이트 피스를 움직인 후 블랙 피스를 움직일 경우 예외가 발생하지 않는다.")
    void shouldSucceedToMoveBlackPieceAfterMovingWhitePiece() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        Position sourcePosition = Position.findPosition("a2");
        Position targetPosition = Position.findPosition("a3");
        chessGame.startGame();

        chessGame.move(sourcePosition, targetPosition);

        assertDoesNotThrow(() -> chessGame.move(Position.findPosition("b7"), Position.findPosition("b6")));
    }
}
