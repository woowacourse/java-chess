package chess.piece;

import chess.Color;
import chess.Fixtures;
import chess.exception.InvalidMoveException;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemovePieceTest {

    @Test
    void 죽는_기물을_반환한다(){
        Pawn pawn = new Pawn(Fixtures.A4, Color.WHITE);
        Pawn enemy = new Pawn(Fixtures.A4, Color.BLACK);
        RemovePiece removePiece = new RemovePiece(pawn, enemy);
        Assertions.assertThat(removePiece.getDeadPiece().get()).isEqualTo(enemy);
    }

    @Test
    void 죽는_기물이_없으면_반환하지_않는다(){
        Pawn pawn = new Pawn(Fixtures.A4, Color.WHITE);
        Pawn enemy = new Pawn(Fixtures.A5, Color.BLACK);
        RemovePiece removePiece = new RemovePiece(pawn, enemy);
        Assertions.assertThat(removePiece.getDeadPiece()).isEqualTo(Optional.empty());
    }
}
