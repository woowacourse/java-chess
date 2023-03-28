package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.GameInfoDto;
import chess.dto.PieceInfoDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {
    private ChessGame chessGame;
    private final GameDao gameDao;
    private final PieceDao pieceDao;

    public ChessService(GameDao gameDao, PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public List<Integer> findPossibleGameIds() {
        return gameDao.findAllPossibleId();
    }

    public List<Integer> findImpossibleGameIds() {
        return gameDao.findAllImpossibleId();
    }

    public void loadChessGame(int gameId) {
        GameInfoDto gameInfo = gameDao.findById(gameId);
        List<PieceInfoDto> pieceInfos = pieceDao.findById(gameId);
        Map<Position, Piece> board = pieceInfos.stream()
                .collect(Collectors.toMap(PieceInfoDto::getPosition, PieceInfoDto::getPiece));

        if (gameInfo == null) {
            this.chessGame = new ChessGame();
            gameDao.save(gameId, chessGame);
            pieceDao.save(gameId, chessGame);
            return;
        }

        ChessGame existedChessGame = new ChessGame(new Board(board), gameInfo.getTurn(), gameInfo.getStatus());

        this.chessGame = existedChessGame;
    }

    public void start(int gameId) {
        chessGame.start();
        gameDao.updateById(gameId, chessGame);
        pieceDao.updateById(gameId, chessGame);
    }

    public void move(int gameId, List<String> arguments) {
        chessGame.move(arguments);
        gameDao.updateById(gameId, chessGame);
        pieceDao.updateById(gameId, chessGame);
    }

    public Map<Color, Double> status() {
        return chessGame.status();
    }

    public void end(int gameId) {
        chessGame.end();
        gameDao.updateById(gameId, chessGame);
        pieceDao.updateById(gameId, chessGame);
    }

    public boolean canPlay() {
        return !chessGame.isEnd() && !chessGame.isCatch();
    }

    public boolean isCatch() {
        return chessGame.isCatch();
    }

    public Color getWinner() {
        return chessGame.getTurn().reverse();
    }

    public BoardDto getBoard() {
        return BoardDto.create(chessGame.getBoard());
    }
}
