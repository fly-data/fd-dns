package cn.jinnyu.fd.dns.model.apisix;

import lombok.Data;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-15
 */
@Data
public class ApisixBase {

    private String id;
    /**
     * 服务名
     */
    private String name;
    /**
     * 服务描述
     */
    private String description;

}
