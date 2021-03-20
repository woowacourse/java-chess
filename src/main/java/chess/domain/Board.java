package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.InvalidMovementException;

import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board getGamingBoard() {
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

    private void movePiece(Position from, Position to, Piece piece) {
        board.put(to, piece);
        board.put(from, Blank.getBlank());
        piece.moved();
    }

    private void validatePawnCase(Position from, Position to, Piece piece) {
        if (piece.isPawn()) {
            // TODO 폰에 관한 로직은 폰에게 옮기기
            checkMoveDiagonal(from, to);
            checkMoveVertical(from, to);
        }
    }

    private void validateMyPieceExistToPosition(Position to, Side playerSide) {
        if (board.get(to).isSideEqualTo(playerSide)) {
            throw new InvalidMovementException("이동하려는 위치에 자신의 기물이 존재합니다.");
        }
    }

    private void validateIsMyPiece(Side playerSide, Piece piece) {
        if (!piece.isSideEqualTo(playerSide)) {
            throw new InvalidMovementException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    private void checkMoveVertical(Position from, Position to) {
        if (Position.differenceOfColumn(from, to) == 0 || Position.differenceOfRow(from, to) == 0) {
            checkPieceExistInToPosition(to);
        }
    }

    private void checkPieceExistInToPosition(Position to) {
        if (!board.get(to).isBlank()) {
            throw new InvalidMovementException("이동하려는 위치에 기물이 존재합니다.");
        }
    }

    private void checkMoveDiagonal(Position from, Position to) {
        if (Math.abs(Position.differenceOfRow(from, to)) == Math.abs(Position.differenceOfColumn(from, to))) {
            checkOpponentPieceNotExistInToPosition(to);
        }
    }

    private void checkOpponentPieceNotExistInToPosition(Position to) {
        if (board.get(to).isBlank()) {
            throw new InvalidMovementException("이동하려는 위치에 상대 기물이 존재하지 않습니다.");
        }
    }

    private void checkRoute(List<Position> route) {
        // TODO 가독성 좋게 개선(이동경로 장애물 체크)
        route.stream()
                .map(board::get)
                .map(Piece::isBlank)
                .filter(isBlank -> !isBlank)
                .findAny()
                .ifPresent(isBlank -> {
                    throw new InvalidMovementException("이동경로에 다른 기물이 존재합니다.");
                });
    }

    public String getInitial(Position position) {
        return board.get(position).getInitial();
    }

    public boolean isGameSet() {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .count() < 2;
    }

    public double score(Side side) {
        return scoreWithoutPawn(side) + scoreOnlyPawn(side);
    }

    private double scoreOnlyPawn(Side side) {
        double score = 0;

        for (Column column : Column.values()) {
            int count = 0;

            for (Row row : Row.values()) {
                Piece piece = board.get(new Position(column, row));

                if (piece.isPawn() && piece.isSideEqualTo(side)) {
                    count++;
                }
            }

            if (count == 1) {
                score += 1;
            }
            if (count > 1) {
                score += count * 0.5;
            }
        }

        return score;
    }

    private double scoreWithoutPawn(Side side) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSideEqualTo(side))
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::score)
                .sum();
    }
}
