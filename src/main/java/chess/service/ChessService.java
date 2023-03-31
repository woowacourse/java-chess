package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.GameInfoDto;
import chess.dto.PieceInfoDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessService {
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

    public ChessGame loadChessGame(int gameId) {
        GameInfoDto gameInfo = gameDao.findById(gameId);
        if (gameInfo == null) {
            return ChessGame.create();
        }

        List<PieceInfoDto> pieceInfos = pieceDao.findAllById(gameId);
        Map<Position, Piece> board = pieceInfos.stream()
                .collect(Collectors.toMap(PieceInfoDto::getPosition, PieceInfoDto::getPiece));
        return ChessGame.load(Board.load(board), gameInfo.getTurn(), gameInfo.getStatus());
    }

    public void start(int gameId, ChessGame chessGame) {
        chessGame.start();

        gameDao.create(makeGameInfoDto(gameId, chessGame));

        Map<Position, Piece> board = chessGame.getBoard().getPositionAndPiece();
        for (Entry<Position, Piece> positionAndPiece : board.entrySet()) {
            pieceDao.create(gameId, makePieceInfoDto(positionAndPiece.getKey(), positionAndPiece.getValue()));
        }
    }

    public void move(int gameId, ChessGame chessGame, List<String> arguments) {
        Position source = Position.from(arguments.get(0));
        Position destination = Position.from(arguments.get(1));
        Piece movablePiece = chessGame.getBoard().getPieceAtPosition(source);

        chessGame.move(arguments);
        gameDao.updateById(makeGameInfoDto(gameId, chessGame));
        pieceDao.updateById(gameId, makePieceInfoDto(source, Empty.create(Color.NONE)));
        pieceDao.updateById(gameId, makePieceInfoDto(destination, movablePiece));
    }

    public void end(int gameId, ChessGame chessGame) {
        chessGame.end();

        gameDao.updateById(makeGameInfoDto(gameId, chessGame));
    }

    private GameInfoDto makeGameInfoDto(int gameId, ChessGame chessGame) {
        return GameInfoDto.create(gameId, chessGame.getStatus(), chessGame.getTurn());
    }

    private PieceInfoDto makePieceInfoDto(Position position, Piece piece) {
        return PieceInfoDto.create(position, piece);
    }
}
