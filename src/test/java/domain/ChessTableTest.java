package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Piece;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessTableTest {


    @DisplayName("제자리 이동은 못한다.")
    @Test
    void moveSamePlace() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.TWO, File.A);

        // when & then
        Assertions.assertThatThrownBy(() -> chessTable.move(source, source))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }

    @DisplayName("갈 수 없는 경로는 못간다.")
    @Test
    void emptyPath() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.TWO, File.A);
        final Square target = new Square(Rank.FIVE, File.A);

        // when & then
        Assertions.assertThatThrownBy(() -> chessTable.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }

    @DisplayName("Source 위치에 말이 없으면 안된다.")
    @Test
    void noPieceInSource() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.THREE, File.A);
        final Square target = new Square(Rank.FOUR, File.A);

        // when & then
        Assertions.assertThatThrownBy(() -> chessTable.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }

    @DisplayName("경로에 다른 기물이 있으면 못간다.")
    @Test
    void blockingPiece() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.ONE, File.D);
        final Square target = new Square(Rank.TWO, File.E);

        // when & then
        Assertions.assertThatThrownBy(() -> chessTable.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("갈 수 없는 경로입니다.");
    }


    @DisplayName("기물을 움직인다.")
    @Test
    void movePawn() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.TWO, File.D);
        final Square target = new Square(Rank.FOUR, File.D);

        final Piece sourcePiece = chessTable.getPieceSquares().get(source);

        // when &
        chessTable.move(source, target);

        //then
        final Map<Square, Piece> pieceSquares = chessTable.getPieceSquares();
        assertThat(pieceSquares.containsKey(source)).isFalse();

        assertThat(pieceSquares.get(target)).isEqualTo(sourcePiece);
    }


    @DisplayName("상대방 기물을 잡는다.")
    @Test
    void killEnemy() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square whiteSource = new Square(Rank.TWO, File.D);
        final Square whiteTarget = new Square(Rank.FOUR, File.D);
        final Piece whitePiece = chessTable.getPieceSquares().get(whiteSource);

        chessTable.move(whiteSource, whiteTarget);

        final Square blackSource = new Square(Rank.SEVEN, File.E);
        final Square blackTarget = new Square(Rank.FIVE, File.E);
        final Piece blackPiece = chessTable.getPieceSquares().get(blackSource);
        chessTable.move(blackSource, blackTarget);

        // when
        chessTable.move(whiteTarget, blackTarget);

        //then
        final Map<Square, Piece> pieceSquares = chessTable.getPieceSquares();

        assertThat(pieceSquares.values()).hasSize(31);

        assertThat(pieceSquares.get(blackTarget)).isEqualTo(whitePiece);
    }

    @DisplayName("자기 기물이 아닌 것을 움직일 수 없다.")
    @Test
    void myTurn() {
        // given
        final ChessTable chessTable = ChessTable.create();

        final Square source = new Square(Rank.SEVEN, File.A);
        final Square target = new Square(Rank.SIX, File.A);

        // when & then
        Assertions.assertThatThrownBy(() -> chessTable.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자기 말이 아닙니다.");
    }
}
