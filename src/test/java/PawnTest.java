import chess.domain.ChessBoard;
import chess.domain.Player;
import chess.domain.chesspieces.Empty;
import chess.domain.chesspieces.Pawn;
import chess.domain.chesspieces.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.views.OutputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    /*
    규칙 1. 처음에는 1칸 or 2칸 이동
    규칙 2. 그 이후 + 앞에 아무도 없을 때:  1칸만 전진할 수 있다.
    규칙 3. 그 이휴 + 대각선 1칸 앞쪽 양 옆에 적이 있을 때: 거기로 이동 가능
        * 같은 팀이면 안됨.
     */

    @Test
    void 초기_1칸_이동_정상() {
        Pawn pawn = new Pawn(Player.WHITE);
        Position source = Positions.of("a1");
        Position target = Positions.of("a2");
        assertThat(pawn.movable(source, target)).isTrue();
    }

    @Test
    void 초기_2칸_이동_정상() {
        Pawn pawn = new Pawn(Player.WHITE);
        Position source = Positions.of("a1");
        Position target = Positions.of("a3");
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

        source = Positions.of("a5");
        target = Positions.of("a4");
        chessBoard.move(source, target);

        source = Positions.of("a2");
        target = Positions.of("a4");
        chessBoard.move(source, target);

        assertThat(chessBoard.getChessBoard().get(target)).isInstanceOf(Pawn.class);
        assertThat(chessBoard.getChessBoard().get(source)).isInstanceOf(Pawn.class);
    }

    @Test
    void 초기x_공격_대각선_공격가능_적이있을때() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Positions.of("b7");
        Position target = Positions.of("b5");
        chessBoard.move(source, target);

        source = Positions.of("a2");
        target = Positions.of("a4");
        chessBoard.move(source, target);

        source = Positions.of("a4");
        target = Positions.of("b5");
        chessBoard.move(source, target);

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

