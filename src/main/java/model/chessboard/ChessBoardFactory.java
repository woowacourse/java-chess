package model.chessboard;

import static model.piece.Color.BLACK;
import static model.piece.Color.WHITE;
import static util.FileConverter.*;
import static util.RankConverter.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.piece.Color;
import model.piece.PieceHolder;
import model.piece.role.Bishop;
import model.piece.role.King;
import model.piece.role.Knight;
import model.piece.role.Pawn;
import model.piece.role.Queen;
import model.piece.role.Role;
import model.piece.role.Rook;
import model.piece.role.Square;
import model.position.Position;

public class ChessBoardFactory {
    private static final Map<Position, PieceHolder> chessBoard = new HashMap<>();

    private ChessBoardFactory() {
    }

    public static Map<Position, PieceHolder> create() {
        initRookBishopKnight();
        initKingQueen();
        initPawn();
        initSquare();
        return chessBoard;
    }

    private static void initRookBishopKnight() {
        initWhiteRookBishopKnight();
        initBlackRookBishopKnight();
    }

    private static void initWhiteRookBishopKnight() {
        List<Role> whiteTypePieces = generateColoredPieces(WHITE);
        for (int file = A.getValue(); file <= C.getValue(); file++) {
            Role role = whiteTypePieces.get(file - 1);
            int mirrorFileIndex = 9 - file;
            chessBoard.put(Position.of(file, ONE.getValue()), new PieceHolder(role));
            chessBoard.put(Position.of(mirrorFileIndex, ONE.getValue()), new PieceHolder(role));
        }
    }

    private static void initBlackRookBishopKnight() {
        List<Role> blackTypePieces = generateColoredPieces(BLACK);
        for (int file = A.getValue(); file <= C.getValue(); file++) {
            Role role = blackTypePieces.get(file - 1);
            int mirrorFileIndex = 9 - file;
            chessBoard.put(Position.of(file, EIGHT.getValue()), new PieceHolder(role));
            chessBoard.put(Position.of(mirrorFileIndex, EIGHT.getValue()), new PieceHolder(role));
        }
    }

    private static List<Role> generateColoredPieces(Color color) {
        return List.of(Rook.from(color), Knight.from(color), Bishop.from(color));
    }

    private static void initKingQueen() {
        chessBoard.put(Position.of(D.getValue(), ONE.getValue()), new PieceHolder(Queen.from(WHITE)));
        chessBoard.put(Position.of(E.getValue(), ONE.getValue()), new PieceHolder(King.from(WHITE)));
        chessBoard.put(Position.of(D.getValue(), EIGHT.getValue()), new PieceHolder(Queen.from(BLACK)));
        chessBoard.put(Position.of(E.getValue(), EIGHT.getValue()), new PieceHolder(King.from(BLACK)));
    }

    private static void initPawn() {
        for (int file = A.getValue(); file <= H.getValue(); file++) {
            chessBoard.put(Position.of(file, TWO.getValue()), new PieceHolder(Pawn.from(WHITE)));
            chessBoard.put(Position.of(file, SEVEN.getValue()), new PieceHolder(Pawn.from(BLACK)));
        }
    }

    private static void initSquare() {
        for (int file = A.getValue(); file <= H.getValue(); file++) {
            chessBoard.put(Position.of(file, THREE.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, FOUR.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, FIVE.getValue()), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, SIX.getValue()), new PieceHolder(new Square()));
        }
    }
}
