package chess.web.service;

import java.util.ArrayList;
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
import chess.web.utils.Converter;

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
        initDB();
        Map<Position, Piece> pieces = chessGame.start();
        pieceDao.saveAll(extractPieceDtos(pieces));
        playerDao.save(chessGame.getPlayer());
    }

    private void initDB() {
        pieceDao.removeAll();
        playerDao.removeAll();
    }

    private List<PieceDto> extractPieceDtos(Map<Position, Piece> board) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        for (Position position : board.keySet()) {
            String positionStr = position.getFile().name() + position.getRank().getValue();
            pieceDtos.add(PieceDto.of(positionStr, board.get(position)));
        }
        return pieceDtos;
    }

    public Map<Color, Double> getStatus() {
        return chessGame.getState().status();
    }

    public void move(String command) {
        Map<Position, Piece> pieces = moveByCommand(command);
        pieceDao.update(extractPieceDtos(pieces));
        playerDao.update(chessGame.getPlayer());
    }

    private Map<Position, Piece> moveByCommand(String command) {
        String[] positions = command.split(",");
        Position from = getPositionFrom(positions[0]);
        Position to = getPositionFrom(positions[1]);
        return chessGame.move(from, to);
    }

    private static Position getPositionFrom(String position) {
        return Converter.positionFrom(position);
    }
}
