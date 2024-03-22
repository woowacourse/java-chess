package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Position;
import chess.domain.piece.Piece;

public class  Chessboard {

    private final List<List<Piece>> chessboard;

    private Chessboard(final List<List<Piece>> chessboard) {
        this.chessboard = chessboard;
    }

    public static Chessboard create() {
        List<List<Piece>> pieces = new ArrayList<>();
        for (final Rank ignored : Rank.values()) {
            addPositions(pieces);
        }
        return new Chessboard(Collections.unmodifiableList(pieces));
    }

    private static void addPositions(final List<List<Piece>> pieces) {
        List<Piece> row = new ArrayList<>();
        pieces.add(Collections.unmodifiableList(row));
        for (final File ignored : File.values()) {
            row.add(null);
        }
    }

    public Piece pieceOf(final Position position) {
        return pieceOf(position.rank(), position.file());
    }

    public Piece pieceOf(final Rank rank, final File file) {
        List<Piece> row = chessboard.get(rank.toRow());
        return row.get(file.toColumn());
    }

    public List<List<Piece>> getChessboard() {
        return List.copyOf(chessboard);
    }
}
