package controller;

import static view.Command.START;

import domain.Board;
import domain.BoardInitiator;
import domain.piece.Piece;
import domain.piece.info.Color;
import domain.piece.info.Position;
import dto.DtoMapper;
import dto.RankInfo;
import dto.PositionInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import view.InputView;
import view.OutputView;
import view.PieceShape;

public class ChessController {
    public void start() {
        OutputView.printGameStartMessage();
        while (START.equals(InputView.inputCommand())) {
            final Board board = new Board(BoardInitiator.init());
            OutputView.printChessBoard(createPieceInfo(board));
        }
    }

    private List<RankInfo> createPieceInfo(final Board board) {
        final List<RankInfo> rankInfos = new ArrayList<>();
        for (int rank = 7; rank >= 0; rank--) {
            final RankInfo pieceShapeOfRank = DtoMapper.getPieceShapeOfRank(board, rank);
            rankInfos.add(pieceShapeOfRank);
        }
        return rankInfos;
    }
}
