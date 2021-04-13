package chess.domain.piece.pawn;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    private Pawn whitePawn;
    private Pawn blackPawn;
    private Piece emptyPiece;
    private Position source;

    @BeforeEach
    void setUp() {
        whitePawn = Pawn.getInstanceOf(Owner.WHITE);
        blackPawn = Pawn.getInstanceOf(Owner.BLACK);
        emptyPiece = EmptyPiece.getInstance();
        source = Position.of("b2");
    }

    @Test
    @DisplayName("BlackPawn 과 WhitePawn 인스턴스 잘 가져온다.")
    void getInstanceOfTest() {
        assertThat(whitePawn).isInstanceOf(WhitePawn.class);
        assertThat(blackPawn).isInstanceOf(BlackPawn.class);
    }

    @Test
    @DisplayName("getInstanceOf의 인자로 NONE을 넘기면 예외가 발생한다.")
    void getInstanceOfThrowExceptionTest() {
        assertThatThrownBy(() -> {
            Pawn.getInstanceOf(Owner.NONE);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid Pawn");
    }

    @Test
    @DisplayName("Pawn 의 최대 이동거리를 반환한다.")
    void maxDistanceTest() {
        //given
        int pawnMaxDistance = whitePawn.maxDistance();

        //then
        assertThat(pawnMaxDistance).isEqualTo(2);
    }

    @Test
    @DisplayName("Pawn 의 심볼 반환된다.")
    void getSymbolTest() {
        //given
        String whitePawnSymbol = whitePawn.getSymbol();
        String blackPawnSymbol = blackPawn.getSymbol();

        //then
        assertThat(whitePawnSymbol).isEqualTo("p");
        assertThat(blackPawnSymbol).isEqualTo("P");
    }

    @ParameterizedTest(name = "WhitePawn 의 첫라인은 7, BlackPawn 의 첫라인은 2이다.")
    @CsvSource({"TWO, SEVEN, true", "ONE, EIGHT, false"})
    void isFirstLineTest(String whitePawnVerticalInput, String blackPawnVerticalInput, boolean isFirstLine) {
        Vertical whitePawnVertical = Vertical.valueOf(whitePawnVerticalInput);
        Vertical blackPawnVertical = Vertical.valueOf(blackPawnVerticalInput);

        assertThat(whitePawn.isFirstLine(whitePawnVertical)).isEqualTo(isFirstLine);
        assertThat(blackPawn.isFirstLine(blackPawnVertical)).isEqualTo(isFirstLine);
    }

    @Test
    @DisplayName("Pawn 은 1칸 전진이 가능하다.")
    void isOneStraightMoveTest() {
        Position target = Position.of("b3");

        boolean isTrue = whitePawn.isReachable(source, target, emptyPiece);

        assertThat(isTrue).isTrue();
    }

    @Test
    @DisplayName("Pawn 은 첫번째 라인에 있을 경우 2칸 전진이 가능하다.")
    void isTwoStraightMoveIfFirstLineTest() {
        Position target = Position.of("b4");

        boolean isTrue = whitePawn.isReachable(source, target, emptyPiece);

        assertThat(isTrue).isTrue();
    }

    @Test
    @DisplayName("Pawn 앞에 적이나 아군이 있으면 1칸 전진도 불가능하다.")
    void isOneStraightMoveIfTargetEnemyOrSameTeamTest() {
        Position target = Position.of("b3");

        boolean isTrue = whitePawn.isReachable(source, target, blackPawn);

        assertThat(isTrue).isFalse();
    }

    @Test
    @DisplayName("Pawn 은 대각선 한칸에 적이 있으면 적을 잡으면서 이동이 가능하다.")
    void isOneDiagonalMoveIfTargetEnemyTest() {
        Position target = Position.of("a3");
        Position target2 = Position.of("c3");

        boolean isTrue = whitePawn.isReachable(source, target, blackPawn);
        boolean isTrue2 = whitePawn.isReachable(source, target2, blackPawn);

        assertThat(isTrue).isTrue();
        assertThat(isTrue2).isTrue();
    }

    @Test
    @DisplayName("Pawn 은 대각선 2칸 이동이 불가능하다, 적이 있어도")
    void isTwoDiagonalMoveIfTargetEnemyTest() {
        Position target = Position.of("a4");
        Position target2 = Position.of("c4");

        boolean isFalse = whitePawn.isReachable(source, target, blackPawn);
        boolean isFalse2 = whitePawn.isReachable(source, target2, blackPawn);

        assertThat(isFalse).isFalse();
        assertThat(isFalse2).isFalse();
    }

    @Test
    @DisplayName("Pawn 의 이동 가능 경로 반환한다.")
    void movablePathTest() {
        //when
        List<Path> pawnPaths = whitePawn.movablePath(Position.of("a2"));

        List<Position> pawnPathsList = pawnPaths.stream().flatMap(Path::stream).collect(Collectors.toList());

        //then
        assertThat(pawnPathsList).containsExactly(
                Position.of("a3"),
                Position.of("a4"),
                Position.of("b3"),
                Position.of("c4")
        );
    }
}