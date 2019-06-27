package chess.application;

import chess.dao.ChessLogDao;
import chess.dao.ChessRoundDao;
import chess.domain.chessboard.ChessBoard;
import chess.domain.Position;
import chess.domain.chessboard.ChessBoardGenerator;
import chess.domain.chesspiece.Team;
import chess.application.dto.ChessBoardDto;
import chess.application.dto.ChessLogDto;
import chess.application.dto.ChessPositionDto;
import chess.application.dto.ChessScoreDto;

import java.util.List;

public class ChessService {
    private static final int WHITE_TEAM = 0;
    private static final int NEXT_NUMBER = 1;
    private static final int OPERATOR_NUMBER = 2;

    private ChessLogDao chessLogDao = ChessLogDao.getInstance();
    private ChessRoundDao chessRoundDao = ChessRoundDao.getInstance();

    private static class ChessServiceLazyHolder {
        private static final ChessService INSTANCE = new ChessService();
    }

    public static ChessService getInstance() {
        return ChessServiceLazyHolder.INSTANCE;
    }

    public ChessBoard getMovedChessBoardByRoundId(int roundId) {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        List<ChessLogDto> chessLogDtos = chessLogDao.selectChessLogByRoundId(roundId);

        for (ChessLogDto chessLogDto : chessLogDtos) {
            chessBoard.movePiece(Position.from(chessLogDto.getSource()), Position.from(chessLogDto.getTarget()));
        }

        return chessBoard;
    }

    public ChessBoardDto moveChessPiece(ChessPositionDto chessPositionDto, int roundId) {
        ChessBoard chessBoard = getMovedChessBoardByRoundId(roundId);

        Position source = Position.of(chessPositionDto.getSourceY(), chessPositionDto.getSourceX());
        Position target = Position.of(chessPositionDto.getTargetY(), chessPositionDto.getTargetX());
        int turn = chessLogDao.selectTurnByRoundId(roundId);

        Team team = getTeam(turn);

        if (!chessBoard.isNextTeam(source, team) || !chessBoard.movePiece(source, target)) {
            throw new IllegalArgumentException();
        }

        chessLogDao.insertChessLogByRoundId(roundId
                , new ChessLogDto(String.valueOf((turn + NEXT_NUMBER) % OPERATOR_NUMBER), source.toString(), target.toString()));

        return ChessAssembler.toDto(chessBoard);
    }

    public ChessScoreDto getChessScore(int roundId) {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        List<ChessLogDto> chessLogDtos = chessLogDao.selectChessLogByRoundId(roundId);

        for (ChessLogDto chessLogDto : chessLogDtos) {
            chessBoard.movePiece(Position.from(chessLogDto.getSource()), Position.from(chessLogDto.getTarget()));
        }

        return new ChessScoreDto(chessBoard.calculateScore(Team.WHITE), chessBoard.calculateScore(Team.BLACK));
    }

    public int getLastRoundId() {
        return chessRoundDao.selectLastRoundId();
    }

    public void addRound(int roundId) {
        chessRoundDao.insertRound(roundId);
    }

    private Team getTeam(int turn) {
        return turn == WHITE_TEAM ? Team.WHITE : Team.BLACK;
    }
}
