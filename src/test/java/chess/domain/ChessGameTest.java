package chess.domain;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ChessGameTest {

    private static ChessGame chessGame = new ChessGame();

    @Test
    void test0_정상_생성() {
        assertDoesNotThrow(ChessGame::new);
    }

    @Test
    void test1_흰색_폰_두칸_정상_이동() {
        Point source = PointFactory.of("d2");
        Point target = PointFactory.of("d4");
        // void 리턴은 assertDoesNotThrow 사용할 수 없음
        chessGame.play(source, target);
        //assertTrue(chessGame.hasPiece(PointFactory.of("d4"), new Pawn(Color.WHITE)))
    }

    @Test
    void test2_검은색_폰_두칸_정상_이동() {
        Point source = PointFactory.of("c7");
        Point target = PointFactory.of("c5");
        chessGame.play(source, target);
    }

    @Test
    void test3_흰색_폰_대각선_공격() {
        Point source = PointFactory.of("d4");
        Point target = PointFactory.of("c5");
        chessGame.play(source, target);
    }

    @Test
    void test4_검은색_폰_제거_확인() {
        Point source = PointFactory.of("c5");
        Point target = PointFactory.of("c4");
        assertThrows(IllegalArgumentException.class, () -> chessGame.play(source, target));
    }

    @Test
    void test5_검은색_나이트_비정상_이동() {
        // 같은 검은색 폰이 있는 곳으로 이동할 때 익셉션
        Point source = PointFactory.of("b8");
        Point target = PointFactory.of("d7");
        assertThrows(IllegalArgumentException.class, () -> chessGame.play(source, target));
    }

    @Test
    void test6_검은색_나이트_정상_이동() {
        Point source = PointFactory.of("b8");
        Point target = PointFactory.of("a6");
        chessGame.play(source, target);
    }

    @Test
    void test7_흰색_폰_비정상_공격() {
        Point source = PointFactory.of("c5");
        Point target = PointFactory.of("c4");
        assertThrows(IllegalArgumentException.class, () -> chessGame.play(source, target));
    }
}
