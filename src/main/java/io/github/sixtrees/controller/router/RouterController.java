package io.github.sixtrees.controller.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/14 0014.
 */
@Controller
@RequestMapping(value = "/")
public class RouterController {

    @RequestMapping(value = "/files")
    public String files() {
        return "files";
    }
}
