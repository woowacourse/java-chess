package chess.service;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.history.Histories;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;
import chess.domain.result.Scores;
import chess.repository.ChessRepository;

import java.sql.SQLException;

public class ChessService {

    private final ChessRepository chessRepository;

    public ChessService(ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
    }

    public ChessBoard findLatestBoardByRoomId(int roomId) throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistoriesByRoomId(roomId));
        histories.restoreChessBoardAsLatest(chessBoard);
        return chessBoard;
    }

    private ChessBoard generateDefaultChessBoard() {
        return new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
    }

    public TeamType findCurrentTeamTypeByRoomId(int roomId) throws SQLException {
        Histories histories = new Histories(chessRepository.findAllHistoriesByRoomId(roomId));
        return histories.findNextTeamType();
    }

    public void updateHistory(String current, String destination, String teamType, int roomId) throws SQLException {
        chessRepository.insertHistory(current, destination, teamType, roomId);
    }

    public Result calculateResult(int roomId) throws SQLException {
        ChessBoard chessBoard = findLatestBoardByRoomId(roomId);
        Scores scores = chessBoard.calculateScores();
        TeamType winnerTeamType = chessBoard.findWinnerTeam();
        return new Result(scores, winnerTeamType);
    }

    public void deleteAllHistoriesByRoomId(int roomId) throws SQLException {
        chessRepository.deleteAllHistoriesByRoomId(roomId);
    }
}
