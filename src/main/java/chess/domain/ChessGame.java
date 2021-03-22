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

    public void move(String command) {
        try {
            Positions positions = new Positions(command);
            // TODO: 보드로부터 피스를 받아 이동시키는 것이 아니라 보드 내부에서 이동시키도록 변경하기.
            Piece piece = board.pieceAt(positions.source());
            piece.confirmTurn(turn);
            board.confirmSameTeamPiece(positions.target(), turn);
            moveBody(positions, piece);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void moveBody(Positions positions, Piece piece) {
        final Direction direction = positions.computeDirection();
        final Strategy strategy = piece.strategy();
        strategy.moveTowards(direction);
        actionEachPiece(positions, piece, direction, strategy);
    }

    // TODO : chessgame은 보드에게, 보드는 각 피스에게 일을 시키도록 수정하기
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
            movePiece(positions);
            return;
        }
        MoveValidator.validateStraightMove(distance);
        MoveValidator.isPieceExist(board, positions.target());
        moveStraight(positions, distance, direction, strategy);
    }

    private void moveStraight(Positions positions, int distance, Direction direction, Strategy strategy) {
        if (distance == Pawn.MOVE_DEFAULT_RANGE) {
            movePiece(positions);
            return;
        }

        if (distance == Pawn.MOVE_FIRST_RANGE) {
            MoveValidator.validatePawnLocation(positions.source());
            action(positions, direction, strategy, distance);
        }
    }

    private void movePiece(Positions positions) {
        if (board.containsPosition(positions.target())) {
            confirmKingCaptured(positions.target());
        }
        board.movePiece(positions);
        turnOver();
    }

    private void action(Positions positions, Direction direction, Strategy strategy, int distance) {
        int step = 1;
        while (strategy.canMove()) {
            Position position = positions.move(direction, step++);
            if (positions.targetEquals(position)) {
                board.confirmSameTeamPiece(position, turn);
                movePiece(positions);
                return;
            }
            MoveValidator.isPieceExist(board, position);
        }
        MoveValidator.validateMoveRange(distance, strategy.getMoveRange());
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
