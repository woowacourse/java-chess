package chess.service;

import chess.domain.board.ChessBoardFactory;
import chess.domain.dao.ChessDao;
import chess.domain.dao.MovementDao;
import chess.domain.entity.Chess;
import chess.domain.entity.Movement;
import chess.domain.player.ChessGame;
import chess.domain.position.Position;
import chess.service.dto.*;

import java.util.List;

public class ChessService {
    private final ChessDao chessDao;
    private final MovementDao movementDao;

    public ChessService(final ChessDao chessDao, final MovementDao movementDao) {
        this.chessDao = chessDao;
        this.movementDao = movementDao;
    }

    public TilesDto emptyBoard() {
        return new TilesDto(ChessBoardFactory.initializeBoard());
    }

    public MoveResponseDto movePiece(final MoveRequestDto requestDto) {
        ChessGame chessGame = ChessGame.newGame();
        List<Movement> movements = movementDao.findByChessName(requestDto.getChessName());

        for (Movement movement : movements) {
            chessGame.moveByTurn(Position.find(movement.getSourcePosition()), Position.find(movement.getTargetPosition()));
        }

        chessGame.moveByTurn(Position.find(requestDto.getSource()), Position.find(requestDto.getTarget()));
        Chess chess = findChessByName(requestDto.getChessName());
        movementDao.save(new Movement(chess.getId(), requestDto.getSource(), requestDto.getTarget()));

        if(chessGame.isGameOver()){
            chess.changeRunning(!chessGame.isGameOver());
            chess.changeWinnerColor(chessGame.findWinnerColor());
            chessDao.update(chess);
        }
        return new MoveResponseDto(requestDto.getSource(), requestDto.getTarget(), chessGame.calculateScoreWeb(), !chess.isRunning());
    }

    public void changeGameStatus(final GameStatusRequestDto requestDto) {
        ChessGame chessGame = ChessGame.newGame();
        Chess chess = findChessByName(requestDto.getChessName());
        List<Movement> movements = movementDao.findByChessName(chess.getName());

        for (Movement movement : movements) {
            chessGame.moveByTurn(Position.find(movement.getSourcePosition()), Position.find(movement.getTargetPosition()));
        }

        chess.changeRunning(!requestDto.isGameOver());
        chess.changeWinnerColor(chessGame.findWinnerColor());
        chessDao.update(chess);
    }

    public GameStatusDto startChess(final ChessSaveRequestDto request) {
        ChessGame chessGame = ChessGame.newGame();
        Chess chess = new Chess(request.getName());
        chessDao.save(chess);
        return new GameStatusDto(chessGame.getAllPieces(), chessGame.calculateScoreWeb(), chessGame.isGameOver());
    }

    public GameStatusDto loadChess(final String chessName) {
        ChessGame chessGame = ChessGame.newGame();
        Chess chess = findChessByName(chessName);
        List<Movement> movements = movementDao.findByChessName(chess.getName());

        for (Movement movement : movements) {
            chessGame.moveByTurn(Position.find(movement.getSourcePosition()), Position.find(movement.getTargetPosition()));
        }
        return new GameStatusDto(chessGame.getAllPieces(), chessGame.calculateScoreWeb(), !chess.isRunning());
    }

    private Chess findChessByName(final String chessName) {
        return chessDao.findByName(chessName).orElseThrow(() -> new IllegalArgumentException("없는 게임 이름입니다"));
    }
}
