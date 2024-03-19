package chess.domain.board;

import static chess.domain.attribute.Color.BLACK;
import static chess.domain.attribute.Color.WHITE;
import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;

public class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        Map<Position, Piece> board = new HashMap<>();
        Set<Piece> pieces = new LinkedHashSet<>();
        pieces.addAll(createPieces(WHITE));
        pieces.addAll(createPieces(BLACK));
        for (final Piece piece : pieces) {
            PieceType pieceType = piece.getPieceType();
            for (int index = 0; index < pieceType.getCount(); index++) {
                for (final Color color : Color.values()) {
                    Position position = pieceType.startPosition(color, index);
                    board.put(position, piece);
                }
            }
        }
        return new Board(board);
    }

    private static Set<Piece> createPieces(final Color color) {
        Set<Piece> pieces = new LinkedHashSet<>();
        pieces.add(new King(color, KING));
        pieces.add(new Queen(color, QUEEN));
        pieces.add(new Bishop(color, BISHOP));
        pieces.add(new Bishop(color, BISHOP));
        pieces.add(new Knight(color, KNIGHT));
        pieces.add(new Knight(color, KNIGHT));
        pieces.add(new Rook(color, ROOK));
        pieces.add(new Rook(color, ROOK));
        for (int i = 0; i < PAWN.getCount(); i++) {
            pieces.add(new StartingPawn(color, PAWN));
        }
        return pieces;
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
