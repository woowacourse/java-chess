package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import chess.domain.attribute.File;
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;
import chess.domain.chessboard.attribute.square.Square;
import chess.domain.piece.Piece;

public class  Chessboard {

    private final List<List<Square>> chessboard;

    private Chessboard(final List<List<Square>> chessboard) {
        this.chessboard = chessboard;
    }

    public static Chessboard create() {
        List<List<Square>> pieces = new ArrayList<>();
        for (final Rank ignored : Rank.values()) {
            addPositions(pieces);
        }
        return new Chessboard(Collections.unmodifiableList(pieces));
    }

    private static void addPositions(final List<List<Square>> pieces) {
        List<Square> row = new ArrayList<>();
        pieces.add(Collections.unmodifiableList(row));
        for (final File ignored : File.values()) {
            row.add();
        }
    }

    public Optional<Piece> pieceIn(final Position position) {
        return pieceIn(position.rank(), position.file());
    }

    public Optional<Piece> pieceIn(final Rank rank, final File file) {
        List<Square> row = chessboard.get(rank.toRow());
        Square square = row.get(file.toColumn());
        return square.piece();
    }

    public List<List<Square>> getChessboard() {
        return List.copyOf(chessboard);
    }
}
