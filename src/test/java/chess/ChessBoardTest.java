package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Shape;
import chess.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    ChessBoard chessBoard = ChessBoard.generateChessBoard();

    @Test
    @DisplayName("체스판 생성 후 룩은 올바른 위치에 만들어진다.")
    void shouldSuccessGenerateRook() {

        Assertions.assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(1, 1)).getShape())
                        .isEqualTo(Shape.ROOK),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(1, 8)).getShape())
                        .isEqualTo(Shape.ROOK),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(8, 1)).getShape())
                        .isEqualTo(Shape.ROOK),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(8, 8)).getShape())
                        .isEqualTo(Shape.ROOK)
        );
    }

    @Test
    @DisplayName("체스판 생성 후 킹은 올바른 위치에 만들어진다.")
    void shouldSuccessGenerateKing() {

        Assertions.assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(5, 1)).getShape())
                        .isEqualTo(Shape.KING),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(5, 8)).getShape())
                        .isEqualTo(Shape.KING)
        );
    }

    @Test
    @DisplayName("체스판 생성 후 퀸은 올바른 위치에 만들어진다.")
    void shouldSuccessGenerateQueen() {

        Assertions.assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(4, 1)).getShape())
                        .isEqualTo(Shape.QUEEN),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(4, 8)).getShape())
                        .isEqualTo(Shape.QUEEN)
        );
    }

    @Test
    @DisplayName("체스판 생성 후 나이트는 올바른 위치에 만들어진다.")
    void shouldSuccessGenerateKnight() {

        Assertions.assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(2, 1)).getShape())
                        .isEqualTo(Shape.KNIGHT),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(7, 1)).getShape())
                        .isEqualTo(Shape.KNIGHT),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(2, 8)).getShape())
                        .isEqualTo(Shape.KNIGHT),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(7, 8)).getShape())
                        .isEqualTo(Shape.KNIGHT)
        );
    }

    @Test
    @DisplayName("체스판 생성 후 비숍은 올바른 위치에 만들어진다.")
    void shouldSuccessGenerateBishop() {

        Assertions.assertAll(
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(3, 1)).getShape())
                        .isEqualTo(Shape.BISHOP),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(6, 1)).getShape())
                        .isEqualTo(Shape.BISHOP),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(3, 8)).getShape())
                        .isEqualTo(Shape.BISHOP),
                () -> assertThat(chessBoard.getChessBoard().get(Position.initPosition(6, 8)).getShape())
                        .isEqualTo(Shape.BISHOP)
        );
    }

    @Test
    @DisplayName("체스판 생성 후 폰은 올바른 위치에 만들어진다.")
    void shouldSuccessGeneratePawn() {
        for (int i = 1; i <= 8; i++) {
            assertThat(chessBoard.getChessBoard().get(Position.initPosition(i, 2)).getShape())
                    .isEqualTo(Shape.PAWN);
            assertThat(chessBoard.getChessBoard().get(Position.initPosition(i, 7)).getShape())
                    .isEqualTo(Shape.PAWN);
        }
    }

    @Test
    @DisplayName("체스판 생성 후 기물이 없는 곳은 공백 클래스가 위치한다.")
    void shouldSuccessSpaceIsEmpty() {
        for (int i = 3; i <= 8; i++) {
            for (int j = 3; j <= 6; j++) {
                assertThat(chessBoard.getChessBoard().get(Position.initPosition(i, j)).getShape())
                        .isEqualTo(Shape.BLANK);
            }
        }
    }
}
