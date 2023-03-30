package chess.service;

import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.ChessBoardGenerator;
import chess.domain.chessGame.Referee;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.request.CommandDto;
import chess.dto.response.StatusDto;
import chess.repository.ChessGameDao;

import java.util.List;

public final class ChessService {

    private static final String BLACK_TEAM = "검은색";
    private static final String WHITE_TEAM = "흰색";
    private static final String DRAW = "무승부";

    private final ChessGameDao chessGameDao;
    private String user;

    public ChessService(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ChessBoard setUpChessBoard(String user) {
        this.user = user;
        ChessBoard chessBoard = chessGameDao.select(user);
        if (chessBoard != null) {
            return chessBoard;
        }
        ChessBoardGenerator generator = new ChessBoardGenerator();
        chessBoard = new ChessBoard(generator.generate(), Color.WHITE);
        chessGameDao.insert(user, chessBoard);
        return chessBoard;
    }

    public ChessBoard executeMoveCommand(ChessBoard chessBoard, CommandDto commandDto) {
        String startInput = commandDto.getStartPosition();
        String endInput = commandDto.getEndPosition();

        chessBoard.movePiece(Position.of(startInput), Position.of(endInput));
        chessGameDao.update(user, chessBoard);
        return chessBoard;
    }

    public StatusDto getGameStatus(ChessBoard chessBoard) {
        Referee referee = new Referee(chessBoard.getChessBoard());

        double blackScore = referee.calculateScore(Color.BLACK);
        double whiteScore = referee.calculateScore(Color.WHITE);
        String winner = getWinner(blackScore, whiteScore);

        return StatusDto.of(blackScore, whiteScore, winner);
    }

    private String getWinner(double blackScore, double whiteScore) {
        Color winningColor = getWinningColor(blackScore, whiteScore);
        if (winningColor == Color.BLACK) {
            return BLACK_TEAM;
        }
        if (winningColor == Color.WHITE) {
            return WHITE_TEAM;
        }
        return DRAW;
    }

    private Color getWinningColor(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            return Color.BLACK;
        }
        if (blackScore < whiteScore) {
            return Color.WHITE;
        }
        return null;
    }

    public boolean checkKingAlive(ChessBoard chessBoard) {
        if (chessBoard.isKingDead()) {
            chessGameDao.delete(user, chessBoard);
            return false;
        }
        return true;
    }

    public List<String> getUsers() {
        return chessGameDao.getUsers();
    }
}
