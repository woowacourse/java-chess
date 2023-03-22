package chess.domain.board;

import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.board.InitialPieceTypes.*;
import static chess.fixture.CoordinateFixture.A_TWO;
import static chess.fixture.CoordinateFixture.B_ONE;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InitialPieceTypesTest {
    
    @Test
    void 좌표에_맞는_InitialPieceTypes_반환() {
        InitialPieceTypes initialPieceTypes = from(A_TWO);
        assertThat(initialPieceTypes).isEqualTo(PAWN_TYPES);
    }
    
    @Test
    void Column을_통해_PieceType_꺼내기() {
        PieceType pieceType = HERO_PIECE_TYPES.findPieceTypeByColumn(B_ONE);
        assertThat(pieceType).isEqualTo(PieceType.KNIGHT);
    }
}