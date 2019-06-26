package chess.service;

import chess.domain.board.Board;
import chess.domain.board.ChessGame;
import chess.domain.board.ChessRound;
import chess.domain.piece.core.Team;
import chess.persistence.dto.ChessBoardDTO;
import chess.persistence.dto.ChessGameDTO;
import chess.persistence.dto.ChessGameStatusDto;

public class ChessGameStatusService {
    public static ChessGameStatusService getInstance() {
        return ChessGameStatusHolder.INSTANCE;
    }

    public ChessGameStatusDto request(ChessGameDTO chessGameDTO, ChessBoardDTO chessBoardDTO) {
        ChessGameStatusDto chessGameStatusDto = new ChessGameStatusDto();
        chessGameStatusDto.setGameId(chessGameDTO.getGameId());
        chessGameStatusDto.setRoundNo(chessBoardDTO.getRoundNo());
        chessGameStatusDto.setLastUser(chessGameDTO.getLastUser());
        chessGameStatusDto.setBoard(chessBoardDTO.getBoard());
        ChessGame chessGame = new ChessGame(Board.drawBoard(chessBoardDTO.getBoard(), Team.valueOf(chessGameDTO.getLastUser())));
        ChessRound chessRound = chessGame.createChessRound();
        chessGameStatusDto.setWhiteScore(chessRound.getWhiteTeamScore());
        chessGameStatusDto.setBlackScore(chessRound.getBlackTeamScore());
        if (chessGame.isGameOver()) {
            chessGameStatusDto.setWinner(chessGame.findWinner().getTeam());
        }
        return chessGameStatusDto;
    }

    private static class ChessGameStatusHolder {
        static final ChessGameStatusService INSTANCE = new ChessGameStatusService();
    }
}
