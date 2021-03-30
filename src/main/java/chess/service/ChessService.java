package chess.service;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import chess.domain.history.History;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import chess.dto.BoardDTO;
import chess.dto.RequestDTO;
import chess.dto.ResultDTO;
import chess.repository.ChessRepository;

import java.sql.SQLException;
import java.util.List;

public class ChessService {

    private final ChessRepository chessRepository;

    public ChessService(ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
    }

    public BoardDTO get() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
        List<History> histories = chessRepository.findAllHistories();

        histories.forEach(t -> {
            Coordinate source = Coordinate.from(t.getSource());
            Coordinate destination = Coordinate.from(t.getDestination());
            TeamType teamType = TeamType.valueOf(t.getTeam());
            chessBoard.move(source, destination, teamType);
        });
        if (chessBoard.isKingCheckmate() || histories.isEmpty()) {
            return BoardDTO.from(new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard()), TeamType.WHITE);
        }
        String beforeTurnTeam = histories.get(histories.size() - 1).getTeam();
        return BoardDTO.from(chessBoard, TeamType.valueOf(beforeTurnTeam).findOppositeTeam());
    }

    public BoardDTO move(RequestDTO requestDTO) throws SQLException {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
        List<History> histories = chessRepository.findAllHistories();
        histories.forEach(t -> {
            Coordinate source = Coordinate.from(t.getSource());
            Coordinate destination = Coordinate.from(t.getDestination());
            TeamType teamType = TeamType.valueOf(t.getTeam());
            chessBoard.move(source, destination, teamType);
        });
        chessRepository.insertHistory(requestDTO.getCurrent(), requestDTO.getDestination(), requestDTO.getTeamType());
        Coordinate current = Coordinate.from(requestDTO.getCurrent());
        Coordinate destination = Coordinate.from(requestDTO.getDestination());
        TeamType teamType = TeamType.valueOf(requestDTO.getTeamType());
        chessBoard.move(current, destination, teamType);
        return BoardDTO.from(chessBoard, teamType.findOppositeTeam());
    }

    public ResultDTO calculateResult() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
        List<History> histories = chessRepository.findAllHistories();
        histories.forEach(t -> {
            Coordinate source = Coordinate.from(t.getSource());
            Coordinate destination = Coordinate.from(t.getDestination());
            TeamType teamType = TeamType.valueOf(t.getTeam());
            chessBoard.move(source, destination, teamType);
        });
        Result result = chessBoard.calculateScores();
        TeamType winnerTeamType = chessBoard.findWinnerTeam();
        return ResultDTO.from(result, winnerTeamType);
    }

    public void resetDefault() throws SQLException {
        chessRepository.deleteAllHistories();
    }
}
