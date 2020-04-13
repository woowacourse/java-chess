package chess.controller;

import chess.dao.*;
import chess.domain.ChessRunner;
import chess.dto.MoveResultDTO;
import chess.dto.TeamDTO;
import chess.dto.TileDTO;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {
    private static final String MESSAGE_STYLE_BLACK = "color:black;";
    private static final String MESSAGE_STYLE_RED = "color:red;";
    private static final String DELIMITER = ": ";
    private static final String NEW_LINE = "\n";
    private static final String ARROW = " -> ";
    private static final String WINNER = " 가 이겼습니다.";

    private ChessRunner chessRunner;
    private ChessBoard chessBoard;
    private CurrentTeam currentTeam;
    private PieceOnBoards originalPieces;

    private ChessService chessService;

    public void playChess() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "index.html");
        });

        post("/name", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return render(model, "name.html");
        });

        post("/load", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Player> players = this.chessService.players();

            model.put("gameData", players);

            return render(model, "table.html");
        });

        post("/newGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String whitePlayer = req.queryParams("white-player");
            String blackPlayer = req.queryParams("black-player");

            Player player = new Player(whitePlayer, blackPlayer);
            newGame(player);
            List<TileDTO> tileDtos = getTiles();
            TeamDTO teamDto = getCurrentTeam();

            model.put("tiles", tileDtos);
            model.put("currentTeam", teamDto);
            model.put("player", player);

            return render(model, "game.html");
        });
//
//        post("/continueGame", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            int chessBoardId = Integer.parseInt(req.queryParams("chess-board-id"));
//
//            webChessController.continueGame(chessBoardId);
//            List<TileDTO> tileDtos = webChessController.getTiles();
//            TeamDTO teamDto = webChessController.getCurrentTeam();
//            Player player = webChessController.getPlayer();
//
//            model.put("tiles", tileDtos);
//            model.put("currentTeam", teamDto);
//            model.put("player", player);
//
//            return render(model, "game.html");
//        });
//
//        post("/move", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            String source = req.queryParams("source");
//            String target = req.queryParams("target");
//
//            MoveResultDTO moveResultDto = webChessController.move(source, target);
//            List<TileDTO> tileDtos = webChessController.getTiles();
//            TeamDTO teamDto = webChessController.getCurrentTeam();
//            Player player = webChessController.getPlayer();
//
//            model.put("tiles", tileDtos);
//            model.put("currentTeam", teamDto);
//            model.put("message", moveResultDto.getMessage());
//            model.put("style", moveResultDto.getStyle());
//            model.put("player", player);
//
//            if (webChessController.isEndGame()) {
//                webChessController.deleteChessGame();
//                return render(model, "end.html");
//            }
//            return render(model, "game.html");
//        });
//
//        post("/status", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            List<TileDTO> tileDtos = webChessController.getTiles();
//            TeamDTO teamDto = webChessController.getCurrentTeam();
//            String message = webChessController.getScores();
//            Player player = webChessController.getPlayer();
//
//            model.put("tiles", tileDtos);
//            model.put("currentTeam", teamDto);
//            model.put("message", message);
//            model.put("player", player);
//
//            return render(model, "game.html");
//        });
//
//        post("/end", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            webChessController.deleteChessGame();
//            String message = webChessController.getScores();
//
//            model.put("message", message);
//
//            return render(model, "end.html");
//        });
    }

    public void newGame(Player player) throws Exception {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
        PieceDAO pieceDAO = new PieceDAO();
        PlayerDAO playerDAO = new PlayerDAO();

        this.chessRunner = new ChessRunner();

        chessBoardDAO.addChessBoard();
        this.chessBoard = chessBoardDAO.findRecentChessBoard();

        this.currentTeam = new CurrentTeam(this.chessRunner.getCurrentTeam());
        currentTeamDAO.addCurrentTeam(this.chessBoard, this.currentTeam);

        List<TileDTO> tileDtos = this.chessRunner.pieceTileDtos();
        pieceDAO.addPiece(this.chessBoard, tileDtos);
        updateOriginalPieces(pieceDAO);

        playerDAO.addPlayer(this.chessBoard, player);
    }

//    public List<Player> players() throws Exception {
//        PlayerDAO playerDAO = new PlayerDAO();
//
//        return Collections.unmodifiableList(playerDAO.findAllPlayer());
//    }

    public Player getPlayer() throws Exception {
        PlayerDAO playerDAO = new PlayerDAO();

        return playerDAO.findPlayer(this.chessBoard);
    }

    public void continueGame(int chessBoardId) throws Exception {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
        PieceDAO pieceDAO = new PieceDAO();

        this.chessBoard = chessBoardDAO.findById(chessBoardId);

        this.currentTeam = currentTeamDAO.findCurrentTeam(this.chessBoard);

        updateOriginalPieces(pieceDAO);
        Map<String, String> pieceOnBoards = this.originalPieces.getPieceOnBoards().stream()
                .collect(Collectors.toMap(entry -> entry.getPosition(),
                        entry -> entry.getPieceImageUrl(),
                        (e1, e2) -> e1, HashMap::new));
        this.chessRunner = new ChessRunner(pieceOnBoards, this.currentTeam.getCurrentTeam());
    }

    private void updateOriginalPieces(PieceDAO pieceDAO) throws Exception {
        List<PieceOnBoard> pieces = pieceDAO.findPiece(this.chessBoard);
        this.originalPieces = PieceOnBoards.create(pieces);
    }

    public MoveResultDTO move(final String source, final String target) throws Exception {
        try {
            this.chessRunner.update(source, target);
            updateChessBoard(source, target);
            String moveResult = moveResult(this.chessRunner, source, target);
            return new MoveResultDTO(moveResult, MESSAGE_STYLE_BLACK);
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            return new MoveResultDTO(e.getMessage(), MESSAGE_STYLE_RED);
        }
    }

    private void updateChessBoard(final String source, final String target) throws Exception {
        PieceOnBoard deletedPiece = null;
        PieceDAO pieceDAO = new PieceDAO();
        CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();

        Optional<PieceOnBoard> targetPiece = this.originalPieces.find(target);
        if (targetPiece.isPresent()) {
            deletedPiece = targetPiece.get();
            pieceDAO.deletePiece(deletedPiece);
        }
        Optional<PieceOnBoard> sourcePiece = this.originalPieces.find(source);
        pieceDAO.updatePiece(sourcePiece.get(), target);
        this.currentTeam = new CurrentTeam(this.chessRunner.getCurrentTeam());
        currentTeamDAO.updateCurrentTeam(this.chessBoard, this.currentTeam);
        updateOriginalPieces(pieceDAO);
    }

    private String moveResult(final ChessRunner chessRunner, final String source, final String target) {
        if (!this.isEndGame()) {
            return source + ARROW + target;
        }
        return chessRunner.getWinner() + WINNER;
    }

    public boolean isEndGame() {
        return this.chessRunner.isEndChess();
    }

    public void deleteChessGame() throws Exception {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        PieceDAO pieceDAO = new PieceDAO();
        CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
        PlayerDAO playerDAO = new PlayerDAO();

        currentTeamDAO.deleteCurrentTeam(this.chessBoard);
        for (PieceOnBoard pieceOnBoard : this.originalPieces.getPieceOnBoards()) {
            pieceDAO.deletePiece(pieceOnBoard);
        }
        playerDAO.deletePlayer(this.chessBoard);
        chessBoardDAO.deleteChessBoard(this.chessBoard);
    }

    public TeamDTO getCurrentTeam() {
        return new TeamDTO(this.chessRunner.getCurrentTeam());
    }

    public List<TileDTO> getTiles() {
        return this.chessRunner.entireTileDtos();
    }

    public String getScores() {
        return this.chessRunner.calculateScores().stream()
                .map(dto -> dto.getTeam() + DELIMITER + dto.getBoardScore())
                .collect(Collectors.joining(NEW_LINE));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
