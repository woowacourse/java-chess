package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class InitializedBoardTest {
    
    private static Stream<Arguments> generateNonPawnSource() {
        return Stream.of(Arguments.of("a", Rook.class), Arguments.of("b", Knight.class), Arguments.of("c",
                Bishop.class), Arguments.of("d", Queen.class), Arguments.of("e", King.class), Arguments.of("f",
                Bishop.class), Arguments.of("g", Knight.class), Arguments.of("h", Rook.class));
    }
    
    @ParameterizedTest(name = "폰이 아닌 기물의 초기화 테스트")
    @MethodSource("generateNonPawnSource")
    void initializedNonPawnTest(String file, Class<Piece> pieceClass) {
        
        // given
        final int blackRow = 1;
        final int whiteRow = 8;
        
        // when
        final Map<Position, Piece> chessBord = InitializedBoard.create()
                                                               .getBoard();
        
        // then
        assertThat(chessBord.get(Position.from(file + blackRow))).isInstanceOf(pieceClass);
        assertThat(chessBord.get(Position.from(file + whiteRow))).isInstanceOf(pieceClass);
    }
    
    @Test
    @DisplayName("폰 초기화 테스트")
    void initializedPawnTest() {
        
        // when
        final Map<Position, Piece> chessBord = InitializedBoard.create()
                                                               .getBoard();
        
        // then
        for (int i = 0; i < 8; i++) {
            Position position = Position.of(i, 1);
            assertThat(chessBord.get(position)).isInstanceOf(Pawn.class);
        }
        
        for (int i = 0; i < 8; i++) {
            Position position = Position.of(i, 6);
            assertThat(chessBord.get(position)).isInstanceOf(Pawn.class);
        }
    }
    
    @ParameterizedTest(name = "블랭크 초기화 테스트")
    @ValueSource(ints = {2, 3, 4, 5})
    void initializedBlankTest(int rank) {
        
        // when
        final Map<Position, Piece> chessBord = InitializedBoard.create()
                                                               .getBoard();
        
        // then
        for (int i = 0; i < 8; i++) {
            Position position = Position.of(i, rank);
            assertThat(chessBord.get(position)).isInstanceOf(Blank.class);
        }
    }
}