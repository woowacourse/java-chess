
package chess;

import chess.controller.ConsoleChessController;
import chess.dao.PieceDao;
import chess.dao.PieceDaoFactory;
import chess.domain.board.Board;
import chess.domain.piece.service.PieceService;
import chess.ui.Console;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        Console console = new Console();
        PieceDaoFactory pieceDaoFactory = new PieceDaoFactory();
        PieceDao pieceDao = pieceDaoFactory.createPieceDao();
        PieceService pieceService = new PieceService(pieceDao);
        ConsoleChessController consoleChessController = new ConsoleChessController(console, pieceService);
        try {
            Board board = consoleChessController.start();
            board = consoleChessController.play(board);
            consoleChessController.showResult(board);
        } catch (RuntimeException e) {
            System.out.println(String.format("다음과 같은 이유로 중단합니다 - %s", e.getMessage()));
            System.exit(-1);
        }
    }
}