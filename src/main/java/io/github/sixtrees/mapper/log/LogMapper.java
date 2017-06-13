package io.github.sixtrees.mapper.log;

import io.github.sixtrees.po.log.Log;
import org.springframework.stereotype.Component;

/**
 * @author github.com/sxitrees
 * @description fs_log表的mapper文件
 * @2017-06-12
 */
@Component("logMapper")
public interface LogMapper {

    //添加
    int addLog(Log log);

    //删除
    int deleteLog(Integer id);

}