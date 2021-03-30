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

    public BoardDTO findLatestBoard() throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistories());
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

    public BoardDTO move(RequestDTO requestDTO) throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistories());
        histories.restoreLatestChessBoard(chessBoard);
        moveChessBoard(chessBoard, requestDTO);
        TeamType nextTeamType = TeamType.valueOf(requestDTO.getTeamType())
                .findOppositeTeam();
        updateHistory(requestDTO);
        return BoardDTO.from(chessBoard, nextTeamType);
    }

    private void updateHistory(RequestDTO requestDTO) throws SQLException {
        String current = requestDTO.getCurrent();
        String destination = requestDTO.getDestination();
        String teamType = requestDTO.getTeamType();
        chessRepository.insertHistory(current, destination, teamType);
    }

    private void moveChessBoard(ChessBoard chessBoard, RequestDTO requestDTO) {
        Coordinate current = Coordinate.from(requestDTO.getCurrent());
        Coordinate destination = Coordinate.from(requestDTO.getDestination());
        TeamType teamType = TeamType.valueOf(requestDTO.getTeamType());
        chessBoard.move(current, destination, teamType);
    }

    public ResultDTO calculateResult() throws SQLException {
        ChessBoard chessBoard = generateDefaultChessBoard();
        Histories histories = new Histories(chessRepository.findAllHistories());
        histories.restoreLatestChessBoard(chessBoard);
        Result result = chessBoard.calculateScores();
        TeamType winnerTeamType = chessBoard.findWinnerTeam();
        return ResultDTO.from(result, winnerTeamType);
    }

    public void resetDefault() throws SQLException {
        chessRepository.deleteAllHistories();
    }
}
