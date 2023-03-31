package chess.domain.board;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.File.F;
import static chess.domain.position.File.G;
import static chess.domain.position.File.H;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.TWO;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public final class BoardFactory {

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
            final Position whitePosition = Position.of(File.from(file), TWO);
            final Position blackPosition = Position.of(File.from(file), SEVEN);
            pieceMap.put(whitePosition, Pawn.from(WHITE));
            pieceMap.put(blackPosition, Pawn.from(BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        pieceMap.put(Position.of(E, ONE), King.from(WHITE));
        pieceMap.put(Position.of(E, EIGHT), King.from(BLACK));
        return pieceMap;
    }

    private Map<Position, Piece> createQueen() {
        return Map.of(
                Position.of(D, ONE), Queen.from(WHITE),
                Position.of(D, EIGHT), Queen.from(BLACK)
        );
    }

    private Map<Position, Piece> createKnight() {
        return Map.of(
                Position.of(B, ONE), Knight.from(WHITE),
                Position.of(G, ONE), Knight.from(WHITE),
                Position.of(B, EIGHT), Knight.from(BLACK),
                Position.of(G, EIGHT), Knight.from(BLACK)
        );
    }

    private Map<Position, Piece> createBishop() {
        return Map.of(
                Position.of(C, ONE), Bishop.from(WHITE),
                Position.of(F, ONE), Bishop.from(WHITE),
                Position.of(C, EIGHT), Bishop.from(BLACK),
                Position.of(F, EIGHT), Bishop.from(BLACK)
        );
    }

    private Map<Position, Piece> createRook() {
        return Map.of(
                Position.of(A, ONE), Rook.from(WHITE),
                Position.of(H, ONE), Rook.from(WHITE),
                Position.of(A, EIGHT), Rook.from(BLACK),
                Position.of(H, EIGHT), Rook.from(BLACK)
        );
    }
}
