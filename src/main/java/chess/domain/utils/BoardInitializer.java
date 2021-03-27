package chess.domain.utils;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Team;
import chess.domain.piece.*;

import java.util.*;

public class BoardInitializer {
    public static final int BLACK_OTHER_PIECE_START_LINE = 8;
    public static final int WHITE_OTHER_PIECE_START_LINE = 1;

    public static final List<Piece> INITIAL_PIECES = new ArrayList<>();

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
    }

    public static Board init() {
        Map<Position, Piece> board = new HashMap<>();
        initPawn(board);
        initPieceExceptPawn(board);
        return new Board(board);
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

    private static void initPawn(Map<Position, Piece> board) {
        for (File file : File.values()) {
            board.put(Position.of(file.getFile() + Pawn.BLACK_PAWN_START_LINE),
                    new Pawn(Team.BLACK));
            board.put(Position.of(file.getFile() + Pawn.WHITE_PAWN_START_LINE),
                    new Pawn(Team.WHITE));
        }
    }
}
