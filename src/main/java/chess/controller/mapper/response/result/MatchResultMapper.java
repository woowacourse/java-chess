package chess.controller.mapper.response.result;

import chess.domain.game.result.MatchResult;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public enum MatchResultMapper {
    BLACK_WIN(MatchResult.BLACK_WIN, "승리: 흑"),
    WHITE_WIN(MatchResult.WHITE_WIN, "승리: 백"),
    DRAW(MatchResult.DRAW, "무승부"),
    ;

    private final MatchResult matchResult;
    private final String viewFormat;

    MatchResultMapper(MatchResult matchResult, String viewFormat) {
        this.matchResult = matchResult;
        this.viewFormat = viewFormat;
    }

    public static String getViewFormatBy(MatchResult matchResult) {
        return Stream.of(MatchResultMapper.values())
                .filter(it -> it.matchResult == matchResult)
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .viewFormat;
    }
}
