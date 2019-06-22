package chess;

import java.sql.SQLException;

import chess.dao.BoardDAO;
import chess.dao.ResultCounterDAO;
import chess.dao.TurnDAO;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardDTO;
import chess.dto.ResultCounterDTO;
import chess.dto.TemplateEngine;
import chess.dto.TurnDTO;
import chess.utils.DataParser;
import chess.utils.Model;
import chess.utils.QueryParser;
import chess.view.JsonTransformer;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    private static BoardDTO boardDTO;
    private static TurnDTO turnDTO;
    private static ResultCounterDTO resultCounterDTO;

    public static void main(String[] args) {
        staticFiles.location("/assets");

        get("/", (req, res) ->
                TemplateEngine.render(Model.empty(), "index.html")
        );

        // 게임을 처음 시작할 때 진입점
        get("/game_play", (req, res) -> {
            daoDeleteHandler();
            dtoInstantiation();

            ChessBoard chessBoard = new ChessBoard();

            dtoChessBoard(chessBoard);
            daoAddHandler();

            return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
        });

        // 계속할 때 진입점
        get("/game_continue", (req, res) ->
                TemplateEngine.render(Model.turn(), "game_play.html")
        );

        post("/game_play", (req, res) -> {
            Position source = QueryParser.position(req.queryParams("source"));
            Position target = QueryParser.position(req.queryParams("target"));

            ChessBoard chessBoard = dtoChessBoard();
            boolean isKingDead = chessBoard.move(source, target);

            dtoChessBoard(chessBoard);
            daoMoveHandler(boardDTO, turnDTO, resultCounterDTO);

            if (isKingDead) {
                return TemplateEngine.render(Model.result(chessBoard), "result.html");
            }
            return TemplateEngine.render(Model.turn(chessBoard), "game_play.html");
        });

        post("/status", (req, res) ->
                DataParser.board(dtoChessBoard().getBoard()), new JsonTransformer()
        );

        get("/result", (req, res) -> {
            ChessBoard chessBoard = dtoChessBoard();

            Double whiteScore = chessBoard.totalScore(Team.WHITE);
            Double blackScore = chessBoard.totalScore(Team.BLACK);

            return TemplateEngine.render(Model.result(whiteScore, blackScore), "result.html");
        });


        get("/end", (req, res) -> {
            daoDeleteHandler();
            return TemplateEngine.render(Model.empty(), "end.html");
        });

        exception(RuntimeException.class, (e, req, res) -> {
            res.status(404);
            res.body(
                    "<h1> 에러 발생 </h1>" +
                            "<div>" + e.getMessage() + "</div>" +
                            "<button onclick=\"window.history.back()\">되돌아가기</button>"
            );
        });
    }

    private static void daoMoveHandler(BoardDTO boardDTO, TurnDTO turnDTO, ResultCounterDTO resultCounterDTO) throws SQLException {
        BoardDAO.afterMove(boardDTO);
        TurnDAO.afterMove(turnDTO);
        ResultCounterDAO.afterMove(resultCounterDTO);
    }

    private static void daoDeleteHandler() throws SQLException {
        BoardDAO.deleteAll();
        TurnDAO.deleteAll();
        ResultCounterDAO.deleteAll();
    }

    private static ChessBoard dtoChessBoard() throws SQLException {
        boardDTO = BoardDAO.selectAll();
        turnDTO = TurnDAO.selectLastTurn();
        resultCounterDTO = ResultCounterDAO.selectAll();

        return new ChessBoard(boardDTO.getBoard(), turnDTO.getTurn(), resultCounterDTO.getResultCounter());
    }

    private static void daoAddHandler() throws SQLException {
        BoardDAO.add(boardDTO);
        TurnDAO.add(turnDTO);
        ResultCounterDAO.add(resultCounterDTO);
    }

    private static void dtoInstantiation() {
        boardDTO = new BoardDTO();
        turnDTO = new TurnDTO();
        resultCounterDTO = new ResultCounterDTO();
    }

    private static void dtoChessBoard(ChessBoard chessBoard) {
        boardDTO.setBoard(chessBoard.getBoard());
        turnDTO.setTurn(chessBoard.getTurn());
        resultCounterDTO.setResultCounter(chessBoard.getResultCounter());
    }
}
