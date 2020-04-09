package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceDto;
import chess.domain.piece.pieces.Pieces;
import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Pieces pieces;

    public Board(Pieces pieces) {
        this.pieces = pieces;
    }

    public List<Rows> getRows() {
        List<Rows> rows = Arrays.stream(Column.values())
                .map(column -> new Rows(column, pieces))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(rows);
    }

    public Map<Position, PieceDto> getPiecesForTransfer() {
        Map<Position, PieceDto> board = new HashMap<>();
        for (Piece piece : pieces.getPieces()) {
            board.put(piece.getPosition(), new PieceDto(piece));
        }
        return board;
    }
}