package domain.piece;

import static org.assertj.core.api.Assertions.*;

import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import domain.piece.info.Vector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoneTest {

    @Test
    @DisplayName("비어있는 칸을 움직이려고 하면 예외가 발생한다")
    void moveNone() {
        final Piece empty = Empty.INSTANCE;
        final Piece other = new Rook(Color.BLACK);
        final Position source = new Position(File.D, Rank.FOUR);
        final Position target = new Position(File.D, Rank.SEVEN);

        assertThatThrownBy(() -> empty.isReachable(new Vector(source, target), other))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("비어 있는 칸입니다.");
    }
}
