package io.github.sixtrees.controller.file;

import io.github.sixtrees.tool.files.DirectoryInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class FileController {

    @RequestMapping(value = "/GetDiskInfo")
    @ResponseBody
    public String getDiskInfo() {
      return  DirectoryInfo.getFreeAndUsableSpace("/");
    }

    @RequestMapping(value = "/getDir")
    @ResponseBody
    public String getDir() {
        return  DirectoryInfo.getDir("/");
    }

}
