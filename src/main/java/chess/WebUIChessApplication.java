package chess;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import chess.command.MoveCommand;
import chess.location.Location;
import chess.progress.Progress;
import converter.ChessGameConverter;
import dao.BoardDAO;
import dao.ChessGameDAO;
import dao.ChessGamesDAO;
import dao.PieceDAO;
import dto.*;
import chess.game.ChessGame;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import vo.PieceVO;

public class WebUIChessApplication {
	private static final Gson GSON = new Gson();
	private static final int GAME_ID = 1;

	public static void main(String[] args) {
		staticFiles.location("templates");

		ChessGame chessGame = new ChessGame();

		get("/main", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "start.html");
		});

		get("/start", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			return render(model, "index.html");
		});

		get("/start/boards", (req, res) -> {
			ChessGamesDAO chessGamesDAO = new ChessGamesDAO();
			ArrayList<ChessGameDTO> all = chessGamesDAO.findAll();
			ChessGamesDTO chessGamesDTO = new ChessGamesDTO(all);
			return GSON.toJson(chessGamesDTO);
		});

		get("/start/board", (req, res) -> {
			int boardID = Integer.parseInt(req.queryParams("id"));

			PieceDAO pieceDAO = new PieceDAO();
			ArrayList<PieceVO> pieceVOs = pieceDAO.findAll(boardID);

			ChessGameDAO chessGameDAO = new ChessGameDAO();
			ChessGameDTO chessGameDTO = chessGameDAO.findChessGameBy(boardID);

			if (pieceVOs == null) {
				BoardDTO boardDTO = new BoardDTO(new ChessGame().getChessBoard());
				return GSON.toJson(boardDTO);
			}

			BoardDTO boardDTO = new BoardDTO(ChessGameConverter.convert(pieceVOs, chessGameDTO).getChessBoard());
			return GSON.toJson(boardDTO);
		});

		post("/start/move", (req, res) -> {
			LocationDTO nowDTO = new LocationDTO(req.queryParams("now"));
			LocationDTO destinationDTO = new LocationDTO(req.queryParams("des"));

			Location nowLocation = new Location(nowDTO.getRow(), nowDTO.getCol());
			Location destinationLocation =
					new Location(destinationDTO.getRow(), destinationDTO.getCol());

			MoveCommand move = MoveCommand.of(nowLocation, destinationLocation, chessGame);

			Progress progress = chessGame.doOneCommand(move);

			changeTurnIfMoved(chessGame, progress);

			ChessMoveDTO chessMoveDTO = new ChessMoveDTO(
					new ChessGameScoresDTO(chessGame.calculateScores())
					, progress
					, chessGame.getTurn());

			return GSON.toJson(chessMoveDTO);
		});

		get("/start/winner", (req, res) -> {
			ChessResultDTO chessResultDTO = new ChessResultDTO(chessGame.findWinner());
			return GSON.toJson(chessResultDTO);
		});

		get("/end", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.addBoard(chessGame.getChessBoard(), GAME_ID);
			return render(model, "start.html");
		});
	}

	private static void changeTurnIfMoved(ChessGame chessGame, Progress progress) {
		if (Progress.CONTINUE == progress) {
			chessGame.changeTurn();
		}
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}