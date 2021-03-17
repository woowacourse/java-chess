package chess.domain;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public class ChessGame {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private Board board;
    private boolean isRunning;
    private Team team;

    public ChessGame() {
        isRunning = true;
        team = Team.WHITE;
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
        // 출발 / 도착 좌표
        List<Position> coordinates = splitSourceAndTarget(command);

        Position sourceCoordinates = coordinates.get(0);
        Piece piece = board.pieceAt(sourceCoordinates);
        piece.checkTurn(team);

        // 팀 변경
        turnOver();
    }

    private void turnOver() {
        team = team.turnOver(team);
    }

    private List<Position> splitSourceAndTarget(String command) {
        String[] commandParameters = command.split(" ");
        String source = commandParameters[SOURCE_INDEX];
        String target = commandParameters[TARGET_INDEX];

        return Arrays
            .asList(Position.of(convertFileToCoordinate(source), convertRankToCoordinate(source))
                , Position.of(convertFileToCoordinate(target), convertRankToCoordinate(target)));
    }

    private Rank convertRankToCoordinate(String coordinate) {
        return Rank.of(Integer.parseInt(String.valueOf(coordinate.charAt(1))));
    }

    private File convertFileToCoordinate(String coordinate) {
        return File.of(String.valueOf(coordinate.charAt(0)));
    }
}
