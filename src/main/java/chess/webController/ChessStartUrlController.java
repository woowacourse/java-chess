package chess.webController;


import chess.service.CreateInitialBoardService;

public class ChessStartUrlController {

    private CreateInitialBoardService createInitialBoardService;

    public ChessStartUrlController() {
        this.createInitialBoardService = new CreateInitialBoardService();
    }

    public String initialChessBoard() {
        return createInitialBoardService.initialChessBoard();
    }
}
