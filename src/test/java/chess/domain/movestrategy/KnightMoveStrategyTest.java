//package chess.domain.movestrategy;
//
//import chess.domain.board.Board;
//import chess.domain.board.BoardGenerator;
//import chess.domain.board.position.Position;
//import chess.domain.piece.Knight;
//import chess.domain.piece.Pawn;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class KnightMoveStrategyTest {
//    private BoardGenerator boardGenerator;
//
//    @BeforeEach
//    void setUp() {
////        boardGenerator = new BoardGenerator();
//    }
//
//    @Test
//    @DisplayName("룩 이동 가능 경로 확인")
//    void movable() {
//        boardGenerator.put(Position.of("f5"), Pawn.createBlack());
//        boardGenerator.put(Position.of("e6"), Pawn.createBlack());
//        boardGenerator.put(Position.of("d7"), Pawn.createWhite());
//
//        boardGenerator.put(Position.of("f6"), Knight.createWhite());
//        boardGenerator.print();
//        Board board = new Board(boardGenerator.create());
//
//        MoveStrategy moveStrategy = Knight.createWhite().moveStrategy();
//        for (Position position : moveStrategy.movable(board, Position.of("f6"))) {
//            System.out.println(position);
//        }
//        assertThat(moveStrategy.movable(board, Position.of("f6"))).containsAll(Arrays.asList(Position.of("a8")));
//    }
//}
