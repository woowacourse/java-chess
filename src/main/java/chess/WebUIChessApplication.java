package chess;

import static spark.Spark.*;

import java.sql.SQLException;

import chess.controller.WebChessController;
import chess.dao.PlayerDao;
import chess.service.ChessService;
import chess.service.ChessServiceImpl;
import chess.util.RoutesConfig;

public class WebUIChessApplication {
    public static void main(String[] args) {
        RoutesConfig.configure();
        addTemporaryPlayers();
        before(RoutesConfig::setJsonContentType);
        ChessService service = new ChessServiceImpl();
        WebChessController controller = new WebChessController(service);
    }

    private static void addTemporaryPlayers() {
        // 플레이어 회원가입 / 로그인 구현 이전 foreign key 오류를 내지 않기 위해 임시로 DB에 플레이어 추가
        try {
            new PlayerDao().addInitialPlayers();
        } catch (SQLException ignored) {
        }
    }
}
