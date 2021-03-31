package chess.service;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import chess.domain.history.Histories;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import chess.dto.BoardDTO;
import chess.dto.RequestDTO;
import chess.dto.ResultDTO;
import chess.repository.ChessRepository;

import java.sql.SQLException;

public class ChessService {

    private final ChessRepository chessRepository;

    public ChessService(ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
    }

    public BoardDTO findLatestBoard(int roomId) throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistoriesByRoomId(roomId));
        histories.restoreLatestChessBoard(chessBoard);
        if (chessBoard.isKingCheckmate() || histories.isEmpty()) {
            return BoardDTO.from(generateDefaultChessBoard(), TeamType.WHITE);
        }
        TeamType nextTeamType = histories.findNextTeamType();
        return BoardDTO.from(chessBoard, nextTeamType);
    }

    private ChessBoard generateDefaultChessBoard() {
        return new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
    }

    public BoardDTO move(RequestDTO requestDTO, int roomId) throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistoriesByRoomId(roomId));
        histories.restoreLatestChessBoard(chessBoard);
        moveChessBoard(chessBoard, requestDTO);
        TeamType nextTeamType = TeamType.valueOf(requestDTO.getTeamType())
                .findOppositeTeam();
        updateHistory(requestDTO, roomId);
        return BoardDTO.from(chessBoard, nextTeamType);
    }

    private void moveChessBoard(ChessBoard chessBoard, RequestDTO requestDTO) {
        Coordinate current = Coordinate.from(requestDTO.getCurrent());
        Coordinate destination = Coordinate.from(requestDTO.getDestination());
        TeamType teamType = TeamType.valueOf(requestDTO.getTeamType());
        chessBoard.move(current, destination, teamType);
    }

    private void updateHistory(RequestDTO requestDTO, int roomId) throws SQLException {
        String current = requestDTO.getCurrent();
        String destination = requestDTO.getDestination();
        String teamType = requestDTO.getTeamType();
        chessRepository.insertHistoryByRoomId(current, destination, teamType, roomId);
    }

    public ResultDTO calculateResult(int roomId) throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistoriesByRoomId(roomId));
        histories.restoreLatestChessBoard(chessBoard);
        Result result = chessBoard.calculateScores();
        TeamType winnerTeamType = chessBoard.findWinnerTeam();
        return ResultDTO.from(result, winnerTeamType);
    }

    public void resetDefaultByRoomId(int roomId) throws SQLException {
        chessRepository.deleteAllHistoriesByRoomId(roomId);
    }
}
