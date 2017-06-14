package io.github.sixtrees.controller.file;

import com.google.gson.Gson;
import io.github.sixtrees.tool.files.DirectoryInfo;
import io.github.sixtrees.tool.files.FileOperator;
import io.github.sixtrees.tool.setting.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class FileController {

    @RequestMapping(value = "/GetDiskInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDiskInfo() {
        return DirectoryInfo.getFreeAndUsableSpace(PropertiesUtil.getString("defaultPath"));
    }

    @RequestMapping(value = "/getDir", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDir(String path) {
        return DirectoryInfo.getDir(path);
    }


    @RequestMapping(value = "CopyFile")
    public void copyFile(String sfile, String dfile) {
        FileOperator.copy(sfile, dfile);
    }

    @RequestMapping(value = "MoveFile")
    public void moveFile(String sfile, String dfile) {
        FileOperator.move(sfile, dfile);
    }

    @RequestMapping(value = "CreateDir")
    public void createDir(String path) {
        FileOperator.mkdir(path);
    }

    @ResponseBody
    @RequestMapping(value = "CreateFile", produces = {"application/json;charset=UTF-8"})
    public String createFile(String path) {
        Map<String, Object> map = new HashMap<>();

        boolean status = FileOperator.touch(path);
        if (status) {
            map.put("msg", "文件创建成功");
            map.put("status", 1);
        } else {
            map.put("msg", "文件创建失败");
            map.put("status", 2);
        }
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "DeleteFile")
    public void deleteFile(String path) {
        FileOperator.rmFile(path);
    }

    @RequestMapping(value = "DeleteDir")
    public void deleteDir(String path) {
        FileOperator.rmFile(path);
    }

    @RequestMapping(value = "SetBatchData")
    public void setBatchData(String path, String type, String[] data) {
        FileOperator.rmRF(path, data);
    }
}
