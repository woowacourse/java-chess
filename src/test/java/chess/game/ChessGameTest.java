package chess.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.board.Board;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.controller.GameStatus;
import chess.piece.AllPiecesGenerator;
import chess.piece.Pieces;
import chess.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("흰색 진영이 기물을 옮길 차례일 때, 검은색 진영의 기물을 옮기면 예외가 발생한다.")
    void checkTurnToMove_whiteTurn_throws() {
        // given
        final ChessGame chessGame = new ChessGame(new Board(new Pieces(new AllPiecesGenerator())),
                Side.WHITE,
                GameStatus.START);
        final Position blackPiecePosition = new Position(File.A, Rank.SEVEN);
        final Position targetPosition = new Position(File.A, Rank.SIX);

        // when, then
        assertThatThrownBy(() -> chessGame.movePiece(blackPiecePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("검은색 진영이 기물을 옮길 차례일 때, 흰색 진영의 기물을 옮기면 예외가 발생한다.")
    void checkTurnToMove_blackTurn_throws() {
        // given
        final ChessGame chessGame = new ChessGame(new Board(new Pieces(new AllPiecesGenerator())),
                Side.BLACK,
                GameStatus.START);
        final Position whitePiecePosition = new Position(File.A, Rank.TWO);
        final Position targetPosition = new Position(File.A, Rank.THREE);

        // when, then
        assertThatThrownBy(() -> chessGame.movePiece(whitePiecePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대방의 말은 이동시킬 수 없습니다.");
    }
}
