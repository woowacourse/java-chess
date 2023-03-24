package chess.controller.mapper.response;

import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.piece.Camp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameResultFormatter {

    private GameResultFormatter() {
    }

    public static List<String> convertToGameResult(GameResult gameResult) {
        List<String> result = new ArrayList<>();
        MatchResult matchResult = gameResult.getMatchResult();

        if (matchResult == MatchResult.PAUSE) {
            return Collections.emptyList();
        }

        String matchResultViewFormat = MatchResultMapper.getViewFormatBy(matchResult);
        result.add(matchResultViewFormat);

        applyCampScore(gameResult, result);
        return result;
    }

    private static void applyCampScore(GameResult gameResult, List<String> result) {
        if (gameResult.containsScore()) {
            result.add(getScoreFormatOfCamp(gameResult, Camp.WHITE));
            result.add(getScoreFormatOfCamp(gameResult, Camp.BLACK));
        }
    }

    private static String getScoreFormatOfCamp(GameResult gameResult, Camp camp) {
        StringBuilder formatBuilder = new StringBuilder();
        appendCampPrefix(camp, formatBuilder);

        double scoreOfCamp = gameResult.peekScoreOfCamp(camp);
        formatBuilder.append(scoreOfCamp);

        return formatBuilder.toString();
    }

    private static void appendCampPrefix(Camp camp, StringBuilder formatBuilder) {
        if (camp == Camp.WHITE) {
            formatBuilder.append("백 : ");
            return;
        }

        formatBuilder.append("흑 : ");
    }
}
