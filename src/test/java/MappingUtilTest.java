import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.MappingUtil;
import chess.model.File;
import chess.model.Position;
import chess.model.Rank;
import chess.model.piece.Piece;
import chess.model.piece.PieceCache;

public class MappingUtilTest {

    @ParameterizedTest
    @CsvSource(value = {"b:whiteBishop", "B:blackBishop", ":empty"}, delimiter = ':')
    @DisplayName("Piece -> fullName(ex: whiteBishop, blackBishop) 변환 검증")
    void fullNameForm(String emblem, String expected) {
        //when
        String actual = MappingUtil.fullNameFrom(PieceCache.of(emblem));

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"whiteBishop:b", "blackBishop:B", "empty:"}, delimiter = ':')
    @DisplayName("fullName(ex: whiteBishop) -> emblem(ex: b) 변환 검증")
    void emblemFrom(String fullName, String expected) {
        //when
        String actual = MappingUtil.emblemFrom(fullName);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("PieceByPositions -> StringPieceByPositions 변환 검증")
    void StringPieceMapByPiecesByPositions() {
        //given
        Map<Position, Piece> testArrangement = Map.of(Position.of("a2"), PieceCache.of("b"),
            Position.of("a4"), PieceCache.of("B"));

        Map<String, String> actual = MappingUtil.StringPieceMapByPiecesByPositions(testArrangement);
        //when

        Map<String, String> expected = new HashMap<>();
        for (Rank rank : Rank.values()) {
            expected.putAll(Arrays.stream(File.values())
                .collect(Collectors.toUnmodifiableMap(file -> file.getValue() + rank.getValue(),
                    file -> "empty")));
        }
        expected.replace("a2", "whiteBishop");
        expected.replace("a4", "blackBishop");

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
