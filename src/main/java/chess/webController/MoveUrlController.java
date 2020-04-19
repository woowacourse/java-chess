package chess.webController;

import chess.service.MoveChessPieceService;

public class MoveUrlController {
    private MoveChessPieceService moveChessPieceService;

    public MoveUrlController() {
        this.moveChessPieceService = new MoveChessPieceService();
    }

    public String moveChessPiece(String sourcePosition, String targetPosition) {
        return moveChessPieceService.moveChessBoard(sourcePosition, targetPosition);
    }
}
