package domain.piece;

import domain.piece.type.Pawn;
import domain.position.ChessFile;
import domain.position.ChessRank;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @DisplayName("블랙 폰은 앞으로 한 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = new Position(ChessFile.A, ChessRank.SIX);
        Position target = new Position(ChessFile.A, ChessRank.FIVE);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 앞으로 한 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = new Position(ChessFile.A, ChessRank.TWO);
        Position target = new Position(ChessFile.A, ChessRank.THREE);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 처음(Rank7) 움직일 때, 앞으로 한 칸  또는 두 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = new Position(ChessFile.A, ChessRank.SEVEN);
        Position target = new Position(ChessFile.A, ChessRank.FIVE);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 처음(Rank2) 움직일 때, 앞으로 한 칸  또는 두 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = new Position(ChessFile.A, ChessRank.TWO);
        Position target = new Position(ChessFile.A, ChessRank.FOUR);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 처음(Rank7) 움직이는 것이 아닐 때, 앞으로 두 칸 움직일 수 없다.")
    @Test
    void cannotBlackPawnMoveTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = new Position(ChessFile.A, ChessRank.SIX);
        Position target = new Position(ChessFile.A, ChessRank.FOUR);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("화이트폰은 처음(Rank2) 움직이는 것이 아닐 때, 앞으로 두 칸 움직일 수 없다.")
    @Test
    void cannotWhitePawnMoveTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = new Position(ChessFile.A, ChessRank.THREE);
        Position target = new Position(ChessFile.A, ChessRank.FIVE);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("화이트폰은 앞 측 대각으로 한 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveDiagonalOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = new Position(ChessFile.A, ChessRank.THREE);
        Position target = new Position(ChessFile.B, ChessRank.FOUR);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("블랙폰은 앞 측 대각으로 한 칸 움직일 수 있다.")
    @Test
    void canBlackPawnMoveDiagonalOneStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = new Position(ChessFile.B, ChessRank.SEVEN);
        Position target = new Position(ChessFile.A, ChessRank.SIX);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 앞 측 대각으로는 두 칸 움직일 수 없다.")
    @Test
    void cannotWhitePawnMoveDiagonalTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.WHITE);
        Position source = new Position(ChessFile.A, ChessRank.TWO);
        Position target = new Position(ChessFile.C, ChessRank.FOUR);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("블랙폰은 앞 측 대각으로는 두 칸 움직일 수 없다.")
    @Test
    void cannotBlackPawnMoveDiagonalTwoStep() {
        // given
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position source = new Position(ChessFile.A, ChessRank.SEVEN);
        Position target = new Position(ChessFile.C, ChessRank.FIVE);

        // when
        boolean result = pawn.isMovable(source, target);

        // then
        assertThat(result).isFalse();
    }
}