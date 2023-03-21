package chess.domain;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.position.File;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessGameTest {

    @Test
    @DisplayName("기물이 가로막고 있으면 통과 할 수 없다.")
    void moveBlockTest() {
        ChessBoard chessBoard = ChessBoardMaker.create();
        ChessGame chessGame = new ChessGame(chessBoard, Camp.WHITE);

        Position whiteRookPosition = Position.of(File.A, Rank.ONE);
        Position toPosition = Position.of(File.A, Rank.THREE);

        assertThatThrownBy(() -> chessGame.move(whiteRookPosition, toPosition, new Path()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("나이트는 기물의 유무와 관계없이 이동할 수 있다.")
    void movePassKnightTest() {
        ChessBoard chessBoard = ChessBoardMaker.create();
        ChessGame chessGame = new ChessGame(chessBoard, Camp.WHITE);

        Position whiteKnightPosition = Position.of(File.B, Rank.ONE);
        Position toPosition = Position.of(File.A, Rank.THREE);

        assertDoesNotThrow(() -> chessGame.move(whiteKnightPosition, toPosition, new Path()));
    }

    @Test
    @DisplayName("빈칸을 이동 시킬 수 없다")
    void moveEmptyExceptionTest() {
        ChessBoard chessBoard = ChessBoardMaker.create();
        ChessGame chessGame = new ChessGame(chessBoard, Camp.WHITE);

        Position emptyPosition = Position.of(File.A, Rank.THREE);
        Position toPosition = Position.of(File.A, Rank.FOUR);

        assertThatThrownBy(() -> chessGame.move(emptyPosition, toPosition, new Path()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("위치로 기물을 이동할 수 있다.")
    void movePieceOn() {
        Position whitePawnPosition = Position.of(File.A, Rank.TWO);
        Position emptyPosition = Position.of(File.A, Rank.FOUR);

        ChessBoard chessBoard = ChessBoardMaker.create();
        ChessGame chessGame = new ChessGame(chessBoard, Camp.WHITE);

        chessGame.move(whitePawnPosition, emptyPosition, new Path());

        assertThat(chessBoard.getBoard().get(whitePawnPosition)).isInstanceOf(Empty.class);
        assertThat(chessBoard.getBoard().get(emptyPosition)).isInstanceOf(Pawn.class);
    }
}
