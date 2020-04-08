public class WebUIChessApplication {
    public static void main(String[] args) {
        ChessService chessService = new ChessService();
        ChessWebController chessWebController = new ChessWebController(chessService);
        chessWebController.run();
    }
}
