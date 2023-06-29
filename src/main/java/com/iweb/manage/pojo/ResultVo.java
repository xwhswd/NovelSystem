package com.iweb.manage.pojo;

import lombok.Data;

/**用于后端给前端返回的结果集对象
 * @author xwh
 * @version 1.0
 * 2023/6/25
 */
@Data
public class ResultVo<T> {
    /**
     * 操作是否成功
     */
    private boolean isOk;
    /**
     * 给页面返回的消息
     */
    private String mess;
    /**
     * 给页面所返回的数据
     */
    private T t;
}
