package chess.domain.board;

import static chess.domain.piece.Side.BLACK;
import static chess.domain.piece.Side.NEUTRALITY;
import static chess.domain.piece.Side.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.File.F;
import static chess.domain.position.File.G;
import static chess.domain.position.File.H;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.FOUR;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static chess.domain.position.Rank.THREE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.immediate.King;
import chess.domain.piece.immediate.Knight;
import chess.domain.piece.linear.Bishop;
import chess.domain.piece.linear.Queen;
import chess.domain.piece.linear.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BoardTest {

    private Map<Position, Piece> boardMap;

    @BeforeEach
    void init() {
        boardMap = new HashMap<>();
        for (final Rank rank : Rank.values()) {
            for (final File file : File.values()) {
                boardMap.put(Position.of(file, rank), new Empty());
            }
        }
    }

    @Test
    void 한_번도_움직이지_않은_폰은_두_칸을_이동할_수_있다() {
        /*

        ........        ........
        ........        ........
        ........        ........
        ........        ........
        ........  -->   ........
        ........        p.......
        ........        ........
        p.......        ........

         */

        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        final Board board = new Board(boardMap);

        assertThatCode(() -> board.move(Position.of(A, ONE), Position.of(A, THREE)))
                .doesNotThrowAnyException();
    }

    @Test
    void 폰은_한_칸을_이동할_수_있다() {
        /*

        ........        ........
        ........        ........
        ........        ........
        ........        ........
        ........  -->   ........
        ........        ........
        ........        p.......
        p.......        ........

         */

        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        final Board board = new Board(boardMap);

        assertThatCode(() -> board.move(Position.of(A, ONE), Position.of(A, TWO)))
                .doesNotThrowAnyException();
    }

    @Test
    void 폰은_적_기물이_있을_경우에_대각선으로_움직일_수_있다() {
        /*

        ........        ........
        ........        ........
        ........        ........
        ........        ........
        ........  -->   ........
        ........        ........
        .R......        .p......
        p.......        ........

         */

        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        boardMap.put(Position.of(B, TWO), new Rook(BLACK));
        final Board board = new Board(boardMap);

        assertThatCode(() -> board.move(Position.of(A, ONE), Position.of(B, TWO)))
                .doesNotThrowAnyException();
    }

    @Test
    void 폰은_앞에_기물이_있다면_움직일_수_없다() {
        /*

        ........
        ........
        ........
        ........
        ........
        ........
        R.......
        p.......

         */

        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        boardMap.put(Position.of(A, TWO), new Rook(BLACK));
        final Board board = new Board(boardMap);

        assertThatThrownBy(() -> board.move(Position.of(A, ONE), Position.of(A, TWO)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 기물을 움직일 수 없습니다.");
    }

    @Test
    void 폰은_아군_기물인_경우에_대각선으로_움직일_수_없다() {
        /*

        ........
        ........
        ........
        ........
        ........
        ........
        .r......
        p.......

         */

        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        boardMap.put(Position.of(B, TWO), new Rook(WHITE));
        final Board board = new Board(boardMap);

        assertThatThrownBy(() -> board.move(Position.of(A, ONE), Position.of(B, TWO)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 기물을 움직일 수 없습니다.");
    }

    @Test
    void 폰은_한_번이라도_움직였다면_두_칸을_움직일_수_없다() {
        /*

        ........        ........
        ........        ........
        ........        ........
        ........        ........
        ........  -->   ........
        ........        ........
        ........        p.......
        p.......        ........

         */

        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        final Board board = new Board(boardMap);

        board.move(Position.of(A, ONE), Position.of(A, TWO));

        assertThatThrownBy(() -> board.move(Position.of(A, TWO), Position.of(A, FOUR)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 기물을 움직일 수 없습니다.");
    }

    @Test
    void 룩은_상하좌우_방향으로_여러_칸을_움직일_수_있다() {
        /*

        Q.......        r.......        .......r        ........        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........  -->   ........  -->   ........  -->   ........  -->   ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        r.......        ........        ........        .......r        r.......

         */

        boardMap.put(Position.of(A, ONE), new Rook(WHITE));
        boardMap.put(Position.of(A, EIGHT), new Queen(BLACK));
        final Board board = new Board(boardMap);

        final SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThatCode(() -> board.move(Position.of(A, ONE), Position.of(A, EIGHT)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(A, EIGHT), Position.of(H, EIGHT)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(H, EIGHT), Position.of(H, ONE)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(H, ONE), Position.of(A, ONE)))
                .doesNotThrowAnyException();

        softAssertions.assertAll();
    }

    @Test
    void 비숍은_대각선_방향으로_여러_칸을_움직일_수_있다() {
        /*

        .......Q        .......b        ........        ........        ........
        ........        ........        ........        b.......        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........  -->   ........  -->   ...b....  -->   ........  -->   ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        b.......        ........        ........        ........        ......b.

         */

        boardMap.put(Position.of(A, ONE), new Bishop(WHITE));
        boardMap.put(Position.of(H, EIGHT), new Queen(BLACK));
        final Board board = new Board(boardMap);

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThatCode(() -> board.move(Position.of(A, ONE), Position.of(H, EIGHT)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(H, EIGHT), Position.of(D, FOUR)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(D, FOUR), Position.of(A, SEVEN)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(A, SEVEN), Position.of(G, ONE)))
                .doesNotThrowAnyException();

        softAssertions.assertAll();
    }

    @Test
    void 퀸은_상하좌우_및_대각선_방향으로_여러_칸을_움직일_수_있다() {
        /*

        .......Q        .......q        q.......        ........        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........  -->   ........  -->   ........  -->   ........  -->   ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        q.......        ........        ........        .......q        q.......

         */

        boardMap.put(Position.of(A, ONE), new Queen(WHITE));
        boardMap.put(Position.of(H, EIGHT), new Queen(BLACK));
        final Board board = new Board(boardMap);

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThatCode(() -> board.move(Position.of(A, ONE), Position.of(H, EIGHT)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(H, EIGHT), Position.of(A, EIGHT)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(A, EIGHT), Position.of(H, ONE)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(H, ONE), Position.of(A, ONE)))
                .doesNotThrowAnyException();

        softAssertions.assertAll();
    }

    @Test
    void 나이트는_기물이_진로를_막고_있더라도_뛰어_넘을_수_있다() {
        /*

        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........  -->   ........  -->   ........  -->   ........  -->   ........
        ..PP....        .nPP....        ..PP....        .nPP....        ..PP....
        PPP.....        PPP.....        PPPn....        PPP.....        PPP.....
        nP......        .P......        .P......        .P......        nP......

         */

        boardMap.put(Position.of(A, ONE), new Knight(WHITE));
        boardMap.put(Position.of(A, TWO), new Pawn(BLACK));
        boardMap.put(Position.of(B, TWO), new Pawn(BLACK));
        boardMap.put(Position.of(C, TWO), new Pawn(BLACK));
        boardMap.put(Position.of(B, ONE), new Pawn(BLACK));
        boardMap.put(Position.of(C, THREE), new Pawn(BLACK));
        boardMap.put(Position.of(D, THREE), new Pawn(BLACK));
        final Board board = new Board(boardMap);

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThatCode(() -> board.move(Position.of(A, ONE), Position.of(B, THREE)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(B, THREE), Position.of(D, TWO)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(D, TWO), Position.of(B, THREE)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(B, THREE), Position.of(A, ONE)))
                .doesNotThrowAnyException();

        softAssertions.assertAll();
    }

    @Test
    void 킹은_상하좌우_및_대각선_방향으로_한_칸씩_이동할_수_있다() {
        /*

        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........        ........        ........        ........        ........
        ........  -->   ........  -->   ........  -->   ........  -->   ........
        ........        ........        ........        ........        ........
        P.......        k.......        .k......        ........        ...k....
        k.......        ........        ........        ..k.....        ........

         */

        boardMap.put(Position.of(A, ONE), new King(WHITE));
        boardMap.put(Position.of(A, TWO), new Pawn(BLACK));
        final Board board = new Board(boardMap);

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThatCode(() -> board.move(Position.of(A, ONE), Position.of(A, TWO)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(A, TWO), Position.of(B, TWO)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(B, TWO), Position.of(C, ONE)))
                .doesNotThrowAnyException();
        softAssertions.assertThatCode(() -> board.move(Position.of(C, ONE), Position.of(D, TWO)))
                .doesNotThrowAnyException();

        softAssertions.assertAll();
    }

    @Test
    void 비어있는_칸을_이동시킬_수_없다() {
        final Board board = new Board(boardMap);

        assertThatThrownBy(() -> board.move(Position.of(A, ONE), Position.of(A, TWO)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물이 있는 위치를 선택해주세요.");
    }

    @Test
    void 체스판_범위_검증을_할_수_있다() {
        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(Board.isInRange(0, 8)).isFalse();
        softAssertions.assertThat(Board.isInRange(1, 9)).isFalse();
        softAssertions.assertThat(Board.isInRange(1, 8)).isTrue();

        softAssertions.assertAll();
    }

    @Test
    void 위치를_통해_진영을_찾을_수_있다() {
        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        final Board board = new Board(boardMap);

        final Side side = board.findSideByPosition(Position.of(A, ONE));
        assertThat(side.isWhite()).isTrue();
    }

    @Test
    void 두_위치를_통해_서로_아군_관계인지_판단할_수_있다() {
        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        boardMap.put(Position.of(A, TWO), new Pawn(WHITE));
        final Board board = new Board(boardMap);

        assertThat(board.isAllyPosition(Position.of(A, ONE), Position.of(A, TWO))).isTrue();
    }

    @Test
    void 두_위치를_통해_서로_적군_관계인지_판단할_수_있다() {
        boardMap.put(Position.of(A, ONE), new Pawn(WHITE));
        boardMap.put(Position.of(A, TWO), new Pawn(BLACK));
        final Board board = new Board(boardMap);

        assertThat(board.isEnemyPosition(Position.of(A, ONE), Position.of(A, TWO))).isTrue();
    }

    @Test
    void 같은_진영의_기물_가치_합을_구할_수_있다() {
        /*

        .KR.....
        P.PB....
        .P..Q...
        ........
        .....nq.
        .....p.p
        .....pp.
        ....rk..

         */

        // given
        boardMap.put(Position.of(E, ONE), new Rook(WHITE));
        boardMap.put(Position.of(F, ONE), new King(WHITE));
        boardMap.put(Position.of(F, TWO), new Pawn(WHITE));
        boardMap.put(Position.of(G, TWO), new Pawn(WHITE));
        boardMap.put(Position.of(F, THREE), new Pawn(WHITE));
        boardMap.put(Position.of(H, THREE), new Pawn(WHITE));
        boardMap.put(Position.of(F, FOUR), new Knight(WHITE));
        boardMap.put(Position.of(G, FOUR), new Queen(WHITE));

        boardMap.put(Position.of(A, SEVEN), new Pawn(BLACK));
        boardMap.put(Position.of(B, SIX), new Pawn(BLACK));
        boardMap.put(Position.of(B, EIGHT), new King(BLACK));
        boardMap.put(Position.of(C, SEVEN), new Pawn(BLACK));
        boardMap.put(Position.of(C, EIGHT), new Rook(BLACK));
        boardMap.put(Position.of(D, SEVEN), new Bishop(BLACK));
        boardMap.put(Position.of(E, SIX), new Queen(BLACK));

        final Board board = new Board(boardMap);

        // when
        final double priceByWhite = board.calculatePriceBySide(WHITE);
        final double priceByBlack = board.calculatePriceBySide(BLACK);

        // then
        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(priceByWhite).isEqualTo(19.5);
        softAssertions.assertThat(priceByBlack).isEqualTo(20.0);

        softAssertions.assertAll();
    }

    @Test
    void 킹이_잡혔는지_판단할_수_있다() {
        /*

        ........        ........
        ........        ........
        ........        ........
        ........        ........
        ........  -->   ........
        K.......        q.......
        ........        ........
        q..k....        ...k....

         */

        boardMap.put(Position.of(A, ONE), new Queen(WHITE));
        boardMap.put(Position.of(D, ONE), new King(WHITE));
        boardMap.put(Position.of(A, THREE), new King(BLACK));
        final Board board = new Board(boardMap);

        board.move(Position.of(A, ONE), Position.of(A, THREE));

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(board.isKingCaptured(WHITE)).isFalse();
        softAssertions.assertThat(board.isKingCaptured(BLACK)).isTrue();
        softAssertions.assertThat(board.isKingCaptured()).isTrue();

        softAssertions.assertAll();
    }

    @Test
    void 흰_진영의_기물_점수가_더_높다면_흰_진영이_더_유리하다() {
        /*

        .K......
        ........
        ........
        ........
        ........
        ........
        ........
        ....rk..

        */

        boardMap.put(Position.of(E, ONE), new Rook(WHITE));
        boardMap.put(Position.of(F, ONE), new King(WHITE));
        boardMap.put(Position.of(B, EIGHT), new King(BLACK));
        final Board board = new Board(boardMap);

        assertThat(board.calculateAdvantageSide()).isEqualTo(WHITE);
    }

    @Test
    void 검은_진영의_기물_점수가_더_높다면_검은_진영이_더_유리하다() {
        /*

        .KR.....
        ........
        ........
        ........
        ........
        ........
        ........
        .....k..

        */

        boardMap.put(Position.of(F, ONE), new King(WHITE));
        boardMap.put(Position.of(C, EIGHT), new Rook(BLACK));
        boardMap.put(Position.of(B, EIGHT), new King(BLACK));
        final Board board = new Board(boardMap);

        assertThat(board.calculateAdvantageSide()).isEqualTo(BLACK);
    }

    @Test
    void 두_진영의_기물_점수가_같다면_어떤_진영도_유리하지_않으므로_중립을_반환한다() {
        /*

        .KR.....
        ........
        ........
        ........
        ........
        ........
        ........
        ....rk..

        */

        boardMap.put(Position.of(E, ONE), new Rook(WHITE));
        boardMap.put(Position.of(F, ONE), new King(WHITE));
        boardMap.put(Position.of(C, EIGHT), new Rook(BLACK));
        boardMap.put(Position.of(B, EIGHT), new King(BLACK));
        final Board board = new Board(boardMap);

        assertThat(board.calculateAdvantageSide()).isEqualTo(NEUTRALITY);
    }
}
