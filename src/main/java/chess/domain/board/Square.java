package chess.domain.board;

import chess.domain.File;
import chess.domain.Rank;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Square {

    private final static Map<String, Square> CACHE = new HashMap<>();
    private static final String SQUARE_INPUT_ERROR_MESSAGE = "잘못된 square 의 입력입니다";

    static {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                CACHE.put(file.getName() + "" + rank.getName(), new Square(file, rank));
            }
        }
    }

    private final File file;
    private final Rank rank;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(String location) {
        if (Objects.isNull(location) || !CACHE.containsKey(location)) {
            throw new IllegalArgumentException(SQUARE_INPUT_ERROR_MESSAGE);
        }
        return CACHE.get(location);
    }

    public static Square of(char file, char rank) {
        return of(file + "" + rank);
    }

    public static Square moveTo(int fileIncrement, int rankIncrement, Square squareBeforeMove) {
        if (addFileOrRankIncrement(fileIncrement, squareBeforeMove.file.getName()) < File.A.getName()
                || addFileOrRankIncrement(fileIncrement, squareBeforeMove.file.getName()) > File.H.getName()) {
            return squareBeforeMove;
        }
        if (addFileOrRankIncrement(rankIncrement, squareBeforeMove.rank.getName()) < Rank.ONE.getName()
                || addFileOrRankIncrement(rankIncrement, squareBeforeMove.rank.getName()) > Rank.EIGHT.getName()) {
            return squareBeforeMove;
        }
        return of(addFileOrRankIncrement(fileIncrement, squareBeforeMove.file.getName()),
                addFileOrRankIncrement(rankIncrement, squareBeforeMove.rank.getName()));
    }

    private static char addFileOrRankIncrement(int fileIncrement, char name) {
        return (char) (name + fileIncrement);
    }


    public char getFile() {
        return file.getName();
    }

    public char getRank() {
        return rank.getName();
    }
}
