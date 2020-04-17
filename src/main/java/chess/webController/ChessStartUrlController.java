package chess.webController;


import chess.service.CreateInitialBoardService;

public class ChessStartUrlController {

    private static final String SUCCESS = "";

    private CreateInitialBoardService createInitialBoardService;

    public ChessStartUrlController() {
        this.createInitialBoardService = new CreateInitialBoardService();
    }

    public String initialChessBoard() {
        createInitialBoardService.initialChessBoard();
        return SUCCESS;
    }
}
