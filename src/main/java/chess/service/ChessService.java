package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.ChessGameFactory;
import chess.domain.command.Command;
import chess.domain.command.MoveOnCommand;
import chess.domain.command.StartOnCommand;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.ChessGameStatusDto;

import java.util.List;

public class ChessService {
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessService() {
        this.chessGameDao = new ChessGameDao();
        this.pieceDao = new PieceDao();
    }

    public void generateChessGame() {
        ChessGame chessGame = generateNewChessGame();
        int chessGameId = chessGameDao.save(chessGame);
        pieceDao.saveAll(chessGame.getPiecesByAllPosition(), chessGameId);
    }

    private ChessGame generateNewChessGame() {
        ChessGame chessGame = new ChessGame();
        Command startOnCommand = new StartOnCommand();
        String[] temp = new String[0];
        startOnCommand.execute(chessGame, temp);
        return chessGame;
    }

    public List<Integer> getAllChessGameId() {
        return chessGameDao.selectAllId();
    }

    public ChessGame moveFromSourceToTarget(int chessGameId, String source, String target) {
        ChessGame chessGame = findChessGameById(chessGameId);
        Command moveOnCommand = new MoveOnCommand();
        String[] sourceTarget = new String[]{"move", source, target};
        moveOnCommand.execute(chessGame, sourceTarget);
        updateChessGame(chessGameId, chessGame, source, target);
        return chessGame;
    }

    public ChessGame findChessGameById(int chessGameId) {
        ChessGameStatusDto chessGameStatusDto = chessGameDao.findChessGameStateById(chessGameId);
        List<Piece> pieces = pieceDao.findAllByChessGameId(chessGameId);
        return ChessGameFactory.loadChessGameByInfo(pieces, chessGameStatusDto.getTurn(), chessGameStatusDto.isFinish());
    }

    public void updateChessGame(int chessGameId, ChessGame chessGame, String source, String target) {
        ChessGameStatusDto chessGameStatusDto = new ChessGameStatusDto(chessGame);
        chessGameDao.updateChessGameStateById(chessGameId, chessGameStatusDto);
        Piece sourcePiece = findPieceByPosition(source, chessGame);
        Piece targetPiece = findPieceByPosition(target, chessGame);
        pieceDao.update(chessGameId, sourcePiece);
        pieceDao.update(chessGameId, targetPiece);
    }

    private Piece findPieceByPosition(String position, ChessGame chessGame) {
        return chessGame.getPiecesByAllPosition()
                .stream()
                .filter(piece -> piece.isSamePosition(Position.of(position)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 찾는 위치의 기물이 없습니다."));
    }

    public void resetChessGame(int chessGameId) {
        ChessGame chessGame = generateNewChessGame();
        ChessGameStatusDto chessGameStatusDto = new ChessGameStatusDto(chessGame);
        chessGameDao.delete(chessGameId);
        chessGameDao.saveWithId(chessGameId, chessGameStatusDto);
        pieceDao.saveAll(chessGame.getPiecesByAllPosition(), chessGameId);
    }
}
