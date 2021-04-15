package chess.domain.utils;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Team;
import chess.domain.piece.*;

import java.util.*;

public class PieceInitializer {
    private static final int BLACK_OTHER_PIECE_START_LINE = 8;
    private static final int WHITE_OTHER_PIECE_START_LINE = 1;

    private static final List<Piece> INITIAL_PIECES = new ArrayList<>();
    private static final Map<Position, Piece> PIECE_INFO = new LinkedHashMap<>();

    static {
        INITIAL_PIECES.addAll(Arrays.asList(
                new Rook(Team.BLACK),
                new Knight(Team.BLACK),
                new Bishop(Team.BLACK),
                new Queen(Team.BLACK),
                new King(Team.BLACK),
                new Bishop(Team.BLACK),
                new Knight(Team.BLACK),
                new Rook(Team.BLACK))
        );

        INITIAL_PIECES.addAll(Arrays.asList(
                new Rook(Team.WHITE),
                new Knight(Team.WHITE),
                new Bishop(Team.WHITE),
                new Queen(Team.WHITE),
                new King(Team.WHITE),
                new Bishop(Team.WHITE),
                new Knight(Team.WHITE),
                new Rook(Team.WHITE))
        );

        initPawn(PIECE_INFO);
        initPieceExceptPawn(PIECE_INFO);
    }

    public static Map<Position, Piece> pieceInfo() {
        return new LinkedHashMap<>(PIECE_INFO);
    }

    private static void initPawn(Map<Position, Piece> board) {
        for (File file : File.values()) {
            board.put(Position.of(file.getFile() + Pawn.BLACK_PAWN_START_LINE),
                    new Pawn(Team.BLACK));
            board.put(Position.of(file.getFile() + Pawn.WHITE_PAWN_START_LINE),
                    new Pawn(Team.WHITE));
        }
    }

    private static void initPieceExceptPawn(Map<Position, Piece> board) {
        File[] values = File.values();
        for (int i = 0; i < values.length; i++) {
            board.put(Position.of(values[i].getFile() + BLACK_OTHER_PIECE_START_LINE),
                    INITIAL_PIECES.get(i));
        }
        for (int i = 0; i < values.length; i++) {
            board.put(Position.of(values[i].getFile() + WHITE_OTHER_PIECE_START_LINE),
                    INITIAL_PIECES.get(i + values.length));
        }
    }
}
