package chess.service;

import chess.dao.BoardDAO;
import chess.dao.GameDAO;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.dto.ChessDTO;
import chess.dto.GameDTO;
import chess.dto.GameIdDTO;
import chess.dto.TurnDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private static final int NOT_EXIST_USER = 0;
    private static final String INIT_TURN = "blank";

    private final BoardDAO chessBoardDao;
    private final GameDAO chessGameDAO;

    public ChessService(BoardDAO chessBoardDAO, GameDAO chessGameDAO) {
        this.chessBoardDao = chessBoardDAO;
        this.chessGameDAO = chessGameDAO;
    }

    public void saveGame(String whiteUser, String blackUser, String turn) {
        if (findGameIdByUser(whiteUser, blackUser).getId() == NOT_EXIST_USER) {
            chessGameDAO.saveGame(new GameDTO(whiteUser, blackUser), new TurnDTO(turn));
        }
    }

    public GameIdDTO findGameIdByUser(String whiteUser, String blackUser) {
        return chessGameDAO.findGameIdByUser(new GameDTO(whiteUser, blackUser));
    }

    public void initGame(ChessGame chessGame, int id) {
        chessGame.start();
        TurnDTO turn = findTurn(new GameIdDTO(id));

        if (!turn.isInitTurn(INIT_TURN) && !chessGame.isRightTurn(turn.getTurn())) {
            chessGame.loadTurn();
        }
        if (turn.isInitTurn(INIT_TURN)) {
            initBoard(chessGame.toBoardModel(), new GameIdDTO(id));
        }
    }

    private TurnDTO findTurn(GameIdDTO gameIdDTO) {
        return chessGameDAO.findTurn(gameIdDTO);
    }

    private void initBoard(Map<String, Piece> board, GameIdDTO gameIdDTO) {
        List<ChessDTO> chessDTOS = new ArrayList<>();
        for (String position : board.keySet()) {
            chessDTOS.add(new ChessDTO(board.get(position).getColor(),
                    board.get(position).getPiece(), position));
        }
        chessBoardDao.savePieces(chessDTOS, gameIdDTO);
    }

    public Map<String, Piece> findPieces(GameIdDTO gameIdDTO) {
        List<ChessDTO> findChessDTOS = chessBoardDao.findAllPiece(gameIdDTO);
        Map<String, Piece> pieces = new HashMap<>();
        for (ChessDTO chessDto : findChessDTOS) {
            pieces.put(chessDto.getPosition(), Pieces.findPiece(chessDto.getColor(), chessDto.getPiece()));
        }
        return pieces;
    }

    public void deleteGame(GameIdDTO gameIdDTO) {
        chessGameDAO.deleteGame(gameIdDTO);
    }

    public void savePieces(List<ChessDTO> chessDTO, GameIdDTO id) {
        chessBoardDao.savePieces(chessDTO, id);
    }

    public void deletePiece(String position, GameIdDTO gameIdDTO) {
        chessBoardDao.deletePiece(position, gameIdDTO);
    }

    public void updateTurn(GameIdDTO gameIdDTO, String turn) {
        chessGameDAO.updateTurn(gameIdDTO, turn);
    }
}
