package net.thumbtack.school.boxes.library.server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.school.library.Server.Server;
import net.thumbtack.school.library.dto.request.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class testServer {
    @Test
    public void serverTest() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();

        server.startServer("./test.json");

        server.registerUser(gson.toJson(new RegisterUserDtoRequest(
                "Jude", "Duarte", "jude@gmail.com", "fairy")));
        String token = server.login(gson.toJson(new LoginUserDtoRequest("jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        String book1 = server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("Js")),
                jsonObjectToken.get("token").getAsString())));
        server.getBooksBySections(gson.toJson(new GetBooksBySectionsDtoRequest(new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        JsonObject jsonObject = gson.fromJson(book1, JsonObject.class);
        server.orderBook(gson.toJson(new OrderBookDtoRequest(jsonObject.get("idBook").getAsString(), "5", jsonObjectToken.get("token").getAsString())));

        server.orderBook(gson.toJson(new OrderBookDtoRequest(jsonObject.get("idBook").getAsString(), "2", jsonObjectToken.get("token").getAsString())));


        server.stopServer("./test.json");
    }
}