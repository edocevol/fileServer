package io.github.sixtrees.tool.files;

import com.google.gson.Gson;
import io.github.sixtrees.tool.setting.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    final static Logger logger = LoggerFactory.getLogger(DirectoryInfo.class);

    public static String getFreeAndUsableSpace(String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("path", path);
        File diskPartition = new File(path);
        long totalCapacity = diskPartition.getTotalSpace();
        long usablePatitionSpace = diskPartition.getUsableSpace();
        long freePartitionSpace = diskPartition.getFreeSpace();
        List<String> list = new ArrayList<>();
        list.add(DiskVolumeTranslate.getPrintSize(totalCapacity) + "");//总文件大小
        list.add(DiskVolumeTranslate.getPrintSize(usablePatitionSpace) + "");//已使用的文件大小
        list.add(DiskVolumeTranslate.getPrintSize(freePartitionSpace) + "");//空余文件大小
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(0);
        String percent = numberFormat.format((float) freePartitionSpace / (float) totalCapacity * 100);
        list.add(percent + "%");//文件已使用占比

        map.put("volume", list);
        String json = new Gson().toJson(map);
        return json;


    }


    public static String getDir(String path) {
        if (path == "" || path == null) {
            path = PropertiesUtil.getString("defaultPath");
        }
        if(!path.startsWith(PropertiesUtil.getString("defaultPath"))) {
            path = PropertiesUtil.getString("defaultPath");
        }
        File dir = new File(path);
        Map<String, Object> map = new HashMap<>();
        long totalSize = 0l;
        totalSize = dir.getUsableSpace();//获取当前文件夹下的所有文件的大小
        map.put("totalSize", totalSize);
        List<String> flist = new ArrayList<>();
        List<String> dlist = new ArrayList<>();
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                try {
                    StringBuilder sb = new StringBuilder();
                    String fileName = files[i].getName();
                    Path cpath = Paths.get(files[i].getAbsolutePath());
                    sb.append(fileName);
                    sb.append(";");
                    sb.append(Files.size(cpath));
                    sb.append(";");
                    sb.append(Files.getLastModifiedTime(cpath).toMillis()).append(";");
                    String permissions = "";
                    //windows下不支持PosixFilePermissions
                    try {
                        permissions = PosixFilePermissions.toString(Files.getPosixFilePermissions(cpath));
                    } catch (Exception ex) {
                        permissions = "-------";
                    }
                    sb.append(permissions);
                    sb.append(";");
                    sb.append(Files.getOwner(cpath));

                    if (files[i].isFile()) {
                        flist.add(sb.toString());
                    } else {
                        dlist.add(sb.toString());
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }
            }
        }
        map.put("FILES", flist);
        map.put("PATH", path);
        map.put("DIR", dlist);
        return new Gson().toJson(map);
    }


    public static void main(String[] args) {
    }
}
