package chess.domain.movestrategy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonMoveStrategy implements MoveStrategy{
    @Override
    public Set<Position> movable(Board board, Position source) {
        Set<Position> movable = new HashSet<>();
        Piece piece = board.pieceOfPosition(source);

        // 비숍, 킹, 퀸, 룩의 로직
        for (List<Position> positions : piece.vectors(source)) {
            movable.addAll(movablePosition(positions, board));
        }
        return movable;
    }

    private List<Position> movablePosition(List<Position> positions, Board board) {
        if (breakIndex(positions, board) < 0) {
            System.out.println("hello");
            return new ArrayList<>();
        }
        return positions.stream()
                .limit(breakIndex(positions, board))
                .collect(Collectors.toList());
    }

    private long breakIndex(List<Position> positions, Board board) {
        return Math.min(
                positions.indexOf(positions.stream().filter(position -> board.pieceOfPosition(position).isBlack()).findFirst()),
                positions.indexOf(positions.stream().filter(position -> board.pieceOfPosition(position).isWhite()).findFirst()) - 1
        );
    }
}
