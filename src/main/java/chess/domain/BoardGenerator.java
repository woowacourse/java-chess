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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BoardGenerator {

    private static final int BLACK_EDGE_ROW = 7;
    private static final int BLACK_PAWN_INITIAL_ROW = 6;
    private static final int EMPTY_END_ROW = 5;
    private static final int EMPTY_START_ROW = 2;
    private static final int WHITE_PAWN_INITIAL_ROW = 1;
    private static final int WHITE_EDGE_ROW = 0;
    private static final int LINE_SIZE = 8;


    public static Board createBoard() {
        HashMap<Position, Piece> board = new LinkedHashMap<>();
        board.putAll(initializePiecesOfEdgeLine(Team.BLACK, BLACK_EDGE_ROW));
        board.putAll(initializePawns(Team.BLACK, BLACK_PAWN_INITIAL_ROW));
        board.putAll(initializeEmptyPieces());
        board.putAll(initializePawns(Team.WHITE, WHITE_PAWN_INITIAL_ROW));
        board.putAll(initializePiecesOfEdgeLine(Team.WHITE, WHITE_EDGE_ROW));
        return new Board(board);
    }

    private static Map<Position, Piece> initializeEmptyPieces() {
        Map<Position, Piece> positionsAndPieces = new LinkedHashMap<>();
        for (int column = 0; column < LINE_SIZE; column++) {
            for (int row = EMPTY_START_ROW; row <= EMPTY_END_ROW; row++) {
                positionsAndPieces.put(new Position(column, row), new EmptyPiece());
            }
        }
        return positionsAndPieces;
    }

    private static Map<Position, Piece> initializePiecesOfEdgeLine(final Team team, final int row) {
        Map<Position, Piece> positionsAndPieces = new LinkedHashMap<>();
        List<Piece> pieces = new ArrayList<>(
                List.of(Rook.from(team), Knight.from(team), Bishop.from(team), Queen.from(team),
                        King.from(team), Bishop.from(team), Knight.from(team), Rook.from(team))
        );
        for (int column = 0; column < LINE_SIZE; column++) {
            positionsAndPieces.put(new Position(column, row), pieces.get(column));
        }
        return positionsAndPieces;
    }

    private static Map<Position, Piece> initializePawns(final Team team, final int row) {
        Map<Position, Piece> positionsAndPieces = new LinkedHashMap<>();
        for (int column = 0; column < LINE_SIZE; column++) {
            positionsAndPieces.put(new Position(column, row), Pawn.from(team));
        }
        return positionsAndPieces;
    }
}
