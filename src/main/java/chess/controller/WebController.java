package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.Member;
import chess.domain.Participant;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.state.WhiteTurn;
import chess.dto.GameResultDTO;
import chess.dto.RankDTO;
import chess.repository.GameRepository;
import chess.repository.MemberRepository;
import chess.util.JsonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private final GameRepository gameRepository;
    private final MemberRepository memberRepository;

    public WebController(GameRepository gameRepository, MemberRepository memberRepository) {
        this.gameRepository = gameRepository;
        this.memberRepository = memberRepository;
    }

    public void run() {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<ChessGame> games = gameRepository.findAll();
            List<Member> members = memberRepository.findAll();
            model.put("games", games);
            model.put("members", members);
            return render(model, "index.html");
        });

        get("/play/:gameId", (req, res) -> {
            Long gameId = Long.valueOf(req.params("gameId"));
            ChessGame chessGame = gameRepository.findById(gameId).get();
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
        });

        get("/result/:gameId", (req, res) -> {
            Long gameId = Long.valueOf(req.params("gameId"));
            ChessGame chessGame = gameRepository.findById(gameId).get();
            Map<String, Object> model = new HashMap<>();
            model.put("winner", chessGame.getWinnerName());
            model.put("whiteScore", chessGame.getWhiteScore());
            model.put("blackScore", chessGame.getBlackScore());
            return render(model, "result.html");
        });

        get("/history/:memberId", (req, res) -> {
            Long memberId = Long.valueOf(req.params("memberId"));
            List<ChessGame> games = gameRepository.findHistorysByMemberId(memberId);
            List<GameResultDTO> history = games.stream()
                    .map(game -> GameResultDTO.toResultDTO(game, memberId))
                    .collect(Collectors.toList());
            Map<String, Object> model = new HashMap<>();
            model.put("history", history);
            return render(model, "history.html");
        });

        get("/member-management", (req, res) -> {
            List<Member> members = memberRepository.findAll();
            Map<String, Object> model = new HashMap<>();
            model.put("members", members);
            return render(model, "member-management.html");
        });

        /**
         * 비동기 요청
         */
        get("/score/:gameId", (req, res) -> {
            Long gameId = Long.valueOf(req.params("gameId"));
            ChessGame chessGame = gameRepository.findById(gameId).get();
            Map<String, String> jsonData = new HashMap<>();
            jsonData.put("whiteScore", String.valueOf(chessGame.getWhiteScore()));
            jsonData.put("blackScore", String.valueOf(chessGame.getBlackScore()));
            res.header("Content-Type", "application/json");
            res.body(JsonUtil.serialize(jsonData));
            return JsonUtil.serialize(jsonData);
        });

        post("/member", (req, res) -> {
            String memberName = req.body();
            memberRepository.save(new Member(memberName));
            return "";
        });

        post("/member", (req, res) -> {
            String name = req.queryParams("name");
            memberRepository.save(new Member(name));
            return "";
        });

        post("/command/:gameId", (req, res) -> {
            Long gameId = Long.valueOf(req.params("gameId"));
            ChessGame chessGame = gameRepository.findById(gameId).get();
            String command = req.body();
            List<String> input = List.of(command.split(" "));
            executeCommand(chessGame, input);
            return "";
        });

        post("/game", (req, res) -> {
            String body = req.body();
            String[] ids = body.split(",");
            Long whiteId = Long.valueOf(ids[0]);
            Long blackId = Long.valueOf(ids[1]);
            Member white = memberRepository.findById(whiteId).orElseThrow(() -> new RuntimeException("찾는 멤버가 없음!"));
            Member black = memberRepository.findById(blackId).orElseThrow(() -> new RuntimeException("찾는 멤버가 없음!"));
            gameRepository.save(new ChessGame(new WhiteTurn(new Board(BoardInitializer.initBoard())), new Participant(white, black)));
            return "";
        });
    }

    private void executeCommand(ChessGame chessGame, List<String> input) {
        if (Command.inGameCommand(input.get(0)) == Command.END) {
            chessGame.terminate();
        }
        if (Command.inGameCommand(input.get(0)) == Command.MOVE && input.size() == 3) {
            chessGame.move(input.get(1), input.get(2));
        }
        gameRepository.update(chessGame);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
