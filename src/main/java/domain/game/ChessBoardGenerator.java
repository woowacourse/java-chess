package domain.game;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.BlackPawn;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.PieceRole;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.piece.piecerole.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoardGenerator {
    private static final int CHESS_RANK_START = 1;
    private static final int CHESS_RANK_END = 8;
    private static final char CHESS_FILE_START = 'a';
    private static final char CHESS_FILE_END = 'h';
    private static final List<PieceRole> ROYAL_PIECES = List.of(
            Rook.from(), Knight.from(), Bishop.from(), Queen.from(),
            King.from(), Bishop.from(), Knight.from(), Rook.from()
    );
    private static final List<PieceRole> BLACK_PAWNS = IntStream.range(0, toColumnIndex(CHESS_FILE_END) + 1)
            .mapToObj(number -> (PieceRole) BlackPawn.from())
            .toList();
    private static final List<PieceRole> WHITE_PAWNS = IntStream.range(0, toColumnIndex(CHESS_FILE_END) + 1)
            .mapToObj(number -> (PieceRole) WhitePawn.from())
            .toList();
    private static final List<Piece> NONE = new ArrayList<>();
    private static final Map<Integer, List<Piece>> RANK_PIECES = new HashMap<>();

    static {
        RANK_PIECES.put(8, generateListPiece(ROYAL_PIECES, Color.BLACK));
        RANK_PIECES.put(7, generateListPiece(BLACK_PAWNS, Color.BLACK));
        RANK_PIECES.put(2, generateListPiece(WHITE_PAWNS, Color.WHITE));
        RANK_PIECES.put(1, generateListPiece(ROYAL_PIECES, Color.WHITE));
    }

    private static List<Piece> generateListPiece(final List<PieceRole> pieceRoles, final Color color) {
        return pieceRoles.stream()
                .map(pieceRole -> new Piece(pieceRole, color))
                .toList();
    }

    public static ChessBoard generate() {
        Map<Position, Piece> piecePosition = new HashMap<>();
        for (int rank = CHESS_RANK_END; rank >= CHESS_RANK_START; rank--) {
            initializeEachRank(piecePosition, rank);
        }
        return new ChessBoard(piecePosition);
    }

    private static void initializeEachRank(final Map<Position, Piece> piecePosition, final int rank) {
        List<Piece> pieces = RANK_PIECES.getOrDefault(rank, NONE);
        for (int column = toColumnIndex(CHESS_FILE_START); column < pieces.size(); column++) {
            Position position = new Position(new File(toFileLetter(column)), new Rank(rank));
            Piece piece = pieces.get(column);
            piecePosition.put(position, piece);
        }
    }

    private static int toColumnIndex(final char file) {
        return file - CHESS_FILE_START;
    }

    private static char toFileLetter(final int index) {
        return (char) (index + CHESS_FILE_START);
    }
}
