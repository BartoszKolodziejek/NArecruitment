package pl.bartosz.kolodziejek.postReceiver;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import pl.bartosz.kolodziejek.postReceiver.configuration.Configuration;
import pl.bartosz.kolodziejek.postReceiver.services.PostsService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main {


    public static void main(String[] args) throws IOException {
        var config = Configuration.getInstance();
        var postService = new PostsService((String) config.get("service.resource.endpoint"));
        Gson gson = new Gson();
        postService.getPosts().parallelStream().forEach(post -> {
            try {
                FileUtils.write(new File(
                        config.get("output.path") + "/" + post.getTitle()),
                        gson.toJson(post).replaceAll(",", ",\n")
                                         .replaceAll("\\{", "\\{\n")
                                         .replaceAll("}", "\n}"),
                        Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
