package chess.service;

import chess.domain.ChessGame;
import chess.domain.DTO.BoardDTO;
import chess.domain.DTO.MoveInfoDTO;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.repository.ChessRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private final ChessRepository chessRepository;

    public ChessService() {
        chessRepository = new ChessRepository();
    }

    public BoardDTO initiateBoard(ChessGame chessGame) throws SQLException {
        chessRepository.resetTurnOwner(chessGame.getTurnOwner());
        chessGame.settingBoard();
        chessRepository.resetBoard(chessGame.getBoard());
        return BoardDTO.of(chessGame.getBoard());
    }

    public BoardDTO getSavedBoardInfo(ChessGame chessGame) throws SQLException {
        ResultSet savedBoardInfo = chessRepository.getSavedBoardInfo();
        Map<String, String> boardInfo = new HashMap<>();
        while (savedBoardInfo.next()) {
            boardInfo.put(savedBoardInfo.getString("position"),
                    savedBoardInfo.getString("piece"));
        }

        ResultSet savedTurnOwner = chessRepository.getSavedTurnOwner();
        String turnOwner = "";
        while (savedTurnOwner.next()) {
            turnOwner = savedTurnOwner.getString("turn_owner");
        }
        chessGame.loadSavedBoardInfo(boardInfo, turnOwner);
        return BoardDTO.of(boardInfo);
    }

    public BoardDTO move(ChessGame chessGame, MoveInfoDTO moveInfoDTO) throws SQLException {
        Board board = chessGame.getBoard();
        Position target = Position.convertStringToPosition(moveInfoDTO.getTarget());

        Piece targetPiece = board.getBoard().get(target);

        chessGame.move(moveInfoDTO.getTarget(), moveInfoDTO.getDestination());

        chessRepository.renewBoardAfterMove(moveInfoDTO.getTarget(), moveInfoDTO.getDestination(), targetPiece);
        chessRepository.renewTurnOwnerAfterMove(chessGame.getTurnOwner());
        return BoardDTO.of(board);
    }
}
