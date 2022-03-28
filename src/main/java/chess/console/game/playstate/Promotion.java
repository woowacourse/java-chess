package chess.console.game.playstate;

import chess.console.view.OutputView;
import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;

public class Promotion implements PlayState {

    private final ChessBoard chessBoard;
    private final Color color;

    public Promotion(ChessBoard chessBoard, Color color) {
        this.chessBoard = chessBoard;
        this.color = color;
    }

    @Override
    public Running run(final String command) {
        Piece promotionPiece = PromotionPiece.createPromotionPiece(command, color);
        chessBoard.promotion(promotionPiece, color);
        OutputView.printChessBoard(chessBoard.getPieces());
        return new Running(chessBoard, color.reverse());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
