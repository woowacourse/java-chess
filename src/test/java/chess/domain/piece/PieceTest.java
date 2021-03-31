package chess.domain.piece;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    private Piece whitePawn;
    private Piece blackPawn;
    private Piece whiteKing;
    private Piece bishop;
    private Piece queen;
    private Piece rook;
    private Piece knight;

    @BeforeEach
    void setUp() {
        whitePawn = Pawn.getInstanceOf(Owner.WHITE);
        blackPawn = Pawn.getInstanceOf(Owner.BLACK);
        whiteKing = King.getInstanceOf(Owner.WHITE);
        bishop = Bishop.getInstanceOf(Owner.BLACK);
        queen = Queen.getInstanceOf(Owner.BLACK);
        rook = Rook.getInstanceOf(Owner.BLACK);
        knight = Knight.getInstanceOf(Owner.BLACK);
    }

    @Test
    @DisplayName("체스말의 이동 가능 경로 반환한다.")
    void movablePathTest() {
        //given
        Pawn whitePawn = Pawn.getInstanceOf(Owner.WHITE);
        Knight whiteKnight = Knight.getInstanceOf(Owner.WHITE);

        //when
        List<Path> pawnPaths = whitePawn.movablePath(Position.of("a2"));
        List<Path> knightPaths = whiteKnight.movablePath(Position.of("b1"));

        List<Position> pawnPathsList = pawnPaths.stream().flatMap(Path::stream).collect(Collectors.toList());
        List<Position> knightPathList = knightPaths.stream().flatMap(Path::stream).collect(Collectors.toList());

        //then
        assertThat(pawnPathsList).containsExactly(
                Position.of("a3"),
                Position.of("a4"),
                Position.of("b3"),
                Position.of("c4")
        );

        assertThat(knightPathList).containsExactly(
                Position.of("c3"),
                Position.of("a3"),
                Position.of("d2")
        );
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
    @DisplayName("인자로 주는 색깔과 같은지 비교한다.")
    void isSameOwnerTest() {
        //when
        boolean isTrue = whitePawn.isSameOwner(Owner.WHITE);
        boolean isFalse = whitePawn.isSameOwner(Owner.BLACK);

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("체스말이 폰인지와 같은 색인지 확인한다.")
    void isSameOwnerPawnTest() {
        //when
        boolean isTrue = whitePawn.isSameOwnerPawn(Owner.WHITE);
        boolean isFalse = whiteKing.isSameOwnerPawn(Owner.WHITE);
        boolean isFalse2 = blackPawn.isSameOwnerPawn(Owner.WHITE);

        //then
        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
        assertThat(isFalse2).isFalse();
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

    @Test
    @DisplayName("체스말의 점수 반환한다.")
    void scoreTest() {
        //given
        Piece bishop = Bishop.getInstanceOf(Owner.BLACK);
        Piece queen = Queen.getInstanceOf(Owner.BLACK);
        Piece rook = Rook.getInstanceOf(Owner.BLACK);
        Piece knight = Knight.getInstanceOf(Owner.BLACK);

        //when
        Score pawnScore = whitePawn.score();
        Score kingScore = whiteKing.score();
        Score bishopScore = bishop.score();
        Score queenScore = queen.score();
        Score rookScore = rook.score();
        Score knightScore = knight.score();

        //then
        assertThat(pawnScore.value()).isEqualTo(1.0d);
        assertThat(kingScore.value()).isEqualTo(0.0d);
        assertThat(bishopScore.value()).isEqualTo(3.0d);
        assertThat(queenScore.value()).isEqualTo(9.0d);
        assertThat(rookScore.value()).isEqualTo(5.0d);
        assertThat(knightScore.value()).isEqualTo(2.5d);
    }

    @Test
    @DisplayName("최대 거리 반환한다.")
    void maxDistanceTest() {
        int pawnMaxDistance = whitePawn.maxDistance();
        int kingMaxDistance = whiteKing.maxDistance();
        int queenMaxDistance = queen.maxDistance();
        int rookMaxDistance = rook.maxDistance();

        assertThat(pawnMaxDistance).isEqualTo(2);
        assertThat(kingMaxDistance).isEqualTo(1);
        assertThat(queenMaxDistance).isEqualTo(7);
        assertThat(rookMaxDistance).isEqualTo(7);
    }

    @Test
    @DisplayName("자신의 심볼을 반환한다.")
    void getSymbolTest() {
        //given
        String whitePawnSymbol = whitePawn.getSymbol(); // "p"
        String blackPawnSymbol = blackPawn.getSymbol(); // "P"
        String whiteKingSymbol = whiteKing.getSymbol(); // "k"

        //then
        assertThat(whitePawnSymbol).isEqualTo("p");
        assertThat(blackPawnSymbol).isEqualTo("P");
        assertThat(whiteKingSymbol).isEqualTo("k");
    }
}