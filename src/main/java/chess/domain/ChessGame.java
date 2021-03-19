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
    public static final int COMMAND_SIZE = 3;

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

    public void move(String command) {
        List<Position> coordinates = splitSourceAndTargetPosition(command);
        Position source = coordinates.get(SOURCE_INDEX - 1);
        Position target = coordinates.get(TARGET_INDEX - 1);

        Piece piece = getSourcePositionPiece(source);
        board.validateTargetPieceIsSameTeam(target, turn);

        Direction currentDirection = source.calculateDirection(target);
        MoveValidator.validateStrategyContainsDirection(currentDirection, piece.strategy());
        applyMoveStrategyV2(source, target, piece, currentDirection);
    }

    private Piece getSourcePositionPiece(Position source) {
        Piece piece = board.pieceAt(source);
        piece.validateCurrentTurn(turn);
        return piece;
    }

    private void applyMoveStrategy(Position source, Position target, Piece piece, Direction currentDirection) {
        Strategy strategy = piece.strategy();
        int sourceTargetDistance = target.calculateDistance(source);
        if (piece.isPawn()) {
            movePawnAfterValidate(piece, source, target, currentDirection, strategy, sourceTargetDistance);
        }
        moveOthersAfterValidate(source, target, currentDirection, strategy, sourceTargetDistance);
    }

    private void applyMoveStrategyV2(Position source, Position target, Piece piece, Direction currentDirection) {
        Strategy strategy = piece.strategy();
        int sourceTargetDistance = target.calculateDistance(source);
        moveOthersAfterValidateV2(source, target, currentDirection, strategy, sourceTargetDistance, piece);
    }

    private void movePawnAfterValidate(Piece piece, Position source, Position target, Direction currentDirection, Strategy strategy, int distance) {
        if (source.isDiagonal(target)) {
            MoveValidator.validateDiagonalMove(board, piece, target, distance);
            movePiece(source, target);
            return;
        }
        MoveValidator.validateStraightMove(distance);
        board.validateHasPieceInPath(target);
        straightMoveDistanceOne(source, target, distance);
        straightMoveDistanceTwo(source, target, currentDirection, strategy, distance);
    }

    private void straightMoveDistanceOne(Position source, Position target, int distance) {
        if (distance == Pawn.MOVE_DEFAULT_RANGE) {
            movePiece(source, target);
        }
    }

    private void straightMoveDistanceTwo(Position source, Position target, Direction currentDirection, Strategy strategy, int distance) {
        if (distance == Pawn.MOVE_FIRST_RANGE) {
            MoveValidator.validatePawnLocation(source);
            moveOthersAfterValidate(source, target, currentDirection, strategy, distance);
        }
    }

    private void moveOthersAfterValidate(Position source, Position target, Direction currentDirection, Strategy strategy, int distance) {
        for (int i = 1; i <= strategy.getMoveRange(); i++) {
            Position movePosition = source.move(currentDirection, i);
            if (movePosition.equals(target)) {
                movePiece(source, target);
                return;
            }
            board.validateHasPieceInPath(movePosition);
        }
        MoveValidator.validateMoveRange(distance, strategy.getMoveRange());
    }

    private void moveOthersAfterValidateV2(Position source, Position target, Direction currentDirection, Strategy strategy, int distance, Piece piece) {
        int moveRange = calculateMoveRange(piece, strategy, source);
        for (int i = 1; i <= moveRange; i++) {
            Position movePosition = source.move(currentDirection, i);
            if (movePosition.equals(target)) {
                validatePawnCondition(target, piece, currentDirection);
                movePiece(source, target);
                return;
            }
            board.validateHasPieceInPath(movePosition);
        }
        MoveValidator.validateMoveRange(distance, moveRange);
    }

    private int calculateMoveRange(Piece piece, Strategy strategy, Position position) {
        if(!piece.isPawn()) {
            return strategy.getMoveRange();
        }

        if(position.getY() == Pawn.WHITE_PAWN_START_LINE && piece.getTeam() == Team.WHITE) {
            return Pawn.MOVE_FIRST_RANGE;
        }

        if(position.getY() == Pawn.BLACK_PAWN_START_LINE && piece.getTeam() == Team.BLACK) {
            return Pawn.MOVE_FIRST_RANGE;
        }

        return Pawn.MOVE_DEFAULT_RANGE;
    }

    private void validatePawnCondition(Position target, Piece piece, Direction direction) {
        if(!board.hasPieceAt(target)) {
            if(piece.isPawn() && Direction.isDiagonalDirection(direction)) {
                throw new IllegalArgumentException("폰은 상대방 말이 없는 대각선 방향으로는 이동할 수 없습니다.");
            }
            return;
        }

        if(piece.isPawn() && Direction.isLinearDirection(direction)) {
            throw new IllegalArgumentException("폰은 대각선 방향으로만 상대 말을 먹을 수 있습니다.");
        }
    }

    private void movePiece(Position source, Position target) {
        if (board.hasPieceAt(target)) {
            checkKingCaptured(target);
        }
        board.movePiece(source, target);
        turnOver();
    }

    private void checkKingCaptured(Position target) {
        Piece piece = board.pieceAt(target);
        if (piece.isKing()) {
            winner = Team.turnOver(piece.getTeam());
            state = state.next();
        }
    }

    private void turnOver() {
        turn = Team.turnOver(turn);
    }

    private List<Position> splitSourceAndTargetPosition(String command) {
        String[] commandParameters = command.split(" ");
        if (commandParameters.length != COMMAND_SIZE) {
            throw new IllegalArgumentException("[ERROR] move 명령어는 두 개의 좌표가 필요합니다.");
        }
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

    public boolean isRunning() {
        return !state.isExit();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public void endGame() {
        state = state.exit();
    }

    public Board getBoard() {
        return board;
    }

    public Team getWinTeam() {
        return winner;
    }

    public PrintBoardDto getPrintBoardDto() {
        return new PrintBoardDto(board, turn);
    }
}
