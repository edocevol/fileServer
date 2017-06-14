package io.github.sixtrees.tool.files;

import io.github.sixtrees.tool.setting.PropertiesUtil;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.File;


public class DirectoryInfoTest {


    final org.slf4j.Logger logger = LoggerFactory.getLogger(DirectoryInfoTest.class);

    @Test
    public void getFreeAndUsableSpace() throws Exception {

        String path = PropertiesUtil.getString("defaultPath");
        String json = DirectoryInfo.getFreeAndUsableSpace(path);
        logger.info(json);
        Assert.assertNotNull(json);
    }

    @Test
    public void getDir() throws Exception {
        String path = PropertiesUtil.getString("defaultPath");
        String json = DirectoryInfo.getDir(path);
        logger.info(json);
        Assert.assertNotNull(json);
    }

    @Test
    public void getFileNameAndParent() {
        String path = "D://bootstrap-3.3.7-dist.zip";
        File file = new File(path);
        System.out.println(file.getAbsoluteFile().getParent());
        System.out.println(file.getName());
    }

    @Test
    public void getDirSize() throws Exception {

        String path = PropertiesUtil.getString("d:/");
        String json = DirectoryInfo.getFreeAndUsableSpace(path);
        logger.info(json);
        Assert.assertNotNull(json);
    }
}