package io.github.sixtrees.controller.task;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class TaskController {

    @RequestMapping(value = "GetTaskCount")
    @ResponseBody
    public String getTaskCount() {
        return new Gson().toJson(0);
    }
}
