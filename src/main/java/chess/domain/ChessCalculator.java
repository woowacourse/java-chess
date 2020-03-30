package chess.domain;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.position.ChessFile;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class ChessCalculator {

    private static final int REDUCING_PAWN_SCORE_LOWER_BOUND = 1;
    private static final int REDUCING_RATE = 2;

    public static double calculateScoreOf(ChessBoard chessBoard) {
        Objects.requireNonNull(chessBoard, "체스 보드가 null입니다.");

        double totalScore = calculateUnConsiderateTotalScore(chessBoard);
        double pawnScore = calculatePawnScore(chessBoard);
        long pawnCount = calculatePawnCountOnChessFile(chessBoard);

        if (pawnCount > REDUCING_PAWN_SCORE_LOWER_BOUND) {
            return totalScore - (pawnScore / REDUCING_RATE);
        }
        return totalScore;
    }

    private static double calculateUnConsiderateTotalScore(ChessBoard chessBoard) {
        return ChessFile.values().stream()
                .flatMap(chessFile -> getChessPiecesOn(chessFile, chessBoard))
                .mapToDouble(ChessPiece::getScore)
                .sum();
    }

    private static double calculatePawnScore(ChessBoard chessBoard) {
        return ChessFile.values().stream()
                .flatMap(chessFile -> getChessPiecesOn(chessFile, chessBoard))
                .filter(chessPiece -> chessPiece instanceof Pawn)
                .mapToDouble(ChessPiece::getScore)
                .sum();
    }

    private static long calculatePawnCountOnChessFile(ChessBoard chessBoard) {
        return ChessFile.values().stream()
                .flatMap(chessFile -> getChessPiecesOn(chessFile, chessBoard))
                .filter(chessPiece -> chessPiece instanceof Pawn)
                .count();
    }

    private static Stream<ChessPiece> getChessPiecesOn(ChessFile chessFile, ChessBoard chessBoard) {
        return chessBoard.getChessBoard().entrySet().stream()
                .filter(entry -> entry.getKey().isSameFilePosition(chessFile))
                .filter(entry -> entry.getValue().isSamePieceColorWith(chessBoard.getPlayerColor()))
                .map(Map.Entry::getValue);
    }
}
