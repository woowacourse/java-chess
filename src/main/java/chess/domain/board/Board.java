package chess.domain.board;

import chess.domain.Side;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.InvalidCommandException;
import chess.exception.InvalidMovementException;
import java.util.List;
import java.util.Map;

public final class Board {

    private static final int KING_COUNT = 2;

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board createGamingBoard() {
        return new Board(BoardInitializer.init());
    }

    public void move(Position from, Position to, Side playerSide) {
        Piece piece = board.get(from);

        validateIsMyPiece(playerSide, piece);
        checkRoute(piece.route(from, to));
        validateMyPieceExistToPosition(to, playerSide);
        validatePawnCase(from, to, piece);
        movePiece(from, to, piece);
    }

    private void validateIsMyPiece(Side playerSide, Piece piece) {
        if (!piece.isSideEqualTo(playerSide)) {
            throw new InvalidMovementException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    private void checkRoute(List<Position> route) {
        route.stream()
                .map(board::get)
                .filter(piece -> !piece.isBlank())
                .findAny()
                .ifPresent(isBlank -> {
                    throw new InvalidMovementException("이동경로에 다른 기물이 존재합니다.");
                });
    }

    private void validateMyPieceExistToPosition(Position to, Side playerSide) {
        if (board.get(to).isSideEqualTo(playerSide)) {
            throw new InvalidMovementException("이동하려는 위치에 자신의 기물이 존재합니다.");
        }
    }

    private void movePiece(Position from, Position to, Piece piece) {
        board.put(to, piece);
        board.put(from, Blank.getBlank());
        piece.moved();
    }

    private void validatePawnCase(Position from, Position to, Piece piece) {
        if (piece.isPawn()) {
            checkPawnMove(piece, from, to);
        }
    }

    private void checkPawnMove(Piece pawn, Position from, Position to) {
        if (pawn.diagonal(from, to)) {
            checkOpponentPieceNotExistInToPosition(to);
        }
        if (pawn.forward(from, to)) {
            checkPieceExistInToPosition(to);
        }
    }

    private void checkOpponentPieceNotExistInToPosition(Position to) {
        if (board.get(to).isBlank()) {
            throw new InvalidMovementException("이동하려는 위치에 상대 기물이 존재하지 않습니다.");
        }
    }

    private void checkPieceExistInToPosition(Position to) {
        if (!board.get(to).isBlank()) {
            throw new InvalidMovementException("이동하려는 위치에 기물이 존재합니다.");
        }
    }

    public String getPieceInitialByPosition(Position position) {
        return board.get(position).getInitial();
    }

    public boolean isGameSet() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() < KING_COUNT;
    }

    public double score(Side side) {
        return scoreWithoutPawn(side) + scoreOnlyPawn(side);
    }

    private double scoreOnlyPawn(Side side) {
        double score = 0;
        for (Column column : Column.values()) {
            score += Pawn.scoreByCount(pawnCountByColumn(side, column));
        }
        return score;
    }

    private int pawnCountByColumn(Side side, Column column) {
        int count = 0;
        for (Row row : Row.values()) {
            Piece piece = board.get(new Position(column, row));

            if (piece.isPawn() && piece.isSideEqualTo(side)) {
                count++;
            }
        }
        return count;
    }

    private double scoreWithoutPawn(Side side) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSideEqualTo(side))
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::score)
                .sum();
    }

    public Side winner() {
        if (!isGameSet()) {
            return Side.NONE;
        }

        Piece piece = board.values()
                .stream()
                .filter(Piece::isKing)
                .findAny()
                .orElseThrow(InvalidCommandException::new);

        if (piece.isSideEqualTo(Side.BLACK)) {
            return Side.BLACK;
        }
        return Side.WHITE;
    }
}
