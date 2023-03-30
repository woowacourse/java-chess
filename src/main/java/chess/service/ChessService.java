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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        if (gameInfo == null) {
            this.chessGame = new ChessGame();
            return;
        }

        List<PieceInfoDto> pieceInfos = pieceDao.findById(gameId);
        Map<Position, Piece> board = pieceInfos.stream()
                .collect(Collectors.toMap(PieceInfoDto::getPosition, PieceInfoDto::getPiece));
        ChessGame existedChessGame = new ChessGame(Board.load(board), gameInfo.getTurn(), gameInfo.getStatus());
        this.chessGame = existedChessGame;
    }

    public void start(int gameId) {
        chessGame.start();

        Map<Position, Piece> board = chessGame.getBoard().getPositionAndPiece();

        gameDao.save(gameId, makeGameInfoDto());
        pieceDao.save(gameId, makePieceInfoDtos(board));
    }

    public void move(int gameId, List<String> arguments) {
        Map<Position, Piece> update = chessGame.move(arguments);

        gameDao.updateById(gameId, makeGameInfoDto());
        pieceDao.updateById(gameId, makePieceInfoDtos(update));
    }

    public Map<Color, Double> status() {
        return chessGame.status();
    }

    public void end(int gameId) {
        chessGame.end();
        gameDao.updateById(gameId, makeGameInfoDto());
    }

    private GameInfoDto makeGameInfoDto() {
        return GameInfoDto.create(chessGame.getStatus(), chessGame.getTurn());
    }

    private List<PieceInfoDto> makePieceInfoDtos(Map<Position, Piece> board) {
        List<PieceInfoDto> pieces = new ArrayList<>();
        for (Entry<Position, Piece> positionAndPiece : board.entrySet()) {
            pieces.add(makePieceInfoDto(positionAndPiece.getKey(), positionAndPiece.getValue()));
        }
        return pieces;
    }

    private PieceInfoDto makePieceInfoDto(Position position, Piece piece) {
        return PieceInfoDto.create(position, piece);
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
