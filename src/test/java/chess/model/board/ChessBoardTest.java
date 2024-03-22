package chess.model.board;

import chess.model.piece.Blank;
import chess.model.piece.Piece;
import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        ChessBoardInitializer chessBoardInitializer = new ChessBoardInitializer();
        chessBoard = new ChessBoard(chessBoardInitializer.create());
    }

    @Test
    @DisplayName("Source 위치의 기물을 Target 위치로 이동한다.")
    void move() {
        ChessPosition source = ChessPosition.of(File.B, Rank.TWO);
        ChessPosition target = ChessPosition.of(File.B, Rank.FOUR);
        chessBoard.move(source, target);

        Map<ChessPosition, Piece> board = chessBoard.getBoard();

        assertThat(board).containsEntry(source, Blank.INSTANCE);
        assertThat(board).doesNotContainEntry(target, Blank.INSTANCE);
    }

    @Test
    @DisplayName("Source 위치에 기물이 없으면 예외가 발생한다.")
    void moveNullSource() {
        // given
        ChessPosition source = ChessPosition.of(File.H, Rank.SEVEN);
        ChessPosition target = ChessPosition.of(File.D, Rank.TWO);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경로가 비어있다면 예외가 발생한다.")
    void moveWhenPathEmpty() {
        // given
        ChessPosition source = ChessPosition.of(File.A, Rank.ONE);
        ChessPosition target = ChessPosition.of(File.D, Rank.TWO);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 경로에 기물이 존재한다면 예외가 발생한다.")
    void moveWhenPathContainsPiece() {
        // given
        ChessPosition source = ChessPosition.of(File.A, Rank.ONE);
        ChessPosition target = ChessPosition.of(File.A, Rank.SIX);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
