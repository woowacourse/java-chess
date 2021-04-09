package chess.service;

import chess.controller.web.dto.PieceDto;
import chess.controller.web.dto.SaveRequestDto;
import chess.dao.MysqlChessDao;
import chess.dao.dto.ChessGame;
import chess.domain.board.Board;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerBundle;
import chess.domain.manager.ChessGameManagerFactory;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ChessService {
    private static final long TEMPORARY_ID = 0;

    private static ChessGameManager chessGameManager = ChessGameManagerFactory.createNotStartedGameManager(TEMPORARY_ID);
    private static MysqlChessDao dao = new MysqlChessDao();

    public static void start() {
        chessGameManager = ChessGameManagerFactory.createRunningGame(TEMPORARY_ID);
    }

    public static ChessGameManagerBundle findRunningGames() {
        return dao.findAllOnRunning();
    }

    public static void save(SaveRequestDto saveRequestDto) {
        ChessGame chessGame = new ChessGame(chessGameManager);
        if (saveRequestDto.getId() == TEMPORARY_ID) {
            dao.save(chessGame);
            return;
        }
        update(chessGame);
    }

    private static void update(ChessGame chessGame) {
        dao.update(chessGame);
    }

    public static void load(long id) {
        chessGameManager = dao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID가 없습니다."));
    }

    public static void move(Position from, Position to) {
        chessGameManager.move(from, to);
        if (chessGameManager.isKingDead()) {
            chessGameManager = chessGameManager.end();
        }
    }

    public static boolean isEnd() {
        return chessGameManager.isEnd();
    }

    public static Map<String, PieceDto> getPieces() {
        Board board = chessGameManager.getBoard();
        return board.getAliveSquares().stream()
                .collect(toMap(square -> square.getPosition().toString()
                        , square -> new PieceDto(square.getNotationText(), square.getColor().name())));
    }

    public static Color nextColor() {
        try {
            return chessGameManager.nextColor();
        } catch (UnsupportedOperationException e) {
            return Color.BLANK;
        }
    }

    public static ChessGameStatistics getStatistics() {
        return chessGameManager.getStatistics();
    }

    public static long getId() {
        return chessGameManager.getId();
    }
}
