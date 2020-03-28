package chess.domain;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.ChessFile;

import java.util.Map;
import java.util.stream.Stream;

public class ChessCalculator {

    private static final int REDUCING_PAWN_SCORE_LOWER_BOUND = 1;
    private static final int REDUCING_RATE = 2;

    public static double calculateScoreOf(PieceColor pieceColor, ChessBoard chessBoard) {
        double totalScore = calculateUnConsiderateTotalScore(pieceColor, chessBoard);
        double pawnScore = calculatePawnScore(pieceColor, chessBoard);
        long pawnCount = calculatePawnCountOnChessFile(pieceColor, chessBoard);

        if (pawnCount > REDUCING_PAWN_SCORE_LOWER_BOUND) {
            return totalScore - (pawnScore / REDUCING_RATE);
        }
        return totalScore;
    }

    private static double calculateUnConsiderateTotalScore(PieceColor pieceColor, ChessBoard chessBoard) {
        return ChessFile.values().stream()
                .flatMap(chessFile -> getChessPiecesOn(chessFile, pieceColor, chessBoard))
                .mapToDouble(ChessPiece::getScore)
                .sum();
    }

    private static double calculatePawnScore(PieceColor pieceColor, ChessBoard chessBoard) {
        return ChessFile.values().stream()
                .flatMap(chessFile -> getChessPiecesOn(chessFile, pieceColor, chessBoard))
                .filter(chessPiece -> chessPiece instanceof Pawn)
                .mapToDouble(ChessPiece::getScore)
                .sum();
    }

    private static long calculatePawnCountOnChessFile(PieceColor pieceColor, ChessBoard chessBoard) {
        return ChessFile.values().stream()
                .flatMap(chessFile -> getChessPiecesOn(chessFile, pieceColor, chessBoard))
                .filter(chessPiece -> chessPiece instanceof Pawn)
                .count();
    }

    private static Stream<ChessPiece> getChessPiecesOn(ChessFile chessFile, PieceColor pieceColor, ChessBoard chessBoard) {
        return chessBoard.getChessBoard().entrySet().stream()
                .filter(entry -> entry.getKey().isSameFilePosition(chessFile))
                .filter(entry -> entry.getValue().isSamePieceColorWith(pieceColor))
                .map(Map.Entry::getValue);
    }
}
