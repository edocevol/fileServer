package io.github.sixtrees.tool.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2017/6/14 0014.
 * 该类主要是用来完成文件的复制和文件的移动，文件的删除等功能
 */
public class FileOperator {
    final static Logger logger = LoggerFactory.getLogger(FileOperator.class);

    public static Path copy(String src, String desc) {
        Path srcPath = Paths.get(src);
        Path descPath = Paths.get(desc);
        Path path;
        try {
            path = Files.copy(srcPath, descPath);
        } catch (IOException e) {
            logger.error("异常", e.getMessage());
            path = null;
        }
        return path;
    }

    /**
     * 移动文件
     *
     * @param src
     * @param desc
     * @return
     */
    public static Path move(String src, String desc) {
        Path srcPath = Paths.get(src);
        Path descPath = Paths.get(desc);
        Path path;
        try {
            path = Files.move(srcPath, descPath);
        } catch (IOException e) {
            logger.error("异常", e.getMessage());
            path = null;
        }
        return path;
    }


    /**
     * 创建文件夹
     *
     * @param desc
     * @return
     */
    public static Boolean mkdir(String desc) {
        Path descPath = Paths.get(desc);
        try {
            Files.createDirectories(descPath);
            return true;
        } catch (IOException e) {
            logger.error("异常", e.getMessage());
            return false;
        }
    }


    /**
     * 创建空白文件
     *
     * @param desc
     * @return
     */
    public static Boolean touch(String desc) {
        Path descPath = Paths.get(desc);
        try {
            Files.createFile(descPath);
            return true;
        } catch (IOException e) {
            logger.error("异常", e.getMessage());
            return false;
        }
    }

    /**
     * 删除文件
     *
     * @param src
     * @return
     */
    public static Boolean rmFile(String src) {
        Path srcPath = Paths.get(src);
        try {
            Files.deleteIfExists(srcPath);
            return true;
        } catch (IOException e) {
            logger.error("异常", e.getMessage());
            return false;
        }
    }

    /**
     * 删除目录
     *
     * @param src
     * @return
     */
    public static Boolean rmDirectory(String src) {
        Path srcPath = Paths.get(src);
        try {
            Files.deleteIfExists(srcPath);
            return true;
        } catch (IOException e) {
            logger.error("异常", e.getMessage());
        }
        return false;
    }

    public static Boolean rmRF(String path, String[] fileNames) {
        for (String name : fileNames) {
            Path onePath = Paths.get(path + fileNames);
            try {
                Files.deleteIfExists(onePath);
            } catch (IOException e) {
                logger.error("异常", e.getMessage());
            }
        }
        return true;
    }
}
