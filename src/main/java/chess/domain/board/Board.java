package chess.domain.board;

import chess.domain.Team;
import chess.domain.piece.*;
import chess.domain.state.turn.State;
import chess.domain.state.turn.WhiteTurn;

import java.util.*;

public class Board {

    private static final String BLOCK_ERROR = "해당 위치로 기물을 옮길 수 없습니다.";
    private static final String BOARD_RANGE_ERROR = "체스 판의 범위를 벗어 났습니다.";
    private static final String BLANK_ERROR = "해당 위치에 기물이 없습니다.";
    private static final String NOT_FINISHED_ERROR = "아직 종료되지 않은 게임입니다.";
    private static final String CATCH_SAME_TEAM_EXCEPTION = "같은 팀의 기물을 잡을 수 없습니다.";
    public static final String INVALID_ORDER_ERROR = "[ERROR] 알맞는 순서가 아닙니다.";

    private final Map<Position, Piece> board;
    private State state;

    public Board() {
        this.board = new HashMap<>();
        this.state = new WhiteTurn();
        initialPlacePiece();
    }

    public void move(Position source, Position target) {
        Piece piece = board.get(source);
        validateMove(source, target);
        state = state.play(board.get(target));
        board.put(target, piece);
        board.put(source, new Blank());
    }

    private void validateMove(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        checkBlank(sourcePiece);
        checkOrder(sourcePiece);
        checkSameTeam(sourcePiece, targetPiece);
        checkReachable(sourcePiece, source, target);
        checkBlocking(source, target);
    }

    private void checkOrder(Piece sourcePiece) {
        if (!sourcePiece.isSameTeam(state.getTeam())) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
    }

    private void checkSameTeam(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException(CATCH_SAME_TEAM_EXCEPTION);
        }
    }

    private void checkBlank(final Piece piece) {
        if (piece.isBlank()) {
            throw new IllegalArgumentException(BLANK_ERROR);
        }
    }

    private void checkReachable(Piece sourcePiece, Position source, Position target) {
        sourcePiece.checkReachable(board.get(target), source, target);
    }

    private void checkBlocking(final Position source, final Position target) {
        Piece piece = board.get(source);
        Direction direction = piece.getDirection(source, target);
        Position checkPosition = source;
        while (checkPosition != target) {
            checkPosition = moveNextPosition(direction, checkPosition);
            Piece currentPiece = board.get(checkPosition);
            checkCatchable(target, checkPosition, currentPiece);
            piece.checkReachable(board.get(checkPosition), source, checkPosition);
        }
    }

    private void checkCatchable(final Position target, final Position checkPosition, final Piece currentPiece) {
        if (checkPosition != target && !currentPiece.isBlank()) {
            throw new IllegalArgumentException(BLOCK_ERROR);
        }
    }

    private Position moveNextPosition(final Direction direction, Position checkPosition) {
        Optional<Position> position = checkPosition.addDirection(direction);
        if (position.isEmpty()) {
            throw new IllegalArgumentException(BOARD_RANGE_ERROR);
        }
        return position.get();
    }

    public double calculateScore(Team team) {
        ScoreCalculator calculator = new ScoreCalculator(new HashMap<>(board));
        return calculator.calculateScore(team);
    }

    private void initialPlacePiece() {
        for (Position position : Position.getPositions()) {
            board.put(position, new Blank());
        }

        List<Piece> blackSpecials = initSpecialBuilder(Team.BLACK);
        List<Piece> whiteSpecials = initSpecialBuilder(Team.WHITE);
        for (int i = 0; i < 8; i++) {
            board.put(Position.of(8, i + 1), blackSpecials.get(i));
            board.put(Position.of(7, i + 1), new Pawn(Team.BLACK));

            board.put(Position.of(1, i + 1), whiteSpecials.get(i));
            board.put(Position.of(2, i + 1), new Pawn(Team.WHITE));
        }
    }

    private List<Piece> initSpecialBuilder(Team team) {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Rook(team));
        pieces.add(new Knight(team));
        pieces.add(new Bishop(team));
        pieces.add(new Queen(team));
        pieces.add(new King(team));
        pieces.add(new Bishop(team));
        pieces.add(new Knight(team));
        pieces.add(new Rook(team));
        return pieces;
    }

    public boolean isBlank(Position position) {
        return board.get(position).isBlank();
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }

    public Team getWinner() {
        if (state.isFinished()) {
            return state.getTeam();
        }
        throw new IllegalArgumentException(NOT_FINISHED_ERROR);
    }
}
