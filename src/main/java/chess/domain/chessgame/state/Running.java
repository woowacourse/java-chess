package chess.domain.chessgame.state;

import chess.controller.ChessBoardFormatter;
import chess.dao.ChessGameDao;
import chess.dao.RoomName;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Team;
import chess.domain.winningstatus.Score;
import chess.domain.winningstatus.WinningStatus;
import chess.domain.winningstatus.WinningStatusByKing;
import chess.domain.winningstatus.WinningStatusByScore;

import java.util.EnumMap;
import java.util.Map;

public class Running implements GameState {
    private static final String RUNNING_STATE_EXCEPTION_MESSAGE = "게임을 실행 중일때 수행할 수 없는 동작입니다.";

    private final ChessBoard chessBoard;
    private Team currentTeam;

    public Running(final ChessBoard chessBoard, final Team currentTeam) {
        this.chessBoard = chessBoard;
        this.currentTeam = currentTeam;
    }

    @Override
    public GameState start() {
        throw new IllegalStateException(RUNNING_STATE_EXCEPTION_MESSAGE);
    }

    @Override
    public void move(final SquareCoordinate from, final SquareCoordinate to) {
        if (chessBoard.isDifferentTeam(currentTeam, from)) {
            throw new IllegalArgumentException();
        }

        chessBoard.move(from, to);
        currentTeam = currentTeam.getNextTurn();
    }

    @Override
    public boolean isKingDead() {
        return chessBoard.isKingDead();
    }

    @Override
    public GameState close() {
        final Team winner = chessBoard.findTeamHavingKing();
        return new Ready(new WinningStatusByKing(winner), chessBoard.copyChessBoard());
    }

    @Override
    public WinningStatus status() {
        Map<Team, Score> scores = new EnumMap<>(Team.class);
        scores.put(Team.WHITE, new Score(chessBoard.getPiecesOf(Team.WHITE), chessBoard.countDoublePawnOf(Team.WHITE)));
        scores.put(Team.BLACK, new Score(chessBoard.getPiecesOf(Team.BLACK), chessBoard.countDoublePawnOf(Team.BLACK)));

        return new WinningStatusByScore(scores);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public GameState save(RoomName roomName) {
        ChessBoardFormatter convertedChessBoard = ChessBoardFormatter.toString(chessBoard);

        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.addGame(roomName, currentTeam, convertedChessBoard.getOneLineChessBoard());

        return new Ready();
    }

    @Override
    public GameState end() {
        return new End();
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
