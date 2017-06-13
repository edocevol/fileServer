package io.github.sixtrees.service.file;

import io.github.sixtrees.tool.setting.PropertiesUtil;

public class FileService{
    public String getDiskInfo() {
        String path = PropertiesUtil.getString("defaultPath");
        return path;
    }


    public String getDir(String path) {

        return "";
    }

}