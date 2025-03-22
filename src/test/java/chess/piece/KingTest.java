package chess.piece;

import chess.Color;
import chess.Fixtures;
import chess.exception.InvalidMoveException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;

public class KingTest {

    @Test
    void 킹은_위로_한칸_이동할수_있다(){
        King king = new King(Fixtures.A2,Color.WHITE);
        king.move(Fixtures.A3, List.of());
        Assertions.assertThat(king.getPosition()).isEqualTo(Fixtures.A3);
    }

    @Test
    void 킹은_오른쪽으로_한칸_이동할수_있다(){
        King king = new King(Fixtures.A2,Color.WHITE);
        king.move(Fixtures.B2, List.of());
        Assertions.assertThat(king.getPosition()).isEqualTo(Fixtures.B2);
    }

    @Test
    void 킹은_왼쪽_한칸_이동할수_있다(){
        King king = new King(Fixtures.B2,Color.WHITE);
        king.move(Fixtures.A2, List.of());
        Assertions.assertThat(king.getPosition()).isEqualTo(Fixtures.A2);
    }

    @Test
    void 킹은_아래로_한칸_이동할수_있다(){
        King king = new King(Fixtures.D2,Color.WHITE);
        king.move(Fixtures.D1, List.of());
        Assertions.assertThat(king.getPosition()).isEqualTo(Fixtures.D1);
    }

    @Test
    void 킹은_적팀이_있으면_잡을수_있다(){
        King king = new King(Fixtures.A2,Color.WHITE);
        Pawn enemy = new Pawn(Fixtures.A3,Color.BLACK);
        king.move(Fixtures.A3, List.of(enemy));
        Assertions.assertThat(king.getPosition()).isEqualTo(Fixtures.A3);
    }

    @Test
    void 킹은_같은팀이_있는곳으로_이동할_수_없다(){
        King king = new King(Fixtures.A2,Color.WHITE);
        Pawn sameTeam = new Pawn(Fixtures.A3,Color.WHITE);
        Assertions.assertThatThrownBy(() -> king.move(Fixtures.A3,List.of(sameTeam))).isInstanceOf(InvalidMoveException.class);
    }
}
