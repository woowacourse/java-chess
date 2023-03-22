package chess.domain.board;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.piece.Bishop;
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

    public Board createInitialBoard() {
        Map<Position, Piece> board = new HashMap<>();

        board.putAll(createPawn());
        board.putAll(createKing());
        board.putAll(createQueen());
        board.putAll(createBishop());
        board.putAll(createRook());
        board.putAll(createKnight());

        return new Board(board);
    }

    private Map<Position, Piece> createPawn() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        for (int file = 1; file <= 8; file++) {
            final Position whitePosition = Position.of(file, 2);
            final Position blackPosition = Position.of(file, 7);
            pieceMap.put(whitePosition, Pawn.from(WHITE));
            pieceMap.put(blackPosition, Pawn.from(BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        pieceMap.put(Position.of(5, 1), King.from(WHITE));
        pieceMap.put(Position.of(5, 8), King.from(BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createQueen() {
        return Map.of(
                Position.of(4, 1), Queen.from(WHITE),
                Position.of(4, 8), Queen.from(BLACK)
        );
    }

    private Map<Position, Piece> createKnight() {
        return Map.of(
                Position.of(2, 1), Knight.from(WHITE),
                Position.of(7, 1), Knight.from(WHITE),
                Position.of(2, 8), Knight.from(BLACK),
                Position.of(7, 8), Knight.from(BLACK)
        );
    }

    private Map<Position, Piece> createBishop() {
        return Map.of(
                Position.of(3, 1), Bishop.from(WHITE),
                Position.of(6, 1), Bishop.from(WHITE),
                Position.of(3, 8), Bishop.from(BLACK),
                Position.of(6, 8), Bishop.from(BLACK)
        );
    }

    private Map<Position, Piece> createRook() {
        return Map.of(
                Position.of(1, 1), Rook.from(WHITE),
                Position.of(8, 1), Rook.from(WHITE),
                Position.of(1, 8), Rook.from(BLACK),
                Position.of(8, 8), Rook.from(BLACK)
        );
    }
}
