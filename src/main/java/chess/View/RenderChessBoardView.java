package chess.View;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RenderChessBoardView {
    private static final String EMPTY_CHESS_PIECE = ".";
    private List<List<String>> chessBoardView;

    public RenderChessBoardView(ChessBoard chessBoard) {
        chessBoardView = generateChessBoardView(chessBoard);
        Collections.reverse(chessBoardView);
    }

    private static List<List<String>> generateChessBoardView(ChessBoard chessBoard) {
        return ChessRank.values().stream()
                .map(rank -> viewOneChessRank(chessBoard, rank))
                .collect(Collectors.toList());
    }

    private static List<String> viewOneChessRank(ChessBoard chessBoard, ChessRank rank) {
        return ChessFile.values().stream()
                .map(file -> chessBoardContainPieceAt(chessBoard, file, rank))
                .collect(Collectors.toList());
    }

    private static String chessBoardContainPieceAt(ChessBoard chessBoard, ChessFile file, ChessRank rank) {
        if (chessBoard.contains(file.toString() + rank.toString())) {
            return String.valueOf(chessBoard.getChessPiece(file, rank).getName());
        }
        return EMPTY_CHESS_PIECE;
    }

    public List<List<String>> getChessBoardView() {
        return Collections.unmodifiableList(chessBoardView);
    }
}
