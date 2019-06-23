package chess.utils;

import org.junit.jupiter.api.Test;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.utils.exceptions.InvalidPositionInputExecption;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PositionParserTest {
    @Test
    void 정상적인_경우_파싱_테스트() {
        assertThat(PositionParser.parse("a5")).isEqualTo(new Position(new Coordinate('a'), new Coordinate(5)));
    }

    @Test
    void 연속_알파벳_두_개_예외처리_테스트() {
        assertThrows(InvalidPositionInputExecption.class, () -> {
            PositionParser.parse("ae");
        });
    }

    @Test
    void 연속_숫자_두_개_예외처리_테스트() {
        assertThrows(InvalidPositionInputExecption.class, () -> {
            PositionParser.parse("15");
        });
    }

    @Test
    void 범위를_벗어나는_첫글자_입력_예외처리_테스트() {
        assertThrows(InvalidPositionInputExecption.class, () -> {
            PositionParser.parse("z3");
        });
    }

    @Test
    void 범위를_벗어나는_두번째글짜_입력_예외처리_테스트() {
        assertThrows(InvalidPositionInputExecption.class, () -> {
            PositionParser.parse("a9");
        });
    }
}