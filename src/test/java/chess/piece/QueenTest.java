package chess.piece;

import chess.Color;
import chess.Fixtures;
import chess.exception.InvalidMoveException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    void 퀸은_왼쪽으로_이동할_수_있다(){
        Queen queen = new Queen(Fixtures.B2,Color.WHITE);
        queen.move(Fixtures.A2, List.of());
        Assertions.assertThat(queen.getPosition()).isEqualTo(Fixtures.A2);
    }

    @Test
    void 퀸은_오른쪽으로_이동할_수_있다(){
        Queen queen = new Queen(Fixtures.C2,Color.WHITE);
        queen.move(Fixtures.D2, List.of());
        Assertions.assertThat(queen.getPosition()).isEqualTo(Fixtures.D2);
    }

    @Test
    void 퀸은_아래로_이동할_수_있다(){
        Queen queen = new Queen(Fixtures.C2,Color.WHITE);
        queen.move(Fixtures.C1, List.of());
        Assertions.assertThat(queen.getPosition()).isEqualTo(Fixtures.C1);
    }

    @Test
    void 퀸은_위로_이동할_수_있다(){
        Queen queen = new Queen(Fixtures.C1,Color.WHITE);
        queen.move(Fixtures.C2, List.of());
        Assertions.assertThat(queen.getPosition()).isEqualTo(Fixtures.C2);
    }

    @Test
    void 퀸은_오른쪽_위로_이동할수_있다(){
        Queen queen = new Queen(Fixtures.C1,Color.WHITE);
        queen.move(Fixtures.D2, List.of());
        Assertions.assertThat(queen.getPosition()).isEqualTo(Fixtures.D2);
    }

    @Test
    void 퀸은_왼쪽_위로_이동할수_있다(){
        Queen queen = new Queen(Fixtures.C1,Color.WHITE);
        queen.move(Fixtures.B2, List.of());
        Assertions.assertThat(queen.getPosition()).isEqualTo(Fixtures.B2);
    }

    @Test
    void 퀸은_오른쪽_아래로_이동할수_있다(){
        Queen queen = new Queen(Fixtures.D4,Color.WHITE);
        queen.move(Fixtures.F2, List.of());
        Assertions.assertThat(queen.getPosition()).isEqualTo(Fixtures.F2);
    }

    @Test
    void 퀸은_왼쪽_아래로_이동할수_있다(){
        Queen queen = new Queen(Fixtures.D4,Color.WHITE);
        queen.move(Fixtures.A1, List.of());
        Assertions.assertThat(queen.getPosition()).isEqualTo(Fixtures.A1);
    }

    @Test
    void 퀸은_이동할려는위치_이전에_다른_기물이_있으면_이동할수_없다(){
        Queen queen = new Queen(Fixtures.D4,Color.WHITE);
        Bishop enemy = new Bishop(Fixtures.C3,Color.WHITE);
        Bishop enemy2 = new Bishop(Fixtures.B2,Color.BLACK);

        Assertions.assertThatThrownBy(() -> queen.move(Fixtures.A1, List.of(enemy,enemy2)))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    void 퀸은_같은팀이_있는곳으로_이동할_수_없다(){
        Queen queen = new Queen(Fixtures.D4,Color.WHITE);
        Pawn sameTeam = new Pawn(Fixtures.D5,Color.WHITE);
        Assertions.assertThatThrownBy(() -> queen.move(Fixtures.D5,List.of(sameTeam))).isInstanceOf(InvalidMoveException.class);
    }

    @Test
    void 퀸은_다른팀이_있는곳으로_이동할_수_있다(){
        Queen queen = new Queen(Fixtures.D4,Color.WHITE);
        Pawn enemy = new Pawn(Fixtures.D5,Color.BLACK);
        queen.move(Fixtures.D5,List.of(enemy));

        Assertions.assertThat(enemy.getPosition()).isEqualTo(Fixtures.D5);
    }
}


