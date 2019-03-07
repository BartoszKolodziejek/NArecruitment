package pl.bartosz.kolodziejek.postReceiver.services;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import pl.bartosz.kolodziejek.postReceiver.dto.Post;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PostsService {

    private final String url;
    private final Gson gson = new Gson();

    public List<Post> getPosts() throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest getPosts = requestFactory.buildGetRequest(new GenericUrl(url));
        var outputStream = new ByteArrayOutputStream();
        System.out.println("I am downloading posts");
        getPosts.execute().download(outputStream);
        var resultJson = outputStream.toString();
        Type postTypes = new TypeToken<ArrayList<Post>>(){}.getType();
        return gson.fromJson(resultJson, postTypes);
    }
}
