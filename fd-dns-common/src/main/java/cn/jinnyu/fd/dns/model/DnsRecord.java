package cn.jinnyu.fd.dns.model;

import lombok.Data;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-15
 */
@Data
public class DnsRecord {

    public enum Action {
        ADD, DELETE, MODIFY
    }

    public enum RecoedType {
        A, AAAA, PTR
    }

    /**
     * 操作类型
     */
    private Action     action;
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
