//package chess.model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class PawnTest {
//    private Pawn whitePawn;
//    private Pawn blackPawn;
//
//    @BeforeEach
//    void setUp() {
//        blackPawn = new Pawn(ChessPieceColor.BLACK);
//        whitePawn = new Pawn(ChessPieceColor.WHITE);
//    }
//
//    @Test
//    void 흰색_한칸_이동_가능_테스트() {
//        Map<Point, AbstractChessPiece> board = new HashMap<>();
//        Point source = new Point(3,7);
//        Point target = new Point(3, 8);
//        board.put(source, whitePawn);
//        assertThat(whitePawn.canMove(source, target, board::get)).isTrue();
//    }
//
//    @Test
//    void 흑색_한칸_이동_가능_테스트() {
//        Map<Point, AbstractChessPiece> board = new HashMap<>();
//        Point source = new Point(3,2);
//        Point target = new Point(3, 1);
//        board.put(source, blackPawn);
//        assertThat(blackPawn.canMove(source, target, board::get)).isTrue();
//    }
//
//    @Test
//    void 흰색_유닛에_막혀_한칸_이동_불가능_테스트_() {
//        Map<Point, AbstractChessPiece> board = new HashMap<>();
//        Point source = new Point(3,7);
//        Point target = new Point(3, 8);
//        board.put(source, whitePawn);
//        board.put(target, blackPawn);
//        assertThat(whitePawn.canMove(source, target, board::get)).isFalse();
//    }
//
//    @Test
//    void 흑색_유닛에_막혀_한칸_이동_불가능_테스트_() {
//        Map<Point, AbstractChessPiece> board = new HashMap<>();
//        Point source = new Point(3,2);
//        Point target = new Point(3, 1);
//        board.put(source, blackPawn);
//        board.put(target, whitePawn);
//        assertThat(blackPawn.canMove(source, target, board::get)).isFalse();
//    }
//
//    @Test
//    void 흰색_폰_아래로_한칸_이동_불가능_테스트_() {
//        Map<Point, AbstractChessPiece> board = new HashMap<>();
//        Point source = new Point(3,2);
//        Point target = new Point(3, 1);
//        board.put(source, whitePawn);
//        assertThat(whitePawn.canMove(source, target, board::get)).isFalse();
//    }
//
//    @Test
//    void 흑색_폰_위로_한칸_이동_불가능_테스트_() {
//        Map<Point, AbstractChessPiece> board = new HashMap<>();
//        Point source = new Point(3,7);
//        Point target = new Point(3, 8);
//        board.put(source, blackPawn);
//        assertThat(blackPawn.canMove(source, target, board::get)).isFalse();
//    }
//
//
//
////    @Test
////    void 흑색_출발시_2칸_이동_가능_테스트() {
////        Point source = new Point(4, 7);
////        Point target = new Point(4, 5);
////        assertThat(blackPawn.canMove(source, target)).isTrue();
////    }
////
////    @Test
////    void 백색_출발시_2칸_이동_가능_테스트() {
////        Point source = new Point(3, 2);
////        Point target = new Point(3, 4);
////        assertThat(whitePawn.canMove(source, target)).isTrue();
////    }
//
//}