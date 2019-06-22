package chess;

import java.sql.SQLException;

import chess.dao.BoardDAO;
import chess.dao.ResultCounterDAO;
import chess.dao.TurnDAO;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.BoardDTO;
import chess.dto.ChessBoardDTO;
import chess.dto.ResultCounterDTO;
import chess.dto.TemplateEngine;
import chess.dto.TurnDTO;
import chess.utils.DataParser;
import chess.utils.QueryParser;
import chess.view.JsonTransformer;
import chess.web.Model;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    private static ChessBoardDTO chessBoardDTO;

    public static void main(String[] args) {
        staticFiles.location("/assets");

        get("/", (req, res) ->
                TemplateEngine.render(Model.empty(), "index.html")
        );

        // 게임을 처음 시작할 때 진입점
        get("/game_play", (req, res) -> {
            daoDeleteHandler();
            chessBoardDTO = initChessBoardDTO();
            ChessBoard chessBoard = new ChessBoard();
            chessBoardDTO = setChessBoardDTO(chessBoard);
            daoAddHandler(chessBoardDTO);
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

            chessBoardDTO = setChessBoardDTO(chessBoard);
            daoMoveHandler(chessBoardDTO);

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

    private static void daoMoveHandler(ChessBoardDTO chessBoardDTO) throws SQLException {
        BoardDAO.afterMove(chessBoardDTO.getBoardDTO());
        TurnDAO.afterMove(chessBoardDTO.getTurnDTO());
        ResultCounterDAO.afterMove(chessBoardDTO.getResultCounterDTO());
    }

    // 독립 가능
    private static void daoDeleteHandler() throws SQLException {
        BoardDAO.deleteAll();
        TurnDAO.deleteAll();
        ResultCounterDAO.deleteAll();
    }

    // 독립 가능
    private static ChessBoard dtoChessBoard() throws SQLException {
        BoardDTO boardDTO = BoardDAO.selectAll();
        TurnDTO turnDTO = TurnDAO.selectLastTurn();
        ResultCounterDTO resultCounterDTO = ResultCounterDAO.selectAll();
        return new ChessBoard(boardDTO.getBoard(), turnDTO.getTurn(), resultCounterDTO.getResultCounter());
    }

    private static void daoAddHandler(ChessBoardDTO chessBoardDTO) throws SQLException {
        BoardDAO.add(chessBoardDTO.getBoardDTO());
        TurnDAO.add(chessBoardDTO.getTurnDTO());
        ResultCounterDAO.add(chessBoardDTO.getResultCounterDTO());
    }

    private static ChessBoardDTO initChessBoardDTO() {
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
        chessBoardDTO.setBoardDTO(new BoardDTO());
        chessBoardDTO.setTurnDTO(new TurnDTO());
        chessBoardDTO.setResultCounterDTO(new ResultCounterDTO());
        return chessBoardDTO;
    }

    private static ChessBoardDTO setChessBoardDTO(ChessBoard chessBoard) {
        BoardDTO boardDTO = new BoardDTO();
        TurnDTO turnDTO = new TurnDTO();
        ResultCounterDTO resultCounterDTO = new ResultCounterDTO();
        boardDTO.setBoard(chessBoard.getBoard());
        turnDTO.setTurn(chessBoard.getTurn());
        resultCounterDTO.setResultCounter(chessBoard.getResultCounter());
        ChessBoardDTO chessBoardDTO = new ChessBoardDTO();
        chessBoardDTO.setBoardDTO(boardDTO);
        chessBoardDTO.setTurnDTO(turnDTO);
        chessBoardDTO.setResultCounterDTO(resultCounterDTO);
        return chessBoardDTO;
    }
}
