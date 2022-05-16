package cn.jinnyu.fd.dns.api;

import cn.jinnyu.fd.dns.exception.DnsException;
import cn.jinnyu.fd.dns.model.DnsRecord;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-15
 */
public interface FdDns {

    /**
     * @return DNS实现类型
     */
    String name();

    /**
     * 操作DNS
     *
     * @param dnsRecord DNS记录
     * @return 操作是否成功
     * @throws DnsException 操作异常
     */
    boolean doAction(DnsRecord dnsRecord) throws DnsException;

}