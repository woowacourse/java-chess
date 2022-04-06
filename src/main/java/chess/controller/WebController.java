package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.ChessGame;
import chess.domain.Member;
import chess.domain.board.Board;
import chess.dto.GameResultDTO;
import chess.dto.RankDTO;
import chess.service.GameService;
import chess.service.MemberService;
import chess.util.JsonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private final GameService gameService;
    private final MemberService memberService;

    public WebController(GameService gameService, MemberService memberService) {
        this.gameService = gameService;
        this.memberService = memberService;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        staticFiles.location("/static");
        /** 웹 페이지 */
        get("/", this::renderHome);
        get("/play/:gameId", this::renderPlayGame);
        get("/result/:gameId", this::renderGameResult);
        get("/history/:memberId", this::renderMemberHistory);
        get("/member-management", this::renderMemberManagement);

        /** API */
        get("/score/:gameId", this::getGameScore);
        post("/member", this::addMember);
        post("/move/:gameId", this::movePiece);
        post("/terminate/:gameId", this::terminateGame);
        post("/game", this::createGame);
    }

    private String renderHome(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        List<ChessGame> games = gameService.findPlayingGames();
        List<Member> members = memberService.findAllMembers();
        model.put("games", games);
        model.put("members", members);
        return render(model, "index.html");
    }

    private String renderPlayGame(Request req, Response res) {
        Long gameId = Long.valueOf(req.params("gameId"));
        ChessGame chessGame = gameService.findByGameId(gameId);
        if (chessGame.isEnd()) {
            res.redirect(String.format("/result/%d", gameId));
        }
        Map<String, Object> model = new HashMap<>();
        Board board = chessGame.getBoard();
        List<RankDTO> ranks = new ArrayList<>();
        for (int i = 8; i > 0; i--) {
            ranks.add(RankDTO.toDTO(board.getRank(i - 1), i));
        }
        model.put("turn", chessGame.getTurn());
        model.put("ranks", ranks);
        model.put("gameId", gameId);
        return render(model, "play.html");
    }

    private String renderGameResult(Request req, Response res) {
        Long gameId = Long.valueOf(req.params("gameId"));
        ChessGame chessGame = gameService.findByGameId(gameId);
        Map<String, Object> model = new HashMap<>();
        model.put("winner", chessGame.getWinnerName());
        model.put("whiteScore", chessGame.getWhiteScore());
        model.put("blackScore", chessGame.getBlackScore());
        return render(model, "result.html");
    }

    private String renderMemberHistory(Request req, Response res) {
        Long memberId = Long.valueOf(req.params("memberId"));
        List<ChessGame> games = gameService.findHistorysByMemberId(memberId);
        List<GameResultDTO> history = games.stream()
                .map(game -> GameResultDTO.toResultDTO(game, memberId))
                .collect(Collectors.toList());
        Map<String, Object> model = new HashMap<>();
        model.put("history", history);
        return render(model, "history.html");
    }

    private String renderMemberManagement(Request req, Response res) {
        List<Member> members = memberService.findAllMembers();
        Map<String, Object> model = new HashMap<>();
        model.put("members", members);
        return render(model, "member-management.html");
    }

    private String getGameScore(Request req, Response res) {
        Long gameId = Long.valueOf(req.params("gameId"));
        ChessGame chessGame = gameService.findByGameId(gameId);
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("whiteScore", String.valueOf(chessGame.getWhiteScore()));
        jsonData.put("blackScore", String.valueOf(chessGame.getBlackScore()));
        res.header("Content-Type", "application/json");
        res.body(JsonUtil.serialize(jsonData));
        return JsonUtil.serialize(jsonData);
    }

    private String addMember(Request req, Response res) {
        String memberName = req.body();
        memberService.addMember(memberName);
        return "OK";
    }

    private String movePiece(Request req, Response res) {
        Long gameId = Long.valueOf(req.params("gameId"));
        String[] positions = req.body().split(",");
        if (gameService.move(gameId, positions[0], positions[1])) {
            return "OK";
        }
        res.status(400);
        return "FAIL";
    }

    private String terminateGame(Request req, Response res) {
        Long gameId = Long.valueOf(req.params("gameId"));
        gameService.terminate(gameId);
        return "OK";
    }

    private String createGame(Request req, Response res) {
        String body = req.body();
        String[] ids = body.split(",");
        Long whiteId = Long.valueOf(ids[0]);
        Long blackId = Long.valueOf(ids[1]);
        gameService.createGame(whiteId, blackId);
        return "OK";
    }
}
