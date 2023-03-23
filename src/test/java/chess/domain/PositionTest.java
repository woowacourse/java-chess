package chess.domain;

import chess.domain.movepattern.RookMovePattern;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PositionTest {

    @Test
    void 패턴에_맞게_움직일_수_있다() {
        final Position target = Position.of(File.getFile(1), Rank.getRank(1));
        final Position expect = Position.of(File.getFile(1), Rank.getRank(2));

        final Position movedPosition = target.move(RookMovePattern.UP);

        Assertions.assertThat(movedPosition).isEqualTo(expect);
    }
}
