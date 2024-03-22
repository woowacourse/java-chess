package model.chessboard;

import static model.piece.Color.BLACK;
import static model.piece.Color.WHITE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.piece.Color;
import model.piece.PieceHolder;
import model.piece.state.Bishop;
import model.piece.state.King;
import model.piece.state.Knight;
import model.piece.state.Pawn;
import model.piece.state.Queen;
import model.piece.state.Role;
import model.piece.state.Rook;
import model.piece.state.Square;
import model.position.Position;

public class ChessBoardFactory {

    private static final int FILE_A = 1;
    private static final int FILE_C = 3;
    private static final int FILE_D = 4;
    private static final int FILE_E = 5;
    private static final int FILE_H = 8;
    private static final int RANK_ONE = 1;
    private static final int RANK_TWO = 2;
    private static final int RANK_THREE = 3;
    private static final int RANK_FOUR = 4;
    private static final int RANK_FIVE = 5;
    private static final int RANK_SIX = 6;
    private static final int RANK_SEVEN = 7;
    private static final int RANK_EIGHT = 8;
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
        for (int file = FILE_A; file <= FILE_C; file++) {
            Role role = whiteTypePieces.get(file - 1);
            int mirrorFileIndex = 9 - file;
            chessBoard.put(Position.of(file, RANK_ONE), new PieceHolder(role));
            chessBoard.put(Position.of(mirrorFileIndex, RANK_ONE), new PieceHolder(role));
        }
    }

    private static void initBlackRookBishopKnight() {
        List<Role> blackTypePieces = generateColoredPieces(BLACK);
        for (int file = FILE_A; file <= FILE_C; file++) {
            Role role = blackTypePieces.get(file - 1);
            int mirrorFileIndex = 9 - file;
            chessBoard.put(Position.of(file, RANK_EIGHT), new PieceHolder(role));
            chessBoard.put(Position.of(mirrorFileIndex, RANK_EIGHT), new PieceHolder(role));
        }
    }

    private static List<Role> generateColoredPieces(Color color) {
        return List.of(Rook.from(color), Knight.from(color), Bishop.from(color));
    }

    private static void initKingQueen() {
        chessBoard.put(Position.of(FILE_D, RANK_ONE), new PieceHolder(Queen.from(WHITE)));
        chessBoard.put(Position.of(FILE_E, RANK_ONE), new PieceHolder(King.from(WHITE)));
        chessBoard.put(Position.of(FILE_D, RANK_EIGHT), new PieceHolder(Queen.from(BLACK)));
        chessBoard.put(Position.of(FILE_E, RANK_EIGHT), new PieceHolder(King.from(BLACK)));
    }

    private static void initPawn() {
        for (int file = 1; file <= 8; file++) {
            chessBoard.put(Position.of(file, RANK_TWO), new PieceHolder(Pawn.from(WHITE)));
            chessBoard.put(Position.of(file, RANK_SEVEN), new PieceHolder(Pawn.from(BLACK)));
        }
    }

    private static void initSquare() {
        for (int file = FILE_A; file <= FILE_H; file++) {
            chessBoard.put(Position.of(file, RANK_THREE), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, RANK_FOUR), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, RANK_FIVE), new PieceHolder(new Square()));
            chessBoard.put(Position.of(file, RANK_SIX), new PieceHolder(new Square()));
        }
    }
}
