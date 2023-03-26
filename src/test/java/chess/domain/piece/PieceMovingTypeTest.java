package chess.domain.piece;

import chess.domain.board.BlackWhiteChessBoard;
import chess.domain.board.coordinate.Coordinate;
import chess.fixture.CoordinateFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static chess.fixture.CoordinateFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceMovingTypeTest {
    
    @Test
    void 갈_수_있는_좌표를_모두_반환() {
        PieceMovingType pawn = PieceMovingType.PAWN;
        Set<Coordinate> coordinates = pawn.movableRouteSearch(BlackWhiteChessBoard.create(), Piece.from(A_TWO), A_TWO);
        assertThat(coordinates).contains(A_THREE, A_FOUR);
    }
}