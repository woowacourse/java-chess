package chess.service;

import chess.database.dao.ChessBoardDao;
import chess.domain.ChessBoard;
import chess.domain.TeamScore;
import chess.domain.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.service.dto.MovingRequest;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoardService {
    private static ChessBoardService instance;
    private static ChessBoardDao chessBoardDao = ChessBoardDao.getInstance();

    private TurnService turnService;

    public static ChessBoardService getInstance(TurnService turnService) {
        if (instance == null) {
            instance = new ChessBoardService(turnService);
        }
        return instance;
    }

    private ChessBoardService(TurnService turnService) {
        this.turnService = turnService;
    }

    public void initialize() throws SQLException {
        saveBoard(new ChessBoard());
    }

    public void saveBoard(ChessBoard chessBoard) throws SQLException {
        chessBoardDao.delete();
        Map<Square, Piece> board = chessBoard.getChessBoard();
        for (Square square : board.keySet()) {
            chessBoardDao.save(square, board.get(square));
        }
    }

    public ChessBoard loadBoard() {
        try {
            return chessBoardDao.load();
        } catch (SQLException e) {
            return new ChessBoard();
        }
    }

    public Map<String, Piece> getBoardView() {
        Map<Square, Piece> board = loadBoard().getChessBoard();
        Map<String, Piece> boardView = new HashMap<>();

        for (Square square : board.keySet()) {
            boardView.put(square.toString(), board.get(square));
        }
        return boardView;
    }

    public Map<String, Double> getTeamScoreView() {
        TeamScore teamScore = new TeamScore();
        Map<String, Double> teamScoreView = new HashMap<>();
        teamScore.updateTeamScore(loadBoard());
        for (Color color : teamScore.getTeamScore().keySet()) {
            teamScoreView.put(color.toString(), teamScore.getTeamScore().get(color));
        }
        return teamScoreView;
    }


    public String movePiece(MovingRequest movingRequest) throws SQLException {
        String result = "";
        List<Square> sourceDestination = Arrays.asList(movingRequest.getSource(), movingRequest.getDestination());
        ChessBoard chessBoard = loadBoard();
        Turn turn = turnService.loadTurn();
        if (chessBoard.canMove(sourceDestination, turn)) {
            chessBoard.movePiece(sourceDestination);
            turn = turn.getOppositeTurn();
        } else {
            result = "움직일 수 없는 위치입니다.";
        }
        saveBoard(chessBoard);
        turnService.save(turn);
        return result;
    }

    public String getWinners() {
        TeamScore teamScore = new TeamScore();

        teamScore.updateTeamScore(loadBoard());

        return teamScore.getWinners().stream()
                .map(Enum::toString)
                .collect(Collectors.joining(","));
    }
}
