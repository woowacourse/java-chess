package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardInitializer {
    public static final int BLACK_OTHER_PIECE_START_LINE = 8;
    public static final int BLACK_PAWN_START_LINE = 7;
    public static final int WHITE_PAWN_START_LINE = 2;
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

        for (File file : File.values()) {
            board.put(Position.of(file.getFile()+BLACK_PAWN_START_LINE), new Pawn(Team.BLACK));
            board.put(Position.of(file.getFile()+WHITE_PAWN_START_LINE), new Pawn(Team.WHITE));
        }

        File[] values = File.values();
        for (int i=0; i<values.length; i++) {
            board.put(Position.of(values[i].getFile() + BLACK_OTHER_PIECE_START_LINE), INITIAL_PIECES.get(i));
        }
        for (int i=0; i<values.length; i++) {
            board.put(Position.of(values[i].getFile() + WHITE_OTHER_PIECE_START_LINE), INITIAL_PIECES.get(i + values.length));
        }

        return new Board(board);
    }
}
