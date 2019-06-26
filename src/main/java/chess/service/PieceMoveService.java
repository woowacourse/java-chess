package chess.service;

import chess.domain.board.Board;
import chess.domain.board.ChessGame;
import chess.domain.direction.Route;
import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.persistence.dao.ChessBoardDAO;
import chess.persistence.dao.ChessGameDAO;
import chess.persistence.dto.ChessBoardDTO;
import chess.persistence.dto.ChessGameDTO;
import chess.persistence.dto.ChessMoveDTO;

public class PieceMoveService {
    public static PieceMoveService getInstance() {
        return PieceMoveServiceHolder.INSTANCE;
    }

    public ChessBoardDTO request(ChessMoveDTO chessMoveDTO) {
        ChessGameDTO chessGameDTO = GameGeneratorService.getInstance().request();
        ChessBoardDTO chessBoardDTO = BoardGeneratorService.getInstance().request(chessGameDTO);
        Board board = Board.drawBoard(chessBoardDTO.getBoard(), Team.valueOf(chessGameDTO.getLastUser()));

        Square source = parseSquare(chessMoveDTO.getSourceX(), chessMoveDTO.getSourceY());
        Square target = parseSquare(chessMoveDTO.getTargetX(), chessMoveDTO.getTargetY());
        Piece piece = board.getPiece(source);
        Route route = piece.getRoute(source, target);
        Board newBoard = board.changeBoard(route);

        chessGameDTO.setLastUser(newBoard.getTeam().getTeam());
        chessGameDTO.setGameStatus(new ChessGame(newBoard).isGameOver());

        chessBoardDTO.setRoundNo(chessBoardDTO.getRoundNo() + 1);
        chessBoardDTO.setBoard(newBoard.getBoard());

        ChessBoardDAO.getInstance().addBoardStatus(chessBoardDTO);
        ChessGameDAO.getInstance().updateGameStatus(chessGameDTO);
        return chessBoardDTO;
    }

    private Square parseSquare(String squareX, String squareY) {
        return Square.of(Integer.parseInt(squareX), Integer.parseInt(squareY));
    }

    private static class PieceMoveServiceHolder {
        static final PieceMoveService INSTANCE = new PieceMoveService();
    }

}
