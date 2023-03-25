package chess.domain.pieces;

import static chess.domain.Team.WHITE;
import static chess.domain.pieces.PieceType.BISHOP;
import static chess.domain.pieces.PieceType.EMPTY;
import static chess.domain.pieces.PieceType.KING;
import static chess.domain.pieces.PieceType.KNIGHT;
import static chess.domain.pieces.PieceType.PAWN;
import static chess.domain.pieces.PieceType.QUEEN;
import static chess.domain.pieces.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.pieces.nonsliding.King;
import chess.domain.pieces.nonsliding.Knight;
import chess.domain.pieces.pawn.BlackPawn;
import chess.domain.pieces.sliding.Bishop;
import chess.domain.pieces.sliding.Queen;
import chess.domain.pieces.sliding.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class PieceTypeTest {

    @Test
    @DisplayName("기물의 종류에 따라 점수를 반환한다")
    void 기물의_종류에_따라_점수를_반환한다() {
        assertAll(
                () -> assertThat(PAWN.getScore()).isEqualTo(new Score(1)),
                () -> assertThat(ROOK.getScore()).isEqualTo(new Score(5)),
                () -> assertThat(KNIGHT.getScore()).isEqualTo(new Score(2.5)),
                () -> assertThat(BISHOP.getScore()).isEqualTo(new Score(3)),
                () -> assertThat(QUEEN.getScore()).isEqualTo(new Score(9)),
                () -> assertThat(KING.getScore()).isEqualTo(new Score(0)),
                () -> assertThat(EMPTY.getScore()).isEqualTo(new Score(0))
        );
    }

    @Test
    @DisplayName("Piece를 받아 PieceType을 반환한다")
    void Piece를_받아_PieceType을_반환한다() {
        assertAll(
                () -> assertThat(PieceType.from(new BlackPawn().getClass())).isEqualTo(PAWN),
                () -> assertThat(PieceType.from(new Rook(WHITE).getClass())).isEqualTo(ROOK),
                () -> assertThat(PieceType.from(new Knight(WHITE).getClass())).isEqualTo(KNIGHT),
                () -> assertThat(PieceType.from(new Bishop(WHITE).getClass())).isEqualTo(BISHOP),
                () -> assertThat(PieceType.from(new Queen(WHITE).getClass())).isEqualTo(QUEEN),
                () -> assertThat(PieceType.from(new King(WHITE).getClass())).isEqualTo(KING),
                () -> assertThat(PieceType.from(new EmptyPiece().getClass())).isEqualTo(EMPTY)
        );
    }

    @Test
    @DisplayName("하나의 열에 있는 기물들의 총점을 계산한다")
    void 하나의_열에_있는_기물들의_총점을_계산한다() {
        List<Piece> pieces = List.of(new Rook(WHITE), new Rook(WHITE), new Knight(WHITE), new Knight(WHITE),
                new Bishop(WHITE), new Bishop(WHITE), new Queen(WHITE), new King(WHITE));

        assertThat(PieceType.calculateSingleColumn(pieces)).isEqualTo(new Score(30.0));
    }

    @Test
    @DisplayName("하나의 열에 폰이 하나면 1점으로 계산한다")
    void 하나의_열에_폰이_하나면_1점으로_계산한다() {
        List<Piece> pieces = List.of(new BlackPawn());

        assertThat(PieceType.calculateSingleColumn(pieces)).isEqualTo(new Score(1));
    }

    @Test
    @DisplayName("하나의 열에 폰이 2개 이상이면 각 0.5점으로 계산한다")
    void 하나의_열에_폰이_2개_이상이면_각_0_5점으로_계산한다_case1() {
        List<Piece> pieces = List.of(new BlackPawn(), new BlackPawn());

        assertThat(PieceType.calculateSingleColumn(pieces)).isEqualTo(new Score(1));
    }

    @Test
    @DisplayName("하나의 열에 폰이 2개 이상이면 각 0.5점으로 계산한다")
    void 하나의_열에_폰이_2개_이상이면_각_0_5점으로_계산한다_case2() {
        List<Piece> pieces = List.of(new BlackPawn(), new BlackPawn(), new BlackPawn());

        assertThat(PieceType.calculateSingleColumn(pieces)).isEqualTo(new Score(1.5));
    }

    @Test
    @DisplayName("Piece가 없으면 0점을 반환한다")
    void Piece가_없으면_0점을_반환한다() {
        Score score = PieceType.calculateSingleColumn(List.of());

        assertThat(score).isEqualTo(Score.ZERO);
    }
}
