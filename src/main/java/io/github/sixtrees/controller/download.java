package io.github.sixtrees.controller;

import io.github.sixtrees.tool.files.FileBean;
import io.github.sixtrees.tool.files.ZipUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@Controller
@RequestMapping(value = "/downZipFile")
public class download {

    /**
     * 打包压缩下载文件
     */
    @RequestMapping(value = "/downLoadZipFile")
    public void downLoadZipFile(List<FileBean> fileList, HttpServletResponse response) throws IOException {
        String zipName = "myfile.zip";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for(Iterator<FileBean> it = fileList.iterator(); it.hasNext();){
                FileBean file = it.next();
                ZipUtils.zipFile(file.getFilePath()+file.getFileName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
}
