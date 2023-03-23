package domain.board;

import static domain.board.ChessAlignmentMock.testStrategy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.fixture.TestFixture;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @DisplayName("Board를 초기화해 생성할 수 있다.")
    @Test
    void createTest() {
        //given
        Board board = Board.create(new InitialChessAlignment().init());
        final List<Position> rooksPosition = Positions.of("A1", "A8", "H1", "H8");
        final List<Position> knightsPosition = Positions.of("B1", "B8", "G1", "G8");
        final List<Position> bishopsPosition = Positions.of("C1", "C8", "F1", "F8");
        final List<Position> pawnsPosition = Positions.of("A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2",
                "A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7");
        final List<Position> kingsPosition = Positions.of("E1", "E8");
        final List<Position> queensPosition = Positions.of("D1", "D8");

        //when
        final Map<Position, Piece> pieces = board.getPieces();

        //then
        assertThat(rooksPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Rook.class));
        assertThat(knightsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Knight.class));
        assertThat(bishopsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Bishop.class));
        assertThat(pawnsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Pawn.class));
        assertThat(kingsPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(King.class));
        assertThat(queensPosition).map(pieces::get).allSatisfy(piece -> assertThat(piece).isInstanceOf(Queen.class));
    }

    @DisplayName("킹은 움직일 수 있다.")
    @Test
    void kingMove() {
        //given
        final King king = TestFixture.BLACK_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), king)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("D3"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("D3"))).isEqualTo(king);
    }

    @DisplayName("킹은 적이 있을 때 먹을 수 있다.")
    @Test
    void kingEat() {
        //given
        final King king = TestFixture.BLACK_KING;
        final King feed = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), king,
                Positions.from("E5"), feed)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("E5"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("E5"))).isEqualTo(king);
    }

    @DisplayName("킹은 자신의 가동 범위 밖으로 움직일 수 없다.")
    @Test
    void kingNotMove() {
        //given
        final King king = TestFixture.BLACK_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), king)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("A3")));
    }

    @DisplayName("퀸은 움직일 수 있다.")
    @Test
    void queenMove() {
        //given
        final Queen queen = TestFixture.BLACK_QUEEN;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), queen)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("D8"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("D8"))).isEqualTo(queen);
    }

    @DisplayName("퀸은 적이 있을 때 먹을 수 있다.")
    @Test
    void queenEat() {
        //given
        final Queen queen = TestFixture.BLACK_QUEEN;
        final King feed = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), queen,
                Positions.from("H8"), feed)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("H8"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("H8"))).isEqualTo(queen);
    }

    @DisplayName("퀸은 자신의 가동 범위 밖으로 움직일 수 없다.")
    @Test
    void queenNotMove() {
        //given
        final Queen queen = TestFixture.BLACK_QUEEN;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), queen)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("E2")));
    }

    @DisplayName("퀸은 이동 경로 사이에 다른 기물이 있으면 움직일 수 없다.")
    @Test
    void queenNotMove2() {
        //given
        final Queen queen = TestFixture.BLACK_QUEEN;
        final King another = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), queen,
                Positions.from("D6"), another)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("D8")));
    }

    @DisplayName("나이트는 움직일 수 있다.")
    @Test
    void knightMove() {
        //given
        final Knight knight = TestFixture.BLACK_KNIGHT;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), knight)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("F3"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("F3"))).isEqualTo(knight);
    }

    @DisplayName("나이트는 적이 있을 때 먹을 수 있다.")
    @Test
    void knightEat() {
        //given
        final Knight knight = TestFixture.BLACK_KNIGHT;
        final King feed = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), knight,
                Positions.from("B3"), feed)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("B3"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("B3"))).isEqualTo(knight);
    }

    @DisplayName("나이트는 사이에 다른 기물이 있어도 움직일 수 있다.")
    @Test
    void knightMove2() {
        //given
        final Knight knight = TestFixture.BLACK_KNIGHT;
        final King another1 = TestFixture.WHITE_KING;
        final King another2 = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), knight,
                Positions.from("D3"), another1,
                Positions.from("E3"), another2)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("F3"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("F3"))).isEqualTo(knight);
    }

    @DisplayName("나이트는 자신의 가동 범위 밖으로 움직일 수 없다.")
    @Test
    void knightNotMove() {
        //given
        final Knight knight = TestFixture.BLACK_KNIGHT;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), knight)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("D2")));
    }

    @DisplayName("폰은 처음에 두 칸 움직일 수 있다.")
    @Test
    void pawnTwoStepMove() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D7"), pawn)).init());

        //when
        board.move(Positions.from("D7"), Positions.from("D5"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D7"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("D5"))).isEqualTo(pawn);
    }

    @DisplayName("폰은 한 칸 움직일 수 있다.")
    @Test
    void pawnOneStepMove() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D7"), pawn)).init());

        //when
        board.move(Positions.from("D7"), Positions.from("D6"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D7"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("D6"))).isEqualTo(pawn);
    }

    @DisplayName("폰은 자신의 가동 범위 밖으로 움직일 수 없다.")
    @Test
    void pawnNotMove() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), pawn)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("A2")));
    }

    @DisplayName("폰은 앞에 적이 있으면 앞으로 갈 수 없다.")
    @Test
    void pawnNotMove2() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final King king = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), pawn,
                Positions.from("D3"), king)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("D3")));
    }

    @DisplayName("폰은 앞에 적이 있으면 처음에 앞으로 두 칸 갈 수 없다.")
    @Test
    void pawnNotMove3() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final King king = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D7"), pawn,
                Positions.from("D6"), king)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D7"), Positions.from("D5")));
    }

    @DisplayName("폰은 대각선에 적이 없으면 대각선으로 전진할 수 없다.")
    @Test
    void pawnNotMove4() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D7"), pawn)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D7"), Positions.from("E8")));
    }

    @DisplayName("폰은 대각선에 적이 있으면 대각선으로 적을 먹을 수 있다.")
    @Test
    void pawnMove1() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final King king = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D7"), pawn,
                Positions.from("E6"), king)).init());

        //when
        board.move(Positions.from("D7"), Positions.from("E6"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D7"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("E6"))).isEqualTo(pawn);
    }

    @DisplayName("비숍은 움직일 수 있다.")
    @Test
    void bishopMove() {
        //given
        final Bishop bishop = TestFixture.BLACK_BISHOP;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), bishop)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("H8"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("H8"))).isEqualTo(bishop);
    }

    @DisplayName("비숍은 자신의 가동 범위 밖으로 움직일 수 없다.")
    @Test
    void bishopNotMove() {
        //given
        final Bishop bishop = TestFixture.BLACK_BISHOP;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), bishop)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("H7")));
    }

    @DisplayName("비숍은 이동 경로 사이에 다른 기물이 있으면 움직일 수 없다.")
    @Test
    void bishopNotMove2() {
        //given
        final Bishop bishop = TestFixture.BLACK_BISHOP;
        final King another = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), bishop,
                Positions.from("D6"), another)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("D8")));
    }

    @DisplayName("비숍은 적이 있을 때 먹을 수 있다.")
    @Test
    void bishopEat() {
        //given
        final Bishop bishop = TestFixture.BLACK_BISHOP;
        final King feed = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), bishop,
                Positions.from("H8"), feed)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("H8"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("H8"))).isEqualTo(bishop);
    }

    @DisplayName("룩은 움직일 수 있다.")
    @Test
    void rookMove() {
        //given
        final Rook rook = TestFixture.BLACK_ROOK;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), rook)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("D8"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("D8"))).isEqualTo(rook);
    }

    @DisplayName("룩은 자신의 가동 범위 밖으로 움직일 수 없다.")
    @Test
    void rookNotMove() {
        //given
        final Rook rook = TestFixture.BLACK_ROOK;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), rook)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("H8")));
    }

    @DisplayName("룩은 이동 경로 사이에 다른 기물이 있으면 움직일 수 없다.")
    @Test
    void rookNotMove2() {
        //given
        final Rook rook = TestFixture.BLACK_ROOK;
        final King another = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), rook,
                Positions.from("D6"), another)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("D8")));
    }

    @DisplayName("룩은 적이 있을 때 먹을 수 있다.")
    @Test
    void rookEat() {
        //given
        final Rook rook = TestFixture.BLACK_ROOK;
        final King feed = TestFixture.WHITE_KING;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), rook,
                Positions.from("D8"), feed)).init());

        //when
        board.move(Positions.from("D4"), Positions.from("D8"));

        //then
        assertThat(board.getPieces().containsKey(Positions.from("D4"))).isFalse();
        assertThat(board.getPieces().get(Positions.from("D8"))).isEqualTo(rook);
    }

    @DisplayName("목적지에 같은 팀이 있으면 갈 수 없다.")
    @Test
    void existOnDestination() {
        //given
        final King king = TestFixture.BLACK_KING;
        final Bishop team = TestFixture.BLACK_BISHOP;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), king,
                Positions.from("E5"), team)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("E5")));
    }

    @DisplayName("존재하지 않는 장소에 말을 두려는 경우 예외가 발생한다.")
    @Test
    void notExistToExistMove() {
        //given
        final Queen queen = TestFixture.BLACK_QUEEN;

        //when

        //then
        assertThatThrownBy(() -> Board.create(testStrategy(Map.of(Positions.from("D0"), queen)).init()));
    }

    @DisplayName("존재하는 장소에서 존재하지 않는 장소로 가는 경우 예외가 발생한다.")
    @Test
    void existToNotExistMove() {
        //given
        final Queen queen = TestFixture.BLACK_QUEEN;
        final Board board = Board.create(testStrategy(Map.of(Positions.from("D4"), queen)).init());

        //when

        //then
        assertThatThrownBy(() -> board.move(Positions.from("D4"), Positions.from("Z4")));
    }
}
