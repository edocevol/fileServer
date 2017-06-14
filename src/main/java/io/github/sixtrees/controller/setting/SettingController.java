package io.github.sixtrees.controller.setting;

import io.github.sixtrees.tool.setting.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/6/14 0014.
 */
@Controller
@RequestMapping(value = "/")
public class SettingController {

    @RequestMapping("/getDefaultPath")
    @ResponseBody
    public String getDefaultPath() {
        String path = PropertiesUtil.getString("defaultPath");
        return path;
    }
}
