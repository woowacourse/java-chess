package chess.service;

import chess.dao.ChessRoundDao;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.chesspiece.Team;
import chess.dto.ChessBoardDto;
import chess.dto.ChessInfoDto;
import chess.dto.ChessPositionDto;
import chess.dto.ChessScoreDto;

import java.util.List;
import java.util.Objects;

public class ChessService {
    private static final int WHITE_TEAM = 0;
    private static final int NEXT_NUMBER = 1;
    private static final int OPERATOR_NUMBER = 2;

    private static ChessService chessService;
    private static ChessRoundDao chessRoundDao = ChessRoundDao.getInstance();

    private ChessService() {
    }

    public static ChessService getInstance() {
        if (Objects.isNull(chessService)) {
            return new ChessService();
        }

        return chessService;
    }

    public ChessBoardDto getChessBoardDTO(int roundId) {
        return ChessAssembler.toDto(getMovedChessBoard(roundId));
    }

    public ChessBoardDto getChessBoard(ChessPositionDto chessPositionDto, int roundId) {
        ChessBoard chessBoard = getMovedChessBoard(roundId);

        Position source = Position.of(chessPositionDto.getSourceY(), chessPositionDto.getSourceX());
        Position target = Position.of(chessPositionDto.getTargetY(), chessPositionDto.getTargetX());
        int turn = ChessRoundDao.getInstance().selectTurnByRoundId(roundId);

        Team team = getTeam(turn);

        if (!chessBoard.isNextTeam(source, team) || !chessBoard.movePiece(source, target)) {
            throw new IllegalArgumentException();
        }

        ChessRoundDao
                .getInstance()
                .insertChessInfoByRoundId(
                        roundId
                        , new ChessInfoDto(String.valueOf((turn + NEXT_NUMBER) % OPERATOR_NUMBER)
                                , source.toString()
                                , target.toString()));

        return ChessAssembler.toDto(chessBoard);
    }

    private Team getTeam(int turn) {
        return turn == WHITE_TEAM ? Team.WHITE : Team.BLACK;
    }

    private ChessBoard getMovedChessBoard(int roundId) {
        ChessBoard chessBoard = new ChessBoard();
        List<ChessInfoDto> chessInfoDtos = ChessRoundDao.getInstance().selectChessInfoByRoundId(roundId);

        for (ChessInfoDto chessInfoDto : chessInfoDtos) {
            chessBoard.movePiece(Position.from(chessInfoDto.getSource()), Position.from(chessInfoDto.getTarget()));
        }

        return chessBoard;
    }

    public ChessScoreDto getChessScore(int roundId) {
        ChessBoard chessBoard = new ChessBoard();
        List<ChessInfoDto> chessInfoDtos = ChessRoundDao.getInstance().selectChessInfoByRoundId(roundId);

        for (ChessInfoDto chessInfoDto : chessInfoDtos) {
            chessBoard.movePiece(Position.from(chessInfoDto.getSource()), Position.from(chessInfoDto.getTarget()));
        }

        return new ChessScoreDto(chessBoard.calculateScore(Team.WHITE), chessBoard.calculateScore(Team.BLACK));
    }

    public int getLastRoundId() {
        return ChessRoundDao.getInstance().selectLastRoundId();
    }

    public void addRound(int roundId) {
        ChessRoundDao.getInstance().insertRound(roundId);
    }
}
