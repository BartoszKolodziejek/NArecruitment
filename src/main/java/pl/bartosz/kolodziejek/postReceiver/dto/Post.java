package pl.bartosz.kolodziejek.postReceiver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Post {

    @Getter private final Long userId;
    @Getter private final Long id;
    @Getter private final String title;
    @Getter private final String body;

}
