package chess.model.board;

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
        chessBoard = new ChessBoard(ChessBoardInitializer.create());
    }

    @Test
    @DisplayName("Source 위치의 기물을 Target 위치로 이동한다.")
    void move() {
        ChessPosition source = new ChessPosition(File.B, Rank.TWO);
        ChessPosition target = new ChessPosition(File.B, Rank.FOUR);
        chessBoard.move(source, target);

        Map<ChessPosition, Piece> board = chessBoard.getBoard();

        assertThat(board.get(source)).isNull();
        assertThat(board.get(target)).isNotNull();
    }

    @Test
    @DisplayName("Source 위치에 기물이 없으면 예외가 발생한다.")
    void moveNullSource() {
        ChessPosition source = null;
        ChessPosition target = new ChessPosition(File.D, Rank.TWO);
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("경로가 비어있다면 예외가 발생한다.")
    void moveWhenPathEmpty() {
        ChessPosition source = new ChessPosition(File.A, Rank.ONE);
        ChessPosition target = new ChessPosition(File.D, Rank.TWO);
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 경로에 기물이 존재한다면 예외가 발생한다.")
    void moveWhenPathContainsPiece() {
        ChessPosition source = new ChessPosition(File.A, Rank.ONE);
        ChessPosition target = new ChessPosition(File.A, Rank.SIX);
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
