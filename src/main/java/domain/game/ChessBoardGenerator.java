package domain.game;

import static domain.game.File.A;
import static domain.game.File.B;
import static domain.game.File.C;
import static domain.game.File.D;
import static domain.game.File.E;
import static domain.game.File.F;
import static domain.game.File.G;
import static domain.game.File.H;
import static domain.game.Rank.EIGHT;
import static domain.game.Rank.ONE;
import static domain.game.Rank.SEVEN;
import static domain.game.Rank.TWO;

import domain.piece.Bishop;
import domain.piece.EmptyPiece;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
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
        chessBoard.put(Position.of(A, ONE), Rook.createOfWhite());
        chessBoard.put(Position.of(B, ONE), Knight.createOfWhite());
        chessBoard.put(Position.of(C, ONE), Bishop.createOfWhite());
        chessBoard.put(Position.of(D, ONE), Queen.createOfWhite());
        chessBoard.put(Position.of(E, ONE), King.createOfWhite());
        chessBoard.put(Position.of(F, ONE), Bishop.createOfWhite());
        chessBoard.put(Position.of(G, ONE), Knight.createOfWhite());
        chessBoard.put(Position.of(H, ONE), Rook.createOfWhite());
    }

    private void generateWhitePawns(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(A, TWO), Pawn.createOfWhite());
        chessBoard.put(Position.of(B, TWO), Pawn.createOfWhite());
        chessBoard.put(Position.of(C, TWO), Pawn.createOfWhite());
        chessBoard.put(Position.of(D, TWO), Pawn.createOfWhite());
        chessBoard.put(Position.of(E, TWO), Pawn.createOfWhite());
        chessBoard.put(Position.of(F, TWO), Pawn.createOfWhite());
        chessBoard.put(Position.of(G, TWO), Pawn.createOfWhite());
        chessBoard.put(Position.of(H, TWO), Pawn.createOfWhite());
    }

    private void generateBlackPiecesExcludePawn(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(A, EIGHT), Rook.createOfBlack());
        chessBoard.put(Position.of(B, EIGHT), Knight.createOfBlack());
        chessBoard.put(Position.of(C, EIGHT), Bishop.createOfBlack());
        chessBoard.put(Position.of(D, EIGHT), Queen.createOfBlack());
        chessBoard.put(Position.of(E, EIGHT), King.createOfBlack());
        chessBoard.put(Position.of(F, EIGHT), Bishop.createOfBlack());
        chessBoard.put(Position.of(G, EIGHT), Knight.createOfBlack());
        chessBoard.put(Position.of(H, EIGHT), Rook.createOfBlack());
    }

    private void generateBlackPawns(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(A, SEVEN), Pawn.createOfBlack());
        chessBoard.put(Position.of(B, SEVEN), Pawn.createOfBlack());
        chessBoard.put(Position.of(C, SEVEN), Pawn.createOfBlack());
        chessBoard.put(Position.of(D, SEVEN), Pawn.createOfBlack());
        chessBoard.put(Position.of(E, SEVEN), Pawn.createOfBlack());
        chessBoard.put(Position.of(F, SEVEN), Pawn.createOfBlack());
        chessBoard.put(Position.of(G, SEVEN), Pawn.createOfBlack());
        chessBoard.put(Position.of(H, SEVEN), Pawn.createOfBlack());
    }

    private void generateEmptyPieceByEachRank(Map<Position, Piece> chessBoard, Rank rank) {
        Arrays.stream(File.values())
                .forEach(file -> chessBoard.put(Position.of(file, rank), new EmptyPiece()));
    }
}
