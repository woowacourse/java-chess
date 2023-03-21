package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    private static final int LEFT_KNIGHT_FILE = 2;
    private static final int RIGHT_KNIGHT_FILE = 7;
    private static final int LEFT_BISHOP_FILE = 3;
    private static final int RIGHT_BISHOP_FILE = 6;
    private static final int PAWN_LOWER_BOUND = 1;
    private static final int PAWN_HIGHER_BOUND = 8;
    private static final int KING_FILE = 5;
    private static final int QUEEN_FILE = 4;

    public Board createInitialBoard() {
        final Map<Position, Piece> board = new HashMap<>();

        board.putAll(createPawn());
        board.putAll(createKing());
        board.putAll(createQueen());
        board.putAll(createBishop());
        board.putAll(createRook());
        board.putAll(createKnight());

        return new Board(board);
    }

    private Map<Position, Piece> createPawn() {
        final Map<Position, Piece> pieceMap = new HashMap<>();

        for (int file = PAWN_LOWER_BOUND; file <= PAWN_HIGHER_BOUND; file++) {
            final Position whitePosition = new Position(file, LEFT_KNIGHT_FILE);
            final Position blackPosition = new Position(file, RIGHT_KNIGHT_FILE);
            pieceMap.put(whitePosition, new Pawn(Color.WHITE));
            pieceMap.put(blackPosition, new Pawn(Color.BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        final Position whitePosition = new Position(KING_FILE, PAWN_LOWER_BOUND);
        final Position blackPosition = new Position(KING_FILE, PAWN_HIGHER_BOUND);

        return Map.of(
                whitePosition, new King(Color.WHITE),
                blackPosition, new King(Color.BLACK)
        );
    }

    private Map<Position, Piece> createKnight() {
        final Position whiteLeftPosition = new Position(LEFT_KNIGHT_FILE, PAWN_LOWER_BOUND);
        final Position whiteRightPosition = new Position(RIGHT_KNIGHT_FILE, PAWN_LOWER_BOUND);
        final Position blackLeftPosition = new Position(LEFT_KNIGHT_FILE, PAWN_HIGHER_BOUND);
        final Position blackRightPosition = new Position(RIGHT_KNIGHT_FILE, PAWN_HIGHER_BOUND);

        return Map.of(
                whiteLeftPosition, new Knight(Color.WHITE),
                whiteRightPosition, new Knight(Color.WHITE),
                blackLeftPosition, new Knight(Color.BLACK),
                blackRightPosition, new Knight(Color.BLACK)
        );
    }

    private Map<Position, Piece> createBishop() {
        final Position whiteLeftPosition = new Position(LEFT_BISHOP_FILE, PAWN_LOWER_BOUND);
        final Position whiteRightPosition = new Position(RIGHT_BISHOP_FILE, PAWN_LOWER_BOUND);
        final Position blackLeftPosition = new Position(LEFT_BISHOP_FILE, PAWN_HIGHER_BOUND);
        final Position blackRightPosition = new Position(RIGHT_BISHOP_FILE, PAWN_HIGHER_BOUND);

        return Map.of(
                whiteLeftPosition, new Bishop(Color.WHITE),
                whiteRightPosition, new Bishop(Color.WHITE),
                blackLeftPosition, new Bishop(Color.BLACK),
                blackRightPosition, new Bishop(Color.BLACK)
        );
    }

    private Map<Position, Piece> createQueen() {
        final Position whitePosition = new Position(QUEEN_FILE, PAWN_LOWER_BOUND);
        final Position blackPosition = new Position(QUEEN_FILE, PAWN_HIGHER_BOUND);

        return Map.of(
                whitePosition, new Queen(Color.WHITE),
                blackPosition, new Queen(Color.BLACK)
        );
    }

    private Map<Position, Piece> createRook() {
        final Position whiteLeftPosition = new Position(PAWN_LOWER_BOUND, PAWN_LOWER_BOUND);
        final Position whiteRightPosition = new Position(PAWN_HIGHER_BOUND, PAWN_LOWER_BOUND);
        final Position blackLeftPosition = new Position(PAWN_LOWER_BOUND, PAWN_HIGHER_BOUND);
        final Position blackRightPosition = new Position(PAWN_HIGHER_BOUND, PAWN_HIGHER_BOUND);

        return Map.of(
                whiteLeftPosition, new Rook(Color.WHITE),
                whiteRightPosition, new Rook(Color.WHITE),
                blackLeftPosition, new Rook(Color.BLACK),
                blackRightPosition, new Rook(Color.BLACK)
        );
    }
}
