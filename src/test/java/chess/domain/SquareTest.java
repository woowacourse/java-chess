package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Role;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    void should_soure에서Destination으로기물이이동한다_when_moveTo가호출됐을때() {
        //given
        Square source = new Square(Position.of(File.F, Rank.FIVE), new King(Team.BLACK));
        Square destination = new Square(Position.of(File.F, Rank.SIX), Blank.getInstance());

        //when
        source.moveTo(destination);

        //then
        assertAll(
                () -> assertThat(source.hasSameRole(Role.BLANK)).isTrue(),
                () -> assertThat(destination.hasSameRole(Role.KING)).isTrue()
        );
    }

}
