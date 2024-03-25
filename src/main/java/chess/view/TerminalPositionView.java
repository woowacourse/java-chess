package chess.view;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.TerminalPosition;

import java.util.List;
import java.util.Map;

public class TerminalPositionView {
    private TerminalPositionView() {
    }

    private static final int START_POSITION_INDEX = 0;
    private static final int END_POSITION_INDEX = 1;
    private static final int FILE_BEGIN_INDEX = 0;
    private static final int FILE_END_INDEX = 1;
    private static final int RANK_BEGIN_INDEX = 1;
    private static final int RANK_END_INDEX = 2;
    private static final Map<String, File> FILE_TEXTS = Map.of(
            "a", File.A,
            "b", File.B,
            "c", File.C,
            "d", File.D,
            "e", File.E,
            "f", File.F,
            "g", File.G,
            "h", File.H
    );
    private static final Map<String, Rank> RANK_TEXTS = Map.of(
            "1", Rank.FIRST,
            "2", Rank.SECOND,
            "3", Rank.THIRD,
            "4", Rank.FOURTH,
            "5", Rank.FIFTH,
            "6", Rank.SIXTH,
            "7", Rank.SEVENTH,
            "8", Rank.EIGHTH
    );

    public static TerminalPosition of(List<String> arguments) {
        Position start = toStartPosition(arguments.get(START_POSITION_INDEX));
        Position end = toEndPosition(arguments.get(END_POSITION_INDEX));

        return new TerminalPosition(start, end);
    }

    public static Position toStartPosition(String startPositionText) {
        return new Position(
                RANK_TEXTS.get(startPositionText.substring(RANK_BEGIN_INDEX, RANK_END_INDEX)),
                FILE_TEXTS.get(startPositionText.substring(FILE_BEGIN_INDEX, FILE_END_INDEX)));
    }

    public static Position toEndPosition(String endPositionText) {
        return new Position(
                RANK_TEXTS.get(endPositionText.substring(RANK_BEGIN_INDEX, RANK_END_INDEX)),
                FILE_TEXTS.get(endPositionText.substring(FILE_BEGIN_INDEX, FILE_END_INDEX)));
    }
}
