package chess.service;


import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Result;
import chess.domain.Score;
import chess.domain.dao.BoardDao;
import chess.domain.dao.ChessGameDao;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.MoveDto;
import chess.dto.ResponseDto;
import chess.dto.StatusDto;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessService {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final BoardDao boardDao = new BoardDao();

    public String start() {
        Long gameId = chessGameDao.save(Color.WHITE);
        boardDao.saveAll(gameId, Board.create());
        return String.valueOf(gameId);
    }

    public Map<String, String> getCurrentBoard(final Long gameId) {
        Map<Position, Piece> board = boardDao.findById(gameId).getBoard();
        Map<String, String> boardDto = new LinkedHashMap<>();
        for (Entry<Position, Piece> positionPieceEntry : board.entrySet()) {
            boardDto.put(positionPieceEntry.getKey().getPosition(), positionPieceEntry.getValue().getName());
        }
        return boardDto;
    }

    public String getCurrentTurn(final Long gameId) {
        return chessGameDao.findTurnById(gameId).getName();
    }

    public ResponseDto move(final MoveDto moveDto) {
        Long gameId = moveDto.getGameId();
        final ChessGame chessGame = new ChessGame(chessGameDao.findTurnById(gameId), boardDao.findById(gameId));
        final String from = moveDto.getFrom();
        final String to = moveDto.getTo();
        try {
            boolean movable = chessGame.move(Position.create(from), Position.create(to));
            updateBoardDao(gameId, chessGame.getBoard(), from, to);
            return checkFinished(chessGame, gameId, movable);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return new ResponseDto("400", e.getMessage(), chessGame.getTurn());
        }
    }

    private void updateBoardDao(final Long gameId, final Board board, final String from, final String to) {
        boardDao.updatePosition(gameId, from, new EmptyPiece().getName());
        boardDao.updatePosition(gameId, to, board.findPiece(Position.create(to)).getName());
    }

    private ResponseDto checkFinished(final ChessGame chessGame, final Long gameId, final boolean movable) {
        if (!movable) {
            end(gameId);
            return new ResponseDto("300", "끝", chessGame.getTurn());
        }
        chessGameDao.updateTurn(gameId, chessGame.getTurn());
        return new ResponseDto("200", "성공", chessGame.getTurn());
    }

    public StatusDto status(final Long gameId) {
        Board board = boardDao.findById(gameId);
        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);
        Result whiteResult = Result.decide(whiteScore, blackScore);
        Result blackResult = Result.decide(blackScore, whiteScore);
        return new StatusDto(whiteScore.getValue(), blackScore.getValue(), whiteResult.getName(),
                blackResult.getName());
    }

    public void end(final Long gameId) {
        boardDao.deleteByGameId(gameId);
        chessGameDao.deleteById(gameId);
    }
}
