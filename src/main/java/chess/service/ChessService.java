package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.ChessGameFactory;
import chess.domain.command.Command;
import chess.domain.command.StartOnCommand;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.dto.ChessGameDto;
import chess.dto.ChessGameStatusDto;
import chess.dto.PieceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;
    private final Map<Integer, ChessGame> chessGames = new HashMap<>();

    public ChessService() {
        this.chessGameDao = new ChessGameDao();
        this.pieceDao = new PieceDao();
    }

    public void generateChessGame() throws SQLException {
        ChessGame chessGame = new ChessGame();
        Command startOnCommand = new StartOnCommand();
        String[] temp = new String[0];
        startOnCommand.execute(chessGame, temp);

        int chessGameId = chessGameDao.insertChessGameReturnId(chessGame);
        pieceDao.addPieces(chessGame.getPiecesByAllPosition(), chessGameId);
        chessGames.put(chessGameId, chessGame);
    }

    public List<Integer> getAllChessGameId() throws SQLException {
        return chessGameDao.selectAllChessGameId();
    }

    public ChessGame findChessGameById(int chessGameId) throws SQLException {
        ChessGameStatusDto chessGameStatusDto = chessGameDao.findChessGameStateById(chessGameId);
        List<PieceDto> piecesInfos = pieceDao.findAllPiecesByChessGameId(chessGameId);
        List<Piece> pieces = getPiecesByInfo(piecesInfos);
        return ChessGameFactory.loadChessGameByInfo(pieces, chessGameStatusDto.getTurn(), chessGameStatusDto.isFinish());
    }

    private List<Piece> getPiecesByInfo(List<PieceDto> piecesInfos) {
        List<Piece> pieces = new ArrayList<>();
        for (PieceDto pieceDto : piecesInfos) {
            pieces.add(PieceFactory.findByInfo(pieceDto.getColor(), pieceDto.getName(), pieceDto.getPosition()));
        }
        return pieces;
    }

    public void updateChessGame(int chessGameId, ChessGame chessGame, String source, String target) throws SQLException {
        ChessGameDto chessGameDto = new ChessGameDto(chessGame);
        chessGameDao.updateChessGameStateById(chessGameId, chessGameDto.getTurn().name(), chessGameDto.isFinish());
        PieceDto sourcePieceDto = findPieceDtoByPosition(source, chessGameDto);
        PieceDto targetPieceDto = findPieceDtoByPosition(target, chessGameDto);
        pieceDao.updatePiecePositionByChessGameId(chessGameId, sourcePieceDto);
        pieceDao.updatePiecePositionByChessGameId(chessGameId, targetPieceDto);
    }

    private PieceDto findPieceDtoByPosition(String position, ChessGameDto chessGameDto) {
        return chessGameDto.getPieces()
                .stream()
                .filter(pieceDto -> pieceDto.getPosition()
                        .equals(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 찾는 위치의 기물이 없습니다."));
    }
}
