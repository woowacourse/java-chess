package chess.domain.chessboard;

import static chess.domain.chesspiece.Color.BLACK;
import static chess.domain.chesspiece.Color.WHITE;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardFactory {

    private ChessBoardFactory() {
    }

    public static ChessBoard createChessBoard() {
        final Map<Position, ChessPiece> map = createInitPieceByPosition();
        return new ChessBoard(map);
    }

    public static Map<Position, ChessPiece> createInitPieceByPosition() {
        final Map<Position, ChessPiece> map = new HashMap<>();

        map.putAll(kingInitPosition());
        map.putAll(queenInitPosition());
        map.putAll(bishopInitPosition());
        map.putAll(rookInitPosition());
        map.putAll(knightInitPosition());
        map.putAll(pawnInitPosition());

        return map;
    }

    private static Map<Position, ChessPiece> kingInitPosition() {
        final Map<Position, ChessPiece> map = new HashMap<>();

        map.put(Position.from("e1"), King.from(WHITE));
        map.put(Position.from("e8"), King.from(BLACK));

        return map;
    }

    private static Map<Position, ChessPiece> queenInitPosition() {
        final Map<Position, ChessPiece> map = new HashMap<>();

        map.put(Position.from("d1"), Queen.from(WHITE));
        map.put(Position.from("d8"), Queen.from(BLACK));

        return map;
    }

    private static Map<Position, ChessPiece> bishopInitPosition() {
        final Map<Position, ChessPiece> map = new HashMap<>();

        map.put(Position.from("c1"), Bishop.from(WHITE));
        map.put(Position.from("f1"), Bishop.from(WHITE));

        map.put(Position.from("c8"), Bishop.from(BLACK));
        map.put(Position.from("f8"), Bishop.from(BLACK));

        return map;
    }

    private static Map<Position, ChessPiece> rookInitPosition() {
        final Map<Position, ChessPiece> map = new HashMap<>();

        map.put(Position.from("a1"), Rook.from(WHITE));
        map.put(Position.from("h1"), Rook.from(WHITE));

        map.put(Position.from("a8"), Rook.from(BLACK));
        map.put(Position.from("h8"), Rook.from(BLACK));

        return map;
    }

    private static Map<Position, ChessPiece> knightInitPosition() {
        final Map<Position, ChessPiece> map = new HashMap<>();

        map.put(Position.from("b1"), Knight.from(WHITE));
        map.put(Position.from("g1"), Knight.from(WHITE));

        map.put(Position.from("b8"), Knight.from(BLACK));
        map.put(Position.from("g8"), Knight.from(BLACK));

        return map;
    }

    private static Map<Position, ChessPiece> pawnInitPosition() {
        final Map<Position, ChessPiece> map = new HashMap<>();
        for (final File file : File.values()) {
            map.put(Position.of(file, Rank.TWO), Pawn.from(WHITE));
            map.put(Position.of(file, Rank.SEVEN), Pawn.from(BLACK));
        }

        return map;
    }
}
