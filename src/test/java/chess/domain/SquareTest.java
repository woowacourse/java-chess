package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    void should_기물을_변경한다_when_changePiece메서드를_호출하면() {
        //given
        Position position = new Position(Rank.C, File.THREE);
        Piece origin = new Pawn(Team.WHITE);
        Piece changed = new Queen(Team.BLACK);
        Square square = new Square(position, origin);

        //when
        square.changePiece(changed);

        //then
        assertThat(square).extracting("piece")
                .isEqualTo(changed);
    }

}