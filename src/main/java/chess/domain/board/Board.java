package chess.domain.board;

import chess.domain.game.Side;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.InvalidMovementException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Board {

    private final Map<Position, Piece> board;
    private Side winner = Side.NONE;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board createGamingBoard() {
        return new Board(BoardInitializer.init());
    }

    public void move(Position from, Position to, Side playerSide) {
        Piece movingPiece = board.get(from);
        Piece targetPiece = board.get(to);

        List<Position> route = movingPiece.route(from, to, targetPiece, playerSide);
        checkRouteIsEmpty(route);
        movePiece(from, to, movingPiece);
        checkWinner(playerSide, targetPiece);
    }

    private void checkRouteIsEmpty(List<Position> route) {
        route.stream()
                .map(board::get)
                .filter(piece -> !piece.isBlank())
                .findAny()
                .ifPresent(isBlank -> {
                    throw new InvalidMovementException("이동경로에 다른 기물이 존재합니다.");
                });
    }

    private void movePiece(Position from, Position to, Piece piece) {
        board.put(to, piece);
        board.put(from, Blank.getBlank());
        piece.moved();
    }

    private void checkWinner(Side playerSide, Piece targetPiece) {
        if (targetPiece.isKing()) {
            winner = playerSide;
        }
    }

    public String getPieceInitialByPosition(Position position) {
        return board.get(position).getInitial();
    }

    public boolean isGameSet() {
        return winner != Side.NONE;
    }

    public Side winner() {
        return winner;
    }

    public double score(Side side) {
        return scoreWithoutPawn(side) + scoreOnlyPawn(side);
    }

    private double scoreWithoutPawn(Side side) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSideEqualTo(side))
                .mapToDouble(Piece::score)
                .sum();
    }

    private double scoreOnlyPawn(Side side) {
        return Arrays.stream(Column.values())
                .map(column -> pawnCountByColumn(side, column))
                .mapToDouble(Pawn::scoreByCount)
                .sum();
    }

    private int pawnCountByColumn(Side side, Column column) {
        return (int) Arrays.stream(Row.values())
                .map(row -> board.get(Position.of(column, row)))
                .filter(piece -> piece.isPawn() && piece.isSideEqualTo(side))
                .count();
    }
}
