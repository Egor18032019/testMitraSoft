package service.user.micro.api.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.user.micro.api.utils.Const;

import java.util.HashMap;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping(value = Const.USER_URL)
public class UserController {

    @GetMapping("/news")
    public HashMap<String, HashMap> getNews() {
        HashMap<String, HashMap> map = new HashMap<>();
        HashMap<String, String> news = new HashMap<>();
        news.put("10.10.10", "Внимание жильцы. Сверка показаний счетчиков будет проходить с 00 по **.Просьба быть дома");
        news.put("11.11.21", "Уважаемы жители дома ХХ. Большая просьба быть дома и пускать газовую службу");

        map.put("message", news);

        return map;
    }
}
