package chess.service;

import chess.dao.ChessRoundDao;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.chesspiece.Team;
import chess.dto.ChessBoardDto;
import chess.dto.ChessInfoDto;
import chess.dto.ChessPositionDto;

import java.util.List;
import java.util.Objects;

public class ChessService {
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

        Team team = turn == 0 ? Team.WHITE : Team.BLACK;

        System.out.println(team);

        if (!chessBoard.isNextTeam(source, team) || !chessBoard.movePiece(source, target)) {
            throw new IllegalArgumentException();
        }

        ChessRoundDao.getInstance().insertChessInfoByRoundId(roundId, new ChessInfoDto(String.valueOf((turn + 1) % 2), source.toString(), target.toString()));

        System.out.println(chessBoard.getChessBoard());
        return ChessAssembler.toDto(chessBoard);
    }

    private ChessBoard getMovedChessBoard(int roundId) {
        ChessBoard chessBoard = new ChessBoard();
        List<ChessInfoDto> chessInfoDtos = ChessRoundDao.getInstance().selectChessInfoByRoundId(roundId);

        for (ChessInfoDto chessInfoDto : chessInfoDtos) {
            chessBoard.movePiece(Position.from(chessInfoDto.getSource()), Position.from(chessInfoDto.getTarget()));
        }

        return chessBoard;
    }
}
