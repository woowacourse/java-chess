package chess.model.board;

import static chess.model.Fixture.A1;
import static chess.model.Fixture.A6;
import static chess.model.Fixture.B2;
import static chess.model.Fixture.B4;
import static chess.model.Fixture.C5;
import static chess.model.Fixture.D2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.piece.Piece;
import chess.model.position.ChessPosition;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard(ChessBoardInitializer.create());
    }

    @Test
    @DisplayName("Source 위치의 기물을 Target 위치로 이동한다.")
    void move() {
        //given
        ChessPosition source = B2;
        ChessPosition target = B4;

        //when
        chessBoard.move(source, target);
        Map<ChessPosition, Piece> board = chessBoard.getBoard();
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        //then
        assertAll(
                () -> assertThat(sourcePiece.isEmpty()).isTrue(),
                () -> assertThat(targetPiece.isEmpty()).isFalse()
        );
    }

    @Test
    @DisplayName("Source 위치에 기물이 없으면 예외가 발생한다.")
    void moveEmptySource() {
        //when //then
        assertThatThrownBy(() -> chessBoard.move(C5, D2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 경로에 기물이 존재한다면 예외가 발생한다.")
    void moveWhenPathContainsPiece() {
        //when //then
        assertThatThrownBy(() -> chessBoard.move(A1, A6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
