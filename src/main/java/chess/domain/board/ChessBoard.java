package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.*;

public class ChessBoard {
    private final Map<Position, Piece> boards;

    public ChessBoard(final Map<Position, Piece> boards) {
        this.boards = new LinkedHashMap<>(boards);
    }

    public void addPieces(final Pieces pieces) {
        pieces.getPieces()
                .forEach(piece -> boards.put(piece.getPosition(), piece));
    }

    public Map<Position, Piece> boards() {
        return Collections.unmodifiableMap(boards);
    }

    public int getRankSize() {
        return Rank.size();
    }

    public int getFileSize() {
        return File.size();
    }
}
