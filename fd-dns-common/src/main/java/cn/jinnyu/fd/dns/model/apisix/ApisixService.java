package cn.jinnyu.fd.dns.model.apisix;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ApisixService extends ApisixBase {

    /**
     * 标签
     */
    private Map<String, String> labels;
    /**
     * 是否开启websocket
     */
    private boolean             enableWebSocket = false;
    /**
     * 匹配域名
     */
    private List<String>        hosts;
    /**
     * 插件列表
     */
    private List<ServicePlugin> plugins;
    private LocalDateTime       createTime;
    private LocalDateTime       updateTime;

    public static class ServicePlugin {
        private String name;
        private String conf;
    }

}
