package chess.domain;

import chess.domain.dto.PrintBoardDto;
import chess.domain.dto.WebBoardDto;
import chess.domain.piece.Direction;
import chess.domain.piece.DirectionStrategy;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.MovePath;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.util.BoardInitializer;
import chess.util.StringParser;
import java.util.EnumMap;
import java.util.Map;

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
        turn = Team.WHITE;
        state = state.init();
    }

    public void move(String command) {
        if (state.isReady() || state.isEnd()) {
            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았거나 종료되었습니다.");
        }
        MovePath movePath = StringParser.splitSourceAndTargetPosition(command);
        Position source = movePath.getSource();
        Position target = movePath.getTarget();
        Piece piece = pieceAtSourcePosition(source);

        validateChessMovement(movePath, piece, source.calculateDirection(target));
        movePiece(source, target);
    }

    private void validateChessMovement(MovePath movePath, Piece piece, Direction currentDirection) {
        DirectionStrategy directionStrategy = piece.strategy();
        board.validateTargetPieceIsSameTeam(movePath.getTarget(), turn);
        MoveValidator.validateStrategyContainsDirection(currentDirection, directionStrategy);
        MoveValidator
            .validateMoveRange(calculateMoveRange(piece, directionStrategy, movePath.getSource()),
                calculateTargetMove(
                    movePath, currentDirection));
        validateTargetPath(movePath, piece, currentDirection);
    }

    private Piece pieceAtSourcePosition(Position source) {
        Piece piece = board.pieceAt(source);
        piece.validateCurrentTurn(turn);
        return piece;
    }

    private int calculateTargetMove(MovePath movePath, Direction currentDirection) {
        Position source = movePath.getSource();
        Position target = movePath.getTarget();
        return (int) (target.calculateDistance(source) / currentDirection.getUnit());
    }

    private void validateTargetPath(MovePath movePath, Piece piece, Direction currentDirection) {
        Position source = movePath.getSource();
        Position target = movePath.getTarget();
        int targetMove = calculateTargetMove(movePath, currentDirection);
        for (int i = 1; i < targetMove; i++) {
            Position currentPosition = source.move(currentDirection, i);
            board.validateHasPieceInPath(currentPosition);
        }
        if (piece.isPawn()) {
            MoveValidator.validatePawnCondition(board, target, currentDirection);
        }
    }

    private int calculateMoveRange(Piece piece, DirectionStrategy directionStrategy,
        Position position) {
        if (!piece.isPawn()) {
            return directionStrategy.getMoveRange();
        }
        if (position.getY() == BoardInitializer.WHITE_PAWN_START_LINE
            && piece.getTeam() == Team.WHITE) {
            return Pawn.MOVE_FIRST_RANGE;
        }
        if (position.getY() == BoardInitializer.BLACK_PAWN_START_LINE
            && piece.getTeam() == Team.BLACK) {
            return Pawn.MOVE_FIRST_RANGE;
        }
        return Pawn.MOVE_DEFAULT_RANGE;
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

    public Map<Team, Double> calculatePoint() {
        Map<Team, Double> result = new EnumMap<>(Team.class);
        calculateEachTeamPoint(result, Team.BLACK);
        calculateEachTeamPoint(result, Team.WHITE);
        return result;
    }

    private void calculateEachTeamPoint(Map<Team, Double> result, Team team) {
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
        if (!state.isEnd()) {
            throw new IllegalArgumentException("[ERROR] 아직 왕이 잡히지 않았습니다.");
        }
        return winner;
    }

    public PrintBoardDto getPrintBoardDto() {
        return new PrintBoardDto(board, turn);
    }

    public WebBoardDto getWebBoardDto() {
        return new WebBoardDto(board, turn, state.isEnd(), winner);
    }
}
