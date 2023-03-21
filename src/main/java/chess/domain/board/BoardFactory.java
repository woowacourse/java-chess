package chess.domain.board;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.position.InitialPositionFixtures.BLACK_BISHOP_LEFT_POSITION;
import static chess.domain.position.InitialPositionFixtures.BLACK_KING_POSITION;
import static chess.domain.position.InitialPositionFixtures.BLACK_KNIGHT_LEFT_POSITION;
import static chess.domain.position.InitialPositionFixtures.BLACK_KNIGHT_RIGHT_POSITION;
import static chess.domain.position.InitialPositionFixtures.BLACK_QUEEN_POSITION;
import static chess.domain.position.InitialPositionFixtures.BLACK_ROOK_LEFT_POSITION;
import static chess.domain.position.InitialPositionFixtures.BLACK_ROOK_RIGHT_POSITION;
import static chess.domain.position.InitialPositionFixtures.WHITE_BISHOP_RIGHT_POSITION;
import static chess.domain.position.InitialPositionFixtures.WHITE_KING_POSITION;
import static chess.domain.position.InitialPositionFixtures.WHITE_KNIGHT_LEFT_POSITION;
import static chess.domain.position.InitialPositionFixtures.WHITE_KNIGHT_RIGHT_POSITION;
import static chess.domain.position.InitialPositionFixtures.WHITE_QUEEN_POSITION;
import static chess.domain.position.InitialPositionFixtures.WHITE_ROOK_LEFT_POSITION;
import static chess.domain.position.InitialPositionFixtures.WHITE_ROOK_RIGHT_POSITION;

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
            final Position whitePosition = new Position(file, 2);
            final Position blackPosition = new Position(file, 7);
            pieceMap.put(whitePosition, Pawn.from(WHITE));
            pieceMap.put(blackPosition, Pawn.from(BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        pieceMap.put(WHITE_KING_POSITION, King.from(WHITE));
        pieceMap.put(BLACK_KING_POSITION, King.from(BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createQueen() {
        return Map.of(
                WHITE_QUEEN_POSITION, Queen.from(WHITE),
                BLACK_QUEEN_POSITION, Queen.from(BLACK)
        );
    }

    private Map<Position, Piece> createKnight() {
        return Map.of(
                WHITE_KNIGHT_LEFT_POSITION, Knight.from(WHITE),
                WHITE_KNIGHT_RIGHT_POSITION, Knight.from(WHITE),
                BLACK_KNIGHT_LEFT_POSITION, Knight.from(BLACK),
                BLACK_KNIGHT_RIGHT_POSITION, Knight.from(BLACK)
        );
    }

    private Map<Position, Piece> createBishop() {
        return Map.of(
                new Position(3, 1), Bishop.from(WHITE),
                WHITE_BISHOP_RIGHT_POSITION, Bishop.from(WHITE),
                BLACK_BISHOP_LEFT_POSITION, Bishop.from(BLACK),
                new Position(6, 8), Bishop.from(BLACK)
        );
    }

    private Map<Position, Piece> createRook() {
        return Map.of(
                WHITE_ROOK_LEFT_POSITION, Rook.from(WHITE),
                WHITE_ROOK_RIGHT_POSITION, Rook.from(WHITE),
                BLACK_ROOK_LEFT_POSITION, Rook.from(BLACK),
                BLACK_ROOK_RIGHT_POSITION, Rook.from(BLACK)
        );
    }
}
