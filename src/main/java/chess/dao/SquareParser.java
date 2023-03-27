package chess.dao;

import chess.domain.piece.Empty;
import chess.domain.piece.SquareState;
import chess.domain.piece.Team;
import chess.domain.piece.state.*;

import java.util.Arrays;
import java.util.function.Function;

public enum SquareParser {
    EMPTY(".", (team) -> new Empty()),
    PAWN("P", Pawn::new),
    ROOK("R", Rook::new),
    KNIGHT("N", Knight::new),
    BISHOP("B", Bishop::new),
    QUEEN("Q", Queen::new),
    KING("K", King::new);

    public static final String REGEX_WHITE_MARK = "[a-z]";
    public static final String REGEX_BLACK_MARK = "[A-Z]";

    private final String mark;
    private final Function<Team, ? extends SquareState> generator;

    SquareParser(final String mark, final Function<Team, ? extends SquareState> generator) {
        this.mark = mark;
        this.generator = generator;
    }

    public static SquareState getSquareStateByMark(final String mark) {
        Function<Team, ? extends SquareState> squareGenerator = getSquareGeneratorByMark(mark);

        if (squareGenerator.equals(EMPTY.generator)) {
            return new Empty();
        }

        return squareGenerator.apply(decideTeam(mark));
    }

    private static Function<Team, ? extends SquareState> getSquareGeneratorByMark(final String mark) {
        return Arrays.stream(SquareParser.values())
                .filter(squareParser -> mark.toUpperCase().equals(squareParser.mark))
                .map(SquareParser::getGenerator)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 종류의 마크입니다."));
    }

    private static Team decideTeam(final String mark) {
        if (mark.matches(REGEX_WHITE_MARK)) {
            return Team.WHITE;
        }
        if (mark.matches(REGEX_BLACK_MARK)) {
            return Team.BLACK;
        }
        throw new IllegalArgumentException("잘못된 문자열 입니다.");
    }

    private Function<Team, ? extends SquareState> getGenerator() {
        return generator;
    }
}
