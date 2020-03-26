import chess.domain.ChessBoard;
import chess.domain.Player;
import chess.domain.chesspieces.Empty;
import chess.domain.chesspieces.King;
import chess.domain.chesspieces.Pawn;
import chess.domain.moverules.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.views.OutputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @DisplayName("Pawn: 이동 가능한 방향 확인 (White User)")
    @Test
    void pawnMoveRulesTest_whiteUser() {
//        P                                                                                                                                                                                                                                     tions.assertThat(directions.contains(Direction.DIAGONAL_DOWN_LEFT)).isTrue();
    }

    @DisplayName("Pawn: 이동 가능한 방향 확인 (Black User)")
    @Test
    void pawnMoveRulesTest_blackUser() {
//        Pawn pawn = new Pawn(Player.WHITE);
//        List<Direction> directions = king.getDirections();
//        Assertions.assertThat(directions.contains(Direction.DIAGONAL_DOWN_LEFT)).isTrue();
    }


    @Test
    void 초기_1칸_이동_정상() {
        Pawn pawn = new Pawn(Player.WHITE, Positions.of("a2"));
        Position source = Positions.of("a2");
        Position target = Positions.of("a3");
        assertThat(pawn.movable(source, target)).isTrue();
    }

    @Test
    void 초기_2칸_이동_정상() {
        Pawn pawn = new Pawn(Player.WHITE, Positions.of("a2"));
        Position source = Positions.of("a2");
        Position target = Positions.of("a4");
        assertThat(pawn.movable(source, target)).isTrue();
    }

    @Test
    void 예외_이동한_후_2번_이동했을때() {
        ChessBoard chessBoard = new ChessBoard();

        Position source = Positions.of("a2");
        Position target = Positions.of("a4");
        chessBoard.move(source, target);

        source = Positions.of("a4");
        target = Positions.of("a6");
        assertThat(chessBoard.move(source, target)).isFalse();
    }

    @Test
    void 초기x_전진_앞에_아무도없다() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Positions.of("a2");
        Position target = Positions.of("a4");
        chessBoard.move(source, target);

        source = Positions.of("a4");
        target = Positions.of("a5");
        chessBoard.move(source, target);
        assertThat(chessBoard.getChessBoard().get(target)).isInstanceOf(Pawn.class);
    }

    @Test
    void 초기x_전진불가_앞에_누가있다() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Positions.of("a7");
        Position target = Positions.of("a5");
        chessBoard.move(source, target);
        OutputView.printChessBoard(chessBoard.getChessBoard());

        source = Positions.of("a5");
        target = Positions.of("a4");
        chessBoard.move(source, target);
        OutputView.printChessBoard(chessBoard.getChessBoard());

        source = Positions.of("a2");
        target = Positions.of("a4");
        chessBoard.move(source, target);
        OutputView.printChessBoard(chessBoard.getChessBoard());

        assertThat(chessBoard.getChessBoard().get(target)).isInstanceOf(Pawn.class);
        assertThat(chessBoard.getChessBoard().get(source)).isInstanceOf(Pawn.class);
    }

    @Test
    void 초기x_공격_대각선_공격가능_적이있을때_공격성공() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Positions.of("b7");
        Position target = Positions.of("b5");
        chessBoard.move(source, target);
//        OutputView.printChessBoard(chessBoard.getChessBoard());

        source = Positions.of("a2");
        target = Positions.of("a4");
        chessBoard.move(source, target);
//        OutputView.printChessBoard(chessBoard.getChessBoard());

        source = Positions.of("a4");
        target = Positions.of("b5");
        chessBoard.move(source, target);

//        OutputView.printChessBoard(chessBoard.getChessBoard());

        assertThat(chessBoard.getChessBoard().get(target)).isInstanceOf(Pawn.class);
        assertThat(chessBoard.getChessBoard().get(source)).isInstanceOf(Empty.class);
    }

    @Test
    void 공격불가_같은팀이있을때() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Positions.of("a2");
        Position target = Positions.of("a3");
        chessBoard.move(source, target);

        source = Positions.of("b2");
        target = Positions.of("b4");
        chessBoard.move(source, target);

        source = Positions.of("a3");
        target = Positions.of("b4");

        assertThat(chessBoard.move(source, target)).isFalse();
    }

    @Test
    void 공격불가_아무도없을때() {
        ChessBoard chessBoard = new ChessBoard();

        Position source = Positions.of("a1");
        Position target = Positions.of("b2");

        assertThat(chessBoard.move(source, target)).isFalse();
    }
}

