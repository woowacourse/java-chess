package chess.domain;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public class ChessGame {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private Board board;
    private boolean isRunning;
    private boolean isKingDead;
    private Team winner;
    private Team team;

    public ChessGame() {
        isRunning = true;
        isKingDead = false;
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

        if (!strategy.containsDirection(currentDirection)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표로 이동할 수 없습니다.");
        }

        if (piece.isPawn()) {
            pawnMovable(piece, source, target, currentDirection, strategy);
            turnOver();
            return;
        }

        movable(source, target, currentDirection, strategy);
        turnOver();
    }

    private void pawnMovable(Piece piece, Position source, Position target,
        Direction currentDirection,
        MoveVO strategy) {

        int distance = target.calculateDistance(source);

        if (source.isStraight(target)) {
            if (distance == 1) {
                if (board.containsPosition(target)) {
                    throw new IllegalArgumentException("[ERROR] 목적지에 말이 존재합니다.");
                }
                movePiece(source, target);
                return;
            }

            if (distance == 2) {
                if (!source.isLocatedAtStartLine()) {
                    throw new IllegalArgumentException("[ERROR] 폰은 처음에만 두 칸 이동할 수 있습니다.");
                }
                movable(source, target, currentDirection, strategy);
                return;
            }
        }

        if (distance >= 2 || !board.containsPosition(target)
            || board.pieceAt(target).isSameTeam(piece.getTeam())) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선에 상대팀의 말이 있는 경우 한 칸 이동할 수 있습니다.");
        }
        movePiece(source, target);
    }

    private void movable(Position source, Position target, Direction currentDirection,
        MoveVO strategy) {
        for (int i = 1; i <= strategy.getMoveRange(); i++) {
            Position movePosition = source.move(currentDirection, i);

            if (movePosition.equals(target)) {
                board.isSameTeam(movePosition, team);
                movePiece(source, target);
                return;
            }

            if (board.containsPosition(movePosition)) {
                throw new IllegalArgumentException("[ERROR] 경로에 말이 존재합니다.");
            }
        }
    }

    private void movePiece(Position source, Position target) {
        if (board.containsPosition(target)) {
            Piece piece = board.pieceAt(target);
            if(piece.isKing()) {
                winner = Team.turnOver(piece.getTeam());
                isKingDead = true;
            }
        }
        board.movePiece(source, target);
    }

    private void turnOver() {
        team = Team.turnOver(team);
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

    public boolean isKingDead() {
        return isKingDead;
    }

    public Team getWinTeam() {
        return winner;
    }
}
