package controller;

import static view.Command.START;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.info.Color;
import dto.PieceInfo;
import dto.PositionInfo;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;
import view.PieceShape;

public class ChessController {
    public void start() {
        OutputView.printGameStartMessage();
        while (START.equals(InputView.inputCommand())) {
            Pieces pieces = Pieces.init();
            OutputView.printChessBoard(createPieceInfo(pieces));
        }
    }

    private List<PieceInfo> createPieceInfo(final Pieces pieces) {
        List<PieceInfo> pieceInfos = new ArrayList<>();
        for (Piece piece : pieces.pieces()) {
            String shape = piece.shape().name();
            PieceShape pieceShape = PieceShape.of(shape);
            PositionInfo position = new PositionInfo(piece.position().rankIndex(), piece.position().fileIndex());
            boolean isWhite = isPieceWhite(piece);

            pieceInfos.add(new PieceInfo(pieceShape, isWhite, position));
        }
        return pieceInfos;
    }

    private static boolean isPieceWhite(final Piece piece) {
        if (piece.color().equals(Color.WHITE)) {
            return true;
        }
        return false;
    }
}
