package chess.service;

import chess.domain.board.ChessBoardFactory;
import chess.domain.player.ChessGame;
import chess.domain.position.Position;
import chess.service.dto.MoveRequestDto;
import chess.service.dto.MoveResponseDto;
import chess.service.dto.PiecesStatusDto;
import chess.service.dto.TilesDto;

public class ChessService {
    private ChessGame chessGame;

    public ChessService(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public TilesDto emptyBoard() {
        return new TilesDto(ChessBoardFactory.initializeBoard());
    }

    public PiecesStatusDto initializeGame() {
        this.chessGame = ChessGame.newGame();
        return new PiecesStatusDto(chessGame.getAllPieces(), this.chessGame.calculateScoreWeb());
    }

    public MoveResponseDto movePiece(MoveRequestDto requestDto) {
        chessGame.moveByTurn(Position.find(requestDto.getSource()), Position.find(requestDto.getTarget()));
        return new MoveResponseDto(requestDto.getSource(), requestDto.getTarget(), chessGame.calculateScoreWeb());
    }

    public PiecesStatusDto pieces() {
        return new PiecesStatusDto(chessGame.getAllPieces(), chessGame.calculateScoreWeb());
    }
}
