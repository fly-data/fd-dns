package cn.jinnyu.fd.dns.coredns;

import cn.jinnyu.fd.dns.api.FdDns;
import cn.jinnyu.fd.dns.coredns.model.CoreDnsConfig;

/**
 * @author jinyu@jinnyu.cn
 * @date 2022-05-16
 */
public abstract class AbstractDnsImpl implements FdDns {

    protected CoreDnsConfig config;

    public AbstractDnsImpl(CoreDnsConfig config) {
        this.config = config;
    }

}
