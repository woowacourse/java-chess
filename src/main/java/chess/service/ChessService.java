package chess.service;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.board.Coordinate;
import chess.domain.history.Histories;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import chess.dto.BoardDTO;
import chess.dto.MoveRequestDTO;
import chess.dto.ResultDTO;
import chess.repository.ChessRepository;

import java.sql.SQLException;

public class ChessService {

    private final ChessRepository chessRepository;

    public ChessService(ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
    }

    public BoardDTO findLatestBoardByRoomId(int roomId) throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistoriesByRoomId(roomId));
        histories.restoreChessBoardAsLatest(chessBoard);
        if (histories.isEmpty() || chessBoard.isKingCheckmate()) {
            return BoardDTO.from(generateDefaultChessBoard(), TeamType.WHITE);
        }
        TeamType nextTeamType = histories.findNextTeamType();
        return BoardDTO.from(chessBoard, nextTeamType);
    }

    private ChessBoard generateDefaultChessBoard() {
        return new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
    }

    public BoardDTO move(MoveRequestDTO moveRequestDTO, int roomId) throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistoriesByRoomId(roomId));
        histories.restoreChessBoardAsLatest(chessBoard);
        moveChessBoard(chessBoard, moveRequestDTO);
        TeamType nextTeamType = TeamType.valueOf(moveRequestDTO.getTeamType())
                .findOppositeTeam();
        updateHistory(moveRequestDTO, roomId);
        return BoardDTO.from(chessBoard, nextTeamType);
    }

    private void moveChessBoard(ChessBoard chessBoard, MoveRequestDTO moveRequestDTO) {
        Coordinate current = Coordinate.from(moveRequestDTO.getCurrent());
        Coordinate destination = Coordinate.from(moveRequestDTO.getDestination());
        TeamType teamType = TeamType.valueOf(moveRequestDTO.getTeamType());
        chessBoard.move(current, destination, teamType);
    }

    private void updateHistory(MoveRequestDTO moveRequestDTO, int roomId) throws SQLException {
        String current = moveRequestDTO.getCurrent();
        String destination = moveRequestDTO.getDestination();
        String teamType = moveRequestDTO.getTeamType();
        chessRepository.insertHistory(current, destination, teamType, roomId);
    }

    public ResultDTO calculateResult(int roomId) throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistoriesByRoomId(roomId));
        histories.restoreChessBoardAsLatest(chessBoard);
        Result result = chessBoard.calculateScores();
        TeamType winnerTeamType = chessBoard.findWinnerTeam();
        return ResultDTO.from(result, winnerTeamType);
    }

    public void resetDefaultByRoomId(int roomId) throws SQLException {
        chessRepository.deleteAllHistoriesByRoomId(roomId);
    }
}
