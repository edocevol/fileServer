package io.github.sixtrees.controller;

import io.github.sixtrees.tool.files.FileBean;
import io.github.sixtrees.tool.files.ZipUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@Controller
@RequestMapping(value = "/")
public class DownLoadController {

    /**
     * 打包压缩下载文件
     */
    @RequestMapping(value = "/downLoadZipFile")
    public void downLoadZipFile(List<FileBean> fileList, HttpServletResponse response) throws IOException {
        String zipName = "myfile.zip";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for (Iterator<FileBean> it = fileList.iterator(); it.hasNext(); ) {
                FileBean file = it.next();
                ZipUtils.zipFile(file.getFilePath() + file.getFileName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }


    /**
     * 打包压缩下载文件
     */
    @RequestMapping(value = "/download")
    public void downLoad(HttpServletResponse response, String path) throws IOException {
        path = new String(path.getBytes("ISO-8859-1"),"utf-8");
        File file = new File(path);// path是根据日志路径和文件名拼接出来的
        String filename = file.getName();// 获取日志文件名称
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        response.reset();
        // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8")));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        os.write(buffer);// 输出文件
        os.flush();
        os.close();
    }
}
