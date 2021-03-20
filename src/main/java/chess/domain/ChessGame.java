package chess.domain;

import chess.domain.board.*;
import chess.domain.dto.Strategy;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.utils.MoveValidator;
import chess.view.OutputView;

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

    public Board board() {
        return board;
    }

    public String turn() {
        return turn.team();
    }

//    public void move(String command) {
//        try {
//            Positions positions = new Positions(splitSourceAndTarget(command));
//            Position source = positions.at(SOURCE_INDEX - 1);
//            Position target = positions.at(TARGET_INDEX - 1);
//
//            board.confirmSameTeamPiece(target, turn); // 목적지에 같은 팀의 말이 존재하는 지 확인.
//            Piece piece = board.pieceAt(source); // 보드에서 출발지의 말을 가져옴.
//            piece.confirmTurn(turn); // 현재 차례에 맞는 말인지 확인.
//
//            Direction direction = source.calculateDirection(target); // 출발지와 목적지간의 거리를 계산.
//            Strategy strategy = piece.strategy(); // 피스의 전략을 가져옴.
//            strategy.containsDirection1(direction); // 피스의 전략에 사용자가 입력한 방향이 포함되어있는지 확인.
////            MoveValidator.validateDirection(direction, strategy); // 피스의 전략에 사용자가 입력한 방향이 포함되어있는 지 확인.
//
//            int distance = target.calculateDistance(source); // 타겟과 소스 간의 거리를 계산
//            if (piece.isPawn()) { // 만약 피스가 폰이라면
//                movePawnAfterValidate(piece, source, target, direction, strategy, distance); // 폰을 이동시킴
//                return;
//            }
//            moveOthersAfterValidate(source, target, direction, strategy, distance);
//
//        } catch (IllegalArgumentException e) {
//            OutputView.printErrorMessage(e.getMessage());
//        }
//    }

    public void move2(String command) {
        try {
            Positions positions = new Positions(splitSourceAndTarget(command));
            Piece piece = board.pieceAt(positions.source()); // 보드에서 출발지의 말을 가져옴.
            piece.confirmTurn(turn);
            board.confirmSameTeamPiece(positions.target(), turn); // 목적지에 같은 팀의 말이 존재하는 지 확인.
            moveBody(positions, piece);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void moveBody(Positions positions, Piece piece) {
        final Direction direction = positions.computeDirection();
        final Strategy strategy = piece.strategy();
        strategy.containsDirection1(direction);
        actionEachPiece(positions, piece, direction, strategy);
    }

    private void actionEachPiece(Positions positions, Piece piece, Direction direction, Strategy strategy) {
        final int distance = positions.computeDistance();
        if (piece.isPawn()) {
            actionPawn(piece, positions, direction, strategy, distance);
            return;
        }
        action(positions, direction, strategy, distance);
    }

    private void actionPawn(Piece piece, Positions positions, Direction direction, Strategy strategy, int distance) {
        if (positions.isDiagonal()) {
            MoveValidator.validateDiagonalMove(board, piece, positions.target(), distance);
            movePiece2(positions);
            return;
        }
        MoveValidator.validateStraightMove(distance);
        MoveValidator.isPieceExist(board, positions.target());
        straightMoveDistanceOne1(positions, distance);
        straightMoveDistanceTwo2(positions, direction, strategy, distance);
    }

    private void straightMoveDistanceOne1(Positions positions, int distance) {
        if (distance == Pawn.MOVE_DEFAULT_RANGE) {
            movePiece2(positions);
        }
    }

    private void straightMoveDistanceTwo2(Positions positions,
                                         Direction currentDirection,
                                         Strategy strategy, int distance) {
        if (distance == Pawn.MOVE_FIRST_RANGE) {
            MoveValidator.validatePawnLocation(positions.source());
            action(positions, currentDirection, strategy, distance);
        }
    }

    private void movePiece2(Positions positions) {
        if (board.containsPosition(positions.target())) {
            confirmKingCaptured(positions.target());
        }
        board.movePiece2(positions);
        turnOver();
    }

    private void action(Positions positions, Direction direction, Strategy strategy, int distance) {
        // TODO positions로 감싸기!!!!
        int step = 1;
        while (strategy.canMove()) {
            Position position = positions.move(direction, step++);
            if (positions.targetEquals(position)) {
                board.confirmSameTeamPiece(position, turn);
                movePiece2(positions);
                return;
            }
            MoveValidator.isPieceExist(board, position);
        }
        MoveValidator.validateMoveRange(distance, strategy.getMoveRange());
    }


//    private void movePawnAfterValidate(Piece piece, Position source, Position target,
//        Direction currentDirection, Strategy strategy, int distance) {
//        if (source.isDiagonal(target)) {
//            MoveValidator.validateDiagonalMove(board, piece, target, distance);
//            movePiece(source, target);
//            return;
//        }
//        MoveValidator.validateStraightMove(distance);
//        MoveValidator.isPieceExist(board, target);
//        straightMoveDistanceOne(source, target, distance);
//        straightMoveDistanceTwo(source, target, currentDirection, strategy, distance);
//    }

//    private void straightMoveDistanceOne(Position source, Position target, int distance) {
//        if (distance == Pawn.MOVE_DEFAULT_RANGE) {
//            movePiece(source, target);
//        }
//    }
//
//    private void straightMoveDistanceTwo(Position source, Position target,
//        Direction currentDirection,
//        Strategy strategy, int distance) {
//        if (distance == Pawn.MOVE_FIRST_RANGE) {
//            MoveValidator.validatePawnLocation(source);
//            moveOthersAfterValidate(source, target, currentDirection, strategy, distance);
//        }
//    }

//    private void moveOthersAfterValidate(Position source, Position target,
//        Direction currentDirection,
//        Strategy strategy,
//        int distance) {
//        for (int i = 1; i <= strategy.getMoveRange(); i++) {
//            Position movePosition = source.move(currentDirection, i);
//            if (movePosition.equals(target)) {
//                board.confirmSameTeamPiece(movePosition, turn);
//                movePiece(source, target);
//                return;
//            }
//            MoveValidator.isPieceExist(board, movePosition);
//        }
//        MoveValidator.validateMoveRange(distance, strategy.getMoveRange());
//    }

//    private void movePiece(Position source, Position target) {
//        if (board.containsPosition(target)) {
//            confirmKingCaptured(target);
//        }
//        board.movePiece(source, target);
//        turnOver();
//    }

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

    // TODO 객체로 감싸기
    private List<Position> splitSourceAndTarget(String command) {
        String[] commandParameters = command.split(" ");
        String source = commandParameters[SOURCE_INDEX];
        String target = commandParameters[TARGET_INDEX];

        return Arrays
            .asList(Position.of(convertFileToCoordinate(source), convertRankToCoordinate(source))
                , Position.of(convertFileToCoordinate(target), convertRankToCoordinate(target)));
    }

    // TODO 다른 곳에서 할 수 있는지 확인하고 변경하기!
    private Rank convertRankToCoordinate(String coordinate) {
        return Rank.of(Integer.parseInt(String.valueOf(coordinate.charAt(1))));
    }

    private File convertFileToCoordinate(String coordinate) {
        return File.of(String.valueOf(coordinate.charAt(0)));
    }


    // TODO Point 객체 새로 만들기
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

    // TODO 상태 변수 체커 한번에 모으
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

    public Team winner() {
        return winner;
    }
}
