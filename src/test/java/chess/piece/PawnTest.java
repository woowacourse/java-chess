package chess.piece;

import chess.Color;
import chess.Fixtures;
import chess.Position;
import chess.exception.InvalidMoveException;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    void 백_폰은_처음_위치에서_두칸_이동할수_있다(){
        Pawn pawn = new Pawn(Fixtures.A2, Color.WHITE);
        pawn.move(Fixtures.A4, List.of());
        Assertions.assertThat(pawn.getPosition()).isEqualTo(Fixtures.A4);
    }

    @Test
    void 백_폰은_처음_위치에서_한칸_이동할수_있다(){
        Pawn pawn = new Pawn(Fixtures.A2, Color.WHITE);
        pawn.move(Fixtures.A3, List.of());
        Assertions.assertThat(pawn.getPosition()).isEqualTo(Fixtures.A3);
    }

    @Test
    void 이동할수_없는곳이면_예외가_발생한다(){
        Pawn pawn = new Pawn(Fixtures.A2, Color.WHITE);
        Assertions.assertThatThrownBy(() -> pawn.move(Fixtures.A2,List.of())).isInstanceOf(InvalidMoveException.class);
    }

    @Test
    void 흑_폰은_처음_위치에서_두칸_이동할수_있다(){
        Pawn pawn = new Pawn(Fixtures.A7, Color.BLACK);
        pawn.move(Fixtures.A5, List.of());
        Assertions.assertThat(pawn.getPosition()).isEqualTo(Fixtures.A5);
    }

    @Test
    void 흑_폰은_처음_위치에서_한칸_이동할수_있다(){
        Pawn pawn = new Pawn(Fixtures.A7, Color.BLACK);
        pawn.move(Fixtures.A6, List.of());
        Assertions.assertThat(pawn.getPosition()).isEqualTo(Fixtures.A6);
    }

    @Test
    void 백_폰은_대각선의_흑폰으로_이동할수_있다(){
        Pawn pawn = new Pawn(Fixtures.A2, Color.WHITE);
        Pawn enemyPawn = new Pawn(Fixtures.B3, Color.BLACK);
        pawn.move(Fixtures.B3,List.of(enemyPawn));
        Assertions.assertThat(pawn.getPosition()).isEqualTo(Fixtures.B3);
    }

    @Test
    void 흑_폰은_대각선의_백폰으로_이동할수_있다(){
        Pawn pawn = new Pawn(Fixtures.A7, Color.BLACK);
        Pawn enemyPawn = new Pawn(Fixtures.B6, Color.WHITE);
        pawn.move(Fixtures.B3,List.of(enemyPawn));
        Assertions.assertThat(pawn.getPosition()).isEqualTo(Fixtures.B3);
    }
}
