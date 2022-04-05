package chess.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.web.dao.PieceDao;
import chess.web.dao.PlayerDao;
import chess.web.dto.PieceDto;

public class ChessService {
    private ChessGame chessGame = new ChessGame();
    private final PieceDao pieceDao = new PieceDao();
    private final PlayerDao playerDao = new PlayerDao();

    public List<PieceDto> initializeData() {
        List<PieceDto> pieces = pieceDao.findAll();
        chessGame = ChessGame.of(ChessBoard.of(pieces), playerDao.find());
        return pieces;
    }

    public void start() {
        Map<Position, Piece> pieces = chessGame.start();
        Map<String, PieceDto> pieceDtos = convertNewBoard(pieces);
        pieceDao.saveAll(new ArrayList<>(pieceDtos.values()));
        playerDao.save(chessGame.getPlayer());
    }

    private Map<String, PieceDto> convertNewBoard(Map<Position, Piece> board) {
        Map<String, PieceDto> newBoard = new HashMap<>();
        for (Position position : board.keySet()) {
            String positionStr = position.getFile().name() + position.getRank().getValue();
            newBoard.put(positionStr, PieceDto.of(positionStr, board.get(position)));
        }
        return newBoard;
    }

    public Map<Color, Double> getStatus() {
        return chessGame.getState().status();
    }
}
