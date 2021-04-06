package chess.service;

import chess.domain.ChessGame;
import chess.domain.DTO.BoardDTO;
import chess.domain.DTO.MoveInfoDTO;
import chess.domain.DTO.TurnDTO;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.repository.ChessDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private final ChessDAO chessDAO;

    public ChessService() {
        chessDAO = new ChessDAO();
    }

    public BoardDTO initiateBoard(ChessGame chessGame) throws SQLException {
        chessDAO.resetTurnOwner(chessGame.getTurnOwner());
        chessGame.settingBoard();
        chessDAO.resetBoard(chessGame.getBoard());
        return BoardDTO.of(chessGame.getBoard());
    }

    public BoardDTO getSavedBoardInfo(ChessGame chessGame) throws SQLException {
        BoardDTO boardDTO = chessDAO.getSavedBoardInfo();
        TurnDTO turnDTO = chessDAO.getSavedTurnOwner();

        chessGame.loadSavedBoardInfo(boardDTO.getBoardInfo(), turnDTO.getTurn());
        return boardDTO;
    }

    public BoardDTO move(ChessGame chessGame, MoveInfoDTO moveInfoDTO) throws SQLException {
        Board board = chessGame.getBoard();
        Position target = Position.convertStringToPosition(moveInfoDTO.getTarget());

        Piece targetPiece = board.getBoard().get(target);

        chessGame.move(moveInfoDTO.getTarget(), moveInfoDTO.getDestination());

        chessDAO.renewBoardAfterMove(moveInfoDTO.getTarget(), moveInfoDTO.getDestination(), targetPiece);
        chessDAO.renewTurnOwnerAfterMove(chessGame.getTurnOwner());
        return BoardDTO.of(board);
    }
}
