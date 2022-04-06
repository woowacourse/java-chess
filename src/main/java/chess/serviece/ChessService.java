package chess.serviece;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.command.MoveCommand;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.dto.ChessResponseDto;
import chess.dto.GameDto;
import chess.dto.PieceDto;

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
        pieceDao.removeAll();
        gameDao.removeAll();

        pieceDao.saveAll(getInitPieceDtos());
        gameDao.save(GameDto.from(PieceColor.WHITE, true));

        return getChess();
    }

    public ChessResponseDto getChess() {
        List<PieceDto> pieceDtos = pieceDao.findAll();
        GameDto gameDto = gameDao.find();

        return new ChessResponseDto(pieceDtos, gameDto.getTurn(), gameDto.getStatus());
    }

    private List<PieceDto> getInitPieceDtos() {
        Map<Position, Piece> initPieces = PieceFactory.createChessPieces();
        return initPieces.entrySet()
                .stream()
                .map(entry -> PieceDto.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public ChessResponseDto movePiece(MoveCommand moveCommand) {
        ChessGame game = getGame();
        game.proceedWith(moveCommand);
        pieceDao.remove(moveCommand.to());
        pieceDao.update(moveCommand.from(), moveCommand.to());
        gameDao.update(GameDto.from(game.getTurnColor(), game.isRunning()));
        return getChess();
    }

    private ChessGame getGame() {
        List<PieceDto> pieceDtos = pieceDao.findAll();
        GameDto gameDto = gameDao.find();
        Map<Position, Piece> pieces = pieceDtos.stream()
                .map(PieceDto::toPieceEntry)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new ChessGame(pieces, PieceColor.find(gameDto.getTurn()));
    }
}
