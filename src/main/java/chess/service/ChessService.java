package chess.service;

import chess.controller.web.dto.PieceDto;
import chess.controller.web.dto.SaveRequestDto;
import chess.dao.MysqlChessDao;
import chess.dao.dto.ChessGame;
import chess.domain.board.Board;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerBundle;
import chess.domain.manager.ChessGameManagerFactory;
import chess.domain.order.MoveResult;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ChessService {
    private static final long TEMPORARY_ID = 0;

    private ChessGameManager chessGameManager = ChessGameManagerFactory.createNotStartedGameManager(TEMPORARY_ID);
    private MysqlChessDao dao = new MysqlChessDao();

    public void start() {
        chessGameManager = ChessGameManagerFactory.createRunningGame(TEMPORARY_ID);
    }

    public ChessGameManagerBundle findRunningGames() {
        return dao.findAllOnRunning();
    }

    public void save(SaveRequestDto saveRequestDto) {
        ChessGame chessGame = new ChessGame(chessGameManager, saveRequestDto.getPieces());
        if (saveRequestDto.getId() == TEMPORARY_ID) {
            dao.save(chessGame);
            return;
        }
        update(chessGame);
    }

    private void update(ChessGame chessGame) {
        dao.update(chessGame);
    }

    public void load(long id) {
        chessGameManager = dao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID가 없습니다."));
    }

    public MoveResult move(Position from, Position to) {
        MoveResult move = chessGameManager.move(from, to);
        if (chessGameManager.isKingDead()) {
            chessGameManager = chessGameManager.end();
        }
        return move;
    }

    public boolean isEnd() {
        return chessGameManager.isEnd();
    }

    public Map<String, PieceDto> getPieces() {
        Board board = chessGameManager.getBoard();
        return board.getAliveSquares().stream()
                .collect(toMap(square -> square.getPosition().toString()
                        , square -> new PieceDto(square.getNotationText(), square.getColor().name())));
    }

    public Color nextColor() {
        try {
            return chessGameManager.nextColor();
        } catch (UnsupportedOperationException e) {
            return Color.BLANK;
        }
    }

    public ChessGameStatistics getStatistics() {
        return chessGameManager.getStatistics();
    }

    public long getId() {
        return chessGameManager.getId();
    }
}
