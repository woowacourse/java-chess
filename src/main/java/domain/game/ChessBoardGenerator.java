package domain.game;

import domain.piece.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardGenerator {


    private static final String FILE_A = "a";
    private static final String FILE_B = "b";
    private static final String FILE_C = "c";
    private static final String FILE_D = "d";
    private static final String FILE_E = "e";
    private static final String FILE_F = "f";
    private static final String FILE_G = "g";
    private static final String FILE_H = "h";

    private static final String RANK_1 = "1";
    private static final String RANK_2 = "2";
    private static final String RANK_7 = "7";
    private static final String RANK_8 = "8";

    public Map<Position, Piece> generate() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> generateEmptyPieceByEachRank(chessBoard, rank));

        chessBoard.putAll(generateWhitePiecesExcludePawn());
        chessBoard.putAll(generateWhitePawns());

        chessBoard.putAll(generateBlackPiecesExcludePawn());
        chessBoard.putAll(generateBlackPawns());

        return chessBoard;
    }

    private Map<Position, Piece> generateWhitePiecesExcludePawn() {
        LinkedHashMap<Position, Piece> whitePiecesExcludePawns = new LinkedHashMap<>();

        whitePiecesExcludePawns.put(Position.of(FILE_C, RANK_1), new Bishop(Side.WHITE));
        whitePiecesExcludePawns.put(Position.of(FILE_D, RANK_1), new Queen(Side.WHITE));
        whitePiecesExcludePawns.put(Position.of(FILE_A, RANK_1), new Rook(Side.WHITE));
        whitePiecesExcludePawns.put(Position.of(FILE_B, RANK_1), new Knight(Side.WHITE));
        whitePiecesExcludePawns.put(Position.of(FILE_E, RANK_1), new King(Side.WHITE));
        whitePiecesExcludePawns.put(Position.of(FILE_F, RANK_1), new Bishop(Side.WHITE));
        whitePiecesExcludePawns.put(Position.of(FILE_G, RANK_1), new Knight(Side.WHITE));
        whitePiecesExcludePawns.put(Position.of(FILE_H, RANK_1), new Rook(Side.WHITE));

        return whitePiecesExcludePawns;
    }

    private Map<Position, Piece> generateWhitePawns() {
        LinkedHashMap<Position, Piece> whitePawns = new LinkedHashMap<>();

        whitePawns.put(Position.of(FILE_A, RANK_2), new Pawn(Side.WHITE));
        whitePawns.put(Position.of(FILE_B, RANK_2), new Pawn(Side.WHITE));
        whitePawns.put(Position.of(FILE_C, RANK_2), new Pawn(Side.WHITE));
        whitePawns.put(Position.of(FILE_D, RANK_2), new Pawn(Side.WHITE));
        whitePawns.put(Position.of(FILE_E, RANK_2), new Pawn(Side.WHITE));
        whitePawns.put(Position.of(FILE_F, RANK_2), new Pawn(Side.WHITE));
        whitePawns.put(Position.of(FILE_G, RANK_2), new Pawn(Side.WHITE));
        whitePawns.put(Position.of(FILE_H, RANK_2), new Pawn(Side.WHITE));

        return whitePawns;
    }

    private Map<Position, Piece> generateBlackPiecesExcludePawn() {
        LinkedHashMap<Position, Piece> blackPiecesExcludePawns = new LinkedHashMap<>();

        blackPiecesExcludePawns.put(Position.of(FILE_A, RANK_8), new Rook(Side.BLACK));
        blackPiecesExcludePawns.put(Position.of(FILE_B, RANK_8), new Knight(Side.BLACK));
        blackPiecesExcludePawns.put(Position.of(FILE_C, RANK_8), new Bishop(Side.BLACK));
        blackPiecesExcludePawns.put(Position.of(FILE_D, RANK_8), new Queen(Side.BLACK));
        blackPiecesExcludePawns.put(Position.of(FILE_E, RANK_8), new King(Side.BLACK));
        blackPiecesExcludePawns.put(Position.of(FILE_F, RANK_8), new Bishop(Side.BLACK));
        blackPiecesExcludePawns.put(Position.of(FILE_G, RANK_8), new Knight(Side.BLACK));
        blackPiecesExcludePawns.put(Position.of(FILE_H, RANK_8), new Rook(Side.BLACK));

        return blackPiecesExcludePawns;
    }

    private Map<Position, Piece> generateBlackPawns() {
        LinkedHashMap<Position, Piece> blackPawns = new LinkedHashMap<>();

        blackPawns.put(Position.of(FILE_A, RANK_7), new Pawn(Side.BLACK));
        blackPawns.put(Position.of(FILE_B, RANK_7), new Pawn(Side.BLACK));
        blackPawns.put(Position.of(FILE_C, RANK_7), new Pawn(Side.BLACK));
        blackPawns.put(Position.of(FILE_D, RANK_7), new Pawn(Side.BLACK));
        blackPawns.put(Position.of(FILE_E, RANK_7), new Pawn(Side.BLACK));
        blackPawns.put(Position.of(FILE_F, RANK_7), new Pawn(Side.BLACK));
        blackPawns.put(Position.of(FILE_G, RANK_7), new Pawn(Side.BLACK));
        blackPawns.put(Position.of(FILE_H, RANK_7), new Pawn(Side.BLACK));

        return blackPawns;
    }

    private void generateEmptyPieceByEachRank(Map<Position, Piece> chessBoard, Rank rank) {
        Arrays.stream(File.values())
                .forEach(file -> chessBoard.put(Position.of(file.getText(), rank.getText()), new EmptyPiece()));
    }
}
