package domain;

import domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator {

    public Map<Position, Piece> generate() {
        Map<Position, Piece> chessBoard = new HashMap<>();
        Arrays.stream(File.values())
                .forEach(file -> generateEmptyPieceByEachRank(chessBoard, file));

        generateWhitePiecesExcludePawn(chessBoard);
        generateWhitePawns(chessBoard);

        generateBlackPiecesExcludePawn(chessBoard);
        generateBlackPawns(chessBoard);

        return chessBoard;
    }

    private void generateWhitePiecesExcludePawn(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of("c", "1"), Bishop.createOfWhite());
        chessBoard.put(Position.of("d", "1"), Queen.createOfWhite());
        chessBoard.put(Position.of("a", "1"), Rook.createOfWhite());
        chessBoard.put(Position.of("b", "1"), Knight.createOfWhite());
        chessBoard.put(Position.of("e", "1"), King.createOfWhite());
        chessBoard.put(Position.of("f", "1"), Bishop.createOfWhite());
        chessBoard.put(Position.of("g", "1"), Knight.createOfWhite());
        chessBoard.put(Position.of("h", "1"), Rook.createOfWhite());
    }

    private void generateWhitePawns(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of("a", "2"), Pawn.createOfWhite());
        chessBoard.put(Position.of("b", "2"), Pawn.createOfWhite());
        chessBoard.put(Position.of("c", "2"), Pawn.createOfWhite());
        chessBoard.put(Position.of("d", "2"), Pawn.createOfWhite());
        chessBoard.put(Position.of("e", "2"), Pawn.createOfWhite());
        chessBoard.put(Position.of("f", "2"), Pawn.createOfWhite());
        chessBoard.put(Position.of("g", "2"), Pawn.createOfWhite());
        chessBoard.put(Position.of("h", "2"), Pawn.createOfWhite());
    }

    private void generateBlackPiecesExcludePawn(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of("a", "8"), Rook.createOfBlack());
        chessBoard.put(Position.of("b", "8"), Knight.createOfBlack());
        chessBoard.put(Position.of("c", "8"), Bishop.createOfBlack());
        chessBoard.put(Position.of("d", "8"), Queen.createOfBlack());
        chessBoard.put(Position.of("e", "8"), King.createOfBlack());
        chessBoard.put(Position.of("f", "8"), Bishop.createOfBlack());
        chessBoard.put(Position.of("g", "8"), Knight.createOfBlack());
        chessBoard.put(Position.of("h", "8"), Rook.createOfBlack());
    }

    private void generateBlackPawns(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of("a", "7"), Pawn.createOfWhite());
        chessBoard.put(Position.of("b", "7"), Pawn.createOfWhite());
        chessBoard.put(Position.of("c", "7"), Pawn.createOfWhite());
        chessBoard.put(Position.of("d", "7"), Pawn.createOfWhite());
        chessBoard.put(Position.of("e", "7"), Pawn.createOfWhite());
        chessBoard.put(Position.of("f", "7"), Pawn.createOfWhite());
        chessBoard.put(Position.of("g", "7"), Pawn.createOfWhite());
        chessBoard.put(Position.of("h", "7"), Pawn.createOfWhite());
    }

    private void generateEmptyPieceByEachRank(Map<Position, Piece> chessBoard, File file) {
        Arrays.stream(Rank.values())
                .forEach(rank -> chessBoard.put(Position.of(file.getText(), rank.getText()), new EmptyPiece()));
    }
}
