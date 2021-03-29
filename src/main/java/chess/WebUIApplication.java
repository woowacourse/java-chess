//package chess;
//
//import chess.beforedb.controller.web.WebUIController;
//import chess.beforedb.domain.board.setting.BoardDefaultSetting;
//import chess.beforedb.domain.board.setting.BoardSetting;
//import chess.beforedb.service.ChessService;
//
//public class WebUIApplication {
//    public static void main(String[] args) {
//        BoardSetting boardSetting = new BoardDefaultSetting();
//
//        ChessService chessService = new ChessService(boardSetting);
//        WebUIController webUIController = new WebUIController(chessService);
//
//        webUIController.run();
//    }
//}
