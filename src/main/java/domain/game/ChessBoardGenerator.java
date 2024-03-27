package domain.game;

import static domain.position.File.END_LETTER;
import static domain.position.File.START_LETTER;
import static domain.position.Rank.END_NUMBER;
import static domain.position.Rank.START_NUMBER;

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
    private static final List<PieceRole> ROYAL_PIECES = List.of(
            Rook.create(), Knight.create(), Bishop.create(), Queen.create(),
            King.create(), Bishop.create(), Knight.create(), Rook.create()
    );
    private static final List<PieceRole> BLACK_PAWNS =
            IntStream.range(0, toColumnIndex(END_LETTER) + 1)
                    .mapToObj(number -> (PieceRole) BlackPawn.create())
                    .toList();
    private static final List<PieceRole> WHITE_PAWNS =
            IntStream.range(0, toColumnIndex(END_LETTER) + 1)
                    .mapToObj(number -> (PieceRole) WhitePawn.create())
                    .toList();
    private static final List<Piece> NONE = new ArrayList<>();
    private static final Map<Integer, List<Piece>> RANK_PIECES = new HashMap<>();

    static {
        RANK_PIECES.put(END_NUMBER, generateListPiece(ROYAL_PIECES, Color.BLACK));
        RANK_PIECES.put(END_NUMBER - 1, generateListPiece(BLACK_PAWNS, Color.BLACK));
        RANK_PIECES.put(START_NUMBER + 1, generateListPiece(WHITE_PAWNS, Color.WHITE));
        RANK_PIECES.put(START_NUMBER, generateListPiece(ROYAL_PIECES, Color.WHITE));
    }

    private static List<Piece> generateListPiece(
            final List<PieceRole> pieceRoles,
            final Color color
    ) {
        return pieceRoles.stream()
                .map(pieceRole -> new Piece(pieceRole, color))
                .toList();
    }

    public static ChessBoard generate() {
        Map<Position, Piece> piecePosition = new HashMap<>();
        for (int rank = END_NUMBER; rank >= START_NUMBER; rank--) {
            initializeEachRank(piecePosition, rank);
        }
        return new ChessBoard(piecePosition);
    }

    private static void initializeEachRank(
            final Map<Position, Piece> piecePosition,
            final int rank
    ) {
        List<Piece> pieces = RANK_PIECES.getOrDefault(rank, NONE);
        for (int column = toColumnIndex(START_LETTER); column < pieces.size(); column++) {
            Position position = new Position(new File(toFileLetter(column)), new Rank(rank));
            Piece piece = pieces.get(column);
            piecePosition.put(position, piece);
        }
    }

    private static int toColumnIndex(final char file) {
        return file - START_LETTER;
    }

    private static char toFileLetter(final int index) {
        return (char) (index + START_LETTER);
    }
}
