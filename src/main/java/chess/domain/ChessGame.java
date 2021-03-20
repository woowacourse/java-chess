package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;

import java.util.*;
import java.util.stream.Collectors;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public List<Map<Position, Piece>> start() {
        Board board = new Board();
        return board.getSquares();
    }

    public Set<Position> movable(Position source) {
        Set<Position> movable = new HashSet<>();
        Rook rook = (Rook) board.pieceOfPosition(source);
        for (List<Position> positions : rook.moveStrategy(source)) {
            movable.addAll(movablePosition(positions));
        }
        return movable;
    }

    private List<Position> movablePosition(List<Position> positions) {
        if (breakIndex(positions) < 0) {
            return new ArrayList<>();
        }
        return positions.stream()
                .limit(breakIndex(positions))
                .collect(Collectors.toList());
    }

    private long breakIndex(List<Position> positions) {
        return Math.min(
                positions.indexOf(positions.stream().filter(position -> board.pieceOfPosition(position).isBlack()).findFirst()),
                positions.indexOf(positions.stream().filter(position -> board.pieceOfPosition(position).isWhite()).findFirst()) - 1
        );
    }
}
