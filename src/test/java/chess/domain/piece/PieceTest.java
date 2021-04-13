package chess.domain.piece;

import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import chess.util.PieceConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    private Piece whitePawn;
    private Piece blackPawn;
    private Piece whiteKing;

    @BeforeEach
    void setUp() {
        whitePawn = Pawn.getInstanceOf(Owner.WHITE);
        blackPawn = Pawn.getInstanceOf(Owner.BLACK);
        whiteKing = King.getInstanceOf(Owner.WHITE);
    }

    @Test
    @DisplayName("다른 체스말과 비교하여 다른 팀인지 판단한다.")
    void isDifferentTeamTest() {
        //when
        boolean isTrue = whitePawn.isDifferentTeam(blackPawn);
        boolean isFalse = whitePawn.isDifferentTeam(whiteKing);

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("인자로 주는 Owner와 같은지 비교한다.")
    void isSameOwnerTest() {
        //when
        boolean isTrue = whitePawn.isSameOwner(Owner.WHITE);
        boolean isFalse = whitePawn.isSameOwner(Owner.BLACK);

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @ParameterizedTest(name = "체스말이 폰인지와 같은 색인지 확인한다.")
    @CsvSource({"p, true", "k, false", "P, false"})
    void isSameOwnerPawnTest(String symbol, boolean isSameOwnerPawn) {
        //given
        Piece piece = PieceConverter.parsePiece(symbol);

        //then
        assertThat(piece.isSameOwnerPawn(Owner.WHITE)).isEqualTo(isSameOwnerPawn);
    }

    @Test
    @DisplayName("현재 말이 폰인지 확인한다.")
    void isPawnTest() {
        //when
        boolean isTrue = whitePawn.isPawn();
        boolean isFalse = whiteKing.isPawn();

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("현재 체스말이 비어있는지 확인한다.")
    void isEmptyPiece() {
        //given
        Piece emptyPiece = EmptyPiece.getInstance();

        //when
        boolean isTrue = emptyPiece.isEmptyPiece();
        boolean isFalse = whiteKing.isEmptyPiece();

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("해당 체스말이 현재 체스말과 적인지 판단한다.")
    void isEnemyTest() {
        //when
        boolean isTrue = whitePawn.isEnemy(blackPawn);
        boolean isFalse = whitePawn.isEnemy(whiteKing);

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @ParameterizedTest(name = "모든 종류의 체스말이 각각 자신에 맞는 점수를 반환한다.")
    @CsvSource({"p, 1.0d", "k, 0.0d", "b, 3.0d", "q, 9.0d", "r, 5.0d", "n, 2.5d"})
    void scoreTest(String symbol, double scoreValue) {
        //given
        Piece piece = PieceConverter.parsePiece(symbol);

        //when
        Score score = piece.score();

        //then
        assertThat(score.value()).isEqualTo(scoreValue);
    }

    @ParameterizedTest(name = "모든 종류의 체스말이 각각 자신에 맞는 최대 거리 반환한다.")
    @CsvSource({"p, 2", "k, 1", "b, 7", "q, 7", "r, 7", "n, 1"})
    void maxDistanceTest(String symbol, int distance) {
        //given
        Piece piece = PieceConverter.parsePiece(symbol);

        int maxDistance = piece.maxDistance();

        assertThat(maxDistance).isEqualTo(distance);
    }

    @Test
    @DisplayName("자신의 심볼을 반환한다.")
    void getSymbolTest() {
        //given
        String whitePawnSymbol = Pawn.getInstanceOf(Owner.WHITE).getSymbol();
        String blackPawnSymbol = Pawn.getInstanceOf(Owner.BLACK).getSymbol();
        String whiteKingSymbol = King.getInstanceOf(Owner.WHITE).getSymbol();
        String blackKingSymbol = King.getInstanceOf(Owner.BLACK).getSymbol();
        String whiteBishopSymbol = Bishop.getInstanceOf(Owner.WHITE).getSymbol();
        String blackBishopSymbol = Bishop.getInstanceOf(Owner.BLACK).getSymbol();
        String whiteKnightSymbol = Knight.getInstanceOf(Owner.WHITE).getSymbol();
        String blackKnightSymbol = Knight.getInstanceOf(Owner.BLACK).getSymbol();
        String whiteQueenSymbol = Queen.getInstanceOf(Owner.WHITE).getSymbol();
        String blackQueenSymbol = Queen.getInstanceOf(Owner.BLACK).getSymbol();
        String whiteRookSymbol = Rook.getInstanceOf(Owner.WHITE).getSymbol();
        String blackRookSymbol = Rook.getInstanceOf(Owner.BLACK).getSymbol();

        //then
        assertThat(whitePawnSymbol).isEqualTo("p");
        assertThat(blackPawnSymbol).isEqualTo("P");
        assertThat(whiteKingSymbol).isEqualTo("k");
        assertThat(blackKingSymbol).isEqualTo("K");
        assertThat(whiteBishopSymbol).isEqualTo("b");
        assertThat(blackBishopSymbol).isEqualTo("B");
        assertThat(whiteKnightSymbol).isEqualTo("n");
        assertThat(blackKnightSymbol).isEqualTo("N");
        assertThat(whiteQueenSymbol).isEqualTo("q");
        assertThat(blackQueenSymbol).isEqualTo("Q");
        assertThat(whiteRookSymbol).isEqualTo("r");
        assertThat(blackRookSymbol).isEqualTo("R");
    }
}