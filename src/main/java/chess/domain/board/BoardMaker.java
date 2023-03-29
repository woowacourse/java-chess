package chess.domain.board;

import chess.domain.position.Position;
import chess.domain.Team;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.nonsliding.King;
import chess.domain.pieces.nonsliding.Knight;
import chess.domain.pieces.pawn.BlackPawn;
import chess.domain.pieces.pawn.WhitePawn;
import chess.domain.pieces.sliding.Bishop;
import chess.domain.pieces.sliding.Queen;
import chess.domain.pieces.sliding.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardMaker {

    private static final int MIN_COLUMN = 0;
    private static final int MIN_ROW = 0;
    private static final int ROW_OF_BLACK_PAWNS = 1;
    private static final int MAX_COLUMN = 8;
    private static final int MAX_ROW = 7;
    private static final int START_MIDDLE_ROW = 2;
    private static final int END_MIDDLE_ROW = 5;
    private static final int ROW_OF_WHITE_PAWNS = 6;

    public Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = new HashMap<>();

        putBlackPiecesExceptPawn(board);
        putPawns(board);
        putWhitePiecesExceptPawn(board);
        putEmptyPieces(board);

        return board;
    }

    private void putBlackPiecesExceptPawn(final Map<Position, Piece> board) {
        board.put(Position.of(MIN_ROW, 0), new Rook(Team.BLACK));
        board.put(Position.of(MIN_ROW, 1), new Knight(Team.BLACK));
        board.put(Position.of(MIN_ROW, 2), new Bishop(Team.BLACK));
        board.put(Position.of(MIN_ROW, 3), new Queen(Team.BLACK));
        board.put(Position.of(MIN_ROW, 4), new King(Team.BLACK));
        board.put(Position.of(MIN_ROW, 5), new Bishop(Team.BLACK));
        board.put(Position.of(MIN_ROW, 6), new Knight(Team.BLACK));
        board.put(Position.of(MIN_ROW, 7), new Rook(Team.BLACK));
    }

    private void putPawns(final Map<Position, Piece> board) {
        for (int column = MIN_COLUMN; column < MAX_COLUMN; column++) {
            board.put(Position.of(ROW_OF_BLACK_PAWNS, column), new BlackPawn());
            board.put(Position.of(ROW_OF_WHITE_PAWNS, column), new WhitePawn());
        }
    }

    private void putWhitePiecesExceptPawn(final Map<Position, Piece> board) {
        board.put(Position.of(MAX_ROW, 0), new Rook(Team.WHITE));
        board.put(Position.of(MAX_ROW, 1), new Knight(Team.WHITE));
        board.put(Position.of(MAX_ROW, 2), new Bishop(Team.WHITE));
        board.put(Position.of(MAX_ROW, 3), new Queen(Team.WHITE));
        board.put(Position.of(MAX_ROW, 4), new King(Team.WHITE));
        board.put(Position.of(MAX_ROW, 5), new Bishop(Team.WHITE));
        board.put(Position.of(MAX_ROW, 6), new Knight(Team.WHITE));
        board.put(Position.of(MAX_ROW, 7), new Rook(Team.WHITE));
    }

    private void putEmptyPieces(final Map<Position, Piece> board) {
        for (int row = START_MIDDLE_ROW; row <= END_MIDDLE_ROW; row++) {
            putEmptyPieces(row, board);
        }
    }

    private void putEmptyPieces(final int row, final Map<Position, Piece> board) {
        for (int column = MIN_COLUMN; column < MAX_COLUMN; column++) {
            board.put(Position.of(row, column), new EmptyPiece());
        }
    }
}
