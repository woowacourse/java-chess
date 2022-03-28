package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.state.State;
import chess.domain.state.WhiteTurn;

import java.util.*;


public class Board {

    private static final String BLOCK_ERROR = "해당 위치로 기물을 옮길 수 없습니다.";
    private static final String BOARD_RANGE_ERROR = "체스 판의 범위를 벗어 났습니다.";
    private static final String BLANK_ERROR = "해당 위치에 기물이 없습니다.";
    private static final String NOT_FINISHED_ERROR = "아직 종료되지 않은 게임입니다.";

    private final Map<Position, Piece> board;
    private State state;

    public Board() {
        this.board = new HashMap<>();
        this.state = new WhiteTurn();
        initialBatchPiece();
    }

    public void move(Position source, Position target) {
        Piece piece = board.get(source);
        validateMove(source, target);
        state = state.play(piece, board.get(target));
        board.put(target, piece);
        board.put(source, new Blank());
    }

    public void endGame() {
        state = state.finish();
    }

    private void validateMove(final Position source, final Position target) {
        Piece piece = board.get(source);
        checkBlank(piece);
        checkReachable(piece, source, target);
        checkBlocking(source, target);
    }

    private void checkBlank(final Piece piece) {
        if (piece.isBlank()) {
            throw new IllegalArgumentException(BLANK_ERROR);
        }
    }

    private void checkReachable(Piece piece, Position source, Position target) {
        piece.checkReachable(source, target);
    }

    private void checkBlocking(final Position source, final Position target) {
        Piece piece = board.get(source);
        Direction direction = piece.getDirection(source, target);
        Position checkPosition = source;
        while (checkPosition != target) {
            checkPosition = moveNextPosition(direction, checkPosition);
            Piece currentPiece = board.get(checkPosition);
            checkCatchable(target, checkPosition, currentPiece);
            piece.validateCatch(currentPiece, direction);
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

    public boolean isFinished() {
        return state.isFinished();
    }

    public double calculateScore(Team team) {
        double score = 0;
        for (int column = 1; column <= 8; column++) {
            List<Piece> columnPieces = findColumnPieces(team, column);
            score += calculateColumnScore(columnPieces);
        }
        return score;
    }

    private double calculateColumnScore(final List<Piece> columnPieces) {
        double sum = 0;
        long pawnCount = columnPieces.stream()
                .filter(Piece::isPawn)
                .count();
        for (Piece piece : columnPieces) {
            sum += piece.getScore();
        }
        sum = calculatePawnScore(sum, pawnCount);
        return sum;
    }

    private double calculatePawnScore(double sum, long pawnCount) {
        if (pawnCount >= 2) {
            sum -= 0.5 * pawnCount;
        }
        return sum;
    }

    private List<Piece> findColumnPieces(Team team, final int column) {
        List<Piece> pieces = new ArrayList<>();
        for (int row = 1; row <= 8; row++) {
            Position position = Position.of(row, column);
            if (board.get(position).isSameTeam(team)) {
                pieces.add(board.get(position));
            }
        }
        return pieces;
    }

    private void initialBatchPiece() {
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
        return board;
    }

    public Team getWinner() {
        if (state.isFinished()) {
            return state.getTeam();
        }
        throw new IllegalArgumentException(NOT_FINISHED_ERROR);
    }
}
