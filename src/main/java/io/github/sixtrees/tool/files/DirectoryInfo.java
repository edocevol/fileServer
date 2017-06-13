package io.github.sixtrees.tool.files;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取文件夹的信息
 */
public class DirectoryInfo {

    public static String getFreeAndUsableSpace(String path) {
        Map<String,Object> map = new HashMap<>();
        map.put("path",path);
        File diskPartition = new File("/");
        long totalCapacity = diskPartition.getTotalSpace();
        long freePartitionSpace = diskPartition.getFreeSpace();
        long usablePatitionSpace = diskPartition.getUsableSpace();
        List<String> list = new ArrayList<>();
        list.add(DiskVolumeTranslate.getPrintSize(totalCapacity)+"");//总文件大小
        list.add(DiskVolumeTranslate.getPrintSize(usablePatitionSpace)+"");//已使用的文件大小
        list.add(DiskVolumeTranslate.getPrintSize(freePartitionSpace)+"");//空余文件大小
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(0);
        String percent = numberFormat.format((float) freePartitionSpace / (float) totalCapacity * 100);
        list.add(percent+"%");//文件已使用占比

        map.put("size",list);
        String json = new Gson().toJson(map);
        return json;


    }


    public static String getDir(String path) {
        File dir = new File(path);
        try {
            Map<String, Object> map = new HashMap<>();
            List<String> flist = new ArrayList<>();
            List<String> dlist = new ArrayList<>();
            File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组

            if (files != null) {
                for (int i = 0; i < files.length; i++) {

                    StringBuilder sb = new StringBuilder();
                    String fileName = files[i].getName();
                    Path cpath = Paths.get(files[i].getAbsolutePath());
                    sb.append(fileName);
                    sb.append(";");
                    sb.append(Files.size(cpath));
                    sb.append(";");
                    sb.append(Files.getLastModifiedTime(cpath).toMillis()).append(";");
                    sb.append(PosixFilePermissions.toString(Files.getPosixFilePermissions(cpath)));
                    sb.append(";");
                    sb.append(Files.getOwner(cpath));

                    if (files[i].isFile()) {
                        flist.add(sb.toString());
                    } else {
                        dlist.add(sb.toString());
                    }
                }

            }
            map.put("FILES", flist);
            map.put("PATH", path);
            map.put("DIR", dlist);
            return new Gson().toJson(map);
        }catch (Exception ex) {
            return new Gson().toJson("");
        }
    }


    public static void main(String[] args) {
        File diskPartition = new File("/");

        long totalCapacity = diskPartition.getTotalSpace();

        long freePartitionSpace = diskPartition.getFreeSpace();
        long usablePatitionSpace = diskPartition.getUsableSpace();

    }
}
