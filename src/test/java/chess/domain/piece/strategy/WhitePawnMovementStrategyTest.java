package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WhitePawnMovementStrategyTest {

    @DisplayName("화이트폰은 앞으로 한 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveOneStep() {
        // given
        Piece pawn = new Piece(PieceType.WHITE_PAWN);
        Position source = Position.of(ChessFile.A, ChessRank.TWO);
        Position target = Position.of(ChessFile.A, ChessRank.THREE);

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 처음(Rank2) 움직일 때, 앞으로 한 칸  또는 두 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveTwoStep() {
        // given
        Piece pawn = new Piece(PieceType.WHITE_PAWN);
        Position source = Position.of(ChessFile.A, ChessRank.TWO);
        Position target = Position.of(ChessFile.A, ChessRank.FOUR);

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 처음(Rank2) 움직이는 것이 아닐 때, 앞으로 두 칸 움직일 수 없다.")
    @Test
    void cannotWhitePawnMoveTwoStep() {
        // given
        Piece pawn = new Piece(PieceType.WHITE_PAWN);
        Position source = Position.of(ChessFile.A, ChessRank.THREE);
        Position target = Position.of(ChessFile.A, ChessRank.FIVE);

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("화이트폰은 앞 측 대각으로 한 칸 움직일 수 있다.")
    @Test
    void canWhitePawnMoveDiagonalOneStep() {
        // given
        Piece pawn = new Piece(PieceType.WHITE_PAWN);
        Position source = Position.of(ChessFile.A, ChessRank.THREE);
        Position target = Position.of(ChessFile.B, ChessRank.FOUR);

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("화이트폰은 앞 측 대각으로는 두 칸 움직일 수 없다.")
    @Test
    void cannotWhitePawnMoveDiagonalTwoStep() {
        // given
        Piece pawn = new Piece(PieceType.WHITE_PAWN);
        Position source = Position.of(ChessFile.A, ChessRank.TWO);
        Position target = Position.of(ChessFile.C, ChessRank.FOUR);

        // when
        boolean result = pawn.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }
}
