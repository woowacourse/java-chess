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

        generateWhitePiecesExcludePawn(chessBoard);
        generateWhitePawns(chessBoard);

        generateBlackPiecesExcludePawn(chessBoard);
        generateBlackPawns(chessBoard);

        return chessBoard;
    }

    private void generateWhitePiecesExcludePawn(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(FILE_C, RANK_1), Bishop.createOfWhite());
        chessBoard.put(Position.of(FILE_D, RANK_1), Queen.createOfWhite());
        chessBoard.put(Position.of(FILE_A, RANK_1), Rook.createOfWhite());
        chessBoard.put(Position.of(FILE_B, RANK_1), Knight.createOfWhite());
        chessBoard.put(Position.of(FILE_E, RANK_1), King.createOfWhite());
        chessBoard.put(Position.of(FILE_F, RANK_1), Bishop.createOfWhite());
        chessBoard.put(Position.of(FILE_G, RANK_1), Knight.createOfWhite());
        chessBoard.put(Position.of(FILE_H, RANK_1), Rook.createOfWhite());
    }

    private void generateWhitePawns(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(FILE_A, RANK_2), Pawn.createOfWhite());
        chessBoard.put(Position.of(FILE_B, RANK_2), Pawn.createOfWhite());
        chessBoard.put(Position.of(FILE_C, RANK_2), Pawn.createOfWhite());
        chessBoard.put(Position.of(FILE_D, RANK_2), Pawn.createOfWhite());
        chessBoard.put(Position.of(FILE_E, RANK_2), Pawn.createOfWhite());
        chessBoard.put(Position.of(FILE_F, RANK_2), Pawn.createOfWhite());
        chessBoard.put(Position.of(FILE_G, RANK_2), Pawn.createOfWhite());
        chessBoard.put(Position.of(FILE_H, RANK_2), Pawn.createOfWhite());
    }

    private void generateBlackPiecesExcludePawn(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(FILE_A, RANK_8), Rook.createOfBlack());
        chessBoard.put(Position.of(FILE_B, RANK_8), Knight.createOfBlack());
        chessBoard.put(Position.of(FILE_C, RANK_8), Bishop.createOfBlack());
        chessBoard.put(Position.of(FILE_D, RANK_8), Queen.createOfBlack());
        chessBoard.put(Position.of(FILE_E, RANK_8), King.createOfBlack());
        chessBoard.put(Position.of(FILE_F, RANK_8), Bishop.createOfBlack());
        chessBoard.put(Position.of(FILE_G, RANK_8), Knight.createOfBlack());
        chessBoard.put(Position.of(FILE_H, RANK_8), Rook.createOfBlack());
    }

    private void generateBlackPawns(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(FILE_A, RANK_7), Pawn.createOfBlack());
        chessBoard.put(Position.of(FILE_B, RANK_7), Pawn.createOfBlack());
        chessBoard.put(Position.of(FILE_C, RANK_7), Pawn.createOfBlack());
        chessBoard.put(Position.of(FILE_D, RANK_7), Pawn.createOfBlack());
        chessBoard.put(Position.of(FILE_E, RANK_7), Pawn.createOfBlack());
        chessBoard.put(Position.of(FILE_F, RANK_7), Pawn.createOfBlack());
        chessBoard.put(Position.of(FILE_G, RANK_7), Pawn.createOfBlack());
        chessBoard.put(Position.of(FILE_H, RANK_7), Pawn.createOfBlack());
    }

    private void generateEmptyPieceByEachRank(Map<Position, Piece> chessBoard, Rank rank) {
        Arrays.stream(File.values())
                .forEach(file -> chessBoard.put(Position.of(file.getText(), rank.getText()), new EmptyPiece()));
    }
}
