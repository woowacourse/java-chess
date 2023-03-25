package chess.boardStrategy;

import chess.boardstrategy.BoardStrategy;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.type.EmptyPiece;
import chess.domain.piece.type.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmptyBoardStrategy implements BoardStrategy {

    private final Map<Position, Piece> board = new HashMap<>();

    /**
     * ........ 8
     * ........ 7
     * .....p.. 6
     * ..B..... 5
     * ...b.... 4
     * ........ 3
     * ........ 2
     * ........ 1

     * abcdefgh
     */
    @Override
    public Map<Position, Piece> generate() {
        initEmptyPieces();
        return board;
    }


    private void initEmptyPieces() {
        Map<Position, Piece> emptyPieces = Column.getOrderedColumns().stream()
                .flatMap(column -> Rank.getOrderedRanks().stream()
                        .map(rank -> Position.of(column, rank)))
                .collect(Collectors.toMap(Function.identity(), ignored -> EmptyPiece.of()));

        board.putAll(emptyPieces);
    }

}
