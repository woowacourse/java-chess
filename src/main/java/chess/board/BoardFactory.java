package chess.board;

import static chess.path.InitialPositionFixtures.BLACK_BISHOP_LEFT_POSITION;
import static chess.path.InitialPositionFixtures.BLACK_BISHOP_RIGHT_POSITION;
import static chess.path.InitialPositionFixtures.BLACK_KING_POSITION;
import static chess.path.InitialPositionFixtures.BLACK_KNIGHT_LEFT_POSITION;
import static chess.path.InitialPositionFixtures.BLACK_KNIGHT_RIGHT_POSITION;
import static chess.path.InitialPositionFixtures.BLACK_QUEEN_POSITION;
import static chess.path.InitialPositionFixtures.BLACK_ROOK_LEFT_POSITION;
import static chess.path.InitialPositionFixtures.BLACK_ROOK_RIGHT_POSITION;
import static chess.path.InitialPositionFixtures.WHITE_BISHOP_LEFT_POSITION;
import static chess.path.InitialPositionFixtures.WHITE_BISHOP_RIGHT_POSITION;
import static chess.path.InitialPositionFixtures.WHITE_KING_POSITION;
import static chess.path.InitialPositionFixtures.WHITE_KNIGHT_LEFT_POSITION;
import static chess.path.InitialPositionFixtures.WHITE_KNIGHT_RIGHT_POSITION;
import static chess.path.InitialPositionFixtures.WHITE_QUEEN_POSITION;
import static chess.path.InitialPositionFixtures.WHITE_ROOK_LEFT_POSITION;
import static chess.path.InitialPositionFixtures.WHITE_ROOK_RIGHT_POSITION;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public Map<Position, Piece> createInitialBoard() {
        Map<Position, Piece> board = new HashMap<>();

        board.putAll(createPawn());
        board.putAll(createKing());
        board.putAll(createQueen());
        board.putAll(createBishop());
        board.putAll(createRook());
        board.putAll(createKnight());

        return board;
    }

    private Map<Position, Piece> createPawn() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        for (int file = 1; file <= 8; file++) {
            final Position whitePosition = new Position(file, 2);
            final Position blackPosition = new Position(file, 7);
            pieceMap.put(whitePosition, new Pawn(Color.WHITE));
            pieceMap.put(blackPosition, new Pawn(Color.BLACK));
        }

        return pieceMap;
    }

    private Map<Position, Piece> createKing() {
        Map<Position, Piece> pieceMap = new HashMap<>();

        pieceMap.put(WHITE_KING_POSITION, new King(Color.WHITE));
        pieceMap.put(BLACK_KING_POSITION, new King(Color.BLACK));

        return pieceMap;
    }

    private Map<Position, Piece> createQueen() {
        return Map.of(
                WHITE_QUEEN_POSITION, new Queen(Color.WHITE),
                BLACK_QUEEN_POSITION, new Queen(Color.BLACK)
        );
    }

    private Map<Position, Piece> createKnight() {
        return Map.of(
                WHITE_KNIGHT_LEFT_POSITION, new Knight(Color.WHITE),
                WHITE_KNIGHT_RIGHT_POSITION, new Knight(Color.WHITE),
                BLACK_KNIGHT_LEFT_POSITION, new Knight(Color.BLACK),
                BLACK_KNIGHT_RIGHT_POSITION, new Knight(Color.BLACK)
        );
    }

    private Map<Position, Piece> createBishop() {
        return Map.of(
                WHITE_BISHOP_LEFT_POSITION, new Bishop(Color.WHITE),
                WHITE_BISHOP_RIGHT_POSITION, new Bishop(Color.WHITE),
                BLACK_BISHOP_LEFT_POSITION, new Bishop(Color.BLACK),
                BLACK_BISHOP_RIGHT_POSITION, new Bishop(Color.BLACK)
        );
    }

    private Map<Position, Piece> createRook() {
        return Map.of(
                WHITE_ROOK_LEFT_POSITION, new Rook(Color.WHITE),
                WHITE_ROOK_RIGHT_POSITION, new Rook(Color.WHITE),
                BLACK_ROOK_LEFT_POSITION, new Rook(Color.BLACK),
                BLACK_ROOK_RIGHT_POSITION, new Rook(Color.BLACK)
        );
    }
}
