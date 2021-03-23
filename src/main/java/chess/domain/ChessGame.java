package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.utils.MoveValidator;
import chess.view.OutputView;

import java.util.EnumMap;

public class ChessGame {

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

//    public void move(Commands command) {
//        try {
//            Path path = new Path(command.path());
//            // TODO: 보드로부터 피스를 받아 이동시키는 것이 아니라 보드 내부에서 이동시키도록 변경하기.
//            Piece piece = board.pieceAt(path.source());
//            piece.confirmTurn(turn);
//            board.confirmSameTeamPiece(path.target(), turn);
//            moveBody(path, piece);
//        } catch (IllegalArgumentException e) {
//            OutputView.printErrorMessage(e.getMessage());
//        }
//    }


    public void move(Commands command) {
        try {
            board.move(new Path(command.path()), turn);
            turnOver(); // 차례 넘기기
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void moveBody(Path path, Piece piece) {
        final Direction direction = path.computeDirection();
        final Strategy strategy = piece.strategy();
        strategy.moveTowards(direction);
        actionEachPiece(path, piece, direction, strategy);
    }

    // TODO : chessgame은 보드에게, 보드는 각 피스에게 일을 시키도록 수정하기
    private void actionEachPiece(Path path, Piece piece, Direction direction, Strategy strategy) {
        final int distance = path.computeDistance();
        if (piece.isPawn()) {
            actionPawn(piece, path, direction, strategy, distance);
            return;
        }
        action(path, direction, strategy, distance);
    }

    private void actionPawn(Piece piece, Path path, Direction direction, Strategy strategy, int distance) {
        if (path.isDiagonal()) {
//            MoveValidator.validateDiagonalMove(board, piece, path.target(), distance);
            movePiece(path);
            return;
        }
        MoveValidator.validateStraightMove(distance);
        MoveValidator.isPieceExist(board, path.target());
        moveStraight(path, distance, direction, strategy);
    }

    private void moveStraight(Path path, int distance, Direction direction, Strategy strategy) {
        if (distance == Pawn.MOVE_DEFAULT_RANGE) {
            movePiece(path);
            return;
        }

        if (distance == Pawn.MOVE_FIRST_RANGE) {
            MoveValidator.validatePawnLocation(path.source());
            action(path, direction, strategy, distance);
        }
    }

    private void movePiece(Path path) {
        if (board.containsPosition(path.target())) {
            confirmKingCaptured(path.target());
        }
        board.movePiece(path);
        turnOver();
    }

    private void action(Path path, Direction direction, Strategy strategy, int distance) {
        int step = 1;
        while (strategy.canMove()) {
            Position position = path.move(direction, step++);
            if (path.targetEquals(position)) {
                board.confirmSameTeamPiece(position, turn);
                movePiece(path);
                return;
            }
            MoveValidator.isPieceExist(board, position);
        }
        MoveValidator.validateDistance(distance, strategy.moveRange());
    }

    private void confirmKingCaptured(Position target) {
        Piece piece = board.pieceAt(target);
        if (piece.isKing()) {
            winner = Team.turnOver(piece.getTeam());
            state = state.next();
        }
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

    public Team winner() {
        return winner;
    }

    public Board board() {
        return board;
    }

    public Team turn() {
        return turn;
    }

    private void turnOver() {
        turn = Team.turnOver(turn);
    }
}
