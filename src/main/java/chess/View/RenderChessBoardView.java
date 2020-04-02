package chess.View;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.ChessRank;

import java.util.*;
import java.util.stream.Collectors;

public class RenderChessBoardView {
    private List<RenderOneChessRank> chessBoardView;

    public RenderChessBoardView(ChessBoard chessBoard) {
        chessBoardView = generateChessBoardView(chessBoard);
        Collections.reverse(chessBoardView);
    }

    private List<RenderOneChessRank> generateChessBoardView(ChessBoard chessBoard) {
        Objects.requireNonNull(chessBoard, "체스 보드가 null 입니다.");
        this.chessBoardView = new ArrayList<>(Collections.singletonList(RenderOneChessRank.generateChessRankGuide()));

        chessBoardView.addAll(Arrays.stream(ChessRank.values())
                .map(rank -> RenderOneChessRank.generateOneChessRank(chessBoard, rank))
                .collect(Collectors.toList()));

        return chessBoardView;
    }


    public List<RenderOneChessRank> getChessBoardView() {
        return Collections.unmodifiableList(chessBoardView);
    }
}
