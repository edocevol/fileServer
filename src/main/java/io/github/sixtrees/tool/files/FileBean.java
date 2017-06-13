package io.github.sixtrees.tool.files;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
public class FileBean {


    private static final long serialVersionUID = -5452801884470115159L;

    private Integer fileId;//主键

    private String filePath;//文件保存路径

    private String fileName;//文件保存名称

    public FileBean() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "fileId=" + fileId +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
