package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoardGenerator {

    private static final int BLACK_PAWN_INITIAL_ROW = 6;
    private static final int WHITE_PAWN_INITIAL_ROW = 1;

    private static final int BLACK_EDGE_ROW = 7;
    private static final int WHITE_EDGE_ROW = 0;
    private static final int EMPTY_START_ROW = 2;
    private static final int EMPTY_END_ROW = 5;

    private static final int LINE_SIZE = 8;


    public static Board createBoard() {
        HashMap<Position, Piece> board = new HashMap<>();
        initializePawns(board, Team.WHITE, WHITE_PAWN_INITIAL_ROW);
        initializePawns(board, Team.BLACK, BLACK_PAWN_INITIAL_ROW);
        initializePiecesOfEdgeLine(board, Team.WHITE, WHITE_EDGE_ROW);
        initializePiecesOfEdgeLine(board, Team.BLACK, BLACK_EDGE_ROW);
        initializeEmptyPieces(board);
        return new Board(board);
    }

    private static void initializeEmptyPieces(final HashMap<Position, Piece> board) {
        for (int i = 0; i < LINE_SIZE; i++) {
            for (int j = EMPTY_START_ROW; j <= EMPTY_END_ROW; j++) {
                board.put(new Position(i, j), new EmptyPiece());
            }
        }
    }

    private static void initializePiecesOfEdgeLine(final HashMap<Position, Piece> board, final Team team, final int row) {
        List<Piece> pieces = new ArrayList<>(
                List.of(Rook.from(team), Knight.from(team), Bishop.from(team), Queen.from(team),
                        King.from(team), Bishop.from(team), Knight.from(team), Rook.from(team))
        );
        for (int i = 0; i < LINE_SIZE; i++) {
            board.put(new Position(i, row), pieces.get(i));
        }
    }

    private static void initializePawns(final HashMap<Position, Piece> board, final Team team, final int row) {
        for (int i = 0; i < LINE_SIZE; i++) {
            board.put(new Position(i, row), Pawn.from(team));
        }
    }
}
