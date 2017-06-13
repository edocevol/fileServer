package io.github.sixtrees.tool.setting;

import java.util.ResourceBundle;

/**
 * 从resources文件夹下读取配置文件
 */
public class PropertiesUtil {
    private static ResourceBundle resource;
    private static String configFile = "settings";
    private static PropertiesUtil dmConfig = null;
    static {
        resource = ResourceBundle.getBundle(configFile);
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static PropertiesUtil getInstance() {
        if (dmConfig == null) {
            dmConfig = new PropertiesUtil();
        }
        return dmConfig;
    }

    public PropertiesUtil() {
        resource = ResourceBundle.getBundle(configFile);
    }

    public static String getString(String itemIndex) {

        try {
            return resource.getString(itemIndex);
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(getString("defaultPath"));

    }

}
