package chess.domain;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position from, final Position to) {
        board.put(to, board.get(from));
        board.put(from, new EmptyPiece());
    }

    public int countPiece(final Piece piece) {
        return (int) board.values().stream()
                .filter(value -> value.equals(piece))
                .count();
    }

    public int countPieceOnSameFile(final Piece piece, final File file) {
        return (int) board.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSameFile(file))
                .filter(entry -> entry.getValue().equals(piece))
                .count();
    }

    public Piece findPiece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(board));
    }
}
