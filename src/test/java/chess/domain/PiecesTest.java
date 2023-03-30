package chess.domain;

import static chess.domain.Shape.BISHOP;
import static chess.domain.Shape.KING;
import static chess.domain.Shape.PAWN;
import static chess.domain.Shape.QUEEN;
import static chess.domain.Shape.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

final class PiecesTest {

    Pieces whitePieces;
    Pieces blackPieces;

    @BeforeEach
    void setUp() {
        Pieces pieces = new Pieces();
        this.whitePieces = pieces.createWhitePieces();
        blackPieces = pieces.createBlackPieces();
    }

    @Test
    @DisplayName("체스 판 기물들이 올바른 개수로 생성되어야 한다.")
    void createWhitePieces() {
        // when, then
        assertAll(
                () -> {
                    long count = whitePieces.getShapeCount(PAWN);
                    assertThat(count).isEqualTo(8);
                },

                () -> {
                    long count = whitePieces.getShapeCount(BISHOP);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = whitePieces.getShapeCount(ROOK);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = whitePieces.getShapeCount(BISHOP);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = whitePieces.getShapeCount(KING);
                    assertThat(count).isEqualTo(1);
                },

                () -> {
                    long count = whitePieces.getShapeCount(QUEEN);
                    assertThat(count).isEqualTo(1);
                }
        );
    }

    @Test
    @DisplayName("기물 리스트로 Pieces를 생성할 수 있다.")
    void createByPieceList() {
        List<Piece> elements = List.of(Piece.from(1, 'a', ROOK), Piece.from(2, 'a', PAWN));
        final var pieces = Pieces.from(elements);
        assertThat(pieces)
                .isNotNull()
                .isInstanceOf(Pieces.class);
    }

    @DisplayName("왕 존재 여부를 반환할 수 있다.")
    @Test
    void testReturnIfKingExists() {
        //given
        Pieces pieces = Pieces.from(Pieces.createWhitePieces().getPieces());
        //when
        boolean containsKing = pieces.containsKing();
        //then
        assertThat(containsKing).isTrue();
    }

    @DisplayName("점수를 계산해서 반환할 수 있다.")
    @Test
    void testCalculateScoreProperly() {
        //given
        Pieces pieces = Pieces.from(Pieces.createBlackPieces().getPieces());
        //when
        Score initialScore = pieces.calculateScore();
        //then
        assertThat(initialScore).isEqualTo(Score.from(38));
    }

    @DisplayName("중복되는 폰의 점수는 0.5점으로 계산한다.")
    @Test
    void testCalculateScoreWhenPawnsOnSameFile() {
        //given
        List<Piece> pawnsOnSameFile = List.of(
                Piece.from(2, 'a', PAWN),
                Piece.from(3, 'a', PAWN),
                Piece.from(4, 'a', PAWN)
        );
        Pieces pieces = Pieces.from(pawnsOnSameFile);
        //when
        Score score = pieces.calculateScore();
        //then
        System.out.println(score.getValue());
        assertThat(score).isEqualTo(Score.from(1.5));
    }
}
