package chess.domain.chessboard.state;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {

    @Test
    void 폰은_팀을_가진다(){
        //given
        final Team team = Team.BLACK;

        //then
        assertDoesNotThrow(()-> new Pawn(team));
    }
}
