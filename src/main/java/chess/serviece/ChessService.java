package chess.serviece;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.Score;
import chess.domain.command.MoveCommand;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.dto.*;
import chess.util.SqlQueryException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private final PieceDao pieceDao;
    private final GameDao gameDao;

    public ChessService(PieceDao pieceDao, GameDao gameDao) {
        this.pieceDao = pieceDao;
        this.gameDao = gameDao;
    }

    public ChessResponseDto initializeGame() {
        try {
            pieceDao.removeAll();
            gameDao.removeAll();

            pieceDao.saveAll(getInitPieceDtos());
            gameDao.save(GameDto.from(PieceColor.WHITE, true));
        } catch (SqlQueryException e) {
            throw new IllegalArgumentException("게임을 초기화할 수 없습니다.");
        }
        return getChess();
    }

    public ChessResponseDto getChess() {
        try {
            List<PieceDto> pieceDtos = pieceDao.findAll();
            GameDto gameDto = gameDao.find();
            return new ChessResponseDto(pieceDtos, gameDto.getTurn(), gameDto.getStatus());
        } catch (SqlQueryException e) {
            throw new IllegalArgumentException("체스 정보를 읽어올 수 없습니다.");
        }
    }

    private List<PieceDto> getInitPieceDtos() {
        Map<Position, Piece> initPieces = PieceFactory.createChessPieces();
        return initPieces.entrySet()
                .stream()
                .map(entry -> PieceDto.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public ChessResponseDto movePiece(MoveCommand moveCommand) {
        try {
            ChessGame game = getGame();
            game.proceedWith(moveCommand);
            pieceDao.remove(moveCommand.to());
            pieceDao.update(moveCommand.from(), moveCommand.to());
            gameDao.update(GameDto.from(game.getTurnColor(), game.isRunning()));
            return getChess();
        } catch (SqlQueryException e) {
            throw new IllegalArgumentException("기물을 움직일 수 없습니다.");
        }
    }

    private ChessGame getGame() {
        try {
            List<PieceDto> pieceDtos = pieceDao.findAll();
            GameDto gameDto = gameDao.find();
            Map<Position, Piece> pieces = pieceDtos.stream()
                    .map(PieceDto::toPieceEntry)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return new ChessGame(pieces, PieceColor.find(gameDto.getTurn()));
        } catch (SqlQueryException e) {
            throw new IllegalArgumentException("게임 정보를 불러올 수 없습니다.");
        }
    }

    public ScoresDto getScore() {
        ChessGame game = getGame();
        Map<PieceColor, Score> scoresByColor = game.calculateScoreByColor();
        return ScoresDto.of(scoresByColor);
    }

    public ScoresDto finishGame() {
        try {
            gameDao.updateStatus(GameStatusDto.FINISHED);
            return getScore();
        } catch (SqlQueryException e) {
            throw new IllegalArgumentException("게임을 종료시킬 수 없습니다.");
        }
    }
}
