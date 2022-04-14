package chess.model.PieceArrangement;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.MappingUtil;
import chess.model.Position;
import chess.model.piece.Piece;
import chess.model.piece.PieceCache;

class SavedPieceArrangementTest {

    @Test
    @DisplayName("저장된 String Piece 정보로부터 초기화된 맵을 반환한다.")
    void apply() {
        //given
        Map<Position, Piece> testArrangement = Map.of(Position.of("a2"), PieceCache.of("b"),
            Position.of("a4"), PieceCache.of("B"));

        Map<String, String> testStringArrangement = MappingUtil.StringPieceMapByPiecesByPositions(testArrangement);

        //when
        Map<Position, Piece> actual = new SavedPieceArrangement(testStringArrangement).apply();

        //then
        Assertions.assertThat(actual).isEqualTo(testArrangement);
    }

}