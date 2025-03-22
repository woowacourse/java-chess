package chess.piece;

import chess.Color;
import chess.Fixtures;
import chess.exception.InvalidMoveException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @Test
    void 말은_왼쪽_두칸_위_한칸으로_이동할_수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        knight.move (Fixtures.B5,List.of());
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.B5);
    }

    @Test
    void 말은_왼쪽_한칸_위_두칸으로_이동할_수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        knight.move (Fixtures.C6,List.of());
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.C6);
    }

    @Test
    void 말은_오른쪽_한칸_위_두칸으로_이동할_수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        knight.move (Fixtures.E6,List.of());
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.E6);
    }

    @Test
    void 말은_오른쪽_두칸_위_한칸으로_이동할_수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        knight.move (Fixtures.F5,List.of());
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.F5);
    }

    @Test
    void 말은_오른쪽_두칸_아래_한칸으로_이동할_수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        knight.move (Fixtures.E2,List.of());
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.E2);
    }

    @Test
    void 말은_오른쪽_한칸_아래_두칸_으로_이동할_수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        knight.move (Fixtures.E2,List.of());
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.E2);
    }

    @Test
    void 말은_아래_한칸_왼쪽_두칸으로_이동할_수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        knight.move (Fixtures.B3,List.of());
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.B3);
    }

    @Test
    void 말은_아래_두칸_왼쪽_한칸으로_이동할_수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        knight.move (Fixtures.C2,List.of());
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.C2);
    }

    @Test
    void 말은_직선으로_이동할수_없다(){
        Knight knight = new Knight(Fixtures.C1,Color.WHITE);
        Assertions.assertThatThrownBy(() -> knight.move (Fixtures.C2,List.of()))
                .isInstanceOf(InvalidMoveException.class);
    }

    @Test
    void 같은팀이_있는곳으로는_이동할수_없다(){
        Knight knight = new Knight(Fixtures.C1,Color.WHITE);
        Pawn sameColorTeam = new Pawn(Fixtures.D2,Color.WHITE);
        Assertions.assertThatThrownBy(() -> knight.move (Fixtures.D2, List.of(sameColorTeam))).isInstanceOf(
                InvalidMoveException.class);
    }

    @Test
    void 적팀_있는곳으로는_이동할수_있다(){
        Knight knight = new Knight(Fixtures.D4,Color.WHITE);
        Pawn enemy = new Pawn(Fixtures.E6,Color.BLACK);
        knight.move(Fixtures.E6,List.of(enemy));
        Assertions.assertThat(knight.getPosition()).isEqualTo(Fixtures.E6);
    }
}


