package chess.view;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Map<Character, File> FILE_INPUT = Map.of(
            'a', File.A, 'b', File.B, 'c', File.C, 'd', File.D,
            'e', File.E, 'f', File.F, 'g', File.G, 'h', File.H);
    private static final Map<Character, Rank> RANK_INPUT = Map.of(
            '1', Rank.ONE, '2', Rank.TWO, '3', Rank.THREE, '4', Rank.FOUR,
            '5', Rank.FIVE, '6', Rank.SIX, '7', Rank.SEVEN, '8', Rank.EIGHT);
    private static final int POSITION_INPUT_LENGTH = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public GameCommand readCommand() {
        String input = SCANNER.next();
        return GameCommand.from(input);
    }

    public Position readPosition() {
        String input = SCANNER.next();
        validatePositionInputLength(input);

        File file = convertToFile(input);
        Rank rank = convertToRank(input);

        return new Position(file, rank);
    }

    private void validatePositionInputLength(String input) {
        if (input.length() != POSITION_INPUT_LENGTH) {
            throw new IllegalArgumentException("위치 형식이 올바르지 않습니다.");
        }
    }

    private File convertToFile(String input) {
        char fileInput = input.charAt(FILE_INDEX);
        File file = FILE_INPUT.get(fileInput);
        if (file == null) {
            throw new IllegalArgumentException("위치 형식이 올바르지 않습니다.");
        }

        return file;
    }

    private Rank convertToRank(String input) {
        char rankInput = input.charAt(RANK_INDEX);
        Rank rank = RANK_INPUT.get(rankInput);
        if (rank == null) {
            throw new IllegalArgumentException("위치 형식이 올바르지 않습니다.");
        }

        return rank;
    }
}
