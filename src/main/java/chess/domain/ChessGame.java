package chess.domain;

import java.util.Arrays;
import java.util.List;

public class ChessGame {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private Board board;
    private boolean isRunning;

    public ChessGame() {
        isRunning = true;
    }

    public void endGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void initBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void move(String command) {
        List<Coordinate> coordinates = splitSourceAndTarget(command);
    }

    private List<Coordinate> splitSourceAndTarget(String command) {
        String[] commandParameters = command.split(" ");
        String source = commandParameters[SOURCE_INDEX];
        String target = commandParameters[TARGET_INDEX];

        return Arrays
            .asList(new Coordinate(convertFileToCoordinate(source), convertRankToCoordinate(source))
                , new Coordinate(convertFileToCoordinate(target), convertRankToCoordinate(target)));
    }

    private int convertRankToCoordinate(String coordinate) {
        return Rank.of(Integer.parseInt(String.valueOf(coordinate.charAt(1)))).getRank();
    }

    private int convertFileToCoordinate(String coordinate) {
        return File.of(String.valueOf(coordinate.charAt(0))).getIndex();
    }
}
