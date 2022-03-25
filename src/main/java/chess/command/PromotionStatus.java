package chess.command;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Promotion;
import chess.domain.piece.Piece;
import chess.view.OutputView;

public class PromotionStatus implements Command {

    private final ChessBoard chessBoard;
    private final Color color;

    public PromotionStatus(ChessBoard chessbBoard, Color color) {
        this.chessBoard = chessbBoard;
        this.color = color;
    }

    @Override
    public Running run(final String command) {
        Piece promotionPiece = Promotion.createPromotionPiece(command, color);
        chessBoard.promotion(promotionPiece, color);
        OutputView.printChessBoard(chessBoard.getPieces());
        return new Running(chessBoard, color.reverse());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
