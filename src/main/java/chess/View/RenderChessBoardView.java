package chess.View;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;

import java.util.ArrayList;
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
        List<List<String>> chessBoardView = new ArrayList<>(Collections.singletonList(ChessFile.values().stream()
                .map(ChessFile::toString)
                .collect(Collectors.toList())));

        chessBoardView.addAll(ChessRank.values().stream()
                .map(rank -> viewOneChessRank(chessBoard, rank))
                .collect(Collectors.toList()));

        return chessBoardView;
    }

    private static List<String> viewOneChessRank(ChessBoard chessBoard, ChessRank rank) {
        List<String> oneChessRankBoard = ChessFile.values().stream()
                .map(file -> chessBoardContainPieceAt(chessBoard, file, rank))
                .collect(Collectors.toList());
        oneChessRankBoard.add(String.format("  %s", rank));

        return oneChessRankBoard;
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
