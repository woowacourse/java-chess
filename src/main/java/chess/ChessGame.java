package chess;

import chess.domain.Board;
import chess.domain.Direction;
import chess.domain.File;
import chess.domain.MoveVO;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.Team;
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
        List<Position> coordinates = splitSourceAndTarget(command);

        Position source = coordinates.get(0);
        Position target = coordinates.get(1);

        Piece piece = board.pieceAt(source);
        board.isSameTeam(target, team);
        piece.checkTurn(team);

        Direction currentDirection = source.calculateDirection(target);
        MoveVO strategy = piece.strategy();

        movable(source, target, currentDirection, strategy);

        turnOver();
    }

    private void movable(Position source, Position target, Direction currentDirection,
        MoveVO strategy) {

        if (!strategy.containsDirection(currentDirection)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표로 이동할 수 없습니다.");
        }

        for (int i = 1; i <= strategy.getMoveRange(); i++) {
            Position movePosition = source.move(currentDirection, i);

            if (movePosition.equals(target)) {
                board.isSameTeam(movePosition, team);
                board.movePiece(source, target);
                return;
            }

            if (board.containsPosition(movePosition)) {
                throw new IllegalArgumentException("[ERROR] 경로에 말이 존재합니다.");
            }
        }
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
