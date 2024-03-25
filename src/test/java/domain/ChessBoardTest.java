package domain;

import domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ChessBoardTest {
    @DisplayName("제자리 이동은 불가능하다.")
    @Test
    void moveSamePlace() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();

        final Square source = new Square(File.A, Rank.TWO);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, source))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }

    @DisplayName("기물이 이동할 수 없는 경로 이동은 불가능하다 : 폰 3칸")
    @Test
    void emptyPath() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();

        final Square source = new Square(File.A, Rank.TWO);
        final Square target = new Square(File.A, Rank.FIVE);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }

    @DisplayName("Source 위치에 말이 없으면 이동이 불가능하다.")
    @Test
    void noPieceInSource() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();

        final Square source = new Square(File.A, Rank.THREE);
        final Square target = new Square(File.A, Rank.FOUR);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }

    @DisplayName("경로에 다른 기물이 있으면 이동이 불가능하다.")
    @Test
    void blockingPiece() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();

        final Square source = new Square(File.A, Rank.ONE);
        final Square target = new Square(File.A, Rank.EIGHT);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }


    @DisplayName("기물을 움직인다 : 폰 2칸")
    @Test
    void movePawn() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();

        final Square source = new Square(File.D, Rank.TWO);
        final Square target = new Square(File.D, Rank.FOUR);

        final Piece sourcePiece = chessBoard.getPieces().get(source);

        // when &
        chessBoard.move(source, target);

        //then
        final Map<Square, Piece> pieceSquares = chessBoard.getPieces();

        assertAll(
                () -> assertThat(pieceSquares.containsKey(source)).isFalse(),
                () -> assertThat(pieceSquares.get(target)).isEqualTo(sourcePiece)
        );
    }


    @DisplayName("상대방 기물을 잡는다 : 폰 to 폰")
    @Test
    void killEnemy() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();

        final Square whiteSource = new Square(File.D, Rank.TWO);
        final Square whiteTarget = new Square(File.D, Rank.FOUR);
        final Piece whitePiece = chessBoard.getPieces().get(whiteSource);

        chessBoard.move(whiteSource, whiteTarget);

        final Square blackSource = new Square(File.E, Rank.SEVEN);
        final Square blackTarget = new Square(File.E, Rank.FIVE);
        final Piece blackPiece = chessBoard.getPieces().get(blackSource);
        chessBoard.move(blackSource, blackTarget);

        // when
        chessBoard.move(whiteTarget, blackTarget);

        //then
        final Map<Square, Piece> pieceSquares = chessBoard.getPieces();

        assertAll(
                () -> assertThat(pieceSquares.values()).hasSize(31),
                () -> assertThat(pieceSquares.get(blackTarget)).isEqualTo(whitePiece)
        );
    }

    @DisplayName("자기 기물이 아닌 것을 움직일 수 없다.")
    @Test
    void myCamp() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();

        final Square source = new Square(File.A, Rank.SEVEN);
        final Square target = new Square(File.A, Rank.SIX);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자기 말이 아닙니다.");
    }

    @DisplayName("폰은 대각선으로만 공격할 수 있다.")
    @Test
    void pawnAttack() {
        // given
        final ChessBoard chessBoard = ChessBoard.create();

        final Square whiteSource = new Square(File.D, Rank.TWO);
        final Square whiteTarget = new Square(File.D, Rank.FOUR);

        chessBoard.move(whiteSource, whiteTarget);

        final Square blackSource = new Square(File.D, Rank.SEVEN);
        final Square blackTarget = new Square(File.D, Rank.FIVE);
        chessBoard.move(blackSource, blackTarget);

        // when & then
        assertThatThrownBy(() -> chessBoard.move(whiteTarget, blackTarget))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }
}
