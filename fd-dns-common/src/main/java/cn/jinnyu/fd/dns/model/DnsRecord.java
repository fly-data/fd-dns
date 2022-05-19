package cn.jinnyu.fd.dns.model;

import lombok.Data;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-15
 */
@Data
public class DnsRecord {

    public enum RecoedType {
        A, AAAA
    }

    /**
     * 操作类型
     */
    private DnsAction  action;
    /**
     * 记录类型
     */
    private RecoedType type;
    /**
     * 记录IP
     */
    private String     ip;
    /**
     * 记录域名
     */
    private String     host;
    /**
     * 记录存在时间
     */
    private long       ttl;

}
