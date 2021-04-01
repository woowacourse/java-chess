package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.ScoreDto;
import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.manager.ChessGame;
import chess.domain.piece.Piece;
import chess.view.web.OutputView;
import chess.view.web.PieceSymbolMapper;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebController {

    private ChessGame chessGame;

    public void mapping(){
        staticFiles.location("/public");
        serviceRoot();
        show();
        move();
    }

    private void serviceRoot(){
        get("/", (req, res) -> {
            chessGame = new ChessGame();
            chessGame.initNewGame();
            return printGame();
        });
    }

    private void show(){
        post("/show", (req, res) -> {
            JSONObject jsonData = new JSONObject(req.body());
            Position source = new Position(jsonData.getString("position"));
            try{
                chessGame.validateTurn(source);
                return reachablePositions(source);
            } catch (Exception e){
                return Collections.EMPTY_LIST;
            }
        });
    }

    private void move(){
        post("/move", (req, res) -> {
            Map<String, String> reqPosition = parseSourceAndTarget(req.body());

            Position source = new Position(reqPosition.get("source"));
            Position target = new Position(reqPosition.get("target"));

            chessGame.validateTurn(source);
            chessGame.move(source, target);
            chessGame.changeTurn();

            return printGame();
        });
    }

    private String printGame() {
        ScoreDto whiteScoreDto = new ScoreDto();
        whiteScoreDto.setScore(chessGame.getWhiteScore());

        ScoreDto blackScoreDto =  new ScoreDto();
        blackScoreDto.setScore(chessGame.getBlackScore());

        BoardDto boardDto = new BoardDto();
        boardDto.setBoard(boardMapping(chessGame.board()));
        return OutputView.printGame(boardDto, whiteScoreDto, blackScoreDto);
    }

    // XXX :: Service로 분리하기
    private Map<String, String> parseSourceAndTarget(String line){
        Map<String, String> infoTable = new HashMap<>();
        String[] positionInfos = line.split("&");
        for(String positionInfo : positionInfos){
            String[] info = positionInfo.split("=");
            infoTable.put(info[0], info[1]);
        }
        return infoTable;
    }

    // XXX :: Service로 분리하기
    private List<String> reachablePositions(Position source){
        return chessGame.reachablePositions(source).stream()
                .map(position -> parsePositionAsString(position))
                .collect(Collectors.toList());
    }

    // XXX :: Service로 분리하기
    private String parsePositionAsString(Position position){
        return position.vertical().name() + position.horizontal().getIndex();
    }

    // XXX :: Service로 분리하기
    private String[][] boardMapping(final Board board){
        String[][] uniCodeBoard = new String[8][8];
        for(Vertical v : Vertical.values()){
            for(Horizontal h : Horizontal.values()){
                Piece piece = board.of(new Position(v,h));
                String uniCode = PieceSymbolMapper.parse(piece.owner(), piece.symbol());
                uniCodeBoard[v.getIndex()-1][h.getIndex()-1] = uniCode;
            }
        }
        return uniCodeBoard;
    }
}
