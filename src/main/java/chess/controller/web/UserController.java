package chess.controller.web;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.web.utils.JsonConverter;
import chess.dto.user.UserRequestDto;
import chess.dto.user.UserResponseDto;
import chess.service.UserService;
import java.util.Collections;

public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        addResponsePath();
        this.userService = userService;
    }

    private void addResponsePath() {
        get("api/user/:name", (req, res) -> {
            final String name = req.params("name");
            res.type("application/json");
            return UserResponseDto.from(userService.findByName(name));
        }, JsonConverter::toJson);

        post("api/user", (req, res) -> {
            final UserRequestDto userRequestDto =
                JsonConverter.fromJson(req.body(), UserRequestDto.class);
            userService.add(userRequestDto);
            res.type("application/json");
            return Collections.emptyMap();
        }, JsonConverter::toJson);
    }
}
