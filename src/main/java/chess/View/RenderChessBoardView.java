package chess.View;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.ChessRank;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RenderChessBoardView {
    private List<RenderOneChessRank> chessBoardView;

    public RenderChessBoardView(ChessBoard chessBoard) {
        chessBoardView = generateChessBoardView(chessBoard);
        Collections.reverse(chessBoardView);
    }

    private List<RenderOneChessRank> generateChessBoardView(ChessBoard chessBoard) {
        this.chessBoardView.add(RenderOneChessRank.generateChessRankGuide());

        chessBoardView.addAll(ChessRank.values().stream()
                .map(rank -> RenderOneChessRank.generateOneChessRank(chessBoard, rank))
                .collect(Collectors.toList()));

        return chessBoardView;
    }


    public List<RenderOneChessRank> getChessBoardView() {
        return Collections.unmodifiableList(chessBoardView);
    }
}
