package chess.domain;

import chess.domain.piece.Pawn;
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

    public void initBoard(Board board) {
        this.board = board;
        state = state.init();
    }

    public Board getBoard() {
        return board;
    }

    public void move(String command) {
        List<Position> coordinates = splitSourceAndTarget(command);
        Position source = coordinates.get(SOURCE_INDEX - 1);
        Position target = coordinates.get(TARGET_INDEX - 1);

        Piece piece = getPieceAfterCheckTurnAndTeam(source, target);
        Direction currentDirection = source.calculateDirection(target);
        Strategy strategy = piece.strategy();
        MoveValidator.validateDirection(currentDirection, strategy);
        applyMoveStrategy(source, target, piece, currentDirection, strategy);
    }

    private Piece getPieceAfterCheckTurnAndTeam(Position source, Position target) {
        board.isSameTeam(target, turn);
        Piece piece = board.pieceAt(source);
        piece.checkTurn(turn);
        return piece;
    }

    private void applyMoveStrategy(Position source, Position target, Piece piece,
        Direction currentDirection, Strategy strategy) {
        if (piece.isPawn()) {
            movePawnAfterValidate(piece, source, target, currentDirection, strategy);
            return;
        }
        moveOthersAfterValidate(source, target, currentDirection, strategy);
    }

    private void movePawnAfterValidate(Piece piece, Position source, Position target,
        Direction currentDirection, Strategy strategy) {
        int distance = target.calculateDistance(source);
        if (source.isDiagonal(target)) {
            MoveValidator.validateDiagonalMove(board, piece, target, distance);
            movePiece(source, target);
        }
        MoveValidator.validateStraightMove(distance);
        straightMoveDistanceOne(source, target, distance);
        straightMoveDistanceTwo(source, target, currentDirection, strategy, distance);
    }

    private void straightMoveDistanceOne(Position source, Position target, int distance) {
        if (distance == Pawn.MOVE_DEFAULT_RANGE) {
            MoveValidator.isPieceExist(board, target);
            movePiece(source, target);
        }
    }

    private void straightMoveDistanceTwo(Position source, Position target,
        Direction currentDirection,
        Strategy strategy, int distance) {
        if (distance == Pawn.MOVE_FIRST_RANGE) {
            MoveValidator.validatePawnLocation(source);
            moveOthersAfterValidate(source, target, currentDirection, strategy);
        }
    }

    private void moveOthersAfterValidate(Position source, Position target,
        Direction currentDirection,
        Strategy strategy) {
        for (int i = 1; i <= strategy.getMoveRange(); i++) {
            Position movePosition = source.move(currentDirection, i);
            if (movePosition.equals(target)) {
                board.isSameTeam(movePosition, turn);
                movePiece(source, target);
                break;
            }
            MoveValidator.isPieceExist(board, movePosition);
        }
    }

    private void movePiece(Position source, Position target) {
        if (board.containsPosition(target)) {
            confirmKingCaptured(target);
        }
        board.movePiece(source, target);
        turnOver();
    }

    private void confirmKingCaptured(Position target) {
        Piece piece = board.pieceAt(target);
        if (piece.isKing()) {
            winner = Team.turnOver(piece.getTeam());
            state = state.next();
        }
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

    public EnumMap<Team, Double> calculatePoint() {
        EnumMap<Team, Double> result = new EnumMap<>(Team.class);
        calculateEachTeamPoint(result, Team.BLACK);
        calculateEachTeamPoint(result, Team.WHITE);
        return result;
    }

    private void calculateEachTeamPoint(EnumMap<Team, Double> result, Team team) {
        double totalPoint = board.calculateTotalPoint(team);
        totalPoint -= board.updatePawnPoint(team);
        result.put(team, totalPoint);
    }

    public boolean isReady() {
        return state.isReady();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public void endGame() {
        state = state.exit();
    }

    public boolean isRunning() {
        return !state.isExit();
    }

    public Team getWinTeam() {
        return winner;
    }
}
