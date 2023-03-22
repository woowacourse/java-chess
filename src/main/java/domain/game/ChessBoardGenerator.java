package domain.game;

import static domain.piece.File.A;
import static domain.piece.File.B;
import static domain.piece.File.C;
import static domain.piece.File.D;
import static domain.piece.File.E;
import static domain.piece.File.F;
import static domain.piece.File.G;
import static domain.piece.File.H;
import static domain.piece.Rank.EIGHT;
import static domain.piece.Rank.ONE;
import static domain.piece.Rank.SEVEN;
import static domain.piece.Rank.TWO;

import domain.piece.Bishop;
import domain.piece.EmptyPiece;
import domain.piece.File;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Queen;
import domain.piece.Rank;
import domain.piece.Rook;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardGenerator {

    public Map<Position, Piece> generate() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> generateEmptyPieceByEachRank(chessBoard, rank));

        generateWhitePiecesExcludePawn(chessBoard);
        generateWhitePawns(chessBoard);

        generateBlackPiecesExcludePawn(chessBoard);
        generateBlackPawns(chessBoard);

        return chessBoard;
    }

    private void generateWhitePiecesExcludePawn(Map<Position, Piece> chessBoard) {
        chessBoard.put(new Position(A, ONE), Rook.createOfWhite());
        chessBoard.put(new Position(B, ONE), Knight.createOfWhite());
        chessBoard.put(new Position(C, ONE), Bishop.createOfWhite());
        chessBoard.put(new Position(D, ONE), Queen.createOfWhite());
        chessBoard.put(new Position(E, ONE), King.createOfWhite());
        chessBoard.put(new Position(F, ONE), Bishop.createOfWhite());
        chessBoard.put(new Position(G, ONE), Knight.createOfWhite());
        chessBoard.put(new Position(H, ONE), Rook.createOfWhite());
    }

    private void generateWhitePawns(Map<Position, Piece> chessBoard) {
        chessBoard.put(new Position(A, TWO), Pawn.createOfWhite());
        chessBoard.put(new Position(B, TWO), Pawn.createOfWhite());
        chessBoard.put(new Position(C, TWO), Pawn.createOfWhite());
        chessBoard.put(new Position(D, TWO), Pawn.createOfWhite());
        chessBoard.put(new Position(E, TWO), Pawn.createOfWhite());
        chessBoard.put(new Position(F, TWO), Pawn.createOfWhite());
        chessBoard.put(new Position(G, TWO), Pawn.createOfWhite());
        chessBoard.put(new Position(H, TWO), Pawn.createOfWhite());
    }

    private void generateBlackPiecesExcludePawn(Map<Position, Piece> chessBoard) {
        chessBoard.put(new Position(A, EIGHT), Rook.createOfBlack());
        chessBoard.put(new Position(B, EIGHT), Knight.createOfBlack());
        chessBoard.put(new Position(C, EIGHT), Bishop.createOfBlack());
        chessBoard.put(new Position(D, EIGHT), Queen.createOfBlack());
        chessBoard.put(new Position(E, EIGHT), King.createOfBlack());
        chessBoard.put(new Position(F, EIGHT), Bishop.createOfBlack());
        chessBoard.put(new Position(G, EIGHT), Knight.createOfBlack());
        chessBoard.put(new Position(H, EIGHT), Rook.createOfBlack());
    }

    private void generateBlackPawns(Map<Position, Piece> chessBoard) {
        chessBoard.put(new Position(A, SEVEN), Pawn.createOfBlack());
        chessBoard.put(new Position(B, SEVEN), Pawn.createOfBlack());
        chessBoard.put(new Position(C, SEVEN), Pawn.createOfBlack());
        chessBoard.put(new Position(D, SEVEN), Pawn.createOfBlack());
        chessBoard.put(new Position(E, SEVEN), Pawn.createOfBlack());
        chessBoard.put(new Position(F, SEVEN), Pawn.createOfBlack());
        chessBoard.put(new Position(G, SEVEN), Pawn.createOfBlack());
        chessBoard.put(new Position(H, SEVEN), Pawn.createOfBlack());
    }

    private void generateEmptyPieceByEachRank(Map<Position, Piece> chessBoard, Rank rank) {
        Arrays.stream(File.values())
                .forEach(file -> chessBoard.put(new Position(file, rank), new EmptyPiece()));
    }
}
