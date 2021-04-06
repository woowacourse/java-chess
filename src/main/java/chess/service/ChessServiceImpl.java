package chess.service;

import chess.dao.ChessDao;
import chess.domain.ChessBoard;
import chess.domain.Result;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import chess.dto.ChessCellDto;
import chess.dto.MovementDto;
import chess.dto.PieceDto;
import chess.dto.PieceMaker;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessServiceImpl implements ChessService {
    private final ChessDao chessDao;

    private ChessBoard chessBoard;

    public ChessServiceImpl(ChessDao chessDao) {
        this.chessDao = chessDao;
        chessBoard = new ChessBoard();
    }

    @Override
    public Map<String, PieceDto> move(MovementDto movementDto) {
        chessBoard.move(movementDto);
        return chessBoard.getChessBoardDto();
    }

    @Override
    public Result saveAndShowResult() {
        chessDao.removePositions();
        Map<String, PieceDto> chessBoardDto = chessBoard.getChessBoardDto();
        List<ChessCellDto> results = new ArrayList<>();
        for (Map.Entry<String, PieceDto> eachInfo : chessBoardDto.entrySet()) {
            PieceDto value = eachInfo.getValue();
            results.add(new ChessCellDto(eachInfo.getKey(), value.getTeamColor(), value.getPieceType(), value.getAlive()));
        }
        chessDao.addPositions(results);
        return chessBoard.result();
    }

    @Override
    public Map<String, PieceDto> getDefaultChessBoard() {
        return chessBoard.getChessBoardDto();
    }

    @Override
    public Result getResult() {
        return chessBoard.result();
    }

    @Override
    public Result terminateGameAndGetResult() {
        chessBoard.terminate();
        chessDao.removePositions();
        return chessBoard.result();
    }

    @Override
    public Map<String, PieceDto> getSavedChessBoard() {
        List<ChessCellDto> previousGame = chessDao.findByGameId("1");

        Map<Position, Piece> boardFromDB = new LinkedHashMap<>();
        for (ChessCellDto eachInfo : previousGame) {
            boardFromDB.put(Position.valueOf(eachInfo.getPosition()),
                    PieceMaker.getInstance(eachInfo.getPieceType(),
                            TeamColor.getInstance(eachInfo.getTeamColor()),
                            State.getInstance(eachInfo.getAlive())
                    ));
        }
        return getSavedOfDefaultChessBoard(boardFromDB);
    }

    private Map<String, PieceDto> getSavedOfDefaultChessBoard(Map<Position, Piece> boardFromDB) {
        if (boardFromDB.isEmpty()) {
            System.out.println("hihi!");
            chessBoard = new ChessBoard();
            return chessBoard.getChessBoardDto();
        }
        chessBoard = new ChessBoard(boardFromDB);
        return chessBoard.getChessBoardDto();
    }

}
