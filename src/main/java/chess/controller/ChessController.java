package chess.controller;

import chess.dao.BoardDao;
import chess.dao.PieceDao;
import chess.dto.request.MoveRequest;
import chess.dto.response.BoardResult;
import chess.dto.response.PieceResult;
import chess.dto.response.Turn;
import chess.game.Board;
import chess.game.BoardInitializer;
import chess.game.MoveCommand;
import chess.game.Position;
import chess.piece.Color;
import chess.piece.Piece;
import chess.state.Move;
import chess.state.Status;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {

    private final Gson gson;
    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessController() {
        gson = new Gson();
        boardDao = new BoardDao();
        pieceDao = new PieceDao();
    }

    public ModelAndView home() {
        return new ModelAndView(null, "index.html");
    }

    public ModelAndView start(final Request request, final Response response) {
        final Long boardId = boardDao.save(Color.WHITE);
        pieceDao.saveAll(boardId, BoardInitializer.getBoard());
        response.redirect("/game/" + boardId);
        return null;
    }

    public ModelAndView move(final Request request, final Response response) {
        final Long boardId = Long.valueOf(request.params("boardId"));
        final MoveRequest moveRequest = gson.fromJson(request.body(), MoveRequest.class);
        final String from = moveRequest.getFrom();
        final String to = moveRequest.getTo();

        final Turn turn = boardDao.findById(boardId);
        final Color color = Color.valueOf(turn.getColor());
        final Move move = new Move(new Board(pieceDao.findAllByBoardId(boardId)), color);
        move.move(new MoveCommand(Position.of(from), Position.of(to)));
        final Board board = move.getBoard();
        pieceDao.deleteByPositionAndBoardId(from, boardId);
        pieceDao.save(boardId, to, board.findPiece(Position.of(to)));
        boardDao.update(boardId, color.reverse());
        response.redirect("/game/" + boardId);
        return null;
    }

    public ModelAndView game(final Request request, final Response response) {
        final Long boardId = Long.valueOf(request.params("boardId"));
        final Map<Position, Piece> board = pieceDao.findAllByBoardId(boardId);
        return new ModelAndView(new BoardResult(board, boardId).getValue(), "game.html");
    }

    public ModelAndView score(final Request request, final Response response) {
        final Long boardId = Long.valueOf(request.params("boardId"));
        final Map<Color, Double> score = new Status(new Board(pieceDao.findAllByBoardId(boardId)), Color.NONE).score().getScore();
        final Map<String, Double> scoreResult = score.keySet()
                .stream()
                .collect(Collectors.toMap(Enum::name, score::get));
        return new ModelAndView(scoreResult, "game.html");
    }
}

