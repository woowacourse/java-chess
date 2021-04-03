package chess.service;

import chess.domain.board.ChessBoardFactory;
import chess.domain.dao.ChessDao;
import chess.domain.player.ChessGame;
import chess.domain.position.Position;
import chess.service.dto.*;

public class ChessService {
    private final ChessDao chessDao;
    private ChessGame chessGame;

    public ChessService(final ChessDao chessDao) {
        this.chessDao = chessDao;
        this.chessGame = ChessGame.newGame();
    }

    public TilesDto emptyBoard() {
        return new TilesDto(ChessBoardFactory.initializeBoard());
    }

    public PiecesStatusDto initializeGame() {
        this.chessGame = ChessGame.newGame();
        return new PiecesStatusDto(chessGame.getAllPieces(), this.chessGame.calculateScoreWeb(), chessGame.isGameOver());
    }

    public MoveResponseDto movePiece(MoveRequestDto requestDto) {
        chessGame.moveByTurn(Position.find(requestDto.getSource()), Position.find(requestDto.getTarget()));
        return new MoveResponseDto(requestDto.getSource(), requestDto.getTarget(), chessGame.calculateScoreWeb(), chessGame.isGameOver());
    }

    public PiecesStatusDto pieces() {
        return new PiecesStatusDto(chessGame.getAllPieces(), chessGame.calculateScoreWeb(), chessGame.isGameOver());
    }

    public void changeGameStatus(GameStatusRequestDto requestDto) {
        chessGame.changeStatus(requestDto);
    }
}
