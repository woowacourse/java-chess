package chess.piece;

import chess.Color;
import chess.Fixtures;
import chess.exception.InvalidMoveException;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    void 비숍은_대각선으로_이동할수_있다(){
        Bishop bishop = new Bishop(Fixtures.C1,Color.WHITE);
        bishop.move(Fixtures.D2,List.of());
        Assertions.assertThat(bishop.getPosition()).isEqualTo(Fixtures.D2);
    }

    @Test
    void 비숍은_직선으로_이동할수_없다(){
        Bishop bishop = new Bishop(Fixtures.C1,Color.WHITE);
        Assertions.assertThatThrownBy(() -> bishop.move(Fixtures.C2,List.of()))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    void 비숍은_적팀이_있는_대각선으로_이동할수_있다(){
        Bishop bishop = new Bishop(Fixtures.C1,Color.WHITE);
        bishop.move(Fixtures.D2,List.of());
        Assertions.assertThat(bishop.getPosition()).isEqualTo(Fixtures.D2);
    }

    @Test
    void 같은팀이_있는곳으로는_이동할수_없다(){
        Bishop bishop = new Bishop(Fixtures.C1,Color.WHITE);
        Pawn sameColorTeam = new Pawn(Fixtures.D2,Color.WHITE);
        Assertions.assertThatThrownBy(() -> bishop.move(Fixtures.D2, List.of(sameColorTeam))).isInstanceOf(
                InvalidMoveException.class);
    }

    @Test
    void 적팀이_있는곳으로는_이동할수_있다(){
        Bishop bishop = new Bishop(Fixtures.C1,Color.WHITE);
        Pawn enemy = new Pawn(Fixtures.D2,Color.BLACK);
        bishop.move(Fixtures.D2,List.of(enemy));
        Assertions.assertThat(bishop.getPosition()).isEqualTo(Fixtures.D2);
    }
}


