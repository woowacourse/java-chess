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
    private static final int PLUS_ROUND_NUMBER = 1;

    private PieceMoveService() {

    }

    public static PieceMoveService getInstance() {
        return PieceMoveServiceHolder.INSTANCE;
    }

    public ChessBoardDTO request(ChessMoveDTO chessMoveDTO, ChessGameDTO chessGameDTO, ChessBoardDTO chessBoardDTO) {
        Board newBoard = changeBoard(chessMoveDTO, chessGameDTO, chessBoardDTO);

        chessGameDTO.setLastUser(newBoard.getTeam().getTeam());
        chessGameDTO.setGameStatus(new ChessGame(newBoard).isGameOver());

        chessBoardDTO.setRoundNo(chessBoardDTO.getRoundNo() + PLUS_ROUND_NUMBER);
        chessBoardDTO.setBoard(newBoard.getBoard());

        ChessBoardDAO.getInstance().addBoardStatus(chessBoardDTO);
        ChessGameDAO.getInstance().updateGameStatus(chessGameDTO);
        return chessBoardDTO;
    }

    private Board changeBoard(ChessMoveDTO chessMoveDTO, ChessGameDTO chessGameDTO, ChessBoardDTO chessBoardDTO) {
        Board board = Board.drawBoard(chessBoardDTO.getBoard(), Team.valueOf(chessGameDTO.getLastUser()));

        Square source = Square.of(chessMoveDTO.getSourceX(), chessMoveDTO.getSourceY());
        Square target = Square.of(chessMoveDTO.getTargetX(), chessMoveDTO.getTargetY());
        Piece piece = board.getPiece(source);
        Route route = piece.getRoute(source, target);

        return board.changeBoard(route);
    }

    private static class PieceMoveServiceHolder {
        static final PieceMoveService INSTANCE = new PieceMoveService();
    }
}
