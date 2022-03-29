package chess.domain.piece;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

import chess.domain.Position;
import chess.domain.piece.multiple.Bishop;
import chess.domain.piece.multiple.Queen;
import chess.domain.piece.multiple.Rook;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.single.King;
import chess.domain.piece.single.Knight;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private PieceFactory() {
        throw new AssertionError();
    }

    public static Map<Position, Piece> createNewChessBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createNewBlackPieces());
        pieces.putAll(createNewWhitePieces());
        return pieces;
    }

    private static Map<Position, Piece> createNewBlackPieces() {
        Map<Position, Piece> blackPieces = new HashMap<>(Map.of(
                Position.of('a', '8'), new Piece(BLACK, new Rook()),
                Position.of('b', '8'), new Piece(BLACK, new Knight()),
                Position.of('c', '8'), new Piece(BLACK, new Bishop()),
                Position.of('d', '8'), new Piece(BLACK, new Queen()),
                Position.of('e', '8'), new Piece(BLACK, new King()),
                Position.of('f', '8'), new Piece(BLACK, new Bishop()),
                Position.of('g', '8'), new Piece(BLACK, new Knight()),
                Position.of('h', '8'), new Piece(BLACK, new Rook())
        ));
        for (char i = 0; i < 8; i++) {
            blackPieces.put(Position.of((char) ('a' + i), '7'), new Piece(BLACK, new BlackPawn()));
        }
        return blackPieces;
    }

    private static Map<Position, Piece> createNewWhitePieces() {
        Map<Position, Piece> whitePieces = new HashMap<>(Map.of(
                Position.of('a', '1'), new Piece(WHITE, new Rook()),
                Position.of('b', '1'), new Piece(WHITE, new Knight()),
                Position.of('c', '1'), new Piece(WHITE, new Bishop()),
                Position.of('d', '1'), new Piece(WHITE, new Queen()),
                Position.of('e', '1'), new Piece(WHITE, new King()),
                Position.of('f', '1'), new Piece(WHITE, new Bishop()),
                Position.of('g', '1'), new Piece(WHITE, new Knight()),
                Position.of('h', '1'), new Piece(WHITE, new Rook())
        ));
        for (char i = 0; i < 8; i++) {
            whitePieces.put(Position.of((char) ('a' + i), '2'), new Piece(WHITE, new WhitePawn()));
        }
        return whitePieces;
    }
}
