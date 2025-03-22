package chess.piece;

import chess.Color;
import chess.Fixtures;
import chess.Row;
import chess.exception.InvalidMoveException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RookTest {

    @Test
    void 룩은_위로_이동할_수_있다(){
        Rook rook = new Rook(Fixtures.A2,Color.WHITE);
        rook.move(Fixtures.A3, List.of());
        Assertions.assertThat(rook.getPosition()).isEqualTo(Fixtures.A3);
    }

    @Test
    void 룩은_오른쪽으로_이동할_수_있다(){
        Rook rook = new Rook(Fixtures.A2,Color.WHITE);
        rook.move(Fixtures.B2, List.of());
        Assertions.assertThat(rook.getPosition()).isEqualTo(Fixtures.B2);
    }

    @Test
    void 룩은_아래로_이동할_수_있다(){
        Rook rook = new Rook(Fixtures.B2,Color.WHITE);
        rook.move(Fixtures.B1, List.of());
        Assertions.assertThat(rook.getPosition()).isEqualTo(Fixtures.B1);
    }

    @Test
    void 룩은_왼쪽으로_이동할_수_있다(){
        Rook rook = new Rook(Fixtures.B2,Color.WHITE);
        rook.move(Fixtures.A2, List.of());
        Assertions.assertThat(rook.getPosition()).isEqualTo(Fixtures.A2);
    }

    @Test
    void 룩은_이동할려는위치_이전에_다른_기물이_있으면_이동할수_없다(){
        Rook rook = new Rook(Fixtures.A1,Color.WHITE);
        Bishop enemy = new Bishop(Fixtures.B1,Color.WHITE);
        Bishop enemy2 = new Bishop(Fixtures.C1,Color.BLACK);

        Assertions.assertThatThrownBy(() -> rook.move(Fixtures.E3,List.of(enemy,enemy2))).isInstanceOf(InvalidMoveException.class);
    }

    @Test
    void 룩은_같은팀이_있는곳으로_이동할_수_없다(){
        Rook rook = new Rook(Fixtures.A2,Color.WHITE);
        Pawn sameTeam = new Pawn(Fixtures.A3,Color.WHITE);
        Assertions.assertThatThrownBy(() -> rook.move(Fixtures.A3,List.of(sameTeam))).isInstanceOf(InvalidMoveException.class);
    }

    @Test
    void 룩은_다른팀이_있는곳으로_이동할_수_있다(){
        Rook rook = new Rook(Fixtures.A2,Color.WHITE);
        Pawn enemy = new Pawn(Fixtures.A3,Color.BLACK);
        rook.move(Fixtures.A3, List.of(enemy));
        Assertions.assertThat(rook.getPosition()).isEqualTo(Fixtures.A3);
    }
}


