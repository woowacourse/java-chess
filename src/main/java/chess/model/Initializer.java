package chess.model;

import chess.model.piece.Bishop;
import chess.model.piece.Color;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Initializer {

    private static final int LINE_RANGE = 8;

    public static Map<Square, Piece> initialize() {
        Map<Square, Piece> startingMembers = new HashMap<>();
        final List<File> files = Arrays.asList(File.values());

        initMajorPieces(Color.WHITE, Rank.ONE, files, startingMembers);
        initMajorPieces(Color.BLACK, Rank.EIGHT, files, startingMembers);
        initPawns(Color.WHITE, Rank.TWO, files, startingMembers);
        initPawns(Color.BLACK, Rank.SEVEN, files, startingMembers);
        initEmpty(startingMembers);

        return startingMembers;
    }


    private static void initMajorPieces(Color color, Rank rank, List<File> files,
                                 Map<Square, Piece> startingMembers) {
        List<Piece> majorPiecesLineup = majorPiecesLineup(color);
        for (int i = 0; i < majorPiecesLineup.size(); i++) {

            startingMembers.put(Square.of(files.get(i), rank), majorPiecesLineup.get(i));
        }
    }

    private static void initPawns(Color color, Rank rank, List<File> files,
                           Map<Square, Piece> startingMembers) {
        for (int i = 0; i < LINE_RANGE; i++) {
            startingMembers.put(Square.of(files.get(i), rank), new Pawn(color));
        }
    }

    private static List<Piece> majorPiecesLineup(final Color color) {
        return List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        );
    }

    private static void initEmpty(Map<Square, Piece> startingMembers) {
        for (Rank rank : Rank.values()) {
            fillSquareByFile(rank, startingMembers);
        }
    }

    private static void fillSquareByFile(Rank rank,
                                  Map<Square, Piece> startingMembers) {
        for (File file : File.values()) {
            Square square = Square.of(file, rank);
            checkEmpty(square, startingMembers);
        }
    }

    private static void checkEmpty(Square square,
                            Map<Square, Piece> startingMembers) {
        if (!startingMembers.containsKey(square)) {
            startingMembers.put(square, new Empty());
        }
    }

}
