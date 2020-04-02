package chess.View;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RenderOneChessRank {

    private static final String EMPTY_CHESS_PIECE = ".";

    private List<String> oneChessRank;

    private RenderOneChessRank(List<String> oneChessRank) {
        this.oneChessRank = oneChessRank;
    }

    public static RenderOneChessRank generateOneChessRank(ChessBoard chessBoard, ChessRank rank) {
        return new RenderOneChessRank(generateChessView(chessBoard, rank));
    }

    private static List<String> generateChessView(ChessBoard chessBoard, ChessRank rank) {
        List<String> oneChessRank = Arrays.stream(ChessFile.values())
                .map(file -> chessBoardContainPieceAt(chessBoard, file, rank))
                .collect(Collectors.toList());

        oneChessRank.add(String.format("  %s", rank.getChessRank()));

        return oneChessRank;
    }

    private static String chessBoardContainPieceAt(ChessBoard chessBoard, ChessFile file, ChessRank rank) {
        if (chessBoard.contains(file, rank)) {
            ChessPiece chessPiece = chessBoard.getChessPiece(file, rank);
            return String.valueOf(chessPiece.getName());
        }
        return EMPTY_CHESS_PIECE;
    }

    public static RenderOneChessRank generateChessRankGuide() {
        return new RenderOneChessRank(Arrays.stream(ChessFile.values())
                .map(chessFile2 -> String.valueOf(chessFile2.getChessFile()))
                .collect(Collectors.toList())
        );
    }

    public List<String> getOneChessRank() {
        return this.oneChessRank;
    }
}
