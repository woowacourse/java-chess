package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class ChessGame {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private Board board;
    private State state;

    private Team winner;
    private Team turn;

    public ChessGame() {
        state = new Ready();
        turn = Team.WHITE;
    }

    public void endGame() {
        state = state.exit();
    }

    public boolean isRunning() {
        return !state.isExit();
    }

    public void initBoard(Board board) {
        this.board = board;
        state = state.next();
    }

    public Board getBoard() {
        return board;
    }

    public void move(String command) {
        List<Position> coordinates = splitSourceAndTarget(command);

        Position source = coordinates.get(0);
        Position target = coordinates.get(1);

        Piece piece = board.pieceAt(source);
        board.isSameTeam(target, turn);
        piece.checkTurn(turn);

        Direction currentDirection = source.calculateDirection(target);
        Strategy strategy = piece.strategy();

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
        Strategy strategy) {

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
        Strategy strategy) {
        for (int i = 1; i <= strategy.getMoveRange(); i++) {
            Position movePosition = source.move(currentDirection, i);

            if (movePosition.equals(target)) {
                board.isSameTeam(movePosition, turn);
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
                state = state.next();
            }
        }
        board.movePiece(source, target);
    }

    private void turnOver() {
        turn = Team.turnOver(turn);
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

    public boolean isEnd() {
        return state.isEnd();
    }

    public Team getWinTeam() {
        return winner;
    }

    public EnumMap<Team, Double> calculatePoint() {
        EnumMap<Team, Double> result = new EnumMap<>(Team.class);

        calculateEachTeamPoint(result, Team.BLACK);
        calculateEachTeamPoint(result, Team.WHITE);

        return result;
    }

    private void calculateEachTeamPoint(EnumMap<Team, Double> result, Team team) {
        double totalPoint = board.calculateTotalPoint(team); // 전체 합산한 포인트
        totalPoint -= board.updatePawnPoint(team); // 폰꺼까지해서

        result.put(team,totalPoint);
    }

    public boolean isReady() {
        return state.isReady();
    }
}
